<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendar</title>
<link href='${pageContext.request.contextPath }/resources/packages/core/main.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath }/resources/packages/daygrid/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath }/resources/packages/core/main.js'></script>
<script src='${pageContext.request.contextPath }/resources/packages/interaction/main.js'></script>
<script src='${pageContext.request.contextPath }/resources/packages/daygrid/main.js'></script>
<style>
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }
</style>
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
	    var calendar = new FullCalendar.Calendar( calendarEl, {
	      plugins: [ 'interaction', 'dayGrid' ],
	      header: {
	        left: 'prevYear,prev,next,nextYear today',   // 좌측 상단 메뉴
	        center: 'title',
	        right: 'dayGridMonth,dayGridWeek,dayGridDay' // 우측 상단 메뉴
	      },
	      defaultDate: '2019-12-01',
	      navLinks: true, // can click day/week names to navigate views
	      editable: true,
	      eventLimit: true, // allow "more" link when too many events
	      events:  'resources/json/events.json'
	    });
	    calendar.render();
	});
</script>
</head>
<body>
<div id='calendar'></div>
</body>
</html>