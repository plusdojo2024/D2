<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<h1>保護者設定ページ</h1>
		<p class = a>各種設定や登録を行えます。</p>

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
							家事の名前：$
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
							<nobr>
							<label>重要度
							<div id="somestars">
									 <span class="star" data-star="1">☆</span>
									 <span class="star" data-star="2">☆</span>
									 <span class="star" data-star="3">☆</span>
							</div>
							 	<input type="hidden" name="stars" id="star-value">
							 <!-- <input type="text" name="stars"> -->
							 </label>
							 </nobr>
						</td>
					</tr>
				</table>
				<div class = button>
				<input type="submit" name="housework_submit" value="更新">
				<input type="reset" name="reset" value="リセット">
				</div>
				</div>
				</form>
			</c:forEach>

		<h2>こどもアカウント設定</h2>
		<p>子供のプロフィール設定を行います。</p>
		
		<p>${result.message}</p>

			<form id="childprofilechildprofile_form" method="post" action="/D2/ParentsServlet">
				<h3>新規登録</h3>
				<div class = childprofile_setting>
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
					<tr>
						<td>
						<br>
							<label>報酬を表示
							<input type="radio" name="reword_display" value = "true" checked>する
							<input type="radio" name="reword_display" value = "false">しない
							</label>
						</td>
						<td>
						<br>
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
					<input type="submit" id="regist" name="childprofile_submit" value="登録">
					<input type="reset" name="reset" value="リセット">
					<span id="error_message"></span><br>
				</div>
				</div>
			</form>
			<br>
			<!-- アカウント情報更新 -->
			<h3>アカウント情報更新</h3>
			<p>登録済みアカウントの情報更新ができます。</p>
			<c:forEach var="e" items="" >
			<form id="childprofile_form" method="post" action="/D2/ParentsServlet">
			<div class = childprofile_setting>
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
					<tr>
						<td>
						<br>
							<label>報酬を表示
							<input type="radio" name="reword_display" value = "true" checked>する
							<input type="radio" name="reword_display" value = "false">しない
							</label>
						</td>
						<td>
						<br>
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
					<input type="submit" id="regist" name="comment_submit" value="更新">
					<input type="submit" name="childprofile_delete" value="削除">
					<span id="error_message"></span><br>
				</div>
				</div>
			</form>
			</c:forEach>
	</main>
	<script>
	const somestars = document.getElementsByClassName('star');
	let fixedStars = 0;
	// 固定された星の数を保持する変数
	const starValueInput = document.getElementById('star-value');

	// 星マークにマウスオーバーした時のイベント
	const starMouseover = (e) => {
	    const index = Number(e.target.getAttribute('data-star'));
	    for (let j = 0; j < somestars.length; j++) {
	        somestars[j].textContent = j < index ? '★' : '☆';
	    }
	}

	// 星マークからマウスが離れた時のイベント
	const starMouseout = (e) => {
	    for (let j = 0; j < somestars.length; j++) {
	        somestars[j].textContent = j < fixedStars ? '★' : '☆';
	    }
	}

	// 星マークをクリックした時のイベント
	const starClick = (e) => {
	    fixedStars = Number(e.target.getAttribute('data-star'));
	    starValueInput.value = fixedStars; // hidden inputの値を更新
	    for (let j = 0; j < somestars.length; j++) {
	        somestars[j].textContent = j < fixedStars ? '★' : '☆';
	    }
	}

	for (let i = 0; i < somestars.length; i++) {
	    somestars[i].addEventListener('mouseover', starMouseover);
	    somestars[i].addEventListener('mouseout', starMouseout);
	    somestars[i].addEventListener('click', starClick);
	}
	</script>
</body>
</html>