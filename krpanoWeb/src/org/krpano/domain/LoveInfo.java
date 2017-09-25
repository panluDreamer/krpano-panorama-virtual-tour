package org.krpano.domain;

/**
 * 点赞详情
 * */
public class LoveInfo {
	private String id;
	private String username;
	private String pano_id;
	private String love_time;
	private String pano_path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getLove_time() {
		return love_time;
	}

	public void setLove_time(String love_time) {
		this.love_time = love_time;
	}

	public String getPano_path() {
		return pano_path;
	}

	public void setPano_path(String pano_path) {
		this.pano_path = pano_path;
	}

}
