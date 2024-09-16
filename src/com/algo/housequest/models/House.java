package com.algo.housequest.models;

public class House {
	
	int id;
	String type;
	String address;
	String bath;
	String bedrooms;
	String houseDp;
	double price;
	
	int distance;
	
	String grocery;
	int groc_dist;
	String transport;
	int trans_dist;
	
	public House(int id, String type, String address, String bath, String bedrooms,String houseDp) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.bath = bath;
		this.bedrooms = bedrooms;
		this.houseDp = houseDp;
	}
	
	public House(int id, int distance) {
		this.id = id;
		this.distance = distance;
		
	}
	
	public House(int id, int distance, double price) {
		this.id = id;
		this.distance = distance;
		this.price = price;
		
	}
	
	public House(int id, String type, String address, String bath, String bedrooms,String houseDp,int distance,double price) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.bath = bath;
		this.bedrooms = bedrooms;
		this.houseDp = houseDp;
		this.distance = distance;
		this.price = price;
	}
	
	public House(int id, String type, String address, String bath, String bedrooms,String houseDp,int distance,double price,
			String grocery, int groc_dist, String transport, int trans_dist) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.bath = bath;
		this.bedrooms = bedrooms;
		this.houseDp = houseDp;
		this.distance = distance;
		this.price = price;
		this.grocery = grocery;
		this.groc_dist = groc_dist;
		this.transport = transport;
		this.trans_dist = trans_dist;
	}

	public String getGrocery() {
		return grocery;
	}

	public int getGroc_dist() {
		return groc_dist;
	}

	public String getTransport() {
		return transport;
	}

	public int getTrans_dist() {
		return trans_dist;
	}

	public void setGrocery(String grocery) {
		this.grocery = grocery;
	}

	public void setGroc_dist(int groc_dist) {
		this.groc_dist = groc_dist;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public void setTrans_dist(int trans_dist) {
		this.trans_dist = trans_dist;
	}

	public String getHouseDp() {
		return houseDp;
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

	public String getBath() {
		return bath;
	}

	public String getBedrooms() {
		return bedrooms;
	}
	public int getDistance() {
		return distance;
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

	public void setBath(String bath) {
		this.bath = bath;
	}

	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}
	
	public void setHouseDp(String houseDp) {
		this.houseDp = houseDp;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
