/**
 * ��½����
 * 
 * @param username,password
 * @returns true or false
 */
function login(name, psw) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "Login.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : name,
			password : psw
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
function regist(name, psw, mail, phonenumber) {
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : false,
		url : "Register.action",
		data : {// ��������Դ
			username : name,
			password : psw,
			email : mail,
			phone : phonenumber
		},
		dataType : "json",
		success : function(data) {
			var d = eval("(" + data + ")");
			// alert(data);
			defer.resolve(data);
		},
		error : function() {
			alert("ajax error");
		}
	});
	return defer.promise();
}
function log_out() {
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "Logout.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
		},
		dataType : "json",
		success : function(data) {
			// var d = eval("(" + data + ")");
			// alert("d.status: " + d.status);// ����json����
			// data_return = d;
			var d = eval("(" + data + ")");
			if (d.status == "true") {
				alert("log out successful");
				window.location.href = "index.html";
			}
			defer.resolve(data);
		},
		error : function() {
			alert("ajax error");
		}
	});
	// return data_return;
	return defer.promise();
}
/**
 * �ղغ���
 */
function savepano(username, pano_id, save_time) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "SavePano.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : username,
			pano_id : pano_id,
			save_time : save_time
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
/**
 * ��ȡ�û�ȫ���ղ�ȫ��id
 */
function getUserAllSave(username) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "GetUserAllSave.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : username
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
/**
 * ��ȡ�û�������Ϣ
 */
function getUserInfo(username) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "GetUserInfo.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : username
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
function getUserSpace(name) {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "GetUserSpace.action",
		data : {
			username : name
		},
		dataType : "json",
		success : function(data) {
			// alert(data);
			// var d = eval("(" + data + ")");
			r = data;
		},
		error : function() {
			alert("����쳣�����Ժ����ԣ�");
		}
	});
	return r;
}

function getUserAllImage(name) {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "GetUsersAllImg.action",
		data : {
			username : name
		},
		dataType : "json",
		success : function(data) {
			// alert(data);
			// var d = eval("(" + data + ")");
			r = data;
		},
		error : function() {
			alert("����쳣�����Ժ����ԣ�");
		}
	});
	return r;
}
function getUserAllMusic(name) {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "GetAllMusicsByUsername.action",
		data : {
			username : name
		},
		dataType : "json",
		success : function(data) {
			// alert(data);
			// var d = eval("(" + data + ")");
			r = data;
		},
		error : function() {
			alert("����쳣�����Ժ����ԣ�");
		}
	});
	return r;
}
function getUserPanosAllComment(name) {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "GetUserPanosComments.action",
		data : {
			username : name
		},
		dataType : "json",
		success : function(data) {
			// alert(data);
			// var d = eval("(" + data + ")");
			r = data;
		},
		error : function() {
			alert("����쳣�����Ժ����ԣ�");
		}
	});
	return r;
}

function searchPanoByKeyword(key) {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "Search.action",
		data : {
			keyword : key
		},
		dataType : "json",
		success : function(data) {
			// alert(data);
			// var d = eval("(" + data + ")");
			r = data;
		},
		error : function() {
			alert("����쳣�����Ժ����ԣ�");
		}
	});
	return r;
}
function modifyPanoInfo(id, name, info, type) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "ModifyPano.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			pano_id : id,
			pano_name : name,
			pano_info : info,
			pano_type : type
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
function modifyImgByKu(id, path) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "ModifyImageByKu.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			pano_id : id,
			image_path : path
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
function modifyMusicByKu(id, path, name) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "ModifyMusicByKu.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			pano_id : id,
			music_path : path,
			music_name : name
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

function deleteCoverImage(name, imageName) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "DeleteCoverImg.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : name,
			image_name : imageName
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

function deleteBGMusic(name, musicName) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "DeleteMusic.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			username : name,
			music_name : musicName
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
function deleteUsersComments(id) {
	// var data_return;
	var defer = $.Deferred();
	$.ajax({
		type : "post",
		async : true,
		url : "DeleteComment.action",
		// ��Ϊweb.xml��Ϊurl-pattern��Ϊ*.action,������Ҫ�Ӻ�׺action������struts.xml�е�action
		// name ��Ҫ��action��׺
		data : {// ��������Դ
			commentId : id,
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
function getUserPanoLoveInfo(name) {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "GetUserLoveInfo.action",
		data : {
			username : name
		},
		dataType : "json",
		success : function(data) {
			// alert(data);
			// var d = eval("(" + data + ")");
			r = data;
		},
		error : function() {
			alert("����쳣�����Ժ����ԣ�");
		}
	});
	return r;
}

function editUserpassword(name, pass) {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "EditPassWord.action",
		data : {
			username : name,
			password : pass
		},
		dataType : "json",
		success : function(data) {
			// alert(data);
			// var d = eval("(" + data + ")");
			r = data;
		},
		error : function() {
			alert("����쳣�����Ժ����ԣ�");
		}
	});
	return r;
}