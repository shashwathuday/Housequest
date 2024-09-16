package com.algo.housequest.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javax.sql.DataSource;

import com.algo.housequest.models.Graph;
import com.algo.housequest.models.House;
import com.algo.housequest.models.Place;


public class DbUtil {

	DataSource dataSource;

	// Servlet will pass on the connection pool to the dataSource variable in
	// dbUtil class
	public DbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private void closeConnection(Connection myCon, Statement myStmt, ResultSet myRes) throws SQLException {
		try {
			if (myStmt != null) {
				myStmt.close();
			}
			if (myRes != null) {
				myRes.close();
			}
			if (myCon != null) {
				myCon.close();// this will not necessarily close the db connection but will return the
								// connection pool
			}
		} catch (Exception exec) {
			exec.printStackTrace();
		}
	}

	// findUser() method validates if the user already exists on the db
	public List<House> getAllHouses() throws Exception {
		
		List<House> houseList = new ArrayList<>();

		// initialize the sql attributes
		Connection myCon = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;

		try {

			// create a connection
			myCon = dataSource.getConnection();

			// prepare the statement
			String query = "select * from house;";
			myStmt = myCon.prepareStatement(query);

			// execute query
			myRes = myStmt.executeQuery();

			// get the user details
			while (myRes.next()) {
				
				int id = Integer.parseInt(myRes.getString("house_id"));
				String type = myRes.getString("type");;
				String address = myRes.getString("address");
				String bath = myRes.getString("bath");
				String bedrooms= myRes.getString("bedroom");
				String houseDp = myRes.getString("house_dp");
				
				House house = new House(id,type,address,bath,bedrooms,houseDp);
				
				houseList.add(house);
				
			}
			
			return houseList;
		}
		
		finally {
			closeConnection(myCon, myStmt, myRes);
		}

	}
	
public List<Place> getAllPlaces() throws Exception {
		
		List<Place> placeList = new ArrayList<>();

		// initialize the sql attributes
		Connection myCon = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;

		try {

			// create a connection
			myCon = dataSource.getConnection();

			// prepare the statement
			String query = "select * from place;";
			myStmt = myCon.prepareStatement(query);

			// execute query
			myRes = myStmt.executeQuery();

			// get the user details
			while (myRes.next()) {
				
				int id = Integer.parseInt(myRes.getString("place_id"));
				String type = myRes.getString("type");
				String address = myRes.getString("address");
				
				Place place = new Place(id,type,address);
				
				placeList.add(place);
				
			}
			
			return placeList;
		}
		
		finally {
			closeConnection(myCon, myStmt, myRes);
		}

	}

public List<Graph> getGraph() throws Exception {
	
	List<Graph> graphInput = new ArrayList<>();

	// initialize the sql attributes
	Connection myCon = null;
	PreparedStatement myStmt = null;
	ResultSet myRes = null;

	try {

		// create a connection
		myCon = dataSource.getConnection();

		// prepare the statement
		String query = "select * from source_dest_mapper;";
		myStmt = myCon.prepareStatement(query);

		// execute query
		myRes = myStmt.executeQuery();

		// get the user details
		while (myRes.next()) {
			
			int source_id = Integer.parseInt(myRes.getString("source_id"));
			int dest_id = Integer.parseInt(myRes.getString("destination_id"));
			int distance = Integer.parseInt(myRes.getString("distance"));
			
			Graph graph = new Graph(source_id,dest_id,distance);
			
			graphInput.add(graph);
			
		}
		
		return graphInput;
	}
	
	finally {
		closeConnection(myCon, myStmt, myRes);
	}

}

public List<Graph> shortestPaths(int src,int vertices,List<Graph> inputGraph) throws Exception {
    PriorityQueue<Graph> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(o -> o.getSource()));
    int[] dist = new int[vertices];
    Arrays.fill(dist, Integer.MAX_VALUE);

    pq.add(new Graph(0, src));
    dist[src] = 0;

