package com.iagami.shopperstack.endpoints;

import com.iagami.shopperstack.payloads.ShopperStackOrderDTO;
import com.iagami.shopperstack.payloads.ShopperStackWishListDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStackOrderEndPoits implements ShopperStackAllURI{
	
	
	public  Response placeOrder( ShopperStackOrderDTO pojo,int shopperId,String jwtToken) {
		RestAssured.useRelaxedHTTPSValidation();
		
		Response post = RestAssured.given().contentType(ContentType.JSON)
				.body(pojo).pathParam("shopperId", shopperId).header("Authorization","Bearer "+jwtToken)
		.when().post(placeOrder);
		
		return post;
	}
	
	public  Response getallOrdersHistry(int shopperId,String jwtToken) {
		RestAssured.useRelaxedHTTPSValidation();
		
		Response post = RestAssured.given().pathParam("shopperId", shopperId).header("Authorization","Bearer "+jwtToken)
		.when().get(getOrderHistory);
		
		return post;
	}
	
	public  Response updateaOrder(int shopperId,String jwtToken,int orderId) {
		RestAssured.useRelaxedHTTPSValidation();
		
		Response post = RestAssured.given().pathParam("shopperId", shopperId).pathParam("orderId", orderId).queryParam("status", "DELIVERED")
				
				.header("Authorization","Bearer "+jwtToken)
		.when().patch(updateOrderStatus);
		
		return post;
	}
	
	public  Response invoice(int shopperId,int orderId,String jwtToken) {
		RestAssured.useRelaxedHTTPSValidation();
		
		Response post = RestAssured.given().pathParam("shopperId", shopperId).pathParam("orderId", orderId)
				
				.header("Authorization","Bearer "+jwtToken)
		.when().get(generateInvoice);
		
		return post;
	}
	
}
