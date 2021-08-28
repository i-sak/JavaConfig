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
					Board Register Page
				</div>
                 <!-- /.panel-heading -->
                 
                 <div class="panel-body">
                 <form role="form" action="/board/register" method="post">
                 	<div class="form-group">
                 		<label>Title</label>
                 		<input class="form-control" name="title">
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>Text area</label>
                 		<textarea class="form-control" rows="3" name="content"></textarea>
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>writer</label>
                 		<input class="form-control" name="writer">
                 	</div>
                 	
                 	<button type="submit" class="btn btn-default">Submit Button</button>
                 	<button type="reset" class="btn btn-default">Reset Button</button>
                 	
                 </form>
				</div>
            	<!-- /.panel-body -->
			</div>	
        	<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<!-- 새로 추가되는 부분, 첨부파일 등록 처리 -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">file Attach</div>
				
				<div class="panel-body">
					<div class="form-group uploadDiv">
						<input type="file" name="uploadFile" multiple>
					</div>
					
					<div class="uploadResult">
						<ul>
						
						</ul>
					</div>
				</div>
				<!-- end panel-body -->
			</div>
		</div>
	</div>
	
	<!-- Javascript 처리 -->
<%@include file="../includes/footer.jsp" %>