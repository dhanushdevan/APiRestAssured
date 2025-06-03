package com.iagami.shopperstack.tests;

import java.io.File;
import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.iagami.shopperstack.endpoints.*;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iagami.shopperstack.payloads.ShopperStackOrderDTO;
import com.iagami.shopperstack.payloads.*;

import io.restassured.response.Response;

public class ShopperStackOrderTest {
	ShopperStackWishListDTO shopperStackWishListDTO= new ShopperStackWishListDTO();
	
	ShopperStackCardEndPoint shopperStackCardEndPoint= new ShopperStackCardEndPoint();
	
	ShopperStackOrderDTO shopperStackOrderDTO= new ShopperStackOrderDTO();
	
	ShopperStackAddressDTO shopperStackAddressDTO= new ShopperStackAddressDTO();
	
	ShopperStackOrderEndPoits   shopperStackOrderEndPoits= new ShopperStackOrderEndPoits();
	
	int orderId;
	
	@Test(dependsOnMethods = "deleteCart")
	void addToCart() {
		
		shopperStackWishListDTO.setProductId(ShopperStackAProfileTest.productId);
		shopperStackWishListDTO.setQuantity(2);
		
		Response data=shopperStackCardEndPoint.addProductToCart(shopperStackWishListDTO, ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.jwtToken);

		data.then().statusCode(201)
		.body("data.productId",Matchers.equalTo(ShopperStackAProfileTest.productId))
        .body("data.quantity", Matchers.equalTo(2))
        .body("data.productName", Matchers.containsString("FOREVER 21"));
		System.out.println("Response Data :"+data.body().asPrettyString());
		 System.out.println("------------Add to Cart Completed------------");
		
	}
	
	
	@Test(dependsOnMethods = "addToCart")
	void ordertheProductTest() throws StreamWriteException, DatabindException, IOException {
		
		shopperStackOrderDTO.setPaymentMode("COD");
		shopperStackAddressDTO.setAddressId(ShopperStackAProfileTest.addressId);
		shopperStackOrderDTO.setAddress(shopperStackAddressDTO);
		
		File f= new File("./src/test/resources/address.json");
		ObjectMapper obj = new ObjectMapper();
		
		obj.writeValue(f, shopperStackOrderDTO);
		
		Response data=shopperStackOrderEndPoits.placeOrder(shopperStackOrderDTO, ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.jwtToken);
		

		
		orderId=data.jsonPath().getInt("data.orderId");
		
		
		data.then().statusCode(201)
		  .body("data.paymentMode", Matchers.equalTo("COD"))
	        .body("data.address.addressId", Matchers.equalTo(ShopperStackAProfileTest.addressId))
	        .body("data.orderStatus", Matchers.equalTo("PLACED"));
		System.out.println("Response Data :"+data.body().asPrettyString());
		 System.out.println("------------Order Product Completed------------");
		
	}
	
	@Test(dependsOnMethods = "ordertheProductTest")
	void getOrderHistory() {
		
		
		Response data=shopperStackOrderEndPoits.getallOrdersHistry(ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.jwtToken);
		
		data.then().statusCode(200);
//		 .body("data.orderId", Matchers.equalTo(orderId))
//       .body("data.shopperId", Matchers.equalTo(ShopperStackAProfileTest.shopperId));
		
		 System.out.println("------------Get Oder History Completed------------");
		
	}
	
	@Test(dependsOnMethods = "getOrderHistory")
	void updateTheOrder() {
		
		
		Response data=shopperStackOrderEndPoits.updateaOrder(ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.jwtToken, orderId);
		
		System.out.println(data.asPrettyString());
		
		data.then().statusCode(200)
		.body("data.orderId", Matchers.equalTo(orderId))
        .body("data.orderStatus", Matchers.equalTo("DELIVERED"));
		System.out.println("Response Data :"+data.body().asPrettyString());
		 System.out.println("------------Update The Order Completed------------");
		
	}
	
	@Test(dependsOnMethods = "updateTheOrder")
	void getInvoice() {
		
		
		Response data=shopperStackOrderEndPoits.invoice(ShopperStackAProfileTest.shopperId, orderId, ShopperStackAProfileTest.jwtToken);
		
		System.out.println(data.asPrettyString());
		
		data.then().statusCode(200);
		 
		System.out.println("Response Data :"+data.body().asPrettyString());
		 System.out.println("------------Get Invoice Completed------------");
		
	}
	

}
