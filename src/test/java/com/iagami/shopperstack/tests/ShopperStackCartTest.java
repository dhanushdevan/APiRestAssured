package com.iagami.shopperstack.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.iagami.shopperstack.endpoints.ShopperStackCardEndPoint;
import com.iagami.shopperstack.payloads.ShopperStackWishListDTO;

import io.restassured.response.Response;

public class ShopperStackCartTest {
	ShopperStackWishListDTO shopperStackWishListDTO= new ShopperStackWishListDTO();
	ShopperStackCardEndPoint shopperStackCardEndPoint= new ShopperStackCardEndPoint();
	
	int cartId;
	
	@Test(dependsOnMethods = "getAllAddress")
	void addToCart() {
		
		shopperStackWishListDTO.setProductId(ShopperStackAProfileTest.productId);
		shopperStackWishListDTO.setQuantity(2);
		
		Response data=shopperStackCardEndPoint.addProductToCart(shopperStackWishListDTO, ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.jwtToken);
		System.out.println("Response Data :"+data.body().asPrettyString());
		data.then().statusCode(201)
		.body("message", Matchers.equalTo("Created"))
        .body("data.productId", Matchers.equalTo(ShopperStackAProfileTest.productId))
        .body("data.quantity", Matchers.equalTo(2))
        .body("data.productName", Matchers.containsString("FOREVER 21"));
		
		cartId=data.jsonPath().getInt("data.itemId");
		
		 System.out.println("------------Add to Cart Completed------------");
		
	}
	@Test(dependsOnMethods = "addToCart")
	void getAllCart() {
		
		Response data=shopperStackCardEndPoint.getAllProductFromCart(ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.jwtToken);
		
		data.then().statusCode(200)
		   .body("message", Matchers.equalTo("Success"))
           .body("data.itemId", Matchers.hasItem(cartId));
		System.out.println("Response Data :"+data.body().asPrettyString());
		 System.out.println("------------Get All Cart Completed------------");
		
	}
	@Test(dependsOnMethods = "getAllCart")
	void updateTheAddedCart() {
		
		shopperStackWishListDTO.setQuantity(1);
		
		Response data=shopperStackCardEndPoint.updateTheAddedCart(cartId, ShopperStackAProfileTest.jwtToken, shopperStackWishListDTO,ShopperStackAProfileTest.shopperId);
		
		data.then().statusCode(200);
//		 .body("message", Matchers.equalTo("Data Updated"))
//         .body("data.itemId", Matchers.equalTo(cartId))
//         .body("data.quantity", Matchers.equalTo(1));
		System.out.println("Response Data :"+data.body().asPrettyString());
		 System.out.println("------------Update the Cart Completed------------");
		
	}

	@Test(dependsOnMethods = "updateTheAddedCart")
	void deleteCart() {
	Response data=shopperStackCardEndPoint.deleteTheAddedCart(ShopperStackAProfileTest.productId, ShopperStackAProfileTest.jwtToken, ShopperStackAProfileTest.shopperId);
		
		data.then().statusCode(200)
		  .body("message", Matchers.equalTo("Success"));
		System.out.println("Response Data :"+data.body().asPrettyString());
		 System.out.println("------------Delete The Cart Completed------------");
	}

}
