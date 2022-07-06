<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script>
	$(document).ready(function(){
		
		$('#itwillCNT').click(function(){
			
			$.ajax({
				url : "/count",
				type : "GET",
				success : function(data){
					alert("성공!");
					console.log(data);
					
					$('#itwillTitle').append("글의 개수 : "+data+"개");
					
				},
				error : function(data){
					alert("에러발생!");
					console.log(data);
				}
		
			});
		});
		
	});
</script>

<h1 id="itwillTitle">listCri.jsp</h1>
<%-- ${boardList } --%>

<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">Bordered Table</h3>
		<input type="button" class="btn btn-block btn-info" id="itwillCNT" value="저장된 글의 수 조회">
	</div>

	<div class="box-body">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 10px">번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th style="width: 40px">조회수</th>
				</tr>
				<c:forEach var="vo"  items="${boardList }">
					<tr>
						<td>${vo.bno }</td>
						<td>
							<a href="/board/read?bno=${vo.bno }">${vo.title }</a>
						</td>
						<td>${vo.writer }</td>
						<td>
						 <%-- <fmt:formatDate value="${vo.regdate }" type="both"/> --%>
						 <fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td><span class="badge bg-red">${vo.viewcnt }</span></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</div>

	<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
		
			<c:if test="${pm.prev }">
<!-- 				<li><a href="#">«</a></li> -->
				<li><a href="listCri?page=${pm.startPage - 1 }">&laquo;</a></li>
			</c:if>
			
			<c:forEach var="idx" begin="${pm.startPage }" end="${pm.endPage }">
				<li <c:out value="${pm.cri.page == idx? 'class=active':''}"/> >
					<a href="listCri?page=${idx }">${idx }</a>
				</li>			
			</c:forEach>			
			
			<c:if test="${pm.next && pm.endPage > 0 }">
<!-- 				<li><a href="#">»</a></li> -->
				<li><a href="listCri?page=${pm.endPage + 1 }">&raquo;</a></li>
			</c:if>
			
		</ul>
	</div>
</div>


<script type="text/javascript">
//    alert('${result}');
//    alert('${boardList[0].title}');
//    $(document).ready(function(){
// 	   $('body').append('${boardList[0].title}');
//    });

	var result = '${result}';
	
	if(result == "REGOK"){
		alert('글쓰기 완료!');
	}
	
	if(result == "MODOK"){
		alert(" 글 수정 완료! ");
	}
	
	if(result == "DELOK"){
		alert(" 글 삭제 완료! ");
	}



</script>


<%@ include file="../include/footer.jsp"%>
