package com.iagami.shopperstack.endpoints;
import com.iagami.shopperstack.payloads.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStackCardEndPoint implements ShopperStackAllURI {
	
	public  Response addProductToCart(ShopperStackWishListDTO pojo,int shopperId,String jwtToken) {
		RestAssured.useRelaxedHTTPSValidation();
		
		Response post = RestAssured.given().contentType(ContentType.JSON)
				.body(pojo).pathParam("shopperId", shopperId).header("Authorization","Bearer "+jwtToken)
		.when().post(postCart);
		
		return post;
	}
	
public Response getAllProductFromCart(int shopperId,String jwtToken) {
	RestAssured.useRelaxedHTTPSValidation();
	
	Response post = RestAssured.given()
			.pathParam("shopperId", shopperId).header("Authorization","Bearer "+jwtToken)
	.when().get(getCart);
	return post;
}
public Response updateTheAddedCart(int itemId,String jwtToken,ShopperStackWishListDTO pojo,int shopperId) {
	RestAssured.useRelaxedHTTPSValidation();
	
	Response post = RestAssured.given().contentType(ContentType.JSON)
			.body(pojo)
			.pathParam("itemId", itemId).pathParam("shopperId", shopperId)
			.header("Authorization","Bearer "+jwtToken)
	.when().put(updateCart);
	return post;
}
public Response deleteTheAddedCart(int productId,String jwtToken,int shopperId) {
	RestAssured.useRelaxedHTTPSValidation();
	
	Response post = RestAssured.given().contentType(ContentType.JSON)
			.pathParam("productId", productId).pathParam("shopperId", shopperId)
			.header("Authorization","Bearer "+jwtToken)
	.when().delete(deleteCart);
	return post;
}
}
