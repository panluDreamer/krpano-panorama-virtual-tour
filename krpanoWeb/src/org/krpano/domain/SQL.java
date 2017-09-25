package org.krpano.domain;

public class SQL {
	public static String getPswByName = "select password from user where username=";
	public static String getAllPano = "select * from panorama;";
	public static String getViewAndLove =  "select view_num,love_num from view_love where pano_id=";
}
