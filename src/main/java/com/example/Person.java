package com.example;

import java.io.Serializable;

public class Person implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6124693982299903223L;
	/**
	 * 
	 */
	private String name;
	private String address;

	public Person(String name, String address) {
		this.name=name;
		this.address=address;
	}
	public Person() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + "]";
	}

}
