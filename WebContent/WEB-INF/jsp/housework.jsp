<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>間取り</title>

<link rel="stylesheet" href="/D2/css/housework.css" />
</head>
<body>

    <div class="hamburger-menu">
      <input type="checkbox" id="menu-btn-check">
      <label for="menu-btn-check" class="menu-btn"><span></span></label>
      <div class="menu-content">
        <ul>
          <li><a href="">ホーム</a></li>
          <li><a href="">カレンダー</a></li>
          <li><a href="">設定</a></li>
          <li><a href="">ログアウト</a></li>
        </ul>
      </div>
    </div>
  </div>

<form id="my_form" action="" method="post">
		<label></label>
		<input type="text" name="my_x1" id="my_x1">
		<input type="text" name="my_y1" id="my_y1">
		<label></label>
		<input type="text" name="my_x2" id="my_x2">
		<input type="text" name="my_y2" id="my_y2">
		<label></label>
		<input type="text" name="my_x3" id="my_x3">
		<input type="text" name="my_y3" id="my_y3">
		<input type="submit" name="my_submit" value="リセット">
	</form>
	<div id='my_xy'>
		<div>
		<img src="./img/housework_back.png" style="width:100%;">
		</div>
		<ul>
		    <li><img id="my_xy1" class="controlXY" src="/D2/img/icon_gomi1.png" ></li>
		    <li><img id="my_xy2" class="controlXY" src="/D2/img/icon_gomi1.png" ></li>
		    <li><img id="my_xy3" class="controlXY" src="/D2/img/icon_gomi1.png" ></li>
		</ul>
	</div>




</body>

<script src="/D2/script/controlXY.js"></script>

</html>