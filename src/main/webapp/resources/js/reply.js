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
					//callback(data); // 댓글 목록만 가져오는 경우
					
					// 댓글 숫자와 목록을 가져오는 경우
					callback(data.replyCnt, data.list); 
					
				}
			}).fail(function(xhr, status, err) {
				if (error) {
					error();
				}
			});
	}
	
	function remove(seq_rno, replyer, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + seq_rno,
			
			data : JSON.stringify({seq_rno : seq_rno, replyer : replyer}),
			contentType : "application/json; charset=utf-8",
			
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
	
	function displayTime(timeValue) {

		var dateObj = new Date(timeValue);
		
		var yy = dateObj.getFullYear();
		var mm = dateObj.getMonth() + 1;
		var dd = dateObj.getDate();
		return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
				(dd > 9 ? '' : '0') + dd ].join('');
				
				
		/*
		당일이면 시간, 전날이면 날짜
		
		var today = new Date();
		var gap = today.getTime - timeValue;
		var dateObj = new Date(timeValue);
		
		if(gap < (1000 * 60 * 60 * 24)) {
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
			 	':', (ss > 9 ? '' : '0') + ss ].join('');
		} else {
			
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
				(dd > 9 ? '' : '0') + dd ].join('');
		}*/
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime
	}; 
}) ();


