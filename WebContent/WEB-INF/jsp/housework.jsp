<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>間取り</title>
<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
<link rel="stylesheet" type="text/css" href="/D2/css/housework.css">
</head>
<body>
 <!-- ヘッダー（ここから） -->
<header>
  <div class="hamburger-menu">
    <input type="checkbox" id="menu-btn-check">
    <label for="menu-btn-check" class="menu-btn"><span></span></label>
    <div class="menu-content">
      <ul>
        <li><a href="/D2/HomeServlet">ホーム</a></li>
        <li><a href="/D2/CalendarServlet">カレンダー</a></li>
        <li><a href="/D2/ParentsServlet">設定</a></li>
        <li><a href="D2/LogoutServlet">ログアウト</a></li>
      </ul>
    </div>
  </div>
  <div class="haikei">
    <img src="/D2/img/background.png" >
  </div>
  <div class="madori">
    <img src="/D2/img/madori.png" >
  </div>
<div id='my_xy'>
  <c:if test="${empty cardList}">
    <p>カードリストは空です。</p>
</c:if>

<div class="work">
    <c:forEach var="e" items="${cardList}">
        <img src="/D2/icon/${e.icon}">
    </c:forEach>
</div>
</div>
</header>
<!-- ヘッダー（ここまで） -->
</body>
<script src="/D2/script/controlXY.js"></script>
</html>