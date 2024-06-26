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
					<li><img src="/D2/img/icon_home.png" alt="home icon"><a href="/D2/HomeServlet">ホーム</a></li>
					<li><img src="/D2/img/icon_calendar.png" alt="calendar icon"><a href="/D2/CalendarServlet">カレンダー</a></li>
					<li><img src="/D2/img/icon_setting.png" alt="setting icon"><a href="/D2/ParentsServlet">設定</a></li>
					<li><img src="/D2/img/icon_logout.png" alt="logout icon"><a href="/D2/LogoutServlet" onclick="return confirmLogout();">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</header>
	<main>
		<h1>保護者設定ページ</h1>
		<p class=a>各種設定や登録を行えます。</p>

		<h2>コメント設定</h2>
		<p>毎日の「ありがとうコメント」を設定できます。</p>
		<span id="error_message"></span><br><br>

		<form id="comment_form" method="post" action="/D2/ParentsServlet">
			<div class=comment_setting>
			<input type="hidden" name="action" value="comment_regist">
				<table>
					<tr>
						<td>
						<label>今日の日付</label>
						<input type = "date"  name = date value="">
						</td>
					</tr>
					<tr>
						<td>
						<label>コメント <input type="text" name="comment"></label>
						<td>
					</tr>
				</table>
				<br>
				<div class=button>
					<input type="submit" id="regist" name="submit" value="登録">
					<input type="reset" name="reset" value="リセット">

				</div>
			</div>
		</form>


		<h2>家事設定</h2>
		<p>家事に関する設定を行います。</p>

		<h3>家事項目の詳細設定</h3>
		<p>家事の内容とむずかしさを変更できます。</p>

		<c:if test="${empty houseList}">
			<p>一致するデータはありません。</p>
		</c:if>

		<div class = children>
		<c:forEach var="e" items="${houseList}" varStatus = "vs">
			<form id="housework_form" method="post" action="/D2/ParentsServlet">
			<input type="hidden" name="action" value="housework_regist">
				<div class=housework_setting>
					<div class=delete>
						<input id=delete type="submit" name="submit" value=削除>
					</div>
					<table>
						<tr>
							<td>家事の名前
								<input type="text" name="houseworkName" value="${e.houseworkName}" readonly>
							</td>
						</tr>
						<tr>
							<td><label>家事の内容
							<input type="text" name="houseworkContents" value="${e.houseworkContents}">
							</label></td>
						</tr>
						<tr>
							<td><label>むずかしさ</label>
								<nobr>
									<span class="stars">
										<input id="dufficulty3${vs.index}" type="radio" name="houseworkPoint" value="3" <c:if test = "${e.houseworkPoint == '3'}">checked</c:if>>
										<label for="dufficulty3${vs.index}">★</label>
										<input id="dufficulty2${vs.index}" type="radio" name="houseworkPoint" value="2" <c:if test = "${e.houseworkPoint == '2'}">checked</c:if>>
										<label for="dufficulty2${vs.index}">★</label>
										<input id="dufficulty1${vs.index}" type="radio" name="houseworkPoint" value="1" <c:if test = "${e.houseworkPoint == '1'}">checked</c:if>>
										<label for="dufficulty1${vs.index}">★</label>
									</span>
								</nobr>
							</td>
						</tr>
					</table>
					<div class=button>
						<input type="submit" name="submit" value="更新">
						<input type="reset" name="reset" value="リセット">
					</div>
				</div>
			</form>
			<br>
		</c:forEach>
		</div>

		<h3>間取り設定</h3>
		<p>下記のボタンから間取り設定画面に遷移します</p>

		<div class = icon>
		<a class = iconURL href = "/D2/IconServlet">間取り設定画面に移動する</a>
		</div>
		<br>
		<br>
		<h2>こどもアカウント設定</h2>
		<p>子供のプロフィール設定を行います。</p>

		<form id="childprofile_form" method="post" action="/D2/ParentsServlet" enctype = "multipart/form-data">
			<h3>新規登録</h3>
			<span id="error_message2"></span><br><br>
			<input type="hidden" name="action" value="child_regist">
			<div class=childprofile_setting>
			<input type="hidden" name="action" value="child_regist">
			<label>登録番号</label><input type="text" name="childId" value="自動採番" readonly="readonly" style="background-color: lightyellow">
				<table>
					<tr>
						<td>
							<label>プロフィール画像<br>
								<input type="file" name="childPicture">
							</label>
						</td>
						<td>
							<label>なまえ
								<input type="text" name="childName">
							</label>
						</td>
					</tr>
					<tr>
						<td>
							<br>
							<label>報酬を表示
								<input type="radio" name="rewardUmu" value="true" checked>する
								<input type="radio" name="rewardUmu" value="false">しない
							</label>
						</td>
						<td>
							<br>
							<label>☆
								<input type="text"name="rewardJouken"> 個で表示
							</label>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<label>表示する文章
								<input type="text" name="rewardText">
							</label>
							<br>
						</td>
					</tr>
				</table>
				<br>
				<div class=button>
					<input type="submit" id="regist" name="submit" value="登録">
					<input type="reset" name="reset" value="リセット">
					<span id="error_message"></span>
					<br>
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
			<form id="childprofile_form2" method="post"action="/D2/ParentsServlet" enctype = "multipart/form-data">
				<div class=childprofile_setting_2>
				<input type="hidden" name="action" value="child_update">
					<label>登録番号</label><input type="text" name="childId" value="${e.childId}" readonly>
					<table>
						<tr>
							<td>
								<label>プロフィール画像<br>
									<input type="file" name="childPicture" value="${e.childPicture}">
								</label>
							</td>
							<td>
								<label>なまえ
									<input type="text" name="childName" value="${e.childName}">
								</label>
							</td>
						</tr>
						<tr>
							<td><br> <label>報酬を表示
								<input type="radio" name="rewardUmu" value="true"<c:if test = "${e.rewardUmu== true}">checked</c:if>>する
								<input type="radio" name="rewardUmu" value="false"<c:if test = "${e.rewardUmu ==false}">checked</c:if>>しない
							</label></td>
							<td>
							<br>
								<label>☆
									<input type="text" name="rewardJouken" value="${e.rewardJouken}"> 個で表示
								</label>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label>表示する文章
									<input type="text" name="rewardText" value="${e.rewardText}">
								</label>
							<br>
							</td>
						</tr>
					</table>
					<br>
					<div class=button>
						<input type="submit" id="regist" name="submit" value="更新">
						<input type="submit" name="submit" value="削除">
					</div>
				</div>
			</form>
		</c:forEach>
	</main>
	<script>
	    'use strict';
	    window.onload = function(){
	        var getToday = new Date();
	        var y = getToday.getFullYear();
	        var m = getToday.getMonth() + 1;
	        var d = getToday.getDate();
	        var today = y + "-" + m.toString().padStart(2,'0') + "-" + d.toString().padStart(2,'0');
	        document.getElementById("datepicker").setAttribute("value",today);
	    }

	    /*子供登録の処理*/
	    let formObj = document.getElementById('childprofile_form');
	    let errorMessageObj = document.getElementById('error_message2');

	    /* [実行]ボタンをクリックしたときの処理 */
	    formObj.onsubmit = function() {
	        /* 氏名を必須入力項目とします */
	        if (!formObj.childName.value) {
	            errorMessageObj.textContent = '※なまえは必ず入力してください！';
	            return false;
	        }

	        if (!window.confirm('この内容で登録します。よろしいですか？')) {
	            window.alert('登録画面に戻ります。');
	            return false;
	        }

	        errorMessageObj.textContent = null;
	    };

	 // コメント登録フォームのJavaScript処理
	    let commentFormObj = document.getElementById('comment_form');  // コメントフォームを取得
	    let commentErrorMessageObj = document.getElementById('error_message');  // エラーメッセージ表示領域を取得

	    commentFormObj.onsubmit = function() {
	    	if(!commentFormObj.date.value && !commentFormObj.comment.value){
	    		commentErrorMessageObj.textContent = '※コメントと日付は必ず入力してください！';
	            return false;
	    	} else if (!commentFormObj.date.value) {  // 日付が入力されていない場合
	            commentErrorMessageObj.textContent = '※日付は必ず入力してください！';
	            return false;  // フォームの送信を中止
	        } else if (!commentFormObj.comment.value) {  // コメントが入力されていない場合
	            commentErrorMessageObj.textContent = '※コメントは必ず入力してください！';
	            return false;  // フォームの送信を中止
	        }

	        // 確認メッセージを表示し、キャンセルされた場合は送信を中止
	        if (!window.confirm('この内容で登録します。よろしいですか？')) {
	            window.alert('登録画面に戻ります。');
	            return false;  // フォームの送信を中止
	        }

	        commentErrorMessageObj.textContent = null;  // エラーメッセージをリセット
	    };


        function confirmLogout() {
            return confirm("ログアウトしますか？");
        }


	</script>
</body>
</html>