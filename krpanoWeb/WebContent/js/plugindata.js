/**
 * ��ȡ�����
 */
function getViewNum(pano_id) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetViewNum.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			pano_id : pano_id
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON�ַ���תΪJSON����
			// alert("d.status: " + d.status);// ����json����
			data_return = d;
		},
		error : function() {
			alert("ajax error");
		}
	});
	return data_return;
}
/**
 * ��ȡ������
 */
function getLoveNum(pano_id) {
	var data_return;
	$.ajax({
		type : "post",
		async : false,
		url : "GetLoveNum.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			pano_id : pano_id
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON�ַ���תΪJSON����
			// alert("d.status: " + d.status);// ����json����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : currUser,
			pano_id : panoId,
			love_time : loveTime
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON�ַ���תΪJSON����
			// alert("d.status: " + d.status);// ����json����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			pano_id : pano_id
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON�ַ���תΪJSON����
			// alert("d.status: " + d.status);// ����json����
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
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : username,
			pano_id : pano_id,
			content : content,
			ath : ath,
			atv : atv,
			comment_time : fabu_time
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");// JSON�ַ���תΪJSON����
			// alert("d.status: " + d.status);// ����json����
			data_return = d;
		},
		error : function() {
			alert("ajax error");
		}
	});
	return data_return;
}