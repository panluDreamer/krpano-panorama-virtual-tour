package org.krpano.domain;
/**
 * 用户评论类
 * */
public class UserComment {
	private int id;
	private String comment_username;
	private String comment_pano_id;
	private String comment_content;
	private String comment_time;
	private String pano_path;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment_username() {
		return comment_username;
	}

	public void setComment_username(String comment_username) {
		this.comment_username = comment_username;
	}

	public String getComment_pano_id() {
		return comment_pano_id;
	}

	public void setComment_pano_id(String comment_pano_id) {
		this.comment_pano_id = comment_pano_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public String getPano_path() {
		return pano_path;
	}

	public void setPano_path(String pano_path) {
		this.pano_path = pano_path;
	}

}
