package com.iagami.shopperstack.payloads;

public class ShopperStackWishListDTO {
	
//	{
//		  "productId": 0,
//		  "quantity": 0
//		}
 private int productId;
 
 private int quantity;
	
/**
 * @return the productId
 */
public int getProductId() {
	return productId;
}
/**
 * @param productId the productId to set
 */
public void setProductId(int productId) {
	this.productId = productId;
}
/**
 * @return the quantity
 */
public int getQuantity() {
	return quantity;
}
/**
 * @param quantity the quantity to set
 */
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}
