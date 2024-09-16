package com.algo.housequest.models;

public class Graph {
	
	int source;
	int destination;
	int distance;
	
	public Graph(int source, int destination, int distance) {
		super();
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}
	
	public Graph(int source,int destination) {
		this.source = source;
		this.destination = destination;
	}

	public int getSource() {
		return source;
	}

	public int getDestination() {
		return destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}	

}
