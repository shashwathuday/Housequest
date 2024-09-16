var houseData = [];

/** AJAX calls */

function getHouses() {

	$("#grid").empty();

	var e = document.getElementById("dropdown");
	var destId = e.value;
	console.log(destId);
	$.ajax({
		url: `/HouseQuest/HouseServlet?destId=${destId}`,
		method: "GET",
		dataType: "json",
		success: function(data) {
			console.log(data);
			houseData = data;
			if (houseData.length) {
				document.getElementById("content-header").innerHTML = "Search Results";
				$("#sorting").show();
			}
			updateResults();
		},
		error: function(error) {
			console.error("Error fetching houses:", error);
		},
	});
}

function sort(isAsc) {
	if (isAsc) {
		houseData.sort(function(a, b) { return a.price - b.price });
	}
	else {
		houseData.sort(function(a, b) { return b.price - a.price })
	}
	$("#grid").empty();
	updateResults();
}

function updateResults() {
	let houseList = document.getElementById("grid");
	for (let i = 0; i < houseData.length; i++) {
		houseList.innerHTML += `<div class="house-grid" id="house-grid">
							<div class="house-tile">
							<img class="house-picture" src=" ${houseData[i].houseDp}" />
							<div class="house-info">
							<div class="house-price">\$${houseData[i].price}/mo</div>
							<div class="house-bbd">${houseData[i].bedrooms} bds | ${houseData[i].bath} ba | ${houseData[i].distance} mi</div>
							<div class="house-address">${houseData[i].address}</div>
							<div class="house-amen">${houseData[i].grocery} ${houseData[i].groc_dist} mi | ${houseData[i].transport} ${houseData[i].trans_dist} mi</div>
							</div>
							</div>
						</div>`;
	}
}
