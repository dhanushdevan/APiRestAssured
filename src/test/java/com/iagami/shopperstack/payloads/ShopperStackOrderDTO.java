package com.iagami.shopperstack.payloads;

public class ShopperStackOrderDTO {
	
	private String paymentMode;
	private ShopperStackAddressDTO address;
	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}
	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	/**
	 * @return the address
	 */
	public ShopperStackAddressDTO getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(ShopperStackAddressDTO address) {
		this.address = address;
	}
	

}
