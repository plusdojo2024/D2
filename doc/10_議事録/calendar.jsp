<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>カレンダー</title>
<link rel="stylesheet" type="text/css" href="/D2/css/calendar.css">
<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
</head>
<body>
<h1>カレンダー</h1>
<div class="hamburger-menu">
    <input type="checkbox" id="menu-btn-check">
    <label for="menu-btn-check" class="menu-btn"><span></span></label>
    <div class="menu-content">
      <ul>
        <li><a href="/D2/HomeServlet">ホーム</a></li>
        <li><a href="/D2/CalendarServlet">カレンダー</a></li>
        <li><a href="/D2/LockServlet">設定</a></li>
        <li><a href="/D2/LogoutServlet">ログアウト</a></li>
      </ul>
    </div>
  </div>
  <div class="container-calendar">
          <h4 id="monthAndYear"></h4>
          <div class="button-container-calendar">
              <button id="previous" onclick="previous()">‹</button>
              <button id="next" onclick="next()">›</button>
          </div>

          <table class="table-calendar" id="calendar" data-lang="ja">
              <thead id="thead-month"></thead>
              <tbody id="calendar-body"></tbody>
          </table>

          <div class="footer-container-calendar">
              <label for="month">日付指定：</label>
              <select id="month" onchange="jump()">
                  <option value=0>1月</option>
                  <option value=1>2月</option>
                  <option value=2>3月</option>
                  <option value=3>4月</option>
                  <option value=4>5月</option>
                  <option value=5>6月</option>
                  <option value=6>7月</option>
                  <option value=7>8月</option>
                  <option value=8>9月</option>
                  <option value=9>10月</option>
                  <option value=10>11月</option>
                  <option value=11>12月</option>
              </select>
              <select id="year" onchange="jump()"></select>
          </div>
    </div>

    <c:if test="${empty commentList}">
    <p></p>
</c:if>

    <c:forEach var="e" items="${commentList}">
        <div class="comment">
            <div class="commentDay">

                    <form class="form" method="post" action="/D2/ParentsServlet" onsubmit="return confirmDelete();">
                        <div class="data-row">
                            <label>日付</label>
                            <input type="text" name="date" value="${e.date}" readonly="readonly">
                        </div>
                        <div class="data-row">
                            <label>コメント</label>
                            <input type="text" name="comment" value="${e.comment}" readonly="readonly">
                        </div>

                    </form>

            </div>
        </div>
    </c:forEach>


    <script>
    function generate_year_range(start, end) {
    	  var years = "";
    	  for (var year = start; year <= end; year++) {
    	      years += "<option value='" + year + "'>" + year + "</option>";
    	  }
    	  return years;
    	}

    	var today = new Date();
    	var currentMonth = today.getMonth();
    	var currentYear = today.getFullYear();
    	var selectYear = document.getElementById("year");
    	var selectMonth = document.getElementById("month");

    	var createYear = generate_year_range(1970, 2200);

    	document.getElementById("year").innerHTML = createYear;

    	var calendar = document.getElementById("calendar");
    	var lang = calendar.getAttribute('data-lang');

    	var months = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
    	var days = ["にちようび", "げつようび", "かようび", "すようび", "もくようび", "きんようび", "どようび"];

    	var dayHeader = "<tr>";
    	for (day in days) {
    	  dayHeader += "<th data-days='" + days[day] + "'>" + days[day] + "</th>";
    	}
    	dayHeader += "</tr>";

    	document.getElementById("thead-month").innerHTML = dayHeader;

    	monthAndYear = document.getElementById("monthAndYear");
    	showCalendar(currentMonth, currentYear);

    	function next() {
    	  currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    	  currentMonth = (currentMonth + 1) % 12;
    	  showCalendar(currentMonth, currentYear);
    	}

    	function previous() {
    	  currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    	  currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    	  showCalendar(currentMonth, currentYear);
    	}

    	function jump() {
    	  currentYear = parseInt(selectYear.value);
    	  currentMonth = parseInt(selectMonth.value);
    	  showCalendar(currentMonth, currentYear);
    	}

    	function showCalendar(month, year) {

    	  var firstDay = ( new Date( year, month ) ).getDay();

    	  tbl = document.getElementById("calendar-body");

    	  tbl.innerHTML = "";

    	  monthAndYear.innerHTML = months[month] + " " + year;
    	  selectYear.value = year;
    	  selectMonth.value = month;

    	  // creating all cells
    	  var date = 1;
    	  for ( var i = 0; i < 6; i++ ) {
    	      var row = document.createElement("tr");

    	      for ( var j = 0; j < 7; j++ ) {
    	          if ( i === 0 && j < firstDay ) {
    	              cell = document.createElement( "td" );
    	              cellText = document.createTextNode("");
    	              cell.appendChild(cellText);
    	              row.appendChild(cell);
    	          } else if (date > daysInMonth(month, year)) {
    	              break;
    	          } else {
    	              cell = document.createElement("td");
    	              cell.setAttribute("data-date", date);
    	              cell.setAttribute("data-month", month + 1);
    	              cell.setAttribute("data-year", year);
    	              cell.setAttribute("data-month_name", months[month]);
    	              cell.className = "date-picker";
    	              cell.innerHTML = "<span>" + date + "</span>";

    	              if ( date === today.getDate() && year === today.getFullYear() && month === today.getMonth() ) {
    	                  cell.className = "date-picker selected";
    	              }
    	              row.appendChild(cell);
    	              date++;
    	          }
    	      }

    	      tbl.appendChild(row);
    	  }

    	}

    	function daysInMonth(iMonth, iYear) {
    	  return 32 - new Date(iYear, iMonth, 32).getDate();
    	}
    </script>
</body>
</html>