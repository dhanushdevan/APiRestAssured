package com.iagami.shopperstack.endpoints;


import com.iagami.shopperstack.payloads.ShopperStackRegisterPojo;
import com.iagami.shopperstack.payloads.ShopperstackLoginDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperProfileEnpoints implements ShopperStackAllURI{
	
	
	public Response registerForShopper (ShopperStackRegisterPojo pojo) {
		RestAssured.useRelaxedHTTPSValidation();
		Response post = RestAssured.given().contentType(ContentType.JSON).body(pojo)
		.when().post(registerShopper);
		
		return post;
	
	}
	
	public Response loginForUser(ShopperstackLoginDTO shopperstackLoginDTO) {
		
		RestAssured.useRelaxedHTTPSValidation();
		Response post = RestAssured.given().contentType(ContentType.JSON).body(shopperstackLoginDTO)
				.when().post(shopperLogin);
				
				return post;
		
	}
	
	
	public Response findShopperBySopperID(int shopperID,String jwtToken) {
		
		Response post = RestAssured.given().pathParam("shopperId", shopperID).header("Authorization","Bearer "+jwtToken)
				.when().get(getShopperData);
				
				return post;
		
	}
	
	public Response updateTheShopper(int shopperID,String jwtToken,ShopperStackRegisterPojo pojo) {
		
		Response post = RestAssured.given().contentType(ContentType.JSON)
				.pathParam("shopperId", shopperID).header("Authorization","Bearer "+jwtToken).body(pojo)
				.when().patch(updateShopperData);
				
				return post;
		
	}
	
	
	

}
