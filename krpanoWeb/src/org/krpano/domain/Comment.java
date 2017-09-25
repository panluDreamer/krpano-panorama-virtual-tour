package org.krpano.domain;

/**
 * 评论类
 * */
public class Comment {
	private String username;
	private String pano_id;
	private String content;
	private String ath;
	private String atv;
	private String comment_time;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPano_id() {
		return pano_id;
	}

	public void setPano_id(String pano_id) {
		this.pano_id = pano_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAth() {
		return ath;
	}

	public void setAth(String ath) {
		this.ath = ath;
	}

	public String getAtv() {
		return atv;
	}

	public void setAtv(String atv) {
		this.atv = atv;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

}
