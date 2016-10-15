package com.nickrepetti.ahmonitor.dao;

import com.nickrepetti.ahmonitor.model.AuctionItem;

public interface AuctionItemDAO {

	public void create(AuctionItem auctionItem);
	public void startMassInsert(String dbUrl, String dbUserName, String dbPassword) throws Exception;
	public void stopMassInsert() throws Exception;
	
}
