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
				<div class="panel-heading">	Board Modify Page</div>
                 <!-- /.panel-heading -->
                 
                 <div class="panel-body">
                 	
                 	<form role="form" action="/board/modify" method="post">
                 	
                 	<!-- 추가 -->
                 	<input type='hidden' name='pageNum' 
                 		value='<c:out value="${cri.pageNum }"/>' >
					<input type='hidden' name='amount' 
						value='<c:out value="${cri.amount }"/>' >
					<!-- 2021-07-25 조회 조건과 키워드 추가 -->
					<input type="hidden" name="type" value='<c:out value="${cri.type }"/>'>
                    <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
					
                 	<div class="form-group">
                 		<label>Bno</label>
                 		<input class="form-control" name="seq_bno" 
                 			value='<c:out value="${board.seq_bno }" />' readonly="readonly">
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>Title</label>
                 		<input class="form-control" name="title"
                 			value='<c:out value="${board.title }" />'>
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>Text area</label>
                 		<textarea class="form-control" rows="3" name="content">
                 		<c:out value="${board.content }"/></textarea>
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>writer</label>
                 		<input class="form-control" name="writer"
                 			value='<c:out value="${board.writer }" />' readonly="readonly">
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>regdate</label>
                 		<input class="form-control" name="regdate"
                 			value='<fmt:formatDate pattern="yyyy/MM/dd" 
                 			value="${board.regdate }" />' readonly="readonly">
                 	</div>
                 	
                 	<div class="form-group">
                 		<label>updatedate</label>
                 		<input class="form-control" name="regdate"
                 			value='<fmt:formatDate pattern="yyyy/MM/dd" 
                 			value="${board.updatedate }" />' readonly="readonly">
                 	</div>
                 	
                 	<button type="submit" data-oper="modify" class="btn btn-default">Modify</button>
                 	<button type="submit" data-oper="remove" class="btn btn-default">Remove</button>
                 	<button type="submit" data-oper="list" class="btn btn-default">List</button>
                 	
                 	
                 	</form>
                 	
				</div>
            	<!-- /.panel-body -->
			</div>	
        	<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<!-- file이 보여질 영역 -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">files</div>
				<div class="panel-body">
					<div class="form-group uploadDiv">
						<input type="file" name="uploadFile" multiple="multiple">
					</div>
					
					<div class="uploadResult">
						<ul>
						
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
	
	<script type="text/javascript">
	// 해당 게시물에 저장된 파일 불러오기
	$(document).ready(function() {
		(function() {
			var seq_bno = '<c:out value="${board.seq_bno}"/>';
			
			$.getJSON("/board/getAttachList", {seq_bno : seq_bno}, function(arr){
				console.log(arr);
				var str = "";
				$(arr).each(function(i, attach){
					// image type
					if(attach.fileType) {
						var fileCellPath = encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"_"+attach.fileName);
						str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"'";
						str += " data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
						str += "<span>"+attach.fileName+"</span>";
						str += "<button type='button' data-file='"+fileCellPath+"' data-type='image' ";
						str += " class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
						str += "<img src='/display?fileName="+fileCellPath+"'></div></li>";
					} else {
						var fileCellPath = encodeURIComponent(attach.uploadPath+"/"+attach.uuid+"_"+attach.fileName);
						//var fileLink = fileCellPath.replace(new RegExp(/\\/g), "/");
						str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"'";
						str += " data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
						str += "<span>"+attach.fileName+"</span>";
						str += "<button type='button' data-file='"+fileCellPath+"' data-type='file' "
						str += " class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>"
						str += "<img src='/resources/img/attach.png'></div></li>";
					}
				});
				$(".uploadResult ul").html(str);
			}); // end getJson
		})();
		
		// 파일 삭제 버튼
		$(".uploadResult").on("click", "button", function(e) {
			console.log("delete file");
			if (confirm("Remove this file ? ")) {
				var targetLi = $(this).closest("li");
				targetLi.remove();
				
			}
		});
		
		// 파일 체크
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; // 5MB
		function checkExtension(fileName, fileSize) {
			if(fileSize >= maxSize) {
				alert("파일 크기 초과");
				return false;
			}
			if(regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드 할 수 없습니다.");
				return false;
			}
			return true;
		}
		
		// 파일 변경 체크
		$("input[type='file']").change(function(e) {
			alert(1);
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			for(var i = 0; i < files.length; i++) {
				if(!checkExtension(files[i].name, files[i].size) ) {
					return false;
				}
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url : "/uploadAjaxAction",
				processData : false,
				contentType : false,
				data : formData,
				type : "POST",
				dataType : 'json',
				success : function(result) {
					console.log(result);
					showUploadResult(result); // 업로드 결과 처리 함수
				}
			}); // $.ajax
		});
		
		function showUploadResult(uploadResultArr) {
			if(!uploadResultArr || uploadResultArr.length == 0) {return;}
			var uploadUL = $(".uploadResult ul");
			var str = "";
			$(uploadResultArr).each(function(i, obj){
				// image type
				if(obj.fileType) {
					var fileCellPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
					str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str += " data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'><div>";
					str += "<span>"+obj.fileName+"</span>";
					str += "<button type='button' data-file='"+fileCellPath +"' data-type='image'" ;
					str += " class='btn btn-warning btn-circle'>";
					str += "<i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName="+fileCellPath+"'></div></li>";
				} else {
					var fileCellPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
					var fileLink = fileCellPath.replace(new RegExp(/\\/g), "/");
					str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str += " data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'><div>";
					str += "<span> " + obj.fileName + "</span>";
					str += "<button type='button' data-file='"+fileCellPath +"' data-type='file'" ;
					str += " class='btn btn-warning btn-circle'>";
					str += "<i class='fa fa-times'></i></button><br>";
					str += "<img src='/resources/img/attach.png'></div></li>";
				}				
			});
			uploadUL.append(str);
		}
	});
	</script>
	<style>
	div.uploadResult li { float: left; list-style: none; border: 1px gray solid; margin-right: 10px;}
	div.uploadResult img {width : 100px; height: 100px; }
	/*div.temp{z-index: 10; position: fixed; left : 200px; top : 50px;}*/
	</style>
	
	<script type="text/javascript">
	// --------- 게시물 저장 ----------------------
	$(document).ready(function() {
		var formObj = $("form");
		$('button').on("click", function(e) {
			e.preventDefault();
			var operation = $(this).data("oper");
			console.log(operation);
			
			if (operation === 'remove') {
				formObj.attr("action", "/board/remove").attr("method","post");
			}
			
			else if(operation === 'list') {
				// move to list
				
				// self.location ="/board/list";
				// return;
				formObj.attr("action", "/board/list").attr("method", "get");
				
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
			} else if(operation === 'modify') {
				console.log("submit clicked");
				var str = "";
				$(".uploadResult ul li").each(function(i, obj) {
					var jobj = $(obj);
					console.dir(jobj);
					str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
					str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
					str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
					str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
				});
				formObj.append(str).submit();
			}
			formObj.submit();
		});
		
	});
	</script>
	
<%@include file="../includes/footer.jsp" %>