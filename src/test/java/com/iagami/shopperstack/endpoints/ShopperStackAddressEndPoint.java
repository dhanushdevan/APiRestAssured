package com.iagami.shopperstack.endpoints;

import com.iagami.shopperstack.payloads.ShopperStackAddressDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ShopperStackAddressEndPoint implements ShopperStackAllURI {

	public Response addAddress(String jwtToken, ShopperStackAddressDTO pojo, int shopperId) {

		RestAssured.useRelaxedHTTPSValidation();
		Response post = RestAssured.given().contentType(ContentType.JSON).header("Authorization", "Bearer " + jwtToken)
				.pathParam("shopperId", shopperId).body(pojo).when().post(addAddress);

		return post;
	}

	public Response getTheAddedAddress(String jwtToken, int shopperId) {

		RestAssured.useRelaxedHTTPSValidation();
		Response post = RestAssured.given().header("Authorization", "Bearer " + jwtToken)
				.pathParam("shopperId", shopperId).when().get(getAllAddress);

		return post;
	}

	public Response getTheAddedSingleAddress(String jwtToken, int shopperId, int addressId) {

		RestAssured.useRelaxedHTTPSValidation();
		Response post = RestAssured.given().header("Authorization", "Bearer " + jwtToken)
				.pathParam("addressId", addressId).pathParam("shopperId", shopperId).when().get(getParticularAddress);

		return post;
	}

	public Response updateSingleAddress(String jwtToken, int shopperId, int addressId, ShopperStackAddressDTO pojo) {

		RestAssured.useRelaxedHTTPSValidation();
		Response post = RestAssured.given().header("Authorization", "Bearer " + jwtToken)
				.pathParam("addressId", addressId).contentType(ContentType.JSON).body(pojo)
				.pathParam("shopperId", shopperId).when().put(getParticularAddress);

		return post;
	}

	public Response deleteTheAddress(String jwtToken, int shopperId, int addressId) {

		RestAssured.useRelaxedHTTPSValidation();
		Response post = RestAssured.given().header("Authorization", "Bearer " + jwtToken)
				.pathParam("addressId", addressId)

				.pathParam("shopperId", shopperId).when().delete(deleteAddress);

		return post;
	}
}
