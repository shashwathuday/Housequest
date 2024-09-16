package com.algo.housequest.models;

public class Place {
	
	int id;
	String type;
	String address;
	
	int distance;
	
	public Place(int id, String type, String address) {
		this.id = id;
		this.type = type;
		this.address = address;
	}
	
	public Place(int id, int distance) {
		this.id = id;
		this.distance = distance;
		
	}
	
	public Place(int id, String type, String address,int distance) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.distance = distance;
	}
	

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}
	
	public String getAddress() {
		return address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
