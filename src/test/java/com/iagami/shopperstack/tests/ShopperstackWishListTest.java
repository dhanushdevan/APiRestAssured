package com.iagami.shopperstack.tests;

import org.testng.annotations.Test;

import com.iagami.shopperstack.endpoints.ShopperStackWishListEndPoint;
import com.iagami.shopperstack.payloads.ShopperStackWishListDTO;

import io.restassured.response.Response;

public class ShopperstackWishListTest {
	
	ShopperStackWishListEndPoint shopperStackWishList= new ShopperStackWishListEndPoint();
	
	@Test(dependsOnMethods ="getInvoice")
	void addProductToWishList() {
		
		ShopperStackWishListDTO  shopperStackWishListDTO= new ShopperStackWishListDTO();
		
		shopperStackWishListDTO.setProductId(ShopperStackAProfileTest.productId);
		shopperStackWishListDTO.setQuantity(5);
		
		Response dataResponse=shopperStackWishList.wishListAddProduct(shopperStackWishListDTO, ShopperStackAProfileTest.jwtToken,ShopperStackAProfileTest.shopperId);
		
		dataResponse.then().assertThat().statusCode(201);
		System.out.println("Response Data :"+dataResponse.body().asPrettyString());
		
		 System.out.println("------------Add Product To Wishlist Completed------------");
	}
	
	
	@Test(dependsOnMethods ="addProductToWishList")
	void getProductToWishList() {
		
	
		
		Response dataResponse=shopperStackWishList.wishListGetProduct(ShopperStackAProfileTest.jwtToken, ShopperStackAProfileTest.shopperId);
		
		dataResponse.then().assertThat().statusCode(200);
		System.out.println("Response Data :"+dataResponse.body().asPrettyString());
		 System.out.println("------------GetProduct To Wishlist Completed------------");
	}
	
	@Test(dependsOnMethods ="getProductToWishList")
//	@Test
	void deleteProductToWishList() {
		
	
		System.out.println("jwt "+ShopperStackAProfileTest.jwtToken);
		System.out.println("shopperId"+ShopperStackAProfileTest.shopperId);
		Response dataResponse=shopperStackWishList.wishListDeleteProduct(ShopperStackAProfileTest.jwtToken, ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.productId);
		
		dataResponse.then().assertThat().statusCode(204);
		System.out.println("Response Data :"+dataResponse.body().asPrettyString());
		 System.out.println("------------Delete Product to WishList Completed------------");
	}		

}
