package com.iagami.shopperstack.tests;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.iagami.shopperstack.endpoints.ShopperStackAddressEndPoint;
import com.iagami.shopperstack.payloads.ShopperStackAddressDTO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStackAddressTest {
	
	ShopperStackAddressEndPoint shopperStackAddressEndPoint = new ShopperStackAddressEndPoint();
	
	ShopperStackAddressDTO shopperStackAddressDTO= new ShopperStackAddressDTO();
	
	@Test(dependsOnMethods = "getWelcomePageProductView")
	void addNewAddress() throws StreamWriteException, DatabindException, IOException {
		
		
		System.out.println("");

		ShopperStackAddressDTO shopperStackAddressDTO = new ShopperStackAddressDTO();
		shopperStackAddressDTO.setAddressId(0);
		shopperStackAddressDTO.setBuildingInfo("home");
		shopperStackAddressDTO.setCity("chennai");
		shopperStackAddressDTO.setCountry("mumbai");
		shopperStackAddressDTO.setLandmark("home");
		shopperStackAddressDTO.setName("dhanush");
		shopperStackAddressDTO.setPhone(ShopperStackAProfileTest.phoneNumberStr);
		shopperStackAddressDTO.setPincode("432105");
		shopperStackAddressDTO.setType("de");
		shopperStackAddressDTO.setState("tamilnadu");
		shopperStackAddressDTO.setStreetInfo("street");

//		File f= new File("./src/test/resources/address.json");
//		ObjectMapper obj = new ObjectMapper();
//		
//		obj.writeValue(f, shopperStackAddressDTO);

		Response dataResponse = shopperStackAddressEndPoint.addAddress(ShopperStackAProfileTest.jwtToken, shopperStackAddressDTO, ShopperStackAProfileTest.shopperId);

//		System.out.println("respone data " + dataResponse.asPrettyString());

//		dataResponse.then().assertThat().statusCode(201).log().all();
		 dataResponse.then().assertThat().statusCode(201)
		    .contentType(ContentType.JSON)
	        .body("message", Matchers.equalTo("Created"))
	        .body("data.name", Matchers.equalTo("dhanush"))
	        .body("data.city", Matchers.equalTo("chennai"))
	        .body("data.country", Matchers.equalTo("mumbai"))
	        .body("data.pincode", Matchers.equalTo("432105"))
	        .body("data.phone", Matchers.equalTo(ShopperStackAProfileTest.phoneNumberStr))
	        .time(Matchers.lessThan(4000L));

		ShopperStackAProfileTest.addressId = dataResponse.jsonPath().getInt("data.addressId");
		System.out.println("Response Data :"+dataResponse.body().asPrettyString());
		 System.out.println("------------Add New Address Completed------------");

	}

	@Test(dependsOnMethods = "addNewAddress")
	void getSingleAddress() {

		Response dataResponse = shopperStackAddressEndPoint.getTheAddedSingleAddress(ShopperStackAProfileTest.jwtToken, ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.addressId);

//		System.out.println("respone data " + dataResponse.asPrettyString());

		dataResponse.then().assertThat().statusCode(200)
	    .body("message", Matchers.equalTo("Success"))
        .body("data.addressId", Matchers.equalTo(ShopperStackAProfileTest.addressId))
        .body("data.name", Matchers.equalTo("dhanush"))
        .body("data.phone", Matchers.equalTo(ShopperStackAProfileTest.phoneNumberStr))
		.body("data.addressId", Matchers.equalTo(ShopperStackAProfileTest.addressId));
		System.out.println("Response Data :"+dataResponse.body().asPrettyString());
		 System.out.println("------------Get Single Address Completed------------");

	}
	
	@Test(dependsOnMethods = "addNewAddress")
	void getAllAddress() {

		Response dataResponse = shopperStackAddressEndPoint.getTheAddedAddress(ShopperStackAProfileTest.jwtToken, ShopperStackAProfileTest.shopperId);

		System.out.println("respone data " + dataResponse.asPrettyString());

		dataResponse.then().assertThat().statusCode(200)
		  .body("message", Matchers.equalTo("Success"))
	        .body("data.addressId", Matchers.hasItem(ShopperStackAProfileTest.addressId));
//		.body("data.addressId", Matchers.equalTo(addressId));
		
		System.out.println("Response Data :"+dataResponse.body().asPrettyString());
		 System.out.println("------------GetAll Address Completed------------");

	}
//	@Test(dependsOnMethods = "getAllAddress")
//	void updateAddress() {
//
//		shopperStackAddressDTO.setAddressId(ShopperStackAProfileTest.addressId);
//		shopperStackAddressDTO.setName("deavna");
//		Response dataResponse = shopperStackAddressEndPoint.updateSingleAddress(ShopperStackAProfileTest.jwtToken, ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.addressId, shopperStackAddressDTO);
//		System.out.println("respone data " + dataResponse.asPrettyString());
//
//		dataResponse.then().assertThat().statusCode(200)
//		.body("data.name", Matchers.equalTo("deavna"));
//
//	}	
	
//	@Test(dependsOnMethods = "updateAddress")
//	void deleteAddress() {
//
//
//		Response dataResponse = shopperStackAddressEndPoint.deleteTheAddress(ShopperStackAProfileTest.jwtToken, ShopperStackAProfileTest.shopperId, ShopperStackAProfileTest.addressId);
//		System.out.println("respone data " + dataResponse.asPrettyString());
//
//		dataResponse.then().assertThat().statusCode(204);
//		
//
//	}

}
