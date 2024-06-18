<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>間取り設定</title>
<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
<link rel="stylesheet" type="text/css" href="/D2/css/icon.css">
</head>
<body>
 <form id="my_form" action="" method="post">
  <div class="hamburger-menu">
    <input type="checkbox" id="menu-btn-check">
    <label for="menu-btn-check" class="menu-btn"><span></span></label>
    <div class="menu-content">
      <ul>
        <li><a href="/D2/HomeServlet">ホーム</a></li>
        <li><a href="/D2/CalendarServlet">カレンダー</a></li>
        <li><a href="/D2/ParentsServlet">設定</a></li>
        <li><a href="/D2/IconServlet">間取り</a></li>
        <li><a href="">ログアウト</a></li>
      </ul>
    </div>
  </div>



    <!-- <form id="my_form" action="" method="post">
		<label></label>
		<input type="text" name="my_x1" value="/D2/icon/${e.icon}" >
		<input type="text" name="my_y1" id="my_y1">
		<label></label>
		<input type="text" name="my_x2" id="my_x2">
		<input type="text" name="my_y2" id="my_y2">
		<label></label>
		<input type="text" name="my_x3" id="my_x3">
		<input type="text" name="my_y3" id="my_y3">

		<input type="submit" name="my_save" value="保存">
        <input type="reset" name="my_reset" value="リセット">
	</form>-->

<!--  <form id="my_form" action="" method="post">
    <label for="my_x1">X1:</label>
    <input type="text" name="my_x1" id="my_x1">
    <label for="my_y1">Y1:</label>
    <input type="text" name="my_y1" id="my_y1"><br>

    <label for="my_x2">X2:</label>
    <input type="text" name="my_x2" id="my_x2">
    <label for="my_y2">Y2:</label>
    <input type="text" name="my_y2" id="my_y2"><br>

    <label for="my_x3">X3:</label>
    <input type="text" name="my_x3" id="my_x3">
    <label for="my_y3">Y3:</label>
    <input type="text" name="my_y3" id="my_y3"><br> -->



<div id='my_xy'>
  <c:if test="${empty cardList}">
    <p>カードリストは空です。</p>
</c:if>
<div class="madori">
    <img src="/D2/img/madori.png" >
  </div>
<div class="work">
    <c:forEach var="e" items="${cardList}" varStatus="s">
        <img id="my_xy${s.count}" class="controlXY" src="/D2/icon/${e.icon}">
    <input type="hidden" name="my_x${s.count}" id="my_x${s.count}">
    <input type="hidden" name="my_y${s.count}" id="my_y${s.count}">
    <input type="hidden" name="my_z${s.count}" value="${e.houseworkName}">
    </c:forEach>
</div>
<div class="action">
    <input type="submit" name="my_save" value="保存">
    <input type="reset" name="my_reset" value="リセット">
</div>
</div>

	<!-- <div id='my_xy'>
		<ul>
		    <li><img id="my_xy1" class="controlXY" src="/D2/icon/${e.icon}" ></li>
		</ul>
	</div> -->
</form>
</body>
<script>
//初期設定値をDBから読み込んで配列としてサーブレットから連携？（画像２が設定済の時の例）
//const xyArray = [{ x:1000, y:50 }, { x:1100, y:50 }, { x:1000, y:150 }, { x:1100, y:150 }, { x:1000, y:250 }, { x:1100, y:250 }];
const xyArray = [
<c:forEach var="e" items="${cardList}" varStatus="s">
{ x:${e.iconX}, y:${e.iconY} },
</c:forEach>
];
</script>

<script src="./script/controlXY.js"></script>


</html>