    List<Graph> results = new ArrayList<>();
    List<List<Graph>> adj;
    adj = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
        adj.add(new ArrayList<>());
    }
    
    for(int i = 0; i < inputGraph.size() ; i++) {
    	
    	adj.get(inputGraph.get(i).getSource()).add(new Graph(inputGraph.get(i).getDestination(),inputGraph.get(i).getDistance()));
    	adj.get(inputGraph.get(i).getDestination()).add(new Graph(inputGraph.get(i).getSource(),inputGraph.get(i).getDistance()));
    	
    }

    while (!pq.isEmpty()) {
        int u = pq.poll().getDestination();

        for (Graph v : adj.get(u)) {
            if (dist[v.getSource()] > dist[u] + v.getDestination()) {
                dist[v.getSource()] = dist[u] + v.getDestination();
                pq.add(new Graph(dist[v.getSource()], v.getSource()));
                results.add(new Graph(src, v.getSource(), dist[v.getSource()]));
            }
        }
    }

    return results;
}

public List<House> getConnectedHouseInfo(List<House> house) throws Exception {
	
	List<House> houseList = new ArrayList<>();

	// initialize the sql attributes
	Connection myCon = null;
	PreparedStatement myStmt = null;
	ResultSet myRes = null;

	try {

		// create a connection
		myCon = dataSource.getConnection();
		
		for(int i = 0; i < house.size(); i++) {
		// prepare the statement
		String query = "select * from house where house_id=?;";
		myStmt = myCon.prepareStatement(query);

		myStmt.setInt(1, house.get(i).getId());

		// execute query
		myRes = myStmt.executeQuery();

		// get the user details
		while (myRes.next()) {
			
			int id = Integer.parseInt(myRes.getString("house_id"));
			String type = myRes.getString("type");;
			String address = myRes.getString("address");
			String bath = myRes.getString("bath");
			String bedrooms= myRes.getString("bedroom");
			String houseDp = myRes.getString("house_dp");
			double price = Double.parseDouble(myRes.getString("price"));
			int distance = house.get(i).getDistance();
			
			House connectedHouse = new House(id,type,address,bath,bedrooms,houseDp,distance,price);
			
			houseList.add(connectedHouse);
			
		}
		}
		
		return houseList;
	}
	
	finally {
		closeConnection(myCon, myStmt, myRes);
	}

}

public List<Place> getConnectedPlaceInfo(List<Place> place) throws Exception {
	
	List<Place> placeList = new ArrayList<>();

	// initialize the sql attributes
	Connection myCon = null;
	PreparedStatement myStmt = null;
	ResultSet myRes = null;

	try {

		// create a connection
		myCon = dataSource.getConnection();
		
		for(int i = 0; i < place.size(); i++) {
		// prepare the statement
		String query = "select * from place where place_id=?;";
		myStmt = myCon.prepareStatement(query);

		myStmt.setInt(1, place.get(i).getId());

		// execute query
		myRes = myStmt.executeQuery();

		// get the user details
		while (myRes.next()) {
			
			int id = Integer.parseInt(myRes.getString("place_id"));
			String type = myRes.getString("type");;
			String address = myRes.getString("address");
			int distance = place.get(i).getDistance();
			
			Place connectedPlace = new Place(id,type,address,distance);
			
			placeList.add(connectedPlace);
			
		}
		}
		
		return placeList;
	}
	
	finally {
		closeConnection(myCon, myStmt, myRes);
	}

}

public List<House> quickSort(List<House> houses, int low, int high) {
    if (low < high) {
        int pi = partition(houses, low, high);

        quickSort(houses, low, pi - 1);
        quickSort(houses, pi + 1, high);
        
    }
    return houses;
}

public List<Place> quickSortPlaces(List<Place> place, int low, int high) {
    if (low < high) {
        int pi = partitionPlaces(place, low, high);

        quickSortPlaces(place, low, pi - 1);
        quickSortPlaces(place, pi + 1, high);
        
    }
    return place;
}

public static int partitionPlaces(List<Place> place, int low, int high) {
    double pivot = place.get(high).getDistance();
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (place.get(j).getDistance() < pivot) {
            i++;

            Place temp = place.get(i);
            place.set(i, place.get(j));
            place.set(j, temp);
        }
    }

    Place temp = place.get(i + 1);
    place.set(i + 1, place.get(high));
    place.set(high, temp);

    return i + 1;
}

