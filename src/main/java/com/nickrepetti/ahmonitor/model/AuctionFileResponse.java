package com.nickrepetti.ahmonitor.model;

public class AuctionFileResponse {
	
	private AuctionItem[] auctions;
	
	public AuctionFileResponse(AuctionItem[] auctions) {
		this.auctions = auctions;
	}
	
	public AuctionItem[] getAuctions() {
		return auctions;
	}
	
	public void setAuctions(AuctionItem[] auctions) {
		this.auctions = auctions;
	}
}
