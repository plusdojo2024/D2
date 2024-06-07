//地図を作成して表示
const leafletMap = L.map('my_leaflet', {
    center: [35.62, 139.72],
    zoom: 18,
    crs: L.CRS.EPSG3857,
    attributionControl: false,
    contextmenu: false,
    contextmenuWidth: 0
});

//地図にタイルを配置
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(leafletMap);

//ルートr探索処理の関数
const leafletLine = function(startLat, startLng, goalLat, goalLng, callbackRroutesfound) {
	const line = L.Routing.control({
	    waypoints: [
	        L.latLng(startLat, startLng),
	        L.latLng(goalLat, goalLng)
	    ],
	    routeWhileDragging: false
	}).on('routesfound', function(e) {
		callbackRroutesfound(e);
	});
	line.addTo(leafletMap);
	return line;
};

//すでに登録されているマーカーを表示（緯度経度はDBから読み込まれる？？）
L.marker([35.61946, 139.72091]).addTo(leafletMap);
L.marker([35.61917, 139.72155]).addTo(leafletMap);
//すでに登録されている2つのマーカーでルートを表示（緯度経度はDBから読み込まれる？？）
L.polyline([
	[35.61946, 139.72091],
	[35.61956, 139.72102],
	[35.61938, 139.72129],
	[35.61918, 139.72155],
	[35.61917, 139.72155],
],{ "color": "blue", "weight": 10, "opacity": 0.5}).addTo(leafletMap);

//2つのマーカーでルート探索結果を表示
const layers = [];
leafletMap.on('click', function(e) {
	if (layers.length === 0) {
        // クリック位置にピンを立てる
        const mark = L.marker(e.latlng).addTo(leafletMap);
        layers.push(mark);
	} else if (layers.length === 1) {
        // クリック位置にピンを立てる
        const mark = L.marker(e.latlng).addTo(leafletMap);
        layers.push(mark);
        // ピンとピンのルートを表示する
        const start = layers[0].getLatLng();
        const goal = layers[1].getLatLng();
        const line = leafletLine(start.lat, start.lng, goal.lat, goal.lng, function(e) {
			//ここで経路情報をHTML上のフォームに書き込む

			const myLat1 = document.getElementById("my_lat1");
			myLat1.value = start.lat;
			const myLng1 = document.getElementById("my_lng1");
			myLng1.value = start.lng;

			const myLat2 = document.getElementById("my_lat2");
			myLat2.value = goal.lat;
			const myLng2 = document.getElementById("my_lng2");
			myLng2.value = goal.lng;

			const routeLatLng = e.routes[0].coordinates;
			const myRoute = document.getElementById("my_route");
			//データベースに格納しやすいように単純な配列に変換してみる
			const arrayPoint = [];
			for (let i = 0; i < routeLatLng.length; i++) {
				const latLng = routeLatLng[i];
				const point = [latLng.lat, latLng.lng];
				arrayPoint.push(point);
			}
			myRoute.value = arrayPoint.join(" - ");//緯度経度の組み合わせが後でわかるように区切り方を工夫しないといけない

			const totalDistance = e.routes[0].summary.totalDistance;
			const myDistance = document.getElementById("my_distance");
			myDistance.value = totalDistance;

			console.log("移動距離は " + totalDistance + " mです。");
			console.log("移動経路は " + routeLatLng + " です。");

		});
        layers.push(line);
	} else {
		leafletMap.removeLayer(layers[0]);
		leafletMap.removeLayer(layers[1]);
		leafletMap.removeControl(layers[2]);
		layers.length = 0;
	}
});
