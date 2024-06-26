<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ホーム｜おてつだいアプリ</title>
<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
	<link rel="stylesheet" type="text/css" href="/D2/css/home.css">
</head>
<body>
	<header>
		<div class="hamburger-menu">
			<input type="checkbox" id="menu-btn-check"> <label
				for="menu-btn-check" class="menu-btn"><span></span></label>
			<div class="menu-content">
				<ul>
					<li><img src="/D2/img/icon_home.png" alt="home icon"><a href="/D2/HomeServlet">ホーム</a></li>
					<li><img src="/D2/img/icon_calendar.png" alt="calendar icon"><a href="/D2/CalendarServlet">カレンダー</a></li>
					<li><img src="/D2/img/icon_setting.png" alt="setting icon"><a href="/D2/ParentsServlet">設定</a></li>
					<li><img src="/D2/img/icon_logout.png" alt="logout icon"><a href="/D2/LogoutServlet" onclick="return confirmLogout();">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</header>
	<main>
	<p class = p>アイコンをタップしておてつだいをはじめよう！</p>
		<div class = childprofile>
			<!--子供のプロフィール-->
			<c:forEach var="e" items="${userList}" varStatus="loop">
				<div class="child_acount" style="background-color:#FF8585;">
					<!-- <a href="/D2/HouseworkServlet?childName=${e.childName}"> --> <!-- JavaScriptで子供の名前をセッションに保存 -->
					<a href="/D2/HouseworkServlet?childName=${e.childName}&saveToSession=true">

					<form>
						<input id = colors  type="radio" name="color" value="#F7DDF1" onchange="changeBackgroundColor(this.value, ${loop.index})">ぴんく
						<input id = colors type="radio" name="color" value="#D9F2D0" onchange="changeBackgroundColor(this.value, ${loop.index})"> みどり
						<input id = colors type="radio" name="color" value="#C1E5F5" onchange="changeBackgroundColor(this.value, ${loop.index})">あお
					</form>
				<table>
					<tr>
						<td>
							<img src = "/D2/upload/${e.childPicture}" width="50" height="50" id=pfp>
						</td>
						<td>
							<p class = child_name>${e.childName}</p>
						</td>
					</tr>
				</table>
				</a>
				</div>
			</c:forEach>
		</div>
		<br>
		<div class = housework>
			<p class = b>おてつだい いちらん</p>
			<div >
				<table class = houseworkTable>
					<tr>
						
						<td colspan="2" class = title>おてつだい</td>
						<td class = title>&nbsp;&nbsp;やること</td>
						<td class = title>おわっているか</td>
					</tr>
					<c:forEach var="e" items="${houseList}" varStatus = "vs">
						<tr>
							<td class = work>${e.houseworkName}</td>
							<td>
							<c:if test = "${e.houseworkPoint == '3'}"><img src = "/D2/img/3star.png" width = 120 height = 27.3 class = star></c:if>
							<c:if test = "${e.houseworkPoint == '2'}"><img src = "/D2/img/2star.png" width = 120 height = 27.3 class = star></c:if>
							<c:if test = "${e.houseworkPoint == '1'}"><img src = "/D2/img/1star.png" width = 120 height = 27.3 class = star></c:if>
							</td>
							<td class = work>　${e.houseworkContents}</td>
							<td class = work><c:if test ="${e.houseworkCheck == 'true'}">✔</c:if><c:if test ="${e.houseworkCheck == 'false'}"></c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</main>
	<script>
		function changeBackgroundColor(color, index) {
			var childAccounts = document.querySelectorAll('.child_acount');
			childAccounts[index].style.backgroundColor = color;
			// 色をローカルストレージに保存
			localStorage.setItem('childAccountColor_' + index, color);
		}

		// ページ読み込み時にローカルストレージから色を復元
		document.addEventListener('DOMContentLoaded', function() {
			var childAccounts = document.querySelectorAll('.child_acount');
			childAccounts.forEach(function(childAccount, index) {
				var savedColor = localStorage.getItem('childAccountColor_' + index);
				if (savedColor) {
					childAccount.style.backgroundColor = savedColor;
				}
			});
		});


	        function confirmLogout() {
	            return confirm("ログアウトしますか？");
	        }

	</script>
</body>
</html>