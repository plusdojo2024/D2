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
    <input type="checkbox" id="menu-btn-check">
    <label for="menu-btn-check" class="menu-btn"><span></span></label>
    <div class="menu-content">
      <ul>
        <li><a href="/D2/HomeServlet">ホーム</a></li>
        <li><a href="/D2/CalendarServlet">カレンダー</a></li>
        <li><a href="/D2/LockServlet">設定</a></li>
        <li><a href="/D2/LogoutServlet">ログアウト</a></li>
      </ul>
    </div>
  </div>
	</header>
	<main>
		<div class = childprofile>
			<!--子供のプロフィール-->
			<c:forEach var="e" items="${userList}" varStatus="loop">
				<div class="child_acount" style="background-color:#FF8585;">
					<a href="/D2/HouseworkServlet">
					<form>
						<input id = colors  type="radio" name="color" value="#F7DDF1" onchange="changeBackgroundColor(this.value, ${loop.index})">ぴんく 
						<input id = colors type="radio" name="color" value="#D9F2D0" onchange="changeBackgroundColor(this.value, ${loop.index})"> みどり
						<input id = color type="radio" name="color" value="#C1E5F5" onchange="changeBackgroundColor(this.value, ${loop.index})">あお
					</form>
				<table>
					<tr>
						<td>
							<img src = "${e.childPicture}">
						</td>
						<td>
							<p class = child_name>${e.childName}</p>
						</td>
					</tr>
					<!--  
					<tr>
						<td>
							<p>ほしのかず</p>
							<p>こ</p>
						</td>
						<td>
						</td>
					</tr>
					-->
				</table>
				</a>
				</div>
			</c:forEach>
		</div>
		<br>
		<div class = housework>
			<p class = b>おてつだい</p>
			<c:forEach var = "e" items = "${houselist}">
			<p></p>
			</c:forEach>
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
</script>
	</script>
</body>
</html>