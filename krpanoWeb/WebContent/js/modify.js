var curr = location.href;
// alert("curr = " + curr);
// 从curr中解析出用户名及pano_id
var len = curr.length;
// alert("len = " + len);
// http://localhost:8080/krpanoWeb/tour/lupan/pano_id=20170410173011
// 这里可以使用KMP,先直接使用暴力法
var temp;
for ( var i = 0; i < len; i++) {
	if (curr.charAt(i) == 't' && curr.charAt(i + 1) == 'o'
			&& curr.charAt(i + 2) == 'u' && curr.charAt(i + 3) == 'r') {
		temp = i;
		break;
	}
}
// alert("temp = " + curr.charAt(temp) + " temp+4=" + curr.charAt(temp + 4));
var username, pano_id;
var first = temp + 5;
var second;
for ( var i = temp + 5; i < len; i++) {
	if (curr.charAt(i) == '/') {
		second = i;
		break;
	}
}
var host;
var index;
for ( var i = 0; i < len; i++) {
	if (curr.charAt(i) == 'k' && curr.charAt(i + 1) == 'r'
			&& curr.charAt(i + 2) == 'p' && curr.charAt(i + 3) == 'a'
			&& curr.charAt(i + 4) == 'n' && curr.charAt(i + 5) == 'o') {
		index = i;
		break;
	}
}
host = curr.substring(0, index - 1);
username = curr.substring(first, second);
pano_id = curr.substring(second + 9, len);
// alert("host=" + host + " username = " + username + " pano_id=" + pano_id);
// document.write("<base href=\"http://localhost:8080/krpanoWeb/user/" +
// username
// + "/" + pano_id + "/vtour/\"/>");
document.write("<base href=\"" + host + "/krpanoWeb/user/" + username + "/"
		+ pano_id + "/vtour/\"/>");
