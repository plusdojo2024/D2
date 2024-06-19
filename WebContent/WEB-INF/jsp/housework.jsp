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
</header>
<form id="my_form" action="" method="post" onsubmit="return confirmFinish();">
<div id='my_xy'>
  <c:if test="${empty cardList}">
    <p></p>
  </c:if>
  <div class="madori">
    <img src="/D2/img/madori.png" >
  </div>
  <div class="work">
    <c:forEach var="e" items="${cardList}" varStatus="s">
      <input type="image" src="/D2/icon/${e.icon}" name="my_x${s.count}" id="my_x${s.count}" value="${e.houseworkName}"
      style="width:120px; left:${e.iconX}px; top:${e.iconY}px; position: absolute;" onclick="makeImageInactive(this);" >
    </c:forEach>
  </div>
</div>
</form>
</body>
<style>
  .inactive {
    opacity: 0.5; /* 半透明にする */
    pointer-events: none; /* クリックを無効にする */
  }
</style>
<script>


function confirmFinish() {
    return confirm("お手伝いはおわりましたか？");
}
function makeImageInactive(image) {
    image.classList.add('inactive');
    image.onclick = null; // クリックイベントを削除してクリックを無効にする
  }

</script>
</html>
