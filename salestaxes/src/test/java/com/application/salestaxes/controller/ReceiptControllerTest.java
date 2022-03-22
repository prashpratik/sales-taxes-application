package com.application.salestaxes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.application.salestaxes.model.Basket;
import com.application.salestaxes.model.ItemDetails;
import com.application.salestaxes.model.Receipt;

public class ReceiptControllerTest {
	
	@Test
	void generateReceiptTest1() {
		ReceiptController controller = new ReceiptController();
		
		//Input
		ItemDetails item1 = new ItemDetails();
		item1.setName("book");
		item1.setQuantity(1);
		item1.setPrice(12.49f);
		item1.setExempted(true);
		item1.setImported(false);
		
		ItemDetails item2 = new ItemDetails();
		item2.setName("music CD");
		item2.setQuantity(1);
		item2.setPrice(14.99f);
		item2.setExempted(false);
		item2.setImported(false);
		
		ItemDetails item3 = new ItemDetails();
		item3.setName("chocolate bar");
		item3.setQuantity(1);
		item3.setPrice(0.85f);
		item3.setExempted(true);
		item3.setImported(false);
		
		ArrayList<ItemDetails> itemDetailsListObj = new ArrayList<ItemDetails>();
		itemDetailsListObj.add(item1);
		itemDetailsListObj.add(item2);
		itemDetailsListObj.add(item3);
		
		Basket basketObj = new Basket();		
		basketObj.setItemDetailsList(itemDetailsListObj);
		
		//Output		
		String receiptStr = "> 1 book: 12.49\r\n"
				+ "> 1 music CD: 16.49\r\n"
				+ "> 1 chocolate bar: 0.85\r\n"
				+ "> Sales Taxes: 1.50\r\n"
				+ "> Total: 29.83";
		
		//Response from controller
		Receipt response = controller.generateReceipt(basketObj);
		String responseStr = "";
		
		Basket receiptBasketObj = response.getBasket();
		ArrayList<ItemDetails> receiptItemDetailsListObj = receiptBasketObj.getItemDetailsList();
		
		for(ItemDetails receiptItemDetailsObj:receiptItemDetailsListObj) {
			responseStr += "> " + receiptItemDetailsObj.getQuantity() +
						(receiptItemDetailsObj.isImported() ? " imported " : " ") +
						receiptItemDetailsObj.getName() +
						": " + String.format("%.2f", receiptItemDetailsObj.getPrice()) +
						"\r\n";
		}
		
		responseStr += "> Sales Taxes: " + String.format("%.2f", response.getSalesTaxes()) +
					"\r\n> Total: " + String.format("%.2f", response.getTotal());
		
		//Assert
		assertEquals(receiptStr, responseStr);
	}
	
	@Test
	void generateReceiptTest2() {
		ReceiptController controller = new ReceiptController();
		
		//Input
		ItemDetails item1 = new ItemDetails();
		item1.setName("box of chocolates");
		item1.setQuantity(1);
		item1.setPrice(10.00f);
		item1.setExempted(true);
		item1.setImported(true);
		
		ItemDetails item2 = new ItemDetails();
		item2.setName("bottle of perfume");
		item2.setQuantity(1);
		item2.setPrice(47.50f);
		item2.setExempted(false);
		item2.setImported(true);
		
		ArrayList<ItemDetails> itemDetailsListObj = new ArrayList<ItemDetails>();
		itemDetailsListObj.add(item1);
		itemDetailsListObj.add(item2);
		
		Basket basketObj = new Basket();		
		basketObj.setItemDetailsList(itemDetailsListObj);
		
		//Output		
		String receiptStr = "> 1 imported box of chocolates: 10.50\r\n"
				+ "> 1 imported bottle of perfume: 54.65\r\n"
				+ "> Sales Taxes: 7.65\r\n"
				+ "> Total: 65.15";
		
		//Response from controller
		Receipt response = controller.generateReceipt(basketObj);
		String responseStr = "";
		
		Basket receiptBasketObj = response.getBasket();
		ArrayList<ItemDetails> receiptItemDetailsListObj = receiptBasketObj.getItemDetailsList();
		
		for(ItemDetails receiptItemDetailsObj:receiptItemDetailsListObj) {
			responseStr += "> " + receiptItemDetailsObj.getQuantity() +
						(receiptItemDetailsObj.isImported() ? " imported " : " ") +
						receiptItemDetailsObj.getName() +
						": " + String.format("%.2f", receiptItemDetailsObj.getPrice()) +
						"\r\n";
		}
		
		responseStr += "> Sales Taxes: " + String.format("%.2f", response.getSalesTaxes()) +
					"\r\n> Total: " + String.format("%.2f", response.getTotal());
		
		//Assert
		assertEquals(receiptStr, responseStr);
	}
	
	@Test
	void generateReceiptTest3() {
		ReceiptController controller = new ReceiptController();
		
		//Input
		ItemDetails item1 = new ItemDetails();
		item1.setName("bottle of perfume");
		item1.setQuantity(1);
		item1.setPrice(27.99f);
		item1.setExempted(false);
		item1.setImported(true);
		
		ItemDetails item2 = new ItemDetails();
		item2.setName("bottle of perfume");
		item2.setQuantity(1);
		item2.setPrice(18.99f);
		item2.setExempted(false);
		item2.setImported(false);
		
		ItemDetails item3 = new ItemDetails();
		item3.setName("packet of headache pills");
		item3.setQuantity(1);
		item3.setPrice(9.75f);
		item3.setExempted(true);
		item3.setImported(false);
		
		ItemDetails item4 = new ItemDetails();
		item4.setName("box of chocolates");
		item4.setQuantity(1);
		item4.setPrice(11.25f);
		item4.setExempted(true);
		item4.setImported(true);
		
		ArrayList<ItemDetails> itemDetailsListObj = new ArrayList<ItemDetails>();
		itemDetailsListObj.add(item1);
		itemDetailsListObj.add(item2);
		itemDetailsListObj.add(item3);
		itemDetailsListObj.add(item4);
		
		Basket basketObj = new Basket();		
		basketObj.setItemDetailsList(itemDetailsListObj);
		
		//Output		
		String receiptStr = "> 1 imported bottle of perfume: 32.19\r\n"
				+ "> 1 bottle of perfume: 20.89\r\n"
				+ "> 1 packet of headache pills: 9.75\r\n"
				+ "> 1 imported box of chocolates: 11.85\r\n"
				+ "> Sales Taxes: 6.70\r\n"
				+ "> Total: 74.68";
		
		//Response from controller
		Receipt response = controller.generateReceipt(basketObj);
		String responseStr = "";
		
		Basket receiptBasketObj = response.getBasket();
		ArrayList<ItemDetails> receiptItemDetailsListObj = receiptBasketObj.getItemDetailsList();
		
		for(ItemDetails receiptItemDetailsObj:receiptItemDetailsListObj) {
			responseStr += "> " + receiptItemDetailsObj.getQuantity() +
						(receiptItemDetailsObj.isImported() ? " imported " : " ") +
						receiptItemDetailsObj.getName() +
						": " + String.format("%.2f", receiptItemDetailsObj.getPrice()) +
						"\r\n";
		}
		
		responseStr += "> Sales Taxes: " + String.format("%.2f", response.getSalesTaxes()) +
					"\r\n> Total: " + String.format("%.2f", response.getTotal());
		
		//Assert
		assertEquals(receiptStr, responseStr);
	}

}
