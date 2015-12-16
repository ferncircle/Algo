package com.chawkalla.algorithms;

public class Person {

	private String fName;
	private String email;
	
	
	public Person(String fName, String email) {
		super();
		this.fName = fName;
		this.email = email;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		return prime;
	}
	@Override
	public boolean equals(Object obj) {
		return true;
	}
	
	
}
