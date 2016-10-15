package com.nickrepetti.ahmonitor.dao.impl;

import com.nickrepetti.ahmonitor.model.AuctionItem;
import com.nickrepetti.ahmonitor.dao.AuctionItemDAO;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.Date;

public class MySQLAuctionItemDAO implements AuctionItemDAO {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String QUERY_CREATE = "INSERT INTO BlackhandAuction VALUES (?, ?, ?, ?, ?)";

	private Connection conn;

	@Override
	public void create(AuctionItem auctionItem) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(QUERY_CREATE);
			ps.setLong(1, auctionItem.getAuctionId());
			ps.setLong(2, auctionItem.getItemId());
			ps.setLong(3, auctionItem.getBuyout());
			ps.setInt(4, auctionItem.getQuantity());
			ps.setTimestamp(5, new Timestamp(new Date().getTime()));
			
			ps.execute();
		} catch (Exception e) {}
	}

	public void startMassInsert(String dbUrl, String dbUserName, String dbPassword) throws Exception {
		Class.forName(JDBC_DRIVER).newInstance();
		conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	}
	
	public void stopMassInsert() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
}
