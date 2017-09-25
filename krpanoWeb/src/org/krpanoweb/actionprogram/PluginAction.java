package org.krpanoweb.actionprogram;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.krpano.domain.Comment;
import org.krpano.service.PluginService;

import com.opensymphony.xwork2.ActionSupport;

public class PluginAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	public HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

	public String viewNumJsonString;
	public String loveNumJsonString;
	public String loveNumAfterChange;
	public String allComments;
	public String addCommentResult;

	/**
	 * 数据库中浏览量+1，并返回更改后的数据
	 * */
	public String getViewNum() {
		System.out.println("GetViewNum Action");
		String pano_id = request.getParameter("pano_id");
		System.out.println("pano_id = " + pano_id);
		String viewNum = PluginService.getView(pano_id);
		System.out.println("viewNum=" + viewNum);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("viewNum", viewNum);
		viewNumJsonString = jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 获取点赞量
	 * */
	public String getLoveNum() {
		System.out.println("GetLoveNum Action");
		String pano_id = request.getParameter("pano_id");
		System.out.println("pano_id = " + pano_id);
		String loveNum = PluginService.getLove(pano_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("loveNum", loveNum);
		loveNumJsonString = jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 添加点赞
	 * */
	public String addAgree() {
		System.out.println("AddAgree Action");
		String username = request.getParameter("username");
		String pano_id = request.getParameter("pano_id");
		String love_time = request.getParameter("love_time");
		System.out.println("username:" + username + " pano_id:" + pano_id
				+ " love_time:" + love_time);
		String nowLove = PluginService.addLove(pano_id);
		Boolean status = PluginService.saveLoveDetail(username, pano_id,
				love_time);
		System.out.println("status:" + status);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("loveNum", nowLove);
		loveNumAfterChange = jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 获取所有评论
	 * */
	public String getAllComment() {
		System.out.println("GetAllComments Action");
		String pano_id = request.getParameter("pano_id");
		System.out.println("pano_id:" + pano_id);
		List<Comment> list = new ArrayList<>();
		list = PluginService.getCommentsById(pano_id);
		JSONArray jsonArray = JSONArray.fromObject(list);
		allComments = jsonArray.toString();
		System.out.println("allComments:" + allComments);
		return SUCCESS;
	}

	/**
	 * 添加评论
	 * */
	public String addComment() {
		System.out.println("AddComment Action");
		String username = request.getParameter("username");
		String pano_id = request.getParameter("pano_id");
		String content = request.getParameter("content");
		String ath = request.getParameter("ath");
		String atv = request.getParameter("atv");
		String comment_time = request.getParameter("comment_time");
		System.out.println("username:" + username + " pano:" + pano_id
				+ " content:" + content + " ath:" + ath + " atv:" + atv
				+ " comment_time:" + comment_time);
		Comment comment = new Comment();
		comment.setUsername(username);
		comment.setPano_id(pano_id);
		comment.setContent(content);
		comment.setAth(ath);
		comment.setAtv(atv);
		comment.setComment_time(comment_time);
		Boolean res = PluginService.addCommentToDB(comment);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", res);
		addCommentResult = jsonObject.toString();
		return SUCCESS;
	}
}
