package com.example.kawa.models;

public class UserResultsItem {
	private String nat;
	private String gender;
	private Name name;
	private Location location;
	private String email;
	private Picture picture;

	public String getNat(){
		return nat;
	}

	public String getGender(){
		return gender;
	}

	public Name getName(){
		return name;
	}

	public Location getLocation(){
		return location;
	}

	public String getEmail(){
		return email;
	}

	public Picture getPicture(){
		return picture;
	}
}
