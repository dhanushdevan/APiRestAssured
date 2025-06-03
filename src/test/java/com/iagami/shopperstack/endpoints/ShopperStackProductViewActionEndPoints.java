package com.iagami.shopperstack.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ShopperStackProductViewActionEndPoints implements ShopperStackAllURI {
	
	public Response getAllTheProducts(String ZoneId,String jwtToken) {
		
		RestAssured.useRelaxedHTTPSValidation();
		
		Response response = RestAssured.given().queryParam("zoneId", ZoneId).header("Authorization","Bearer "+jwtToken)
		.when().get(homePageProduct);
		
		return response;
		
	}
	public Response getWelcomePageData() {
	RestAssured.useRelaxedHTTPSValidation();
		
		Response response = RestAssured.given()
		.when().get(welcomePageProduct);
		
		return response;
	}

}
