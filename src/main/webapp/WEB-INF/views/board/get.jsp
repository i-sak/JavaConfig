<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tables</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Board Read Page
				</div>
                 <!-- /.panel-heading -->
                 
                 <div class="panel-body">
                 
                 	<div class="form-group">
                 		<label>Bno</label>
                 		<input class="form-control" name="seq_bno" 
                 			value='<c:out value="${board.seq_bno }" />' readonly="readonly">
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>Title</label>
                 		<input class="form-control" name="title"
                 			value='<c:out value="${board.title }" />' readonly="readonly">
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>Text area</label>
                 		<textarea class="form-control" rows="3" name="content" readonly="readonly">
                 		<c:out value="${board.content }"/></textarea>
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>writer</label>
                 		<input class="form-control" name="writer"
                 			value='<c:out value="${board.writer }" />' readonly="readonly">
                 	</div>
                 	 
                 	<button data-oper="modify" class="btn btn-default">Modify</button>
                 	<button data-oper="list" class="btn btn-default">List</button>
                 	
                 	 <form id="openForm" action="/board/modify" method="get">
                 	 	<input type="hidden" id="seq_bno" name="seq_bno"
                 	 		 value='<c:out value="${board.seq_bno }" />' />
                 	 	<input type="hidden" name="pageNum" 
                 	 		value='<c:out value="${cri.pageNum }"/>' />
                	 	<input type="hidden" name="amount" 
                 	 		value='<c:out value="${cri.amount }"/>' />
                 	 	<input type="hidden" name="type" value='<c:out value="${cri.type }"/>'>
                        <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
                 	 </form>
                 	 
				</div>
            	<!-- /.panel-body -->
			</div>	
        	<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<script type="text/javascript">
	$(document).ready(function() {
		var openForm = $("#openForm");
		$("button[data-oper='modify']").on("click", function(e) {
			openForm.attr("action", "/board/modify").submit();
		});
		$("button[data-oper='list']").on("click", function(e) {
			openForm.find("#seq_bno").remove();
			openForm.attr("action", "/board/list").submit();
		});
		
	});
	</script>
                 	 
<%@include file="../includes/footer.jsp" %>