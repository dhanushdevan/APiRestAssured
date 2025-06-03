package com.iagami.shopperstack.endpoints;

import com.iagami.shopperstack.payloads.ShopperStackWishListDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStackWishListEndPoint implements ShopperStackAllURI {
	
	public Response wishListAddProduct( ShopperStackWishListDTO pojo,String jwtToken,int shopperid) {
		
		RestAssured.useRelaxedHTTPSValidation();
		
		Response post = RestAssured.given().contentType(ContentType.JSON).header("Authorization","Bearer "+jwtToken).pathParam("shopperId", shopperid)
		.body(pojo)
		.when().post(addWishlist);
		
		return post;
		
		
	}
public Response wishListGetProduct(String jwtToken,int shopperid) {
	RestAssured.useRelaxedHTTPSValidation();
	Response post = RestAssured.given().header("Authorization","Bearer "+jwtToken).pathParam("shopperId", shopperid)
			.when().get(getWishlist);
	
	return post;
			
	}


public Response wishListDeleteProduct(String jwtToken,int shopperid,int productid) {
	RestAssured.useRelaxedHTTPSValidation();
	Response post = RestAssured.given().header("Authorization","Bearer "+jwtToken).pathParam("shopperId", shopperid).pathParam("productId", productid)
			.when().delete(deleteWishlist);
	
	return post;
	
}
}
