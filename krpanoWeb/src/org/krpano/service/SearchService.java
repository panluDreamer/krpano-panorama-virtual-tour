package org.krpano.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.krpano.domain.IndexData;
import org.krpano.util.DBoperation;

public class SearchService {
	/**
	 * 根据panoId获取全景信息
	 * */
	private static List<IndexData> getPanosByPanoIdVector(
			Vector<String> panoIdVector) {
		DBoperation.connectDB();
		List<IndexData> list = new ArrayList<>();
		int size = panoIdVector.size();
		for (int i = 0; i < size; i++) {
			String tempPanoId = panoIdVector.get(i);
			String sql1 = "select * from panorama where pano_id='" + tempPanoId
					+ "'";
			String sql2 = "select view_num,love_num from view_love where pano_id=";
			String sql3 = "select count(*) from comments where pano_id=";
			Vector<Vector<String>> tempPanorama = DBoperation.execSearch(sql1);
			IndexData indexData = new IndexData();
			if (tempPanorama.size() > 0) {
				indexData.setPano_id(tempPanoId);
				indexData.setPano_path(tempPanorama.get(0).get(1));
				indexData.setPano_name(tempPanorama.get(0).get(2));
				indexData.setPano_type(tempPanorama.get(0).get(3));
				indexData.setInfo(tempPanorama.get(0).get(4));
				indexData.setCover_img(tempPanorama.get(0).get(5));
			}
			Vector<Vector<String>> tempView = DBoperation.execSearch(sql2 + "'"
					+ tempPanoId + "'");
			Vector<Vector<String>> tempComment = DBoperation.execSearch(sql3
					+ "'" + tempPanoId + "'");
			if (tempView.size() > 0) {
				indexData.setViewNum(Integer.valueOf(tempView.get(0).get(0)));
				indexData.setLoveNum(Integer.valueOf(tempView.get(0).get(1)));
			}
			indexData.setCommentNum(Integer.valueOf(tempComment.get(0).get(0)));
			list.add(indexData);
		}
		DBoperation.disconnectDB();
		return list;
	}

	/**
	 * 根据全景作者搜索
	 * */
	public static List<IndexData> searchByUsername(String keyword) {
		List<IndexData> list = new ArrayList<>();
		System.out.println("Search service keyword:" + keyword);
		DBoperation.connectDB();
		String sql = "select pano_id from user_pano where username like '%"
				+ keyword + "%'";
		Vector<Vector<String>> tempPanos = DBoperation.execSearch(sql);
		DBoperation.disconnectDB();
		Vector<String> panoIdVector = new Vector<>();
		int size = tempPanos.size();
		System.out.println("size = " + size);
		for (int i = 0; i < size; i++) {
			panoIdVector.add(tempPanos.get(i).get(0));
		}
		if (panoIdVector.size() > 0) {
			list = getPanosByPanoIdVector(panoIdVector);
		}
		System.out.println("list.size = " + list.size());

		return list;
	}

	/**
	 * 按标题或内容搜索
	 * */
	public static List<IndexData> searchByTitleOrContent(String searchword,
			String mode) {
		List<IndexData> list = new ArrayList<>();
		String sql;
		if (mode.equals("title")) {
			sql = "select pano_id from panorama where pano_name like '%"
					+ searchword + "%'";
		} else {
			sql = "select pano_id from panorama where info like '%"
					+ searchword + "%'";
		}
		DBoperation.connectDB();
		Vector<Vector<String>> tempVector = DBoperation.execSearch(sql);
		Vector<String> panoIdVector = new Vector<>();
		int size = tempVector.size();
		for (int i = 0; i < size; i++) {
			panoIdVector.add(tempVector.get(i).get(0));
		}
		if (panoIdVector.size() > 0) {
			list = getPanosByPanoIdVector(panoIdVector);
		}
		DBoperation.disconnectDB();
		return list;
	}

}
