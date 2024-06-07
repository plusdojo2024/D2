//地図を作成して表示
const leafletMap = L.map('leaflet', {
    center: [35.62, 139.72],
    zoom: 13,
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
const leafletLine = function(startLat, startLng, goalLat, goalLng) {
	const line = L.Routing.control({
	    waypoints: [
	        L.latLng(startLat, startLng),
	        L.latLng(goalLat, goalLng)
	    ],
	    routeWhileDragging: false
	});
	line.addTo(leafletMap);
	return line;
};

//すでに登録されているマーカーを表示（緯度経度はDBから読み込まれる？？）
const marker1 = L.marker([35.63, 139.77]).addTo(leafletMap);
const marker2 = L.marker([35.64, 139.78]).addTo(leafletMap);

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
        const line = leafletLine(start.lat, start.lng, goal.lat, goal.lng).on('routesfound', function(e) {
			console.log("移動距離は " + e.routes[0].summary.totalDistance + " mです。");
		});
        layers.push(line);
	} else {
		leafletMap.removeLayer(layers[0]);
		leafletMap.removeLayer(layers[1]);
		leafletMap.removeControl(layers[2]);
		layers.length = 0;
	}
});
