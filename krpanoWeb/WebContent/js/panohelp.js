function produce_pano(name, id, num, panoName, panoType, panoInfo) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "Produce.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
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
			// alert("d.status: " + d.status);// ����json����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// ���ﲻҪ��dataд��d�ˣ�����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			pano_id : panoId
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// ���ﲻҪ��dataд��d�ˣ�����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			pano_id : panoId
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// ���ﲻҪ��dataд��d�ˣ�����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : name
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// ���ﲻҪ��dataд��d�ˣ�����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : name
		},
		dataType : "json",
		success : function(data) {
			data_return = data;// ���ﲻҪ��dataд��d�ˣ�����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : name,
			pano_id : id
		},
		dataType : "json",
		success : function(data) {
			// var d = eval("(" + data + ")");
			// alert("d.status: " + d.status);// ����json����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : name,
			pano_id : id
		},
		dataType : "json",
		success : function(data) {
			// var d = eval("(" + data + ")");
			// alert("d.status: " + d.status);// ����json����
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