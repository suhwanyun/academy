<%@ page language="java" import="java.sql.*,java.io.*" contentType="text/html;charset=euc-kr" %>

<html>
<head>
<title>학업 게시판</title>
</head>


<style type="text/css">
/* boardcss_list 에서 사용되는 글 등록 버튼 테이블 크기 */
#boardcss_list_add_button_table { width: 100%; margin: 0 auto 15px; /*position: relative; background: #bddcff; font-weight: bold;*/ }

/* 화면에 보여지는 글 등록 버튼 */
#boardcss_list_add_button_table .add_button { cursor: pointer; border: 1px solid #bebebe; position: absolute; right: 10px; top: 10px; width: 85px; padding: 6px 0 6px; text-align: center; font-weight: bold; }
#boardcss_list_add_button_table .add_button a { color: #ffffff; }

/* 글 등록 버튼과 글 목록이 겹치지 않게 만들어준 아무것도 아닌것 */
#boardcss_list_add_button_table .boardcss_list_add_button ul { width: 100%; overflow: hidden; height: 10px;}

/* boardcss_list 에서 사용하는 글 목록 테이블 크기*/
.boardcss_list_table { width: 100%;}

/* 화면에 보여지는 글 목록 테이블 */
.list_table { width: 100%;}

/* 화면에 보여지는 caption */
/* .list_table caption { display: none; } */

/* list_table 에서 사용되는 thead */
 .list_table thead th { text-align: center; border-top: 1px solid #e5e5e5; border-bottom: 1px solid #e5e5e5; height:100px; padding: 10px; background: #faf9fa; } 

/* list_table 에서 사용되는 tbody */
.list_table tbody td { text-align: center;  border-bottom: 1px solid #e5e5e5; padding: 5px 0; }

/*테이블 항목 글꼴 스타일 변경*/
#gomchi{
	color: blue;
	font-size: 50px;
	}
</style>

<body>


<!-- 테이블 시작 -->
<div class="boardcss_list_table">
	<table class="list_table">
		<caption>공지 목록</caption>
		<colgroup>
			<col width="30%" />
			<col width="50%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr id="gomchi">
				<th>공지 종류</th>
				<th>공지 제목</th>
				<th>날짜</th>
			</tr>
		</thead>
		<!--  데이터 넣기 -->
		<tbody>
				<tr>
				<th>시험공지</th>
				<th>sex</th>
				<th>야메떼</th>
			</tr>
				<tr>
				<th>공지 종류</th>
				<th>공지 제목</th>
				<th>날짜</th>
			</tr>
		</tbody>
		
		
	</table>
</div>
<!-- 테이블 종료 -->

</body>
</html>