<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>슬립몰</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/common.css' />" />
</head>
<body>
	<div class="w3-border-bottom">
		<c:import url="/admin/header.do" />
	</div>
	<div class="w3-border-bottom w3-border-blue">
		<c:import url="/admin/nav.do?menu=2" />
	</div>
	<div>
		<section class="block-center w3-container">
		<h4>주문 리스트</h4>
		<table class="w3-table-all">
			<tr>
				<th>확인</th>
				<th>번호</th>			
				<th>회원번호</th>				
				<th>회워ID</th>
				<th>상품번호</th>
				<th>수량</th>
				<th>주문날짜</th>
			</tr>
			<c:forEach var="orderVO" items="${orderVOs}">
			<tr>
				<td><input type="checkbox" name="idxes" value="${orderVO.idx}"></td>
				<td>${orderVO.idx}</td>			
				<td>${orderVO.memberIdx}</td>
				<td>${orderVO.memberUsername}</td>								
				<td>${orderVO.productIdx}</td>
				<td>${orderVO.qty}</td>
				<td>${fn:substring(orderVO.createTime,0,10)}</td>
			</tr>
			</c:forEach>
		</table>
		</section>
	</div>
	<c:import url="/admin/footer.do" />	
</body>
</html>