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
			<input type="checkbox" id="menu-btn-check"> <label
				for="menu-btn-check" class="menu-btn"><span></span></label>
			<div class="menu-content">
				<ul>
					<li><img src="/D2/img/icon_home.png" alt="home icon"><a href="/D2/HomeServlet">ホーム</a></li>
					<li><img src="/D2/img/icon_calendar.png" alt="calendar icon"><a href="/D2/CalendarServlet">カレンダー</a></li>
					<li><img src="/D2/img/icon_setting.png" alt="setting icon"><a href="/D2/ParentsServlet">設定</a></li>
					<li><img src="/D2/img/icon_logout.png" alt="logout icon" ><a href="/D2/LogoutServlet" onclick="return confirmLogout();">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</header>

	<h1>おてつだいチェック</h1>
	<h2>おわらせたおてつだいをチェックしよう！</h2>

	<form id="my_form" action="" method="post"
		onsubmit="return confirmFinish();">
		<div id='my_xy'>
			<c:if test="${empty cardList}">
				<p></p>
			</c:if>
			<div class="madori">
				<img src="/D2/img/madori.png">
			</div>
			<div class="work">
				<nobr>
					<span class="icons"> <c:forEach var="e" items="${cardList}"
							varStatus="s">

							<label for="my_p${s.count}"> <img
								src="/D2/icon/${e.icon}" id="my_i${s.count}"
								style="width:120px; left:${e.iconX}px; top:${e.iconY}px; position: absolute;"
								onclick="makeTransparent(this)"> <input type="hidden"
								name="my_z${s.count}" value="${e.houseworkName}"> <input
								type="radio" name="my_p${s.count}" id="my_p${s.count}"
								style="width:120px; left:${e.iconX}px; top:${e.iconY}px; position: absolute; display: none;"
								value="true">
								<script>
    								var img = document.getElementById("my_i${s.count}");

   									if (${e.houseworkCheck} == true) {
       									 img.style.opacity = 0.5;
       									 img.onclick = null;
    								} else {
        								 img.style.opacity = 1.0;
    								}
								</script>
							</label>
						</c:forEach>
					</span>
				</nobr>
				<div class="action">
					<input type="submit" name="my_save" value="かくていする">
					<c:if test="${mw == true}">
						<a href="#" class="btn">おしてみてね！</a>
					</c:if>
				</div>
			</div>
		</div>
		<div class="overlay"></div>

		<!-- モーダルウィンドウ -->
		<div class="modal">
			<div class="close">×</div>
			<p class = "mwp"><c:out value="${ms}" /></p></div>
	</form>
</body>
<script src="./script/HouseWork.js"></script>
</html>
