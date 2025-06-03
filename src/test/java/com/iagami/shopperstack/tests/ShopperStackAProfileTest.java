package com.iagami.shopperstack.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;
import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.iagami.shopperstack.endpoints.*;
import com.iagami.shopperstack.endpoints.ShopperStackCardEndPoint;
import com.iagami.shopperstack.endpoints.ShopperStackProductViewActionEndPoints;
import com.iagami.shopperstack.endpoints.ShopperStackWishListEndPoint;
import com.iagami.shopperstack.payloads.ShopperStackAddressDTO;
import com.iagami.shopperstack.payloads.ShopperStackOrderDTO;
import com.iagami.shopperstack.payloads.ShopperStackRegisterPojo;
import com.iagami.shopperstack.payloads.ShopperStackWishListDTO;
import com.iagami.shopperstack.payloads.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStackAProfileTest {
	ShopperStackAddressEndPoint shopperStackAddressEndPoint = new ShopperStackAddressEndPoint();
	ShopperStackWishListDTO shopperStackWishListDTO = new ShopperStackWishListDTO();
	ShopperStackAddressDTO shopperStackAddressDTO = new ShopperStackAddressDTO();
	ShopperStackRegisterPojo shopperStackRegisterPojo = new ShopperStackRegisterPojo();
	ShopperStackCardEndPoint shopperStackCardEndPoint = new ShopperStackCardEndPoint();
	ShopperStackOrderDTO shopperStackOrderDTO= new ShopperStackOrderDTO();
	ShopperStackOrderEndPoits shopperStackOrderEndPoits= new  ShopperStackOrderEndPoits();
	static String phoneNumberStr = "9344212304";
	static long phoneNumber = Long.parseLong(phoneNumberStr);
	static int shopperId = 253551;
	String email = "dorethea.bogisich@yahoo.com";
	String password = "9eb0hdo8";
	String role = "SHOPPER";
	static String jwtToken;
	String zoneId;
	static int productId = 17;
	static int cartId;
	static int addressId;
	static int orderId;

	ShopperStackWishListEndPoint shopperStackWishList = new ShopperStackWishListEndPoint();

	ShopperProfileEnpoints shopperProfileEnpoints = new ShopperProfileEnpoints();

	ShopperStackProductViewActionEndPoints shopperStackProductViewActionEndPoints = new ShopperStackProductViewActionEndPoints();
	
	@Test 
	void registerForShopper() { //TestCase for register the user;
		System.out.println("------------------Welcome to Dhaush End-End Testing ShopperStack-----------------------");
		
		Faker faker= new Faker();
		
		
		shopperStackRegisterPojo.setFirstName(faker.name().firstName());
		shopperStackRegisterPojo.setLastName(faker.name().lastName());
		shopperStackRegisterPojo.setCity(faker.address().city());
		shopperStackRegisterPojo.setCountry(faker.address().country());
		shopperStackRegisterPojo.setEmail(faker.internet().emailAddress());
		shopperStackRegisterPojo.setGender(faker.demographic().sex().toUpperCase());
		shopperStackRegisterPojo.setPassword(faker.internet().password());
		String phoneNumberStr = "9344212304";
		long phoneNumber = Long.parseLong(phoneNumberStr);
//		System.out.println("number :"+phoneNumber);
		shopperStackRegisterPojo.setPhone(phoneNumber);
		shopperStackRegisterPojo.setZoneId("ALPHA");
		shopperStackRegisterPojo.setState(faker.address().state());
//		System.out.println("password :"+shopperStackRegisterPojo.getPassword());
		Response data=shopperProfileEnpoints.registerForShopper(shopperStackRegisterPojo);
		
		data.then().assertThat() .statusCode(201);
		
		 shopperId=data.body().jsonPath().getInt("data.userId");
		 email=data.body().jsonPath().getString("data.email");
		 password=shopperStackRegisterPojo.getPassword();
		 role=data.body().jsonPath().getString("data.role");
		 zoneId=data.body().jsonPath().getString("data.zoneId");
		 
		 //Validation for register
		 
		data.then()
		.body("data.userId",Matchers.equalTo(shopperId))
		.body("data.role",Matchers.equalTo( "SHOPPER"))
		.body("data.email", Matchers.equalTo(email))
		.body("data.zoneId", Matchers.equalTo(zoneId))
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(20000L))
		.body(matchesJsonSchemaInClasspath("registerSchema.json"));
		System.out.println("Response Data :"+data.body().asPrettyString());
		
		 System.out.println("------------Register Completed------------");
		
	}
	
	@Test(dependsOnMethods = "registerForShopper")

