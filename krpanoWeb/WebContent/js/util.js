/**
 * ����û��Ƿ��½��ÿ�ν���һ��ҳ��ʱ��ҳ���onload()�����ж�Ҫ���ø÷���
 */
function checkLoginStatus() {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "checkLogin.action",
		data : {},
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

function getCurrLoginUser() {
	var r;
	$.ajax({
		type : "post",
		async : false,
		url : "GetCurrUser.action",
		data : {},
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
