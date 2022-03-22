package com.application.salestaxes.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.salestaxes.model.Basket;
import com.application.salestaxes.model.ItemDetails;
import com.application.salestaxes.model.Receipt;

@RestController
@RequestMapping("/api")
public class ReceiptController {
	
	@PostMapping("/generateReceipt")
	public Receipt generateReceipt(@RequestBody Basket basketObj) {  
		ArrayList<ItemDetails> itemDetailsListObj = basketObj.getItemDetailsList();
		float salesTaxes = 0.0f;
		float total = 0.0f;
		
		for(ItemDetails itemDetailsObj:itemDetailsListObj) {
			if (itemDetailsObj.isImported()) {
				if (itemDetailsObj.isExempted()) {
					float itemPrice = itemDetailsObj.getQuantity()*itemDetailsObj.getPrice();
					float itemSalesTaxes = (float) (Math.ceil(itemPrice*0.05f*20.0f)/20.0f);
					float itemPriceWithSalesTax = itemPrice + itemSalesTaxes;
					itemDetailsObj.setPrice(itemPriceWithSalesTax);
					salesTaxes += itemSalesTaxes;
					total += itemPriceWithSalesTax;
				}
				else {
					float itemPrice = itemDetailsObj.getQuantity()*itemDetailsObj.getPrice();
					float itemSalesTaxes = (float) (Math.ceil(itemPrice*0.15f*20.0f)/20.0f);
					float itemPriceWithSalesTax = itemPrice + itemSalesTaxes;
					itemDetailsObj.setPrice(itemPriceWithSalesTax);
					salesTaxes += itemSalesTaxes;
					total += itemPriceWithSalesTax;
				}
			}
			else {
				if (itemDetailsObj.isExempted()) {
					float itemPrice = itemDetailsObj.getQuantity()*itemDetailsObj.getPrice();
					float itemSalesTaxes = 0f;
					float itemPriceWithSalesTax = itemPrice + itemSalesTaxes;
					itemDetailsObj.setPrice(itemPriceWithSalesTax);
					salesTaxes += itemSalesTaxes;
					total += itemPriceWithSalesTax;
				}
				else {
					float itemPrice = itemDetailsObj.getQuantity()*itemDetailsObj.getPrice();
					float itemSalesTaxes = (float) (Math.ceil(itemPrice*0.1f*20.0f)/20.0f);
					float itemPriceWithSalesTax = itemPrice + itemSalesTaxes;
					itemDetailsObj.setPrice(itemPriceWithSalesTax);
					salesTaxes += itemSalesTaxes;
					total += itemPriceWithSalesTax;
				}
			}
		}
		
		Receipt receiptObj = new Receipt();
		
		receiptObj.setBasket(basketObj);
		receiptObj.setSalesTaxes(salesTaxes);
		receiptObj.setTotal(total);
		
		return receiptObj;
	}

}
