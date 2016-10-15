package com.nickrepetti.ahmonitor.dao;

import com.nickrepetti.ahmonitor.dao.impl.MySQLAuctionItemDAO;

public abstract class DAOFactory {
	
	public static AuctionItemDAO getAuctionItemDAO() {
		return new MySQLAuctionItemDAO();
	}
}
