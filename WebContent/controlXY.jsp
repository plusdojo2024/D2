<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./style/controlXY.css" />

</head>
<body>

<main>
	<form id="my_form" action="" method="post">
		<label>画像１</label>
		<input type="text" name="my_x1" id="my_x1">
		<input type="text" name="my_y1" id="my_y1">
		<label>画像２</label>
		<input type="text" name="my_x2" id="my_x2">
		<input type="text" name="my_y2" id="my_y2">
		<label>画像３</label>
		<input type="text" name="my_x3" id="my_x3">
		<input type="text" name="my_y3" id="my_y3">
		<input type="submit" name="my_submit" value="ボタン">
	</form>
	<div id='my_xy'>
		<div>
		</div>
		<ul>
		    <li><img id="my_xy1" class="controlXY" src="https://www.seplus.jp/static/common/images/seplus-logo.gif"></li>
		    <li><img id="my_xy2" class="controlXY" src="https://www.seplus.jp/static/common/images/seplus-logo.gif"></li>
		    <li><img id="my_xy3" class="controlXY" src="https://www.seplus.jp/static/common/images/seplus-logo.gif"></li>
		</ul>
	</div>

</main>

<script src="./script/controlXY.js"></script>

</body>
</html>