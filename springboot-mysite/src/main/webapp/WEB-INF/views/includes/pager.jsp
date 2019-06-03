<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- pager 추가 -->
<div class="pager">
	<ul>
		<c:choose>
			<c:when test="${pager.currentBlock == 0 }">
				<li>◀</li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${pager.previewPage }&kwd=${keyword }">◀</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach begin="${pager.currentBlock * pager.countPerBlock + 1 }" end="${pager.currentBlock * pager.countPerBlock + pager.countPerBlock }" var="i">
			<c:choose>
				<c:when test="${i == pager.currentPage + 1 }">
					<li class="selected"><a href="${pageContext.servletContext.contextPath }/board/list?page=${i-1 }&kwd=${keyword }">${i }</a></li>
				</c:when>
				<c:when test="${i > pager.totalPageCount }">
					<li>${i }</li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${i-1 }&kwd=${keyword }">${i }</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${pager.currentBlock + 1 == pager.totalBlockCount }">
				<li>▶</li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${pager.nextPage }&kwd=${keyword }">▶</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>					
<!-- pager 추가 -->