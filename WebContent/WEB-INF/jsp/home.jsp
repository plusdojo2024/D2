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
        <li><a href="">ホーム</a></li>
        <li><a href="">カレンダー</a></li>
        <li><a href="">設定</a></li>
        <li><a href="">ログアウト</a></li>
      </ul>
    </div>
  </div>
	</header>
	<main>
		<div class = childprofile>
			<!--子供のプロフィール-->
			<c:forEach>
				<div class = child_acount>
				<table>
					<tr>
						<td>
							<img src = /D2/img/icon_setting.png width = 60px height = 60px>
						</td>
						<td>
							<p>なまえ</p>
						</td>
					</tr>
					<tr>
						<td colspan = "2">
							<p>ほしのかず</p>
						</td>
					</tr>
				</table>
				</div>
			</c:forEach>
		</div>
		<br>
		<div class = housework>
			<p>おてつだい</p>
			<c:forEach>
			<p>残りの手伝い</p>
			</c:forEach>
		</div>
			
	</main>
</body>
</html>