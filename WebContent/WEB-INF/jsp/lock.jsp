<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>親のログイン</title>
<link rel="stylesheet" type="text/css" href="/D2/css/lock.css">
<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
</head>
<body>
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
  <main>
  <div class="wrapper">

  <!-- ヘッダー（ここから） -->

  <h1>保護者専用ページにログイン</h1>

  <!-- ヘッダー（ここまで） -->
  <!-- メイン（ここから） -->
  <h2><img src="/D2/img/big.png" width="20" height="20"alt="ビックリマーク">おうちのひとに、がめんをみせてね！</h2>
  <div class="box_css">
  <form id="login_form" method="post" action="/D2/LockServlet">
    <table>
      <tr>
        <td>
          <label><img src="/D2/img/key.png" width="25" height="25"alt="鍵">パスコード<br>
          <input type="password" name="pc">
          </label>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <input type="submit" name="submit" value="ログイン">
          <span id="error_message"></span>
        <td>
       </tr>
    </table>
    </form>
   </div>

  </div>
  </main>
</body>
  <script>
        function confirmLogout() {
            return confirm("ログアウトしますか？");
        }
    </script>
</html>