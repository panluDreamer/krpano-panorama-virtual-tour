package org.krpano.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.krpano.domain.Comment;
import org.krpano.util.DBoperation;

public class PluginService {
	/**
	 * 浏览量处理
	 * */
	public static String getView(String pano_id) {
		String res = "";
		String sql1 = "select view_num from view_love where pano_id='"
				+ pano_id + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector1 = DBoperation.execSearch(sql1);
		// System.out.println("vector.get(0).get(0):" + vector1.get(0).get(0));
		int preViewNum = Integer.valueOf(vector1.get(0).get(0)).intValue();
		int nowViewNum = preViewNum + 1;
		res = String.valueOf(nowViewNum);
		// update database
		String sql2 = "update view_love set view_num='" + nowViewNum
				+ "' where pano_id='" + pano_id + "'";
		DBoperation.execsql(sql2);
		DBoperation.disconnectDB();
		// System.out.println("preViewNum:" + preViewNum + " nowViewNum:"
		// + nowViewNum);
		return res;
	}

	/**
	 * 获取点赞
	 * */
	public static String getLove(String pano_id) {
		String res = "";
		String sql1 = "select love_num from view_love where pano_id='"
				+ pano_id + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector = DBoperation.execSearch(sql1);
		res = vector.get(0).get(0);
		DBoperation.disconnectDB();
		return res;
	}

	/**
	 * 添加点赞
	 * */
	public static String addLove(String pano_id) {
		String nowLoveNum = "";
		String sql1 = "select love_num from view_love where pano_id='"
				+ pano_id + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector = DBoperation.execSearch(sql1);
		int pre = Integer.valueOf(vector.get(0).get(0)).intValue();
		int now = pre + 1;
		nowLoveNum = String.valueOf(now);
		String sql2 = "update view_love set love_num='" + now
				+ "' where pano_id='" + pano_id + "'";
		DBoperation.execsql(sql2);
		DBoperation.disconnectDB();
		return nowLoveNum;
	}

	/**
	 * 保存点赞详情
	 * */
	public static Boolean saveLoveDetail(String username, String pano_id,
			String love_time) {
		Boolean res = true;
		String sql = "insert into love_detail(username,pano_id,love_time) values('"
				+ username + "','" + pano_id + "','" + love_time + "')";
		DBoperation.connectDB();
		res = DBoperation.execsql(sql);
		DBoperation.disconnectDB();
		return res;
	}

	/**
	 * 获取评论
	 * */
	public static List<Comment> getCommentsById(String pano_id) {
		List<Comment> list = new ArrayList<>();
		String sql = "select username,pano_id,content,ath,atv,comment_time from comments where pano_id='"
				+ pano_id + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector = DBoperation.execSearch(sql);
		int size = vector.size();
		for (int i = 0; i < size; i++) {
			Comment comment = new Comment();
			comment.setUsername(vector.get(i).get(0));
			comment.setPano_id(vector.get(i).get(1));
			comment.setContent(vector.get(i).get(2));
			comment.setAth(vector.get(i).get(3));
			comment.setAtv(vector.get(i).get(4));
			comment.setComment_time(vector.get(i).get(5));
			list.add(comment);
		}
		DBoperation.disconnectDB();
		return list;
	}

	/**
	 * 保存评论
	 * */
	public static Boolean addCommentToDB(Comment comment) {
		Boolean res = true;
		String sql = "insert into comments(username,pano_id,content,ath,atv,comment_time) values('"
				+ comment.getUsername()
				+ "','"
				+ comment.getPano_id()
				+ "','"
				+ comment.getContent()
				+ "','"
				+ comment.getAth()
				+ "','"
				+ comment.getAtv() + "','" + comment.getComment_time() + "')";
		DBoperation.connectDB();
		res = DBoperation.execsql(sql);
		DBoperation.disconnectDB();
		return res;
	}
}
