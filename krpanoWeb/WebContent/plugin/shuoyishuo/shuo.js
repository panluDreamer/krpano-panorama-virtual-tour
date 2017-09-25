var comment_num;
var allcomments;
var krpanoplugin = function() {

	var local = this;
	// save the 'this' pointer from the current plugin object

	var krpano = null;
	// the krpano and plugin interface objects
	var plugin = null;

	var plugincanvas = null;
	// optionally - a canvas object for graphic content
	var plugincanvascontext = null;

	// registerplugin - startup point for the plugin (required)
	// - krpanointerface = krpano interface object
	// - pluginpath = string with the krpano path of the plugin (e.g.
	// "plugin[pluginname]")
	// - pluginobject = the plugin object itself (the same as: pluginobject =
	// krpano.get(pluginpath) )
	local.registerplugin = function(krpanointerface, pluginpath, pluginobject) {
		krpano = krpanointerface;
		plugin = pluginobject;
		// add a from xml callable functions:
		plugin.initial_plugin = initial_plugin;
		plugin.add_message = add_message;
		plugin.another_test = another_test;
		plugin.fabu_test = fabu_test;
		plugin.open_music = open_music;
		plugin.close_music = close_music;
		plugin.registerattribute("onloadnumok", "");

	}

	// unloadplugin - end point for the plugin (optionally)
	// - will be called from krpano when the plugin will be removed
	// - everything that was added by the plugin (objects,intervals,...) should
	// be removed here
	local.unloadplugin = function() {
		plugin = null;
		krpano = null;
	}

	function initial_plugin() {
		// alert("initial_plugin here");
		krpano
				.call("set(layer[ssa1].visible, true);set(layer[ssa].onclick,'plugin[talk].fabu_test();');");
		krpano
				.call("set(layer[another].url,'http://localhost:8080/krpanoWeb/plugin/shuoyishuo/music_on.png');set(layer[another].onclick,'plugin[talk].close_music();')");
		// krpano
		// .call("set(layer[test_fabu].onclick,'plugin[talk].fabu_test();')");
		show_data();
		open_music();
	}
	function open_music() {
		// bg_sound.play();
		var bg_sound = document.getElementById("mp");
		bg_sound.play();
		// alert("open_music bgsound.src = " + bg_sound.src);
		krpano
				.call("set(layer[another].url,'http://localhost:8080/krpanoWeb/plugin/shuoyishuo/music_on.png');set(layer[another].onclick,'plugin[talk].close_music();')");
	}
	function close_music() {
		var bg_sound = document.getElementById("mp");
		bg_sound.pause();
		// alert("close_music bg = " + bg_sound.src);
		krpano
				.call("set(layer[another].url,'http://localhost:8080/krpanoWeb/plugin/shuoyishuo/music_off.png');set(layer[another].onclick,'plugin[talk].open_music();')");
	}
	function add_message() {
		alert("add_message 调用show_data函数");
		// krpano.call("addlayer(sss1)");
		show_data();
	}
	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var second = date.getSeconds();
		var ans_sec;
		var minute = date.getMinutes();
		var ans_minute;
		var hour = date.getHours();
		var ans_hour;
		if (second >= 0 && second <= 9) {
			ans_sec = "0" + second;
		} else {
			ans_sec = second;
		}
		if (minute >= 0 && minute <= 9) {
			ans_minute = "0" + minute;
		} else {
			ans_minute = minute;
		}
		if (hour >= 0 && hour <= 9) {
			ans_hour = "0" + hour;
		} else {
			ans_hour = hour;
		}
		var currentdate = date.getFullYear() + seperator1 + month + seperator1
				+ strDate + " " + ans_hour + seperator2 + ans_minute
				+ seperator2 + ans_sec;
		return currentdate;
	}
	function testfunc() {
		alert("testfunc");
	}
	/**
	 * 思考这里怎么修改
	 */
	function addMessageToPano(id, content, ath, atv) {
		krpano.call("addhotspot(ss" + id + ")");
		krpano.set("hotspot[ss" + id + "].ath", ath);
		krpano.set("hotspot[ss" + id + "].atv", atv);
		// krpano.set("hotspot[ss" + id + "].scale", 1.5);
		krpano.set("hotspot[ss" + id + "].url", "textfield.swf");
		krpano.set("hotspot[ss" + id + "].css",
				'font-family:微软雅黑;letter-spacing:1px;');
		krpano.set("hotspot[ss" + id + "].html",
				'[p]&nbsp;&nbsp;&nbsp;&nbsp;[b]' + content + '[/b][/p]');
		krpano.set("hotspot[ss" + id + "].height", '20');
		krpano.set("hotspot[ss" + id + "].visible", 'true');
		krpano.set("hotspot[ss" + id + "].backgroundalpha", '0.5');
		// krpano.set("hotspot[ss" + id + "].ondown",
		// 'plugin[talk].tuodong();');
		// krpano.set("hotspot[ss<%=id%>].backgroundcolor", "0x000000");
		krpano.set("hotspot[ss" + id + "].roundedge", "30");
		// krpano.call("set(hotspot[ss" + id + "].onclick,'testfunc()')");//
		// 点击事件不行???
		krpano.call("addlayer(sss" + id + ")");
		krpano.set("layer[sss" + id + "].parent", 'hotspot[ss' + id + ']');
		krpano.set("layer[sss" + id + "].url",
				'http://localhost:8080/krpanoWeb/plugin/shuoyishuo/fff.png');
		// krpano.set("layer[sss" + id + "].align", 'center');
		// alert("i=" + id + " width:" + krpano.get("hotspot[ss" + id +
		// "].width"));
	}
	function show_data() {
		allcomments = getAllComments(pano_id);
		// alert("pano_id:" + pano_id + "allcomments:" + allcomments);
		size = allcomments.length;
		comment_num = size;
		for ( var i = 0; i < size; i++) {
			var ath = allcomments[i].ath;
			var atv = allcomments[i].atv;
			addMessageToPano(i, allcomments[i].content, ath, atv);
			// krpano.set("layer[sss1].parent", 'hotspot[ss' + id + ']');
			// krpano.set("layer[sss1].url", 'fff.png');
		}
	}
	function detailMessage() {
		alert("here");
		// alert(allcomments[i].username + "在" + allcomments[i].comment_time
		// + "说了" + allcomments[i].content);
	}
	function another_test() {
		// krpano.set("hotspot[spot1].keep", false);
		krpano.set("hotspot[spot1].visible", false);
		var sys = document.getElementById("sys");
		sys.style.display = 'none';
		alert("点击了anther按钮 visible = " + krpano.get("hotspot[spot1].visible"));
	}
	function fabu_test() {
		if (currentUsername == "nouser") {
			alert("未登录，登陆之后才能评论");
		} else {

			var sys = document.getElementById("sys");
			var close = document.getElementById("close");
			var sure = document.getElementById("fabu");
			close.onclick = function() {
				// alert("close function");
				krpano.set("hotspot[spot1].visible", false);
				var sys = document.getElementById("sys");
				sys.style.display = 'none';
			};
			sure.onclick = function() {
				/*
				 * 与后台交互,添加hotspot到全景上，同时检查是否登陆，未登录用户不能发布说一说
				 */
				var text = document.getElementById("text");
				var ath_now = krpano.get("hotspot[spot1].ath");
				var atv_now = krpano.get("hotspot[spot1].atv");
				if (text.value == "") {
					alert("请输入说一说内容");
				} else {
					// alert("sure function 内容为：" + text.value + " ath_now = " +
					// ath_now
					// + " atv_now = " + atv_now + " comment_num = " +
					// comment_num);

					var now_time = getNowFormatDate();
					var res = addComment(currentUsername, pano_id, text.value,
							ath_now, atv_now, now_time);
					if (res.status == true) {// 发布成功
						// alert("here res.status:" + res.status);
						addMessageToPano(++comment_num, text.value, ath_now,
								atv_now);
						krpano.set("hotspot[spot1].visible", false);
						var sys = document.getElementById("sys");
						sys.style.display = 'none';
					} else {
						alert("未知错误");
					}

				}
			};
			sys.style.display = 'block';
			var ath, atv;
			temp_ath = krpano.get("hotspot[spot1].ath");
			temp_atv = krpano.get("hotspot[spot1].atv");// 获取当前移动到的位置ath,atv
			ath = krpano.get("view.hlookat");
			atv = krpano.get("view.vlookat");
			krpano.set("hotspot[spot1].ath", ath);
			krpano.set("hotspot[spot1].atv", atv);
			// krpano.set("hotspot[spot1].keep", true);
			krpano.set("hotspot[spot1].visible", true);
			// krpano.call("set(hotspot[spot1].keep),true");
			var temp_ath, temp_atv;
			// alert("fabu_test ath=" + temp_ath + " atv=" + temp_atv + " keep="
			// + krpano.get("hotspot[spot1].keep") + " view.ath="
			// + krpano.get("view.hlookat") + " view.atv="
			// + krpano.get("view.vlookat") + " sys=" + sys.style.display);
		}
	}
};
