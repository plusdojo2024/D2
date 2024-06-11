<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>設定</title>
	<meta charset="UTF-8">
	<title>保護者設定ページ</title>
	<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
	<link rel="stylesheet" type="text/css" href="/D2/css/parents.css">
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
		<h1>保護者設定ページ</h1>
		<p class = a>各種設定や登録を行えます。</p>
		
		<h2>報酬設定</h2>
		<p>報酬の表示に関する設定を行います。</p>
			<form id="reward_form" method="post" action="/D2/ParentsServlet">
			<div class = reward_setting>
				<table>
					<tr>
						<td>
							<label>報酬を表示
							<input type="radio" name="reword_display" value = "true" checked>する
							<input type="radio" name="reword_display" value = "false">しない
							</label>
						</td>
						<td>
							<label>☆
							<input type="text" name="reword_star">
							個で表示
							</label>
						</td>
					</tr>
					<tr>
						<td colspan = "2">
							<label>表示する文章
							<input type="text" name="reword_paragraph">
							</label>
							<br>
						</td>
					</tr>
				</table>
				<br>
				<div class = button>
					<input type="submit" id="regist" name="reword_submit" value="登録">
					<span id="error_message"></span><br>
				</div>
			</div>
			</form>
		
		
		<h2>コメント設定</h2>
		<p>毎日の「ありがとうコメント」を設定できます。</p>
		
			<form id="comment_form" method="post" action="/D2/ParentsServlet">
				<div class = comment_setting>
				<table>
					<tr>
						<td>
							<label>コメント
								<input type="text" name="reword_paragraph">
							</label>
						<td>
					</tr>
				</table>
				<br>
				<div class = button>
					<input type="submit" id="regist" name="comment_submit" value="登録">
					<input type="reset" name="reset" value="リセット">
					<span id="error_message"></span><br>
				</div>
				</div>
			</form>
		
		
		<h2>家事設定</h2>
		<p>家事に関する設定を行います。</p>
		
		
		<c:forEach var="e" items="" >
			<form id="housework_form" method="post" action="/D2/ParentsServlet">
				<div class = housework_setting>
				<div class = delete>
					<input id = delete type="submit" name="submit" value=削除>
				</div>
				<table>
					<tr>
						<td >
							家事の名前：
						</td>
					</tr>
					<tr>
						<td>
							<label>家事の内容
								<input type="text" name="housework_contents">
							</label>
						</td>
					</tr>
					<tr>
						<td>
							<label>難しさ
								<input type="text" name="housework_difficulty">
							</label>
						</td>
					</tr>
				</table>
				<br>
				<div class = button>
				<input type="submit" name="housework_submit" value="更新">
				<input type="reset" name="reset" value="リセット">
				</div>
				</div>
				</form>
			</c:forEach>
		
		<h2>こどもアカウント設定</h2>
		<p>子供のプロフィール設定を行います。</p>
		
			<form id="childprofilechildprofile_form" method="post" action="/D2/ParentsServlet">
				
				<div class = childprofile_setting>
				<h3>新規登録</h3>
				<table>
					<tr>
						<td>
							<label>プロフィール画像<br>
								<input type="file" name="child_image">
							</label>
						</td>
						<td>
							<label>なまえ
								<input type="text" name="child_name">
							</label>
						</td>
					</tr>
				</table>
				<br>
				<div class = button>
					<input type="submit" id="regist" name="childprofile_submit" value="登録">
					<input type="reset" name="reset" value="リセット">
					<span id="error_message"></span><br>
				</div>
				</div>
			</form>
			<br>
			<!-- アカウント情報更新 -->
			<c:forEach var="e" items="" >
			<form id="childprofile_form" method="post" action="/D2/ParentsServlet">
			<div class = childprofile_setting>
				<h3>アカウント情報更新</h3>
				
				<table>
					<tr>
						<td>
							<label>プロフィール画像<br>
								<input type="file" name="child_image">
							</label>
						</td>
						<td>
							<label>なまえ
								<input type="text" name="child_name">
							</label>
						</td>
					</tr>
				</table>
				<br>
				<div class = button>
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