public static int partition(List<House> houses, int low, int high) {
    double pivot = houses.get(high).getDistance();
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (houses.get(j).getDistance() < pivot) {
            i++;

            House temp = houses.get(i);
            houses.set(i, houses.get(j));
            houses.set(j, temp);
        }
    }

    House temp = houses.get(i + 1);
    houses.set(i + 1, houses.get(high));
    houses.set(high, temp);

    return i + 1;
}


public List<House> getHouse(int destId) throws Exception {
	
	List<Graph> inputGraph = new ArrayList<>();
	inputGraph = getGraph();
	
	int vertices = inputGraph.size();
	
	List<Graph> outputGraph = shortestPaths(destId,vertices,inputGraph);
	
	List<House> destNodes = new ArrayList<>();
	
	for(int i = 0; i < outputGraph.size(); i++) {
		destNodes.add(new House(outputGraph.get(i).getDestination(),outputGraph.get(i).getDistance()));		
	}
	
	List<House> houseList  = getAllHouses();
	List<House> tempList = new ArrayList<>();
	
	for(House temp : destNodes) {
		for(int i = 0 ; i < houseList.size() ; i++) {
			if(temp.getId()==houseList.get(i).getId()) {
				tempList.add(new House(temp.getId(),temp.getDistance(),houseList.get(i).getPrice()));
			}
		}
	}
	
	if(tempList!=null) {
		tempList = quickSort(tempList, 0, tempList.size() - 1);
	}
	
	List<House> connectedHouseList = new ArrayList<>();
	connectedHouseList = getConnectedHouseInfo(tempList);
	
	List<House> topConnectedHouses = new ArrayList<>();
	
	int count = 1;
	
	for(int j = 0; j < connectedHouseList.size(); j++) {
		
		if(count<=16) {
			
			List<Graph> outputHouseGraph = shortestPaths(connectedHouseList.get(j).getId(),vertices,inputGraph);
			
			List<Place> placeNodes = new ArrayList<>();
			
			for(int i = 0; i < outputHouseGraph.size(); i++) {
				placeNodes.add(new Place(outputHouseGraph.get(i).getDestination(),outputHouseGraph.get(i).getDistance()));		
			}
			
			List<Place> placeList  = getAllPlaces();
			List<Place> tempPlaceList = new ArrayList<>();
			
			for(Place tempPlace : placeNodes) {
				for(int i = 0 ; i < placeList.size() ; i++) {
					if(tempPlace.getId()==placeList.get(i).getId()) {
						tempPlaceList.add(new Place(tempPlace.getId(),tempPlace.getDistance()));
					}
				}
			}
			
			if(tempPlaceList!=null) {
				tempPlaceList = quickSortPlaces(tempPlaceList, 0, tempPlaceList.size() - 1);
			}
			
			List<Place> connectedPlaces = new ArrayList<>();
			connectedPlaces = getConnectedPlaceInfo(tempPlaceList);
			
			
			boolean grouceryFound = false;
			boolean transportFound = false;
			String grocery = "";
			int groc_dist = 0;
			String transport = "";
			int trans_dist = 0;
			
			for(int k = 0; k < connectedPlaces.size(); k++) {
				if(!grouceryFound && (connectedPlaces.get(k).getType()).equalsIgnoreCase("grocery")) {
					grocery = connectedPlaces.get(k).getAddress();
					groc_dist = connectedPlaces.get(k).getDistance();	
					grouceryFound = true;
				}
				else if(!transportFound && (connectedPlaces.get(k).getType()).equalsIgnoreCase("transport")) {
					transport = connectedPlaces.get(k).getAddress();
					trans_dist = connectedPlaces.get(k).getDistance();
					transportFound = true;
				}
			}
			
			
		House house = new House(connectedHouseList.get(j).getId(), connectedHouseList.get(j).getType(), connectedHouseList.get(j).getAddress(), 
				connectedHouseList.get(j).getBath(), connectedHouseList.get(j).getBedrooms(), connectedHouseList.get(j).getHouseDp(),
				connectedHouseList.get(j).getDistance(), connectedHouseList.get(j).getPrice(),grocery,groc_dist,transport,trans_dist);

		topConnectedHouses.add(house);
	
		count++;
		}
		else {
			break;
		}
		
	}
	
	return topConnectedHouses;
}

}

	