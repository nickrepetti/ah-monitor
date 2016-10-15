package com.nickrepetti.ahmonitor.model;

import com.google.gson.annotations.SerializedName;

public class AuctionItem {
	
	@SerializedName("auc")
	private Long auctionId;
	@SerializedName("item")
	private Long itemId;
	private Long buyout;
	private Integer quantity;
	
	public AuctionItem(Long auctionId, Long itemId, Long buyout, Integer quantity) {
		this.auctionId = auctionId;
		this.itemId = itemId;
		this.buyout = buyout;
		this.quantity = quantity;
	}
	
	public Long getAuctionId() {
		return auctionId;
	}
	
	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}
	
	public Long getItemId() {
		return itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public Long getBuyout() {
		return buyout;
	}
	
	public void setBuyout(Long buyout) {
		this.buyout = buyout;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
