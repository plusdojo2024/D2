<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>保護者設定ページ｜おてつだいアプリ</title>
<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
<link rel="stylesheet" type="text/css" href="/D2/css/parents.css">
</head>
<body>

	<header>
		<div class="hamburger-menu">
			<input type="checkbox" id="menu-btn-check"> <label
				for="menu-btn-check" class="menu-btn"><span></span></label>
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
		<h1>保護者設定ページ</h1>
		<p class=a>各種設定や登録を行えます。</p>

		<h2>コメント設定</h2>
		<p>毎日の「ありがとうコメント」を設定できます。</p>

		<form id="comment_form" method="post" action="/D2/ParentsServlet">
			<div class=comment_setting>
				<table>
					<tr>
						<td><label>コメント <input type="text"
								name="reword_paragraph">
						</label>
						<td>
					</tr>
				</table>
				<br>
				<div class=button>
					<input type="submit" id="regist" name="comment_submit" value="登録">
					<input type="reset" name="reset" value="リセット"> <span
						id="error_message"></span><br>
				</div>
			</div>
		</form>


		<h2>家事設定</h2>
		<p>家事に関する設定を行います。</p>
		
		<c:if test="${empty houseList}">
			<p>一致するデータはありません。</p>
		</c:if>
		
		<c:forEach var="e" items="${houseList}">
			<form id="housework_form" method="post" action="/D2/ParentsServlet">
				<div class=housework_setting>
					<div class=delete>
						<input id=delete type="submit" name="submit" value=削除>
					</div>
					<table>
						<tr>
							<td>家事の名前：</td>
						</tr>
						<tr>
							<td><label>家事の内容 <input type="text"
									name="housework_contents" value="${e.houseworkContents}">
							</label></td>
						</tr>
						<tr>
							<td><label>重要度 </label> 
								<nobr>
									<span class="stars"> 
										<input id="3" type="radio" name="dufficulty" value="3" <c:if test = "${e.houseworkPoint == '3'}">checked</c:if>><label for="3">★</label> 
										<input id="2" type="radio" name="dufficulty" value="2" <c:if test = "${e.houseworkPoint == '2'}">checked</c:if>><label for="2">★</label>
										<input id="1" type="radio" name="dufficulty" value="1" <c:if test = "${e.houseworkPoint == '1'}">checked</c:if>><label for="1">★</label>
									</span>
								</nobr>
							</td>
						</tr>
					</table>
					<div class=button>
						<input type="submit" name="housework_submit" value="更新"> <input
							type="reset" name="reset" value="リセット">
					</div>
				</div>
			</form>
		</c:forEach>

		<h2>こどもアカウント設定</h2>
		<p>子供のプロフィール設定を行います。</p>

		<p>${result.message}</p>

		<form id="childprofilechildprofile_form" method="post"
			action="/D2/ParentsServlet">
			<h3>新規登録</h3>
			<div class=childprofile_setting>
				<table>
					<tr>
						<td><label>プロフィール画像<br> <input type="file"
								name="child_image">
						</label></td>
						<td><label>なまえ <input type="text" name="child_name">
						</label></td>
					</tr>
					<tr>
						<td><br> <label>報酬を表示 <input type="radio"
								name="reword_display" value="true" checked>する <input
								type="radio" name="reword_display" value="false">しない
						</label></td>
						<td><br> <label>☆ <input type="text"
								name="reword_star"> 個で表示
						</label></td>
					</tr>
					<tr>
						<td colspan="2"><label>表示する文章 <input type="text"
								name="reword_paragraph">
						</label> <br></td>
					</tr>
				</table>
				<br>
				<div class=button>
					<input type="submit" id="regist" name="childprofile_submit"
						value="登録"> <input type="reset" name="reset" value="リセット">
					<span id="error_message"></span><br>
				</div>
			</div>
		</form>
		<br>

		<!-- アカウント情報更新 -->


		<h3>アカウント情報更新</h3>
		<p>登録済みアカウントの情報更新ができます。</p>
		<c:if test="${empty userList}">
			<p>一致するデータはありません。</p>
		</c:if>

		<c:forEach var="e" items="${userList}">
			<form id="childprofile_form" method="post"
				action="/D2/ParentsServlet">
				<div class=childprofile_setting>
					<table>
						<tr>
							<td><label>プロフィール画像<br> <input type="file"
									name="child_image" value="${e.childPicture}">
							</label></td>
							<td><label>なまえ <input type="text" name="child_name"
									value="${e.childName}">
							</label></td>
						</tr>
						<tr>
							<td><br> <label>報酬を表示 <input type="radio"
									name="reword_display" value="${e.rewardText}">する <input
									type="radio" name="reword_display" value="${e.rewardText}">しない
							</label></td>
							<td><br> <label>☆ <input type="text"
									name="reword_star" value="${e.rewardJouken}"> 個で表示
							</label></td>
						</tr>
						<tr>
							<td colspan="2"><label>表示する文章 <input type="text"
									name="reword_paragraph" value="${e.rewardText}">
							</label> <br></td>
						</tr>
					</table>
					<br>
					<div class=button>
						<input type="submit" id="regist" name="comment_submit" value="更新">
						<input type="submit" name="childprofile_delete" value="削除">
						<span id="error_message"></span><br>
					</div>
				</div>
			</form>
		</c:forEach>
	</main>
</body>
</html>