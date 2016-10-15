package com.nickrepetti.ahmonitor.util;

import com.nickrepetti.ahmonitor.dao.DAOFactory;
import com.nickrepetti.ahmonitor.dao.AuctionItemDAO;
import com.nickrepetti.ahmonitor.model.AuctionFileResponse;
import com.nickrepetti.ahmonitor.model.AuctionItem;
import com.nickrepetti.ahmonitor.model.AuctionResponse;
import com.nickrepetti.ahmonitor.model.AuctionFileData;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

public class Monitor implements Runnable {

	private String endpoint;
	private String dbUrl;
	private String dbUserName;
	private String dbPassword;

	public Monitor(String endpoint, String dbUrl, String dbUserName, String dbPassword) {
		this.endpoint = endpoint;
		this.dbUrl = dbUrl;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
	}
	
	@Override
	public void run() {
		System.out.println("--- Querying auction house ---");
		
		InputStream inputStream = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(endpoint);
		
		try {
			HttpResponse response = client.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				inputStream = entity.getContent();
				String responseJson = IOUtils.toString(inputStream);
				
				Gson gson = new Gson();
				
				AuctionResponse auctionResponse = gson.fromJson(responseJson, AuctionResponse.class);
				
				AuctionFileData auctionFileData = auctionResponse.getFiles()[0];
				
				String auctionUrl = auctionFileData.getUrl();
				request = new HttpGet(auctionUrl);
		
				response = client.execute(request);
				
				statusCode = response.getStatusLine().getStatusCode();
				
				if (statusCode == HttpStatus.SC_OK) {
					entity = response.getEntity();
					inputStream = entity.getContent();
					
					responseJson = IOUtils.toString(inputStream);
					
					AuctionFileResponse auctionFileResponse = gson.fromJson(responseJson, AuctionFileResponse.class);
					
					AuctionItemDAO auctionItemDAO = DAOFactory.getAuctionItemDAO();
					auctionItemDAO.startMassInsert(dbUrl, dbUserName, dbPassword);
					
					System.out.println("--- Inserting data ---");
					
					for (AuctionItem auctionItem : auctionFileResponse.getAuctions()) {
						auctionItemDAO.create(auctionItem);
					}
					
					auctionItemDAO.stopMassInsert();
				}	
			}
		System.out.println("--- Data successfully updated ---");
		System.out.println("--- Going to sleep ---");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {				
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
