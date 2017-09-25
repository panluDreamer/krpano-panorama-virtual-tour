/**
 * 检测用户是否登陆，每次进入一个页面时在页面的onload()方法中都要调用该方法
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
			alert("检测异常，请稍后重试！");
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
			alert("检测异常，请稍后重试！");
		}
	});
	return r;
}
