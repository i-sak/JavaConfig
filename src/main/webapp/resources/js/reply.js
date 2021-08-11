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
	
	function remove(seq_rno, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + seq_rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, err) {
				if (error) {
					error(err);
				}
			}
		});
	}
	
	function update(reply, callback, error) {
		console.log("seq_rno : "+ reply.seq_rno);
		
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.seq_rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, err) {
				if(error) {
					error(err);
				}
			}
		});
		
	}
	
	function get(seq_rno, callback, error) {
		
		$.get("/replies/" + seq_rno + ".json", function(result) {
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err) {
			if(error) {
				error();
			}
		});
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get
	}; 
}) ();


