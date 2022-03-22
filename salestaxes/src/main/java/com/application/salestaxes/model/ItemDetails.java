package com.application.salestaxes.model;

public class ItemDetails {
	
	private String name;
	private int quantity;
	private float price;
	private boolean isExempted;
	private boolean isImported;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public boolean isExempted() {
		return isExempted;
	}
	
	public void setExempted(boolean isExempted) {
		this.isExempted = isExempted;
	}
	
	public boolean isImported() {
		return isImported;
	}
	
	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}
	
}