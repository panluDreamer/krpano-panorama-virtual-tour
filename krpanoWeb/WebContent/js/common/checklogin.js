var username;
function mainload() {
	var loginStatus = true;
	username = checkLoginStatus();
	// alert("checkLoginStatus username = " + username);
	if (username == "false") {
		// not login
		var father = document.getElementById("nav");
		var child = document.getElementById("loginlist");
		father.removeChild(child);
	} else {
		// login
		var father = document.getElementById("nav");
		var child1 = document.getElementById("loginbtn");
		var child2 = document.getElementById("registerbtn");
		father.removeChild(child1);
		father.removeChild(child2);
		// alert("login");
		document.getElementById("user").innerHTML = "<p>"
				+ username
				+ "的个人中心</p>"
				+ "<ul>"
				+ "<li><img src=\"img/huiyuan1.png\" alt=\"\"> <a href=\"my.html\">我的主页</a></li>"
				+ "<li><img src=\"img/huiyuan1.png\" alt=\"\"> <a href=\"uploadimg.html\">发布全景</a></li>"
				+ "<li><img src=\"img/huiyuan1.png\" alt=\"\"> <a href=\"commentMessages.html\">消息中心</a></li>"
				+ "<li><img src=\"img/huiyuan1.png\" alt=\"\"> <a href=\"allCoverImg.html\">我的素材</a></li>"
				+ "<li><img src=\"img/huiyuan1.png\" alt=\"\"> <a onclick=\"log_out()\"style=\"cursor: pointer\">退出登录</a></li>"
				+ "</ul>";
	}
}
