package com.ducanh.model;

public class BuildingTypeDTO {
   private String name;

   private String address;
   
   private String rentValue;

	public String getRentValue() {
		return rentValue;
	}
	public void setRentValue(String rentValue) {
		this.rentValue = rentValue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
