package com.nickrepetti.ahmonitor.model;

public class AuctionResponse {
	
	private AuctionFileData[] files;
	
	public AuctionResponse(AuctionFileData[] files) {
		this.files = files;
	}
	
	public AuctionFileData[] getFiles() {
		return files;
	}
	
	public void setFiles(AuctionFileData[] files) {
		this.files = files;
	}
}
