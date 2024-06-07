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
/*L.tileLayer('https://cyberjapandata.gsi.go.jp/xyz/std/{z}/{x}/{y}.png', {
	attribution: "<a href='https://maps.gsi.go.jp/development/ichiran.html' target='_blank'>地理院タイル</a>"
}).addTo(leafletMap);*/
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(leafletMap);

//ルート探索処理の関数
const leafletLine = function(startLatLng, goalLatLng, callbackRoutesfound) {
	const line = L.Routing.control({
		waypoints: [startLatLng, goalLatLng],
		draggableWaypoints: false,
		addWaypoints: false,
		lineOptions: { styles: [{ color: "red", weight: 8, opacity: 0.5 }] }
	}).on('routesfound', function(e) {
		callbackRoutesfound(e);
	});
	line.addTo(leafletMap);
	return line;
};

//すでに登録されているマーカーを表示（緯度経度はDBから読み込まれる？？）
L.marker([35.61946, 139.72091]).addTo(leafletMap);
L.marker([35.61917, 139.72155]).addTo(leafletMap);
//すでに登録されている2つのマーカーで経路を表示（緯度経度はDBから読み込まれる？？）
L.polyline([
	[35.61946, 139.72091],
	[35.61956, 139.72102],
	[35.61938, 139.72129],
	[35.61918, 139.72155],
	[35.61917, 139.72155],
],{ color: "blue", weight: 8, opacity: 0.5 }).addTo(leafletMap);

//2つのマーカーでルート探索結果を表示（レイヤー情報操作）
const layers = [];
leafletMap.on('click', function(e) {
	if (layers.length === 0) {
        // クリック位置にピンを立てる
        const mark = L.marker(e.latlng).addTo(leafletMap);
		//レイヤー情報に始点を追加
        layers.push(mark);
	} else if (layers.length === 1) {
        // クリック位置にピンを立てる
        const mark = L.marker(e.latlng).addTo(leafletMap);
		//レイヤー情報に終点を追加
        layers.push(mark);
        // ピンとピンのルートを表示する
        const startLatLng = layers[0].getLatLng();
        const goalLatLng = layers[1].getLatLng();
        const line = leafletLine(startLatLng, goalLatLng, function(e) {
			//ここで経路情報をHTML上のフォームに書き込む

			//始点のマーカー位置（緯度経度）
			const myLat1 = document.getElementById("my_lat1");
			myLat1.value = startLatLng.lat;
			const myLng1 = document.getElementById("my_lng1");
			myLng1.value = startLatLng.lng;

			//終点のマーカー位置（緯度経度）
			const myLat2 = document.getElementById("my_lat2");
			myLat2.value = goalLatLng.lat;
			const myLng2 = document.getElementById("my_lng2");
			myLng2.value = goalLatLng.lng;

			//始点～終点の経路（緯度経度の配列）
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

			//始点～終点の距離（実数）
			const totalDistance = e.routes[0].summary.totalDistance;
			const myDistance = document.getElementById("my_distance");
			myDistance.value = totalDistance;

			//開発者画面にログ出力
			console.log("移動距離は " + totalDistance + " mです。");
			console.log("移動経路は " + routeLatLng + " です。");

		});
		//レイヤー情報に経路を追加
        layers.push(line);
	} else {
		//マーカー削除
		leafletMap.removeLayer(layers[0]);
		leafletMap.removeLayer(layers[1]);
		//経路削除
		leafletMap.removeControl(layers[2]);
		//レイヤー情報をクリア
		layers.length = 0;
	}
});
