<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/D2/css/calendar.css">
<link rel="stylesheet" type="text/css" href="/D2/css/all.css">
<title>カレンダー</title>
</head>
<body>

	<header>
		<div class="hamburger-menu">
			<input type="checkbox" id="menu-btn-check"> <label
				for="menu-btn-check" class="menu-btn"><span></span></label>
			<div class="menu-content">
				<ul>
					<li><a href="/D2/HomeServlet">ホーム</a></li>
					<li><a href="/D2/CalendarServlet">カレンダー</a></li>
					<li><a href="/D2/LockServlet">設定</a></li>
					<li><a href="/D2/LogoutServlet">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</header>

	<main>
		<h1>カレンダー</h1>
		<p class=a>やり終えた家事をカレンダーで見ることができるよ！</p>
		<div class="container-calendar">

			<h4 id="monthAndYear"></h4>
			<div class="button-container-calendar">

				<button id="previous" onclick="previous()">‹</button>
				<button id="next" onclick="next()">›</button>
			</div>

			<table class="table-calendar" id="calendar" data-lang="ja">
				<thead id="thead-month">
				</thead>
				<tbody id="calendar-body"></tbody>
			</table>

			<div class="footer-container-calendar">
				<label for="month">日付指定：</label> <select id="month"
					onchange="jump()">
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
				</select> <select id="year" onchange="jump()"></select>
			</div>
		</div>



	</main>

<script>
//指定された範囲の年を選択肢として生成する関数。startからendまでの各年を <option> タグの文字列として結合している。
function generate_year_range(start, end) {
  var years = "";
  for (var year = start; year <= end; year++) {
      years += "<option value='" + year + "'>" + year + "</option>";
  }
  return years;
}
//Dateオブジェクトを使用して現在の日付を取得している。
var today = new Date();
//const today = new Date(${currentYear},${currentMonth-1},${currentDay});

//JSPから取得した現在の月と年を設定している。
var currentMonth = ${currentMonth-1};
var currentYear = ${currentYear};
//id属性の "year" と "month" の要素を取得している。
var selectYear = document.getElementById("year");
var selectMonth = document.getElementById("month");
//generate_year_range 関数を使って、1970年から2200年までの年の選択肢を生成し、HTMLの年の選択要素に設定している。
var createYear = generate_year_range(1970, 2200);
document.getElementById("year").innerHTML = createYear;
// langはlangageの略で、HTMLでjapaneseが指定されている。
var calendar = document.getElementById("calendar");
var lang = calendar.getAttribute('data-lang');

var months = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
var days = ["にちようび", "げつようび", "かようび", "すいようび", "もくようび", "きんようび", "どようび"];

var dayHeader = "<tr>";
for (day in days) {
  if (days[day] === "にちようび") {
    dayHeader += "<th style='color: red;' data-days='" + days[day] + "'>" + days[day] + "</th>";
  } else if (days[day] === "どようび") {
    dayHeader += "<th style='color: blue;' data-days='" + days[day] + "'>" + days[day] + "</th>";
  } else {
    dayHeader += "<th data-days='" + days[day] + "'>" + days[day] + "</th>";
  }
}
dayHeader += "</tr>";
document.getElementById("thead-month").innerHTML = dayHeader;

//showCalendar 関数を呼び出して、初期表示として現在の月と年のカレンダーを表示している。
monthAndYear = document.getElementById("monthAndYear");
showCalendar(currentMonth, currentYear);

//次の月へ移動する処理。currentMonth が11（12月）の場合は年を次の年に進め、それ以外の場合は currentMonth を1プラスしている。
function next() {
  currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
  currentMonth = (currentMonth + 1) % 12;
  //showCalendar(currentMonth, currentYear);
  window.location.href="./CalendarServlet?year="+currentYear+"&month="+(currentMonth+1);
}
//前の月へ移動する処理で、currentMonth が0（1月）の場合は年を前の年に戻し、それ以外の場合は currentMonth を1マイナスしている。
function previous() {
  currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
  currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
  //showCalendar(currentMonth, currentYear);
  window.location.href="./CalendarServlet?year="+currentYear+"&month="+(currentMonth+1);
}
//年と月の選択要素から選択された値を取得して反映している。
function jump() {
  currentYear = parseInt(selectYear.value);
  currentMonth = parseInt(selectMonth.value);
  //showCalendar(currentMonth, currentYear);
  window.location.href="./CalendarServlet?year="+currentYear+"&month="+(currentMonth+1);
}
//指定された月と年のカレンダーを生成して表示する。月初めの曜日を取得して、テーブルの枠に日付やコメントを表示している。
function showCalendar(month, year) {

  var firstDay = ( new Date( year, month ) ).getDay();

  tbl = document.getElementById("calendar-body");

  tbl.innerHTML = "";

  monthAndYear.innerHTML = months[month] + " " + year;
  selectYear.value = year;
  selectMonth.value = month;

  // カレンダーのセルを構築
  var date = 1;
  const commentDateArray = [];
  <c:forEach var="e" items="${commentList}" >
  commentDateArray[${e.date.date}]='${e.comment}';
  </c:forEach>

  const datesListArray = [];
  <c:forEach var="e" items="${datesList}" >
  datesListArray[${e.clickDate.date}]='${e.clickHousework}';
  </c:forEach>
//外側のループは、6行の行を生成（1ヶ月のカレンダーが最大で6週間分の行で構成されるため）
  for ( var i = 0; i < 6; i++ ) {
	  //ここで新しい行を生成している。
      var row = document.createElement("tr");
//内側のループは、1行あたりのセル（日）を生成（1週間分のセルが必要）。
      for ( var j = 0; j < 7; j++ ) {
    	  //最初の週で、1日の曜日が先頭に来るまでの空白のセルを生成。
          if ( i === 0 && j < firstDay ) {
              cell = document.createElement( "td" );
              cellText = document.createTextNode("");
              cell.appendChild(cellText);
              row.appendChild(cell);
              //その月の日数を超えない範囲でセルに日付をセット。
          } else if (date > daysInMonth(month, year)) {
              break;
          } else {
        	  //各日付に対するコメントを取得します。
        	  let comment1 = commentDateArray[date]||'';
        	  let comment2 = datesListArray[date]||'';
              cell = document.createElement("td");
              cell.setAttribute("data-date", date);
              cell.setAttribute("data-month", month + 1);
              cell.setAttribute("data-year", year);
              cell.setAttribute("data-month_name", months[month]);
              cell.className = "date-picker";
              if(comment1 ==='' || comment2 ==='' ){
            	  cell.innerHTML = "<div class=balloon_b><span>" + date + "</span></div>";

              }else{
            	  //空でない場合は、それぞれのコメントを <td> 内の適切な要素に表示。
            	  cell.innerHTML = "<div class=balloon_b><span>" + date + "</span><div class=\"balloon_contents\"><div>コメント："+comment1+"</div><div>家事："+comment2+"</div></div></div>";

              }



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
//指定された月と年の日数を求める関数で、32日(31日)から指定された月の最後の日を引いて、その月の日数を正確に取得する。
function daysInMonth(iMonth, iYear) {
  return 32 - new Date(iYear, iMonth, 32).getDate();
}

</script>
</body>
</html>