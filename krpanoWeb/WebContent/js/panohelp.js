function produce_pano(name, id, num, panoName, panoType, panoInfo) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "Produce.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			username : name,
			pano_id : id,
			number : num,
			pano_name : panoName,
			pano_type : panoType,
			pano_info : panoInfo
		},
		dataType : "json",
		success : function(data) {
			// var d = eval("(" + data + ")");
			// alert("d.status: " + d.status);// 访问json对象
			// data_return = d;
			defer.resolve(data);
		},
		error : function() {
			alert("ajax error");
		}
	});
	// return data_return;
	return defer.promise();
}

function get_all_pano() {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetAllPano.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// 这里不要把data写出d了！！！
		},
		error : function() {
			alert("get_all_pano ajax error");
		}
	});
	return data_return;
}
function getPanoById(panoId) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetPanoById.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			pano_id : panoId
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// 这里不要把data写出d了！！！
		},
		error : function() {
			alert("getPanoById ajax error");
		}
	});
	return data_return;
}
function getBgMusicById(panoId) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetMusicById.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			pano_id : panoId
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// 这里不要把data写出d了！！！
		},
		error : function() {
			alert("get_music ajax error");
		}
	});
	return data_return;
}
function get_user_all_pano(name) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetUserAllPano.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			username : name
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// 这里不要把data写出d了！！！
		},
		error : function() {
			alert("get_user_all_pano ajax error");
		}
	});
	return data_return;
}
function get_user_all_save(name) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetUserAllSavePanos.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			username : name
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// 这里不要把data写出d了！！！
		},
		error : function() {
			alert("get_user_all_save ajax error");
		}
	});
	return data_return;
}
function deleteSave(name, id) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "DeleteSavePano.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			username : name,
			pano_id : id
		},
		dataType : "json",
		success : function(data) {
			// var d = eval("(" + data + ")");
			// alert("d.status: " + d.status);// 访问json对象
			// data_return = d;
			defer.resolve(data);
		},
		error : function() {
			alert("ajax error");
		}
	});
	// return data_return;
	return defer.promise();
}

function deleteFabu(name, id) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "DeleteFabuPano.action",
		// 因为web.xml中为url-pattern中为*.action,故这里要加后缀action，但是struts.xml中的action
		// name 不要加action后缀
		data : {// 设置数据源
			username : name,
			pano_id : id
		},
		dataType : "json",
		success : function(data) {
			// var d = eval("(" + data + ")");
			// alert("d.status: " + d.status);// 访问json对象
			// data_return = d;
			defer.resolve(data);
		},
		error : function() {
			alert("ajax error");
		}
	});
	// return data_return;
	return defer.promise();
}