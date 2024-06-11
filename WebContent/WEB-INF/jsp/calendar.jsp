<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>カレンダー</title>
<link rel="stylesheet" type="text/css" href="/D2/css/calendar.css">
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
  <div class="calendar-wrap">
  <table class="calendar">
    <thead>
      <tr>
        <th class="sun">Sun</th>
        <th>Mon</th>
        <th>Tue</th>
        <th>Wed</th>
        <th>Thu</th>
        <th>Fri</th>
        <th class="sat">Sat</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td class="mute">29</td>
        <td class="mute">30</td>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
      </tr>
      <tr>
        <td>6</td>
        <td class="off">7</td>
        <td>8</td>
        <td>9</td>
        <td>10</td>
        <td>11</td>
        <td>12</td>
      </tr>
      <tr>
        <td class="today">13</td>
        <td class="off">14</td>
        <td>15</td>
        <td>16</td>
        <td>17</td>
        <td>18</td>
        <td>19</td>
      </tr>
      <tr>
        <td>20</td>
        <td class="off">21</td>
        <td>22</td>
        <td>23</td>
        <td>24</td>
        <td>25</td>
        <td>26</td>
      </tr>
      <tr>
        <td>27</td>
        <td class="off">28</td>
        <td>29</td>
        <td>30</td>
        <td>31</td>
        <td class="mute">1</td>
        <td class="mute">2</td>
      </tr>
    </tbody>
  </table>
  <table class="calendar">
    <thead>
      <tr>
        <th class="sun">Sun</th>
        <th>Mon</th>
        <th>Tue</th>
        <th>Wed</th>
        <th>Thu</th>
        <th>Fri</th>
        <th class="sat">Sat</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td class="mute">27</td>
        <td class="mute">28</td>
        <td class="mute">29</td>
        <td class="mute">30</td>
        <td class="mute">31</td>
        <td>1</td>
        <td>2</td>
      </tr>
      <tr>
        <td>3</td>
        <td class="off">4</td>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
        <td>9</td>
      </tr>
      <tr>
        <td class="today">10</td>
        <td class="off">11</td>
        <td>12</td>
        <td>13</td>
        <td>14</td>
        <td>15</td>
        <td>16</td>
      </tr>
      <tr>
        <td>17</td>
        <td class="off">18</td>
        <td>19</td>
        <td>20</td>
        <td>21</td>
        <td>22</td>
        <td>23</td>
      </tr>
      <tr>
        <td>24</td>
        <td class="off">25</td>
        <td>26</td>
        <td>27</td>
        <td>28</td>
        <td>29</td>
        <td>30</td>
      </tr>
    </tbody>
  </table>
 </div>
</body>
</html>