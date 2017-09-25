package org.krpano.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.krpano.domain.IndexData;
import org.krpano.domain.PanoData;
import org.krpano.domain.Panorama;
import org.krpano.domain.SQL;
import org.krpano.util.DBoperation;

public class PanoService {
	/**
	 * 全景生成成功时，向数据库插入全景信息
	 * */
	public static Boolean addPanorama(Panorama panorama) {
		boolean result = true;
		String sql = "insert into panorama(pano_id,pano_path,pano_name,pano_type,info,cover_img) values('"
				+ panorama.getPano_id()
				+ "','"
				+ panorama.getPano_path()
				+ "','"
				+ panorama.getPano_name()
				+ "','"
				+ panorama.getPano_type()
				+ "','"
				+ panorama.getInfo()
				+ "','"
				+ panorama.getCover_img() + "');";
		result = DBoperation.execsql(sql);
		return result;
	}

	/**
	 * 保存全景时，同时保存用户-全景关系，默认背景音乐
	 * */
	public static Boolean addOtherPanoInfo(String username, String pano_id,
			String uploadTime, String musicPath, int viewNum, int loveNum) {
		boolean result = true;
		/**
		 * 向用户-全景表中插入数据
		 * */
		String sql1 = "insert into user_pano(username,pano_id,upload_time) values('"
				+ username + "','" + pano_id + "','" + uploadTime + "');";
		/**
		 * 向背景音乐表中插入数据
		 * */
		String sql2 = "insert into bg_music(pano_id,music_path,music_name) values('"
				+ pano_id + "','" + musicPath + "','人民的名义.mp3');";
		/**
		 * 向view_love表中插入数据
		 * */
		String sql3 = "insert into view_love(pano_id,view_num,love_num) values('"
				+ pano_id + "','" + viewNum + "','" + loveNum + "');";
		boolean r1 = DBoperation.execsql(sql1);
		boolean r2 = DBoperation.execsql(sql2);
		boolean r3 = DBoperation.execsql(sql3);
		result = r1 & r2 & r3;
		return result;
	}

	/**
	 * 获取所有全景信息
	 * */
	public static List<IndexData> getAllPanoramas() {
		List<IndexData> tempList = new ArrayList<>();
		String sql1 = SQL.getAllPano;
		String sql2 = SQL.getViewAndLove;
		String sql3 = " select count(*) from comments where pano_id=";
		Vector<Vector<String>> vector = new Vector<>();
		DBoperation.connectDB();
		vector = DBoperation.execSearch(sql1);
		int size = vector.size();
		for (int i = 0; i < size; i++) {
			Vector<String> temp = vector.get(i);
			IndexData indexData = new IndexData();
			indexData.setPano_id(temp.get(0));
			indexData.setPano_path(temp.get(1));
			indexData.setPano_name(temp.get(2));
			indexData.setPano_type(temp.get(3));
			indexData.setInfo(temp.get(4));
			indexData.setCover_img(temp.get(5));
			Vector<Vector<String>> temp2 = DBoperation.execSearch(sql2 + "'"
					+ indexData.getPano_id() + "'");
			indexData.setViewNum(Integer.valueOf(temp2.get(0).get(0)));
			indexData.setLoveNum(Integer.valueOf(temp2.get(0).get(1)));
			Vector<Vector<String>> temp3 = DBoperation.execSearch(sql3 + "'"
					+ indexData.getPano_id() + "'");
			indexData.setCommentNum(Integer.valueOf(temp3.get(0).get(0)));
			tempList.add(indexData);
		}
		DBoperation.disconnectDB();
		return tempList;
	}

	/**
	 * 根据pano_id获取全景信息
	 * */
	public static Panorama getPanoById(String pano_id) {
		Panorama panorama = new Panorama();
		String sql = "select * from panorama where pano_id='" + pano_id + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector = DBoperation.execSearch(sql);
		DBoperation.disconnectDB();
		panorama.setPano_id(vector.get(0).get(0));
		panorama.setPano_path(vector.get(0).get(1));
		panorama.setPano_name(vector.get(0).get(2));
		panorama.setPano_type(vector.get(0).get(3));
		panorama.setInfo(vector.get(0).get(4));
		panorama.setCover_img(vector.get(0).get(5));
		return panorama;
	}

	/**
	 * 根据pano_id获取全景背景音乐资源
	 * */
	public static String getMusicById(String pano_id) {
		String music = "";
		String sql = "select music_path from bg_music where pano_id='"
				+ pano_id + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector = DBoperation.execSearch(sql);
		music = vector.get(0).get(0);
		DBoperation.disconnectDB();
		return music;
	}

	public static List<PanoData> getUsersAllPanos(String username) {
		List<PanoData> list = new ArrayList<>();
		String sql1 = "select pano_id,upload_time from user_pano where username='"
				+ username + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector1 = DBoperation.execSearch(sql1);
		int size = vector1.size();
		for (int i = 0; i < size; i++) {
			Vector<String> temp = vector1.get(i);
			PanoData panoData = new PanoData();
			panoData.setPano_id(temp.get(0));
			panoData.setFabu_time(temp.get(1));
			String sql2 = "select * from panorama where pano_id='"
					+ panoData.getPano_id() + "'";
			Vector<Vector<String>> temp2 = DBoperation.execSearch(sql2);
			int len = temp2.size();
			if (len > 0) {
				panoData.setPano_path(temp2.get(0).get(1));
				panoData.setPano_name(temp2.get(0).get(2));
				panoData.setPano_type(temp2.get(0).get(3));
				panoData.setInfo(temp2.get(0).get(4));
				panoData.setCover_img(temp2.get(0).get(5));
			}
			String sql3 = "select music_path,music_name from bg_music where pano_id ='"
					+ panoData.getPano_id() + "'";
			Vector<Vector<String>> temp3 = DBoperation.execSearch(sql3);
			int len2 = temp3.size();
			if (len2 > 0) {
				panoData.setBackground_music(temp3.get(0).get(0));
				panoData.setBackground_music_name(temp3.get(0).get(1));
			}
			list.add(panoData);
		}
		DBoperation.disconnectDB();
		return list;
	}

	public static List<PanoData> getUsersAllSave(String username) {
		List<PanoData> list = new ArrayList<>();
		String sql1 = "select pano_id,save_time from user_save where username='"
				+ username + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector1 = DBoperation.execSearch(sql1);
		int size = vector1.size();
		for (int i = 0; i < size; i++) {
			Vector<String> temp = vector1.get(i);
			PanoData panoData = new PanoData();
			panoData.setPano_id(temp.get(0));
			panoData.setFabu_time(temp.get(1));// 这里的fabu_time为save_time
			String sql2 = "select * from panorama where pano_id='"
					+ panoData.getPano_id() + "'";
			Vector<Vector<String>> temp2 = DBoperation.execSearch(sql2);
			int len = temp2.size();
			if (len > 0) {
				panoData.setPano_path(temp2.get(0).get(1));
				panoData.setPano_name(temp2.get(0).get(2));
				panoData.setPano_type(temp2.get(0).get(3));
				panoData.setInfo(temp2.get(0).get(4));
				panoData.setCover_img(temp2.get(0).get(5));
			}
			list.add(panoData);
		}
		DBoperation.disconnectDB();
		return list;
	}
}
