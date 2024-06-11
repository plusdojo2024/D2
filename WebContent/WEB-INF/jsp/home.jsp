<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ホーム｜おてつだいアプリ</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		<nav>
			<ul>
				<li><a href="/D2/MenuServlet">ホーム</a></li>
				<li><a href="/D2/SearchServlet">カレンダー</a></li>
				<li><a href="/D2/RegistServlet">設定</a></li>
				<li><a href="/D2/LogoutServlet">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<div class = childprofile>
			<p>子供のプロフィール</p>
			<c:forEach>
			
			</c:forEach>
		</div>
		
		<div class = housework>
			<p>おてつだい</p>
			<c:forEach>
			
			</c:forEach>
		</div>
			
	</main>
</body>
</html>