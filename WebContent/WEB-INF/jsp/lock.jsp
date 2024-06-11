<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>親のログイン</title>
</head>
<body>
<div class="wrapper">

  <!-- ヘッダー（ここから） -->
  <h1>
  保護者専用ページにログイン
  </h1>

  <!-- ヘッダー（ここまで） -->
  <!-- メイン（ここから） -->
  <h2>おうちのひとに、がめんをみせてね！</h2>
    <table>
      <tr>
        <td>
          <label>パスワード<br>
          <input type="password" name="pw">
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
<script>
'use strict';

const now = new Date();
const year = now.getFullYear();
const month = now.getMonth();
const date = now.getDate();
const hour = now.getHours();
const min = now.getMinutes();

const output = `${year}/${month + 1}/${date} ${hour}:${min}`;
document.getElementById('time').textContent = output;
</script>
</body>
</html>