package org.krpano.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.krpano.domain.LoveInfo;
import org.krpano.domain.SQL;
import org.krpano.domain.User;
import org.krpano.domain.UserComment;
import org.krpano.util.DBoperation;
import org.krpano.util.FileUtil;
import org.krpano.util.Path;

public class UserService {
	/**
	 * 根据用户名获取密码
	 * */
	public static String getPswByUsername(String username) {
		String password = "null";
		Vector<Vector<String>> res = DBoperation.execSearch(SQL.getPswByName
				+ "'" + username + "'");
		if (res.size() == 0) {
			password = "null";
		} else {
			password = res.get(0).get(0);
		}
		return password;
	}

	/**
	 * 保存发布全景记录信息
	 * */
	public static String savePanoToDB(String username, String pano_id,
			String save_time) {
		Boolean res = true;
		String sql = "insert into user_save(username,pano_id,save_time) values('"
				+ username + "','" + pano_id + "','" + save_time + "')";
		DBoperation.connectDB();
		res = DBoperation.execsql(sql);
		DBoperation.disconnectDB();
		return String.valueOf(res);
	}

	/**
	 * 获取收藏全景
	 * */
	public static List<String> getSavePanoIdByUsername(String username) {
		List<String> list = new ArrayList<>();
		String sql = "select pano_id from user_save where username='"
				+ username + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> vector = DBoperation.execSearch(sql);
		int size = vector.size();
		for (int i = 0; i < size; i++) {
			list.add(vector.get(i).get(0));
		}
		DBoperation.disconnectDB();
		return list;
	}

	/**
	 * 获取用户信息
	 * */
	public static User getUserInfoByName(String username) {
		User user = new User();
		String sql = "select * from user where username='" + username + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> temp = DBoperation.execSearch(sql);
		if (temp.size() != 0) {
			user.setUsername(temp.get(0).get(0));
			user.setPassword(temp.get(0).get(1));
			user.setMail(temp.get(0).get(2));
			user.setTelephone(temp.get(0).get(3));
		}
		DBoperation.disconnectDB();
		return user;
	}

	/**
	 * 取消收藏
	 * */
	public static Boolean deleteUserSaves(String username, String pano_id) {
		Boolean result = true;
		String sql = "delete from user_save where username='" + username
				+ "' and pano_id='" + pano_id + "'";
		DBoperation.connectDB();
		result = DBoperation.execsql(sql);
		DBoperation.disconnectDB();
		return result;
	}

	public static List<UserComment> getUserPanosTotallyComment(String username) {
		List<UserComment> list = new ArrayList<>();
		DBoperation.connectDB();
		String sql1 = "select pano_id from user_pano where username='"
				+ username + "'";
		Vector<Vector<String>> userAllPanoId = DBoperation.execSearch(sql1);
		int len1 = userAllPanoId.size();
		for (int i = 0; i < len1; i++) {
			String tempPanoId = userAllPanoId.get(i).get(0);

			String sql2 = "select id,username,content,comment_time from comments where pano_id='"
					+ tempPanoId + "'";
			Vector<Vector<String>> tempComments = DBoperation.execSearch(sql2);
			String sql3 = " select pano_path from panorama where pano_id='"
					+ tempPanoId + "'";
			Vector<Vector<String>> tempPath = DBoperation.execSearch(sql3);
			int len2 = tempComments.size();
			for (int j = 0; j < len2; j++) {
				UserComment userComment = new UserComment();
				userComment.setComment_pano_id(tempPanoId);
				userComment.setId(Integer.valueOf(tempComments.get(j).get(0)));
				userComment.setComment_username(tempComments.get(j).get(1));
				userComment.setComment_content(tempComments.get(j).get(2));
				userComment.setComment_time(tempComments.get(j).get(3));
				if (tempPath.size() > 0) {
					userComment.setPano_path(tempPath.get(0).get(0));
				}
				list.add(userComment);
			}

		}
		DBoperation.disconnectDB();
		return list;
	}

	/**
	 * 删除用户发布的全景，包括表panorama表中的记录及文件系统中的文件
	 * */
	public static Boolean deletePanos(String username, String pano_id) {
		Boolean result = true;
		String deleteSql = "delete from panorama where pano_id='" + pano_id
				+ "'";
		/**
		 * 删除数据库记录
		 * */
		DBoperation.connectDB();
		result = DBoperation.execsql(deleteSql);
		DBoperation.disconnectDB();
		/**
		 * 删除对应全景文件
		 * */
		String dirpath = Path.basicPath + "/user/" + username + "/" + pano_id;
		System.out.println("待删除全景文件夹路径为：" + dirpath);
		File file = new File(dirpath);
		FileUtil.deleteAllFilesInDir(file);
		return result;

	}

