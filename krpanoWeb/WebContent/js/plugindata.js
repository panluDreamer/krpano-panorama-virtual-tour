/**
 * 获取浏览量
 */
function getViewNum(pano_id) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetViewNum.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			pano_id : pano_id
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON字符串转为JSON对象
			// alert("d.status: " + d.status);// 访问json对象
			data_return = d;
		},
		error : function() {
			alert("ajax error");
		}
	});
	return data_return;
}
/**
 * 获取点赞量
 */
function getLoveNum(pano_id) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetLoveNum.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			pano_id : pano_id
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON字符串转为JSON对象
			// alert("d.status: " + d.status);// 访问json对象
			data_return = d;
		},
		error : function() {
			alert("ajax error");
		}
	});
	return data_return;
}
function addAgree(currUser, panoId, loveTime) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "AddAgree.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			username : currUser,
			pano_id : panoId,
			love_time : loveTime
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON字符串转为JSON对象
			// alert("d.status: " + d.status);// 访问json对象
			data_return = d;
		},
		error : function() {
			alert("ajax error");
		}
	});
	return data_return;
}

function getAllComments(pano_id) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetAllComments.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			pano_id : pano_id
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON字符串转为JSON对象
			// alert("d.status: " + d.status);// 访问json对象
			data_return = d;
		},
		error : function() {
			alert("ajax error");
		}
	});
	return data_return;
}
function addComment(username, pano_id, content, ath, atv, fabu_time) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "AddComment.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			username : username,
			pano_id : pano_id,
			content : content,
			ath : ath,
			atv : atv,
			comment_time : fabu_time
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON字符串转为JSON对象
			// alert("d.status: " + d.status);// 访问json对象
			data_return = d;
		},
		error : function() {
			alert("ajax error");
		}
	});
	return data_return;
}