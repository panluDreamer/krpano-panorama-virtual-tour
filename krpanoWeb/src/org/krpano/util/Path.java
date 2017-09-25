package org.krpano.util;

public class Path {
	/**
	 * 服务器上的工程运行目录路径(该路径中间不能出现空格，因为krpano exe生成全景时输入的图片路径不允许包含空格)
	 * */
	public static final String basicPath = "E:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/krpanoWeb";
	/**
	 * 主机地址
	 * */
	public static final String host = "http://localhost:8080/krpanoWeb";
	/**
	 * vbs脚本调用路径，.vbs后要加空格，用于向脚本传入参数
	 * */
	public static final String vbsPath = "cmd.exe /c start E:\\krpanomin\\control.vbs ";
	/**
	 * 默认背景音乐地址
	 * */
	public static final String defaultMusicPath = "http://localhost:8080/krpanoWeb/default/bgMusic/ren.mp3";
}
