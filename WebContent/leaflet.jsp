<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
<link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine@3.2.12/dist/leaflet-routing-machine.css" />
<link rel="stylesheet" href="./style/leaflet.css" />
</head>
<body>
<main>
	<form id="my_form" action="" method="post">
		<label>ポイント１</label>
		<input type="text" name="my_lat1" id="my_lat1">
		<input type="text" name="my_lng1" id="my_lng1">
		<label>ポイント２</label>
		<input type="text" name="my_lat2" id="my_lat2">
		<input type="text" name="my_lng2" id="my_lng2">
		<label>経路</label>
		<input type="text" name="my_route" id="my_route">
		<label>距離</label>
		<input type="text" name="my_distance" id="my_distance">
		<input type="submit" name="my_submit" value="ボタン">
	</form>
	<div id="my_leaflet">ここに地図が表示される</div>
</main>

<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
<script src="https://unpkg.com/leaflet-routing-machine@3.2.12/dist/leaflet-routing-machine.js"></script>
<script src="./script/leaflet.js"></script>

</body>
</html>