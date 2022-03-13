package com.example.kawa.models;

public class Location{
	private String country;
	private String city;
	private Street street;
//	private Timezone timezone;
//	private int postcode;
//	private Coordinates coordinates;
	private String state;

	public String getCountry(){
		return country;
	}

	public String getCity(){
		return city;
	}

	public Street getStreet(){
		return street;
	}

//	public Timezone getTimezone(){
//		return timezone;
//	}
//
//	public int getPostcode(){
//		return postcode;
//	}
//
//	public Coordinates getCoordinates(){
//		return coordinates;
//	}

	public String getState(){
		return state;
	}
}
