/**
 * reply module javascript 
 */

console.log("Reply Module......");

var replyService = (function() {
	
	function add(reply, callback) {
		console.log("reply..........");
		
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if(error) {
					error(er);
				}
			}
		})
	}

	function getList(param, callback, error) {
		var seq_bno = param.seq_bno;
		var page = param.page || 1 ;
		
		$.getJSON("/replies/pages/"+seq_bno+"/"+page+".json",
			function(data) {
				if (callback) {
					callback(data);
				}
			}).fail(function(xhr, status, err) {
				if (error) {
					error();
				}
			});
	}
	
	return {
		add : add,
		getList : getList
	}; 
}) ();


