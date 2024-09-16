<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.*"%>
<%@ page language="java" contentType="text/html;
charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>HomeQuest | Homepage</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="js/homeQuest.js"></script>
<link rel="stylesheet" href="css/global.css" />
<link rel="stylesheet" href="css/home.css" />
<link rel="icon" href="images/quest_logo.jpg" type="image/x-icon" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine" />
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
</head>
<body>
	<div class="wrapper">
		<div class="nav-container"
			style="background-image: url('images/quest_cover.jpg'); background-size: cover; background-repeat: no-repeat;">
			<div class="logo">
				<img class="logo-image" src="images/quest_logo.jpg" />
				<h1 class="logo-text" style="font-family: Tangerine; color: white">
					HouseQuest</h1>
			</div>
			<div class="search-section">
				<div class="input-box">
					<select id="dropdown" name="combo" required>
						<option class="options" value="" disabled selected>Select
							your destination</option>
						<option class="options" value="52">Roux Institute</option>
						<option class="options" value="33">University of Southern
							Maine</option>
						<option class="options" value="25">Bank of America</option>
						<option class="options" value="26">JP Morgan and Chase</option>
						<option class="options" value="27">Maine Medical Center</option>
						<option class="options" value="43">New England Rehabilitation</option>
					</select>
				</div>
				<div class="search-button">
					<div class="search-btn" onclick="getHouses()">Search</div>
				</div>
			</div>
		</div>
		<div class="content">
		<div class="sorting" id="sorting" style="display:none">
		<div class="sort" onclick="sort(true)">Lowest Price</div>
		<div class="sort" onclick="sort(false)">Highest Price</div>
		</div>
		<div id="content-header">Begin your Quest now!</div>
			<div id="grid" class="grid">										
				</div>
			</div>
		</div>
</body>
</html>
