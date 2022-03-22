package com.application.salestaxes.model;

public class Receipt {
	
	private Basket basket;	
	private float salesTaxes;
	private float total;
	
	public Basket getBasket() {
		return basket;
	}
	
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	public float getSalesTaxes() {
		return salesTaxes;
	}
	
	public void setSalesTaxes(float salesTaxes) {
		this.salesTaxes = salesTaxes;
	}
	
	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}

}
