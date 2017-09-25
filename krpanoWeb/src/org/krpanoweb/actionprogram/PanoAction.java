package org.krpanoweb.actionprogram;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.krpano.domain.IndexData;
import org.krpano.domain.PanoData;
import org.krpano.domain.Panorama;
import org.krpano.service.PanoService;

import com.opensymphony.xwork2.ActionSupport;

public class PanoAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	public HttpServletRequest request;
	/**
	 * 返回全部的全景信息(JSON数组字符串形式)
	 * */
	public String allPanosJsonString;
	/**
	 * 返回单个全景信息 JSON字符串
	 * */
	public String onePanoJsonString;
	/**
	 * 返回背景音乐资源路径 JSON字符串
	 * */
	public String panoMusic;
	/**
	 * 返回某一用户发布的所有全景
	 * */
	public String userAllPanosJsonString;
	/**
	 * 返回用户收藏的所有全景
	 * */
	public String userAllSavePanoJsonString;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

	public String getAllPano() throws InterruptedException {
		System.out.println("GetAllPano Action");
		List<IndexData> list = new ArrayList<>();
		list = PanoService.getAllPanoramas();
		JSONArray jsonArray = JSONArray.fromObject(list);
		allPanosJsonString = jsonArray.toString();
		return SUCCESS;
	}

	public String getPanoById() {
		System.out.println("getPanoById Action");
		String pano_id = request.getParameter("pano_id");
		System.out.println("pano_id = " + pano_id);
		Panorama panorama = new Panorama();
		panorama = PanoService.getPanoById(pano_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("data", panorama);
		onePanoJsonString = jsonObject.toString();
		System.out.println("onePanoJsonString: " + onePanoJsonString);
		return SUCCESS;
	}

	public String getMusicById() {
		System.out.println("GetMusicById Action");
		String pano_id = request.getParameter("pano_id");
		String musicPath = PanoService.getMusicById(pano_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("music", musicPath);
		panoMusic = jsonObject.toString();
		System.out.println("panoMusic: " + panoMusic);
		return SUCCESS;
	}

	public String gerUsersAllPanos() {
		System.out.println("GetUserAllPano Action");
		String username = request.getParameter("username");
		System.out.println("username:" + username);
		List<PanoData> list = new ArrayList<>();
		list = PanoService.getUsersAllPanos(username);
		JSONArray jsonArray = JSONArray.fromObject(list);
		userAllPanosJsonString = jsonArray.toString();
		return SUCCESS;
	}

	public String getUsersAllSavePanos() {
		System.out.println("GetUserAllSave Action");
		String username = request.getParameter("username");
		System.out.println("username:" + username);
		List<PanoData> list = new ArrayList<>();
		list = PanoService.getUsersAllSave(username);
		JSONArray jsonArray = JSONArray.fromObject(list);
		userAllSavePanoJsonString = jsonArray.toString();
		return SUCCESS;
	}
}
