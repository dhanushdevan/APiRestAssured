package com.iagami.shopperstack.endpoints;



	public interface ShopperStackAllURI {
		//Shopper Profile related
		String baseUrl="https://www.shoppersstack.com/shopping";
		String registerShopper=baseUrl+"/shoppers";
		String getShopperData=baseUrl+"/shoppers/{shopperId}";
		String updateShopperData=baseUrl+"/shoppers/{shopperId}";
		String generateForgotPassUrl=baseUrl+"/users/forgot-password";
		String shopperLogin=baseUrl+"/users/login";
		String shopperResetPass=baseUrl+"/users/reset-password";
		String shopperPassCreate=baseUrl+"/users/verify-account";
		
		//Product View Action related
		String homePageProduct=baseUrl+"/products";
		String welcomePageProduct=baseUrl+"/products/alpha";
		
		//Shopper Address related
		String getAllAddress=baseUrl+"/shoppers/{shopperId}/address";
		String addAddress=baseUrl+"/shoppers/{shopperId}/address";
		String getParticularAddress=baseUrl+"/shoppers/{shopperId}/address/{addressId}";
		String updateAddress=baseUrl+"/shoppers/{shopperId}/address/{addressId}";
		String deleteAddress=baseUrl+"/shoppers/{shopperId}/address/{addressId}";
		
		//Shopper wishlist related
		String getWishlist=baseUrl+"/shoppers/{shopperId}/wishlist";
		String addWishlist=baseUrl+"/shoppers/{shopperId}/wishlist";
		String deleteWishlist=baseUrl+"/shoppers/{shopperId}/wishlist/{productId}";
		
		//Shopper cart Related
		String getCart=baseUrl+"/shoppers/{shopperId}/carts";
		String postCart=baseUrl+"/shoppers/{shopperId}/carts";
		String updateCart=baseUrl+"/shoppers/{shopperId}/carts/{itemId}";
		String deleteCart=baseUrl+"/shoppers/{shopperId}/carts/{productId}";
		
		//Shopper Order Related
		String getOrderHistory=baseUrl+"/shoppers/{shopperId}/orders";
		String placeOrder=baseUrl+"/shoppers/{shopperId}/orders";
		String updateOrderStatus=baseUrl+"/shoppers/{shopperId}/orders/{orderId}";
		String generateInvoice=baseUrl+"/shoppers/{shopperId}/orders/{orderId}/invoice";
		
		//Shopper Product Review related
		String addReview=baseUrl+"/reviews";
		String getReviews=baseUrl+"/reviews/{productId}";
		String updateReview=baseUrl+"/reviews/{reviewId}";
		String deleteReview=baseUrl+"/reviews/{reviewId}";


	}

