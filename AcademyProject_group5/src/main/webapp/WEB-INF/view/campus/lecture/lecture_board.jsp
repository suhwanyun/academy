<%@ page language="java" import="java.sql.*,java.io.*" contentType="text/html;charset=euc-kr" %>

<html>
<head>
<title>�о� �Խ���</title>
</head>


<style type="text/css">
/* boardcss_list ���� ���Ǵ� �� ��� ��ư ���̺� ũ�� */
#boardcss_list_add_button_table { width: 100%; margin: 0 auto 15px; /*position: relative; background: #bddcff; font-weight: bold;*/ }

/* ȭ�鿡 �������� �� ��� ��ư */
#boardcss_list_add_button_table .add_button { cursor: pointer; border: 1px solid #bebebe; position: absolute; right: 10px; top: 10px; width: 85px; padding: 6px 0 6px; text-align: center; font-weight: bold; }
#boardcss_list_add_button_table .add_button a { color: #ffffff; }

/* �� ��� ��ư�� �� ����� ��ġ�� �ʰ� ������� �ƹ��͵� �ƴѰ� */
#boardcss_list_add_button_table .boardcss_list_add_button ul { width: 100%; overflow: hidden; height: 10px;}

/* boardcss_list ���� ����ϴ� �� ��� ���̺� ũ��*/
.boardcss_list_table { width: 100%;}

/* ȭ�鿡 �������� �� ��� ���̺� */
.list_table { width: 100%;}

/* ȭ�鿡 �������� caption */
/* .list_table caption { display: none; } */

/* list_table ���� ���Ǵ� thead */
 .list_table thead th { text-align: center; border-top: 1px solid #e5e5e5; border-bottom: 1px solid #e5e5e5; height:100px; padding: 10px; background: #faf9fa; } 

/* list_table ���� ���Ǵ� tbody */
.list_table tbody td { text-align: center;  border-bottom: 1px solid #e5e5e5; padding: 5px 0; }

/*���̺� �׸� �۲� ��Ÿ�� ����*/
#gomchi{
	color: blue;
	font-size: 50px;
	}
</style>

<body>


<!-- ���̺� ���� -->
<div class="boardcss_list_table">
	<table class="list_table">
		<caption>���� ���</caption>
		<colgroup>
			<col width="30%" />
			<col width="50%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr id="gomchi">
				<th>���� ����</th>
				<th>���� ����</th>
				<th>��¥</th>
			</tr>
		</thead>
		<!--  ������ �ֱ� -->
		<tbody>
				<tr>
				<th>�������</th>
				<th>sex</th>
				<th>�߸޶�</th>
			</tr>
				<tr>
				<th>���� ����</th>
				<th>���� ����</th>
				<th>��¥</th>
			</tr>
		</tbody>
		
		
	</table>
</div>
<!-- ���̺� ���� -->

</body>
</html>