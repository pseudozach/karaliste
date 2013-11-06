package com.pseudozach.karaliste;

public class Mesaj {
	
	static String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		Mesaj.address = address;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		Mesaj.body = body;
	}
	static String body;	
}
