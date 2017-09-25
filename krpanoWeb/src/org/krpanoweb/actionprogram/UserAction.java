package org.krpanoweb.actionprogram;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.krpano.domain.CoverImg;
import org.krpano.domain.IndexData;
import org.krpano.domain.LoveInfo;
import org.krpano.domain.Music;
import org.krpano.domain.User;
import org.krpano.domain.UserComment;
import org.krpano.service.SearchService;
import org.krpano.service.UserService;
import org.krpano.util.DBoperation;
import org.krpano.util.FileUtil;
import org.krpano.util.SpaceGetter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {// 初始化request
		request = arg0;
	}

	/**
	 * 返回的登陆状态(JSON字符串的格式)
	 * */
	public String loginResult;
	/**
	 * 注册状态
	 * */
	public String registResult;
	/**
	 * 退出登陆状态
	 * */
	public String logoutResult;
	/**
	 * 当前登录用户
	 * */
	public String currentLoginUser;
	/**
	 * 收藏状态返回
	 * */
	public String savePanoStatus;
	/**
	 * 用户的全部收藏
	 * */
	public String userAllSavePanos;
	/**
	 * 用户个人信息
	 * */
	public String userInfo;
	/**
	 * 用户空间使用量
	 * */
	public String userSpaceJsonString;
	/**
	 * 删除收藏状态
	 * */
	public String deleteSavePanosString;
	/**
	 * 用户素材库全部图片
	 * */
	public String allImages;
	/**
	 * 用户已发布全景上的所有评论
	 * */
	public String allComments;
	/**
	 * 关键字搜索结果
	 * */
	public String searchResult;
	/**
	 * 全景删除结果
	 * */
	public String deleteFabuResult;
	/**
	 * 全景修改结果
	 * */
	public String modifyStatus;
	/**
	 * 图片库方式修改结果
	 * */
	public String modifyImageKu;
	/**
	 * 音乐库返回
	 * */
	public String allMusics;
	/**
	 * 音乐库方式修改结果
	 * */
	public String modifyMusicKu;
	/**
	 * 图片库中的图片删除结果
	 * */
	public String imageDatabaseDelete;
	/**
	 * 音乐库的音乐删除结果
	 * */
	public String musicDatabaseDelete;
	/**
	 * 评论删除结果
	 * */
	public String commentDelete;
	/**
	 * 点赞信息返回
	 * */
	public String loveDetailInfo;
	/**
	 * 更改密码返回结果
	 * */
	public String passwordEdit;

	/**
	 * 登陆操作
	 * */
	public String login() throws InterruptedException {
		System.out.println("login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out
				.println("username = " + username + " password = " + password);
		DBoperation.connectDB();// 连接数据库
		String pass = UserService.getPswByUsername(username);
		DBoperation.disconnectDB();
		System.out.println("pass = " + pass);
		String checkResult;
		if (pass.equals("null")) {
			checkResult = "no_exist";
		} else if (!pass.equals(password)) {
			checkResult = "wrong";
		} else {
			checkResult = "true";
			Map<String, Object> session = ActionContext.getContext()
					.getSession();// 加入session,后续检查登陆状态
			session.put("username", username);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", checkResult);
		loginResult = jsonObject.toString();
		Thread.sleep(1000);// 模拟延时操作
		System.out.println("loginResult = " + loginResult);
		return SUCCESS;
	}

	/**
	 * 注册操作
	 * */
	public String register() throws InterruptedException {
		System.out.println("Register Action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		System.out.println("username:" + username + " pasword:" + password
				+ " email:" + email + " phone:" + phone);
		String status = UserService
				.registuser(username, password, email, phone);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", status);
		registResult = jsonObject.toString();
		// registResult = Boolean.toString(true);
		return SUCCESS;
	}

	/**
	 * 退出登录
	 * */
	public String logout() {
		System.out.println("logout");
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("username");
		// System.out.println("退出之后，session遍历：");
		// for (Map.Entry<String, Object> entry : session.entrySet()) {
		// System.out.println(entry.getKey() + "   " + entry.getValue());
		// }
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", "true");
		logoutResult = jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 获取当前登录用户
	 * */
	public String getCurrLoginUser() {
		System.out.println("GetCurrLoginUser Action");
		Object loginUserName = ActionContext.getContext().getSession()
				.get("username");
		JSONObject jsonObject = new JSONObject();
		if (loginUserName == null) {
			jsonObject.element("user", "nouser");
		} else {
			jsonObject.element("user", loginUserName);
		}
		currentLoginUser = jsonObject.toString();
		System.out.println("currentLoginUser:" + currentLoginUser);
		return SUCCESS;
	}

	/**
	 * 一个空方法,检测用户登录状态
	 * */
	public String checkUser() {
		return SUCCESS;
	}

	/**
	 * 收藏全景
	 * */
	public String savePano() {
		System.out.println("SavePano Action");
		String username = request.getParameter("username");
		String pano_id = request.getParameter("pano_id");
		String save_time = request.getParameter("save_time");
		System.out.println("username:" + username + " pano_id:" + pano_id
				+ " save_time:" + save_time);
		String res = UserService.savePanoToDB(username, pano_id, save_time);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", res);
		savePanoStatus = jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 获取所有收藏全景
	 * */
	public String getAllSavePanos() throws InterruptedException {
		System.out.println("GetUserAllSave Action");
		String username = request.getParameter("username");
		System.out.println("username:" + username);
		List<String> allPanos = new ArrayList<>();
		allPanos = UserService.getSavePanoIdByUsername(username);
		JSONArray jsonArray = JSONArray.fromObject(allPanos);
		// Thread.sleep(2000);
		userAllSavePanos = jsonArray.toString();
		return SUCCESS;
	}

	/**
	 * 获取用户个人信息
	 * */
	public String getUsersInfo() throws InterruptedException {
		System.out.println("GetUserInfo Action");
		String username = request.getParameter("username");
		System.out.println("username:" + username);
		User user = new User();
		user = UserService.getUserInfoByName(username);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("username", user.getUsername());
		jsonObject.element("password", user.getPassword());
		jsonObject.element("mail", user.getMail());
		jsonObject.element("telephone", user.getTelephone());
		userInfo = jsonObject.toString();
		// Thread.sleep(2000);
		return SUCCESS;
	}

	/**
	 * 获取用户空间使用量
	 * */
	public String getSpaceByUsername() throws InterruptedException {
		System.out.println("GetUserSpace Action");
		String username = request.getParameter("username");
		System.out.println("username:" + username);
		String panoPath = org.krpano.util.Path.basicPath + "/user/" + username;
		String imageSucaiPath = org.krpano.util.Path.basicPath + "/user/"
				+ username + "/image";
		String musicSucaiPath = org.krpano.util.Path.basicPath + "/user/"
				+ username + "/music";
		long panoSpace = SpaceGetter.getSpace(panoPath);
		long imageSpace = SpaceGetter.getSpace(imageSucaiPath);
		long musicSpace = SpaceGetter.getSpace(musicSucaiPath);
		long truePanoSpace = panoSpace - imageSpace - musicSpace;
		System.out.println("panoSpace:" + truePanoSpace + " imageSpace:"
				+ imageSpace + " musicSpace:" + musicSpace);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("panoSpace", truePanoSpace);
		jsonObject.element("musicSpace", musicSpace);
		jsonObject.element("imageSpace", imageSpace);
		userSpaceJsonString = jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 取消收藏
	 * */
	public String deleteUserSave() throws InterruptedException {
		System.out.println("DeleteSavePano Action");
		String username = request.getParameter("username");
		String pano_id = request.getParameter("pano_id");
		System.out.println("username=" + username + " pano_id=" + pano_id);
		Boolean res = UserService.deleteUserSaves(username, pano_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", String.valueOf(res));
		Thread.sleep(1000);// 模拟延时，使得进度条可见
		deleteSavePanosString = jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 获取用户图片库
	 * */
	public String getAllImagesByUsername() {
		System.out.println("GetUsersAllImg Action");
		String username = request.getParameter("username");
		System.out.println("username = " + username);
		String filePath = org.krpano.util.Path.basicPath + "/user/" + username
				+ "/image";
		Vector<String> temp = traverseFolder(filePath);
		List<CoverImg> imageUrl = new ArrayList<>();
		int size = temp.size();
		for (int i = 0; i < size; i++) {
			CoverImg coverImg = new CoverImg();
			coverImg.setImagePath("user/" + username + "/image/" + temp.get(i));
			coverImg.setImageName(temp.get(i));
			imageUrl.add(coverImg);
		}
		JSONArray jsonArray = JSONArray.fromObject(imageUrl);
		allImages = jsonArray.toString();
		System.out.println("全部素材图片路径为:" + allImages);
		return SUCCESS;
	}

	/**
	 * 获取用户已发布全景的所有评论
	 * */
	public String getAllUserPanosCmt() {
		System.out.println("GetUserPanosComments action");
		String username = request.getParameter("username");
		System.out.println("username:" + username);
		List<UserComment> list = new ArrayList<>();
		list = UserService.getUserPanosTotallyComment(username);
		JSONArray jsonArray = JSONArray.fromObject(list);
		allComments = jsonArray.toString();
		return SUCCESS;
	}

	/**
	 * 这里只使用最简单的LIKE关键字实现搜索
	 * */
	public String searchPanorama() {
		System.out.println("Search action");
		String keyword = request.getParameter("keyword");
		System.out.println("keyword:" + keyword);
		List<IndexData> list = new ArrayList<>();
		String identify = keyword.substring(0, 2);
		System.out.println("identify:" + identify);
		if (identify.equals("作者")) {
			String searchUser = keyword.substring(3);
			System.out.println("searchUser:" + searchUser);
			list = SearchService.searchByUsername(searchUser);
		} else if (identify.equals("标题")) {
			String searchTitle = keyword.substring(3);
			System.out.println("search title:" + searchTitle);
			list = SearchService.searchByTitleOrContent(searchTitle, "title");
		} else if (identify.equals("内容")) {
			String searchContent = keyword.substring(3);
			System.out.println("searchContent:" + searchContent);
			list = SearchService.searchByTitleOrContent(searchContent,
					"content");
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		searchResult = jsonArray.toString();
		System.out.println("searchResult:" + searchResult);
		return SUCCESS;
	}

	/**
	 * 删除已发布全景
	 * */
	public String deleteUserFabu() throws InterruptedException {
		System.out.println("DeleteFabuPano Action");
		String username = request.getParameter("username");
		String pano_id = request.getParameter("pano_id");
		System.out.println("username:" + username + " pano_id:" + pano_id);
		Boolean result = UserService.deletePanos(username, pano_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", result.toString());
		deleteFabuResult = jsonObject.toString();
		Thread.sleep(500);
		return SUCCESS;
	}

	/**
	 * 编辑全景
	 * */
	public String modifyUserPanos() throws InterruptedException {
		System.out.println("ModifyPano Action");
		String pano_name = request.getParameter("pano_name");
		String pano_info = request.getParameter("pano_info");
		String pano_type = request.getParameter("pano_type");
		String pano_id = request.getParameter("pano_id");
		System.out.println("pano_id:" + pano_id + " pano_name:" + pano_name
				+ " pano_info:" + pano_info + " pano_type:" + pano_type);
		JSONObject jsonObject = new JSONObject();
		Boolean res = UserService.modifyPanos(pano_id, pano_name, pano_info,
				pano_type);
		jsonObject.element("status", res.toString());
		modifyStatus = jsonObject.toString();
		Thread.sleep(500);
		return SUCCESS;
	}

	/**
	 * 图片库方式更改全景封面
	 * */
	public String modifyImagesByKu() throws InterruptedException {
		System.out.println("ModifyImageByKu Action");
		String pano_id = request.getParameter("pano_id");
		String image_path = request.getParameter("image_path");
		System.out.println("pano_id:" + pano_id + " image_path:" + image_path);

		JSONObject jsonObject = new JSONObject();
		Boolean res = UserService.modifyCoverImage(pano_id, image_path);
		jsonObject.element("status", res.toString());
		Thread.sleep(1000);
		modifyImageKu = jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 音乐库方式更改全景漫游背景音乐
	 * */
	public String modifyMusicsByKu() throws InterruptedException {
		System.out.println("ModifyMusicByKu Action");
		String pano_id = request.getParameter("pano_id");
		String music_path = request.getParameter("music_path");
		String music_name = request.getParameter("music_name");
		System.out.println("pano_id:" + pano_id + " music_path:" + music_path
				+ " music_name:" + music_name);

		JSONObject jsonObject = new JSONObject();
		Boolean res = UserService.modifyMusic(pano_id, music_path, music_name);
		jsonObject.element("status", res.toString());
		modifyMusicKu = jsonObject.toString();
		Thread.sleep(1000);
		return SUCCESS;
	}

	/**
	 * 获取用户音乐库
	 * */
	public String getuserAllMusics() {
		System.out.println("GetAllMusicsByUsername Action");
		String username = request.getParameter("username");
		System.out.println("username:" + username);
		List<Music> list = new ArrayList<>();
		String filePath = org.krpano.util.Path.basicPath + "/user/" + username
				+ "/music";
		Vector<String> temp = traverseFolder(filePath);
		int size = temp.size();
		for (int i = 0; i < size; i++) {
			Music music = new Music();
			music.setMusicPath(org.krpano.util.Path.host + "/user/" + username
					+ "/music/" + temp.get(i));
			music.setMusicName(temp.get(i));
			list.add(music);
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		allMusics = jsonArray.toString();
		System.out.println("allMusics:" + allMusics);
		return SUCCESS;
	}

	/**
	 * 删除图片库中的图片
	 * */
	public String deleteCoverImgByName() throws InterruptedException {
		System.out.println("DeleteCoverImg Action");
		String username = request.getParameter("username");
		String image_name = request.getParameter("image_name");
		System.out
				.println("username:" + username + " image_name:" + image_name);
		String dirpath = org.krpano.util.Path.basicPath + "/user/" + username
				+ "/image";
		System.out.println("dirpath:" + dirpath);
		Boolean res = FileUtil.deleteFileByPathAndName(dirpath, image_name);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", res.toString());
		imageDatabaseDelete = jsonObject.toString();
		Thread.sleep(500);
		return SUCCESS;
	}

	/**
	 * 删除音乐库中的音乐
	 * */
	public String deleteBGMusicByName() throws InterruptedException {
		System.out.println("DeleteMusic Action");
		String username = request.getParameter("username");
		String music_name = request.getParameter("music_name");
		System.out
				.println("username:" + username + " music_name:" + music_name);
		String dirpath = org.krpano.util.Path.basicPath + "/user/" + username
				+ "/music";
		System.out.println("music dirpath:" + dirpath);
		Boolean res = FileUtil.deleteFileByPathAndName(dirpath, music_name);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", res.toString());
		musicDatabaseDelete = jsonObject.toString();
		Thread.sleep(500);
		return SUCCESS;
	}

	/**
	 * 删除全景漫游场景中的评论
	 * */
	public String deleteCommentByCommentID() throws InterruptedException {
		System.out.println("DeleteComment Action");
		String commentID = request.getParameter("commentId");
		System.out.println("commentID:" + commentID);
		Boolean res = UserService.deleteCommentDatabase(commentID);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", res.toString());
		commentDelete = jsonObject.toString();
		Thread.sleep(500);
		return SUCCESS;
	}

	/**
	 * 获取点赞详情
	 * */
	public String getUserLoveDetail() {

		System.out.println("GetUserLoveInfo Action");
		String username = request.getParameter("username");
		System.out.println("username:" + username);

		List<LoveInfo> list = new ArrayList<>();
		list = UserService.getUserPanosAllLoveDetails(username);
		JSONArray jsonArray = JSONArray.fromObject(list);
		loveDetailInfo = jsonArray.toString();
		System.out.println("loveDetailInfo:" + loveDetailInfo);
		return SUCCESS;
	}

	/**
	 * 更改用户密码
	 * */
	public String editUserPassWord() {
		System.out.println("EditPassWord Action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:" + username + " password:" + password);

		JSONObject jsonObject = new JSONObject();
		Boolean res = UserService.editPasswordByUsername(username, password);
		jsonObject.element("status", res.toString());
		passwordEdit = jsonObject.toString();

		return SUCCESS;
	}

	/**
	 * 返回文件夹下的所有一级文件名字
	 * */
	public static Vector<String> traverseFolder(String folderPath) {
		Vector<String> imgPath = new Vector<>();
		File file = new File(folderPath);
		if (file.exists()) {
			File[] files = file.listFiles();
			int length = files.length;
			// System.out.println("length = " + length);
			for (int i = 0; i < length; i++) {
				// System.out.println(files[i].getAbsolutePath());
				imgPath.add(files[i].getName());
			}
		} else {
			System.out.println("文件不存在");
		}
		return imgPath;
	}
}
