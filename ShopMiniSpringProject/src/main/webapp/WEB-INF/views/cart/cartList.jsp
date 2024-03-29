<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>

function totalXXX(){
	var totalSum=0;
	$(".sum").each(function (idx, data){
		totalSum += Number.parseInt($(data).text());
	});
	$("#totalSum").text(totalSum);
}

$(document).ready(function() {
	totalXXX();
	$("#checkAll").on("click", checkAll);
	$(".deleteBtn").on("click", deleteCart);
	$(".updateBtn").on("click", updateAmount);
	$("#delAllCart").on("click", delAllCart);
	$(".orderBtn").on("click", orderConfirm);
});

function orderConfirm(){
	var num = $(this).attr("data-num");
	location.href="loginCheck/orderConfirm?num="+num;
}
function delAllCart(){
	$("form").attr("action", "loginCheck/delAllCart");
	$("form").submit();
}

function checkAll(){
	$(".check").attr("checked", this.checked);
}

function deleteCart() {
	var num= $(this).attr("data-num");
	var xxx= $(this);
	$.ajax({
		url: "loginCheck/cartDelete",
		type:"get",
		dataType: "text",
		data: {
			num: num
		},
		success: function(data, status, xhr) {
			console.log("success");
			//dom삭제 
			xxx.parents().filter("tr").remove();
			totalXXX();
		},
		error: function(xhr, status, error) {
			console.log(error);
		}			
	});
}

function updateAmount() {
	//console.log("updateBtn Click 실행됨");
	var num=$(this).attr("data-num");
	var gAmount = $("#cartAmount"+num).val();
	var gPrice = $(this).attr("data-price");
	//console.log(num, gPrice);
	$.ajax({
		url: "loginCheck/cartUpdate",
		type: "get",
		dataType: "text",
		data: {
			num: num,
			gAmount: gAmount
		},
		success: function (data, status, xhr) {
			var total= 
					parseInt(gAmount)*parseInt(gPrice);
			$("#sum"+num).text(total);
			totalXXX();
		},
		error: function (xhr, status,error) {
			console.log(error);
		}		
	});
}

</script>

<table width="90%" cellspacing="0" cellpadding="0" border="0">

	<tr>
		<td height="30">
	</tr>

	<tr>
		<td colspan="5" class="td_default">&nbsp;&nbsp;&nbsp; <font
			size="5"><b>- 장바구니-</b></font> &nbsp;
		</td>
	</tr>

	<tr>
		<td height="15">
	</tr>

	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>

	<tr>
		<td height="7">
	</tr>

	<tr>
		<td class="td_default" align="center">
		<input type="checkbox" id="checkAll"
		name="allCheck" id="allCheck"> <strong>전체선택</strong></td>
		<td class="td_default" align="center"><strong>주문번호</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>상품정보</strong></td>
		<td class="td_default" align="center"><strong>판매가</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>수량</strong></td>
		<td class="td_default" align="center"><strong>합계</strong></td>
		<td></td>
	</tr>

	<tr>
		<td height="7">
	</tr>
	
	
	
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>



	<form name="myForm">
	    
	    
<!-- 반복시작 -->
<c:forEach var="x" items="${cartList}">

		<tr>
			<td class="td_default" width="80">
			<!-- checkbox는 체크된 값만 서블릿으로 넘어간다. 
			따라서 value에 삭제할 num값을 설정한다. -->
			<input type="checkbox"
				name="check" id="check81" class="check" 
				value="${x.num}"></td>
			<td class="td_default" width="80">${x.num}</td>
			<td class="td_default" width="80"><img
				src="images/items/${x.gImage}.gif" border="0" align="center"
				width="80" /></td>
			<td class="td_default" width="300" style='padding-left: 30px'>
			${x.gName}
				<br> <font size="2" color="#665b5f">[옵션 : 사이즈(${x.gSize})
					, 색상(${x.gColor})]
			</font></td>
			<td class="td_default" align="center" width="110">
			<span id="ggPrice${x.num}">${x.gPrice}</span>
			</td>
			<td class="td_default" align="center" width="90"><input
				class="input_default" type="text" name="cartAmount"
				id="cartAmount${x.num}" style="text-align: right" maxlength="3"
				size="2" value="${x.gAmount}"></input></td>
			<td><input type="button" value="수정"
			     class="updateBtn"
			     data-num="${x.num}" 
			     data-price="${x.gPrice}" /></td>
			<td class="td_default" align="center" width="80"
				style='padding-left: 5px'><span id="sum${x.num}" class="sum">
				${x.gPrice * x.gAmount}
				</span></td>
			<td><input type="button" value="주문"
				data-num="${x.num }" class="orderBtn"></td>
			<td class="td_default" align="center" width="30"
				style='padding-left: 10px'>
				<input type="button" value="삭제" class="deleteBtn"
				data-num="${x.num }"></td>
			<td height="10"></td>
		</tr>

 <!-- 반복끝 --> 
</c:forEach>
	</form>
	<tr>
		<td colspan="10">
			총합:<span id="totalSum"></span> 
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<tr>
		<td height="30">
	</tr>

	<tr>
		<td align="center" colspan="5"><a class="a_black"
			href="javascript:orderAllConfirm(myForm)"> 전체 주문하기 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<button onclick="orderAllConfirm(myForm)">전체 주문하기</button>
			<button id="delAllCart">전체 삭제하기</button>
		</td>
	</tr>
	<tr>
		<td height="20">
	</tr>

</table>
    