//	@Test

	void loginForUser() {// TestCase for login

		
		
		ShopperstackLoginDTO shopperstackLoginDTO = new ShopperstackLoginDTO();

		shopperstackLoginDTO.setEmail(email);
		shopperstackLoginDTO.setPassword(password);
		shopperstackLoginDTO.setRole(role);

		Response data = shopperProfileEnpoints.loginForUser(shopperstackLoginDTO);
		System.out.println("Response Data :"+data.body().asPrettyString());
		data.then().assertThat().statusCode(200);
		jwtToken = data.body().jsonPath().getString("data.jwtToken");

		shopperId = data.body().jsonPath().getInt("data.userId");

		System.out.println("userid" + shopperId);
		// validations
		data.then().body("data.userId", Matchers.equalTo(shopperId)).body("data.email", Matchers.equalTo(email))
				.body("data.role", Matchers.equalTo(role)).contentType(ContentType.JSON).time(Matchers.lessThan(5000L))
				.body(matchesJsonSchemaInClasspath("loginSchema.json"));
	
		 System.out.println("------------Login Completed------------");
	}

	@Test(dependsOnMethods ="loginForUser" )//Test Case for get the User Details
	
	void getShopperDatabyShopperId() {
		Response data=shopperProfileEnpoints.findShopperBySopperID(shopperId, jwtToken);
		System.out.println("Response Data :"+data.body().asPrettyString());
		data.then().assertThat().statusCode(200);
		
		data.then().body("data.userId",Matchers.equalTo(shopperId))
		.body("data.userId",Matchers.equalTo(shopperId))
		.body("data.email",Matchers.equalTo(email))
		.body("data.role",Matchers.equalTo(role))
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(2000L))
		.body(matchesJsonSchemaInClasspath("getUserDeatilsSchema.json"));
	
		 System.out.println("------------ProfileDeatils Completed------------");
	}
	
	@Test(dependsOnMethods = "getShopperDatabyShopperId") //Test Case for Update the User Details
	
	void updateTheUserDeatils() {
		Faker faker= new Faker();

		shopperStackRegisterPojo.setFirstName(faker.name().firstName());
		String name= shopperStackRegisterPojo.getFirstName();
		
		Response data=shopperProfileEnpoints.updateTheShopper(shopperId, jwtToken, shopperStackRegisterPojo);
		data.then().assertThat().statusCode(200);
		
		//Validations 
//		
//		System.out.println("phone nu,mber"+phoneNumberStr);
//		System.out.println("actual data "+data.jsonPath().getString("data.phone"));
		data.then().body("data.userId",Matchers.equalTo(shopperId))
		.body("data.role",Matchers.equalTo(role))
		.body("data.email",Matchers.equalTo(email))
		.body("data.role",Matchers.equalTo(role))
		.body("data.firstName", Matchers.equalTo(name))
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(10000L))
		.body(matchesJsonSchemaInClasspath("updateScheam.json"));
		System.out.println("Response Data :"+data.body().asPrettyString());
		 System.out.println("------------Update User Completed------------");
	
	}
	
	@Test(dependsOnMethods ="updateTheUserDeatils")
	void getHomePageProductView() {
		
		Response dataResponse=shopperStackProductViewActionEndPoints.getAllTheProducts(zoneId, jwtToken);
		
		dataResponse.then().assertThat().statusCode(200)
		 .body("data", Matchers.notNullValue())
		    .body("data.size()", Matchers.greaterThan(0))
		    .contentType(ContentType.JSON)
		    .time(Matchers.lessThan(10000L));
//		System.out.println("Response Data :"+dataResponse.body().asPrettyString());
		 System.out.println("------------Get Home Page Completed------------");
		
	}
	
	@Test(dependsOnMethods ="getHomePageProductView")
	void getWelcomePageProductView() {
		
		Response dataResponse=shopperStackProductViewActionEndPoints.getWelcomePageData();
		
		dataResponse.then().assertThat().statusCode(200)
	    .body("data", Matchers.notNullValue())
		.contentType(ContentType.JSON)
		.time(Matchers.lessThan(4000L));
//		System.out.println("Response Data :"+dataResponse.body().asPrettyString());
		 System.out.println("------------Get WelcomePage Completed------------");
	}
	
	
	
}