	/**
	 * 更改全景信息
	 * */
	public static Boolean modifyPanos(String pano_id, String pano_name,
			String pano_info, String pano_type) {
		Boolean res = true;
		String sql = "update panorama set pano_name='" + pano_name
				+ "',pano_type='" + pano_type + "',info='" + pano_info
				+ "' where pano_id='" + pano_id + "'";
		DBoperation.connectDB();
		res = DBoperation.execsql(sql);

		DBoperation.disconnectDB();
		return res;
	}

	/**
	 * 更改全景封面图片
	 * */
	public static Boolean modifyCoverImage(String pano_id, String image_path) {
		Boolean res = true;
		String sql = "update panorama set cover_img='" + image_path
				+ "' where pano_id='" + pano_id + "'";
		DBoperation.connectDB();
		res = DBoperation.execsql(sql);
		DBoperation.disconnectDB();
		return res;
	}

	/**
	 * 更改全景漫游背景音乐
	 * */
	public static Boolean modifyMusic(String pano_id, String music_path,
			String music_name) {
		Boolean res = true;
		String sql = "update bg_music set music_path='" + music_path
				+ "',music_name='" + music_name + "' where pano_id='" + pano_id
				+ "'";
		DBoperation.connectDB();
		res = DBoperation.execsql(sql);
		DBoperation.disconnectDB();
		return res;
	}

	/**
	 * 删除评论
	 * */
	public static Boolean deleteCommentDatabase(String commentID) {
		Boolean res = true;
		String sql = "delete from comments where id='" + commentID + "'";
		DBoperation.connectDB();
		res = DBoperation.execsql(sql);
		DBoperation.disconnectDB();

		return res;
	}

	/**
	 * 获取用户所有点赞
	 * */
	public static List<LoveInfo> getUserPanosAllLoveDetails(String username) {
		List<LoveInfo> list = new ArrayList<>();
		String sql1 = " select pano_id from user_pano where username='"
				+ username + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> allPanoId = DBoperation.execSearch(sql1);
		int size = allPanoId.size();
		for (int i = 0; i < size; i++) {
			String temp_pano_id = allPanoId.get(i).get(0);
			String sql2 = "select id,username,love_time from love_detail where pano_id='"
					+ temp_pano_id + "'";
			Vector<Vector<String>> temp2 = DBoperation.execSearch(sql2);
			for (int j = 0; j < temp2.size(); j++) {
				LoveInfo loveInfo = new LoveInfo();
				loveInfo.setPano_id(temp_pano_id);
				loveInfo.setId(temp2.get(j).get(0));
				loveInfo.setUsername(temp2.get(j).get(1));
				loveInfo.setLove_time(temp2.get(j).get(2));
				String sql3 = "select pano_path from panorama where pano_id='"
						+ loveInfo.getPano_id() + "'";
				Vector<Vector<String>> temp3 = DBoperation.execSearch(sql3);
				if (temp3.size() > 0) {
					loveInfo.setPano_path(temp3.get(0).get(0));
				}
				list.add(loveInfo);
			}

		}
		DBoperation.disconnectDB();
		return list;
	}

	/**
	 * 更改密码
	 * */
	public static Boolean editPasswordByUsername(String username,
			String password) {
		Boolean res = true;
		String sql = "update user set password='" + password
				+ "' where username='" + username + "'";
		DBoperation.connectDB();
		res = DBoperation.execsql(sql);
		DBoperation.disconnectDB();
		return res;
	}

	/**
	 * 注册
	 * */
	public static String registuser(String username, String password,
			String email, String phone) {
		String result = "true";
		String sql1 = "select * from user where username='" + username + "'";
		DBoperation.connectDB();
		Vector<Vector<String>> temp1 = DBoperation.execSearch(sql1);
		if (temp1.size() != 0) {// 用户名已存在
			result = "exist";
		} else {
			String sql2 = "insert into user values('" + username + "','"
					+ password + "','" + email + "','" + phone + "')";
			Boolean res = DBoperation.execsql(sql2);
			if (res == true) {
				result = "success";
			} else {
				result = "fail";
			}
		}
		return result;
	}
}
