<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン | おてつだいアプリ</title>
<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
<link rel="stylesheet" type="text/css" href="/D2/css/login.css">
</head>
<body>
<div class="wrapper">
  <!-- メイン（ここから） -->
  <h1>おてつだいアプリ</h1>
  <form id="login_form" method="post" action="/D2/LoginServlet">
    <table>
      <tr>
        <td>
          <label>ID
          <input type="text" name="id">
          </label>
        </td>
      </tr>
      <tr>
        <td>
          <label>パスワード
          <input type="password" name="pw">
          </label>
        </td>
      <tr>
      <tr>
        <td colspan="2">
          <input type="submit" name="submit" value="ログイン">
          <input type="reset" name="reset" value="リセット">
          <span id="error_message"></span>
        <td>
      </tr>
    </table>
  </form>
  <!-- メイン（ここまで） -->
  <!-- フッター（ここから） -->
  <div id="footer">
    <p>&copy;D2</p>
  </div>
  <!-- フッター（ここまで） -->
</div>
<!-- JavaScript（ここから） -->
<script>
/* HTML要素をオブジェクトとして取得する */
let formObj = document.getElementById('login_form');
let errorMessageObj = document.getElementById('error_message');

/* [ログイン]ボタンをクリックしたときの処理 */
formObj.onsubmit = function() {
  if (!formObj.id.value || !formObj.pw.value) {
    errorMessageObj.textContent = '※IDとパスワードを入力してください！';
    return false;
  }
  errorMessageObj.textContent = null;
};

/* [リセット]ボタンをクリックしたときの処理 */
formObj.onreset = function() {
  errorMessageObj.textContent = null;
};
</script>
<!-- JavaScript（ここまで） -->
</body>
</html>