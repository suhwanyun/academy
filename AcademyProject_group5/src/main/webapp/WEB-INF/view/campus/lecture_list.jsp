<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>전체 강의 목록</title>
</head>
<body>
	<table>
		<tr>
			<td><select>
					<option selected="selected">선택</option>
					<option>강의 이름</option>
					<option>교수 이름</option>
			</select></td>
			<td><input type="search" /></td>
			<td><button>검색</button></td>
		</tr>
	</table>
	<table>

		<tr>
			<th>강의 이름</th>
			<th>교수 이름</th>
			<th>강의 분반</th>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><button>더 보기</button></td>
			<td align="right"><button>맨 위로</button></td>
		</tr>
	</table>

</body>
</html>