<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${result.title}｜おてつだいアプリ</title>
<link rel = "stylesheet" href = "/D2/css/result.css">
</head>
<body>
<div class = result>
<p>${result.title}</p>
<p>${result.message}</p>
</div>
<br>
<div class = c >
<a class = back href="${result.backTo}">戻る</a>
</div>
</body>
</html>
