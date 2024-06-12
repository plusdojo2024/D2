<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<c:forEach >
			<div class = child_acount>
			<a href="/D2/HouseworkServlet">
				<table>
					<tr>
						<td>
							<img src = /D2/img/icon_setting.png width = 60px height = 60px>
						</td>
						<td>
							<p class = child_name>なまえ</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>ほしのかず</p>
						</td>
						<td>
						</td>
					</tr>
				</table>
				</a>
				</div>
			</c:forEach>
		</div>
		<br>
		<div class = housework>
			<p class = b>おてつだい</p>
			<c:forEach>
			</c:forEach>
		</div>
			
	</main>
</body>
</html>