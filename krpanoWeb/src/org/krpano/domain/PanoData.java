package org.krpano.domain;

/**
 * 发布或收藏交互数据格式封装
 * */
public class PanoData {
	private String pano_id;
	private String pano_path;
	private String pano_name;
	private String pano_type;
	private String info;
	private String cover_img;
	private String fabu_time;
	/**
	 * 背景音乐绝对路径，因为showpanorama界面使用了URL重写
	 * */
	private String background_music;
	/**
	 * 背景音乐歌曲名字
	 * */
	private String background_music_name;

	public String getBackground_music_name() {
		return background_music_name;
	}

	public void setBackground_music_name(String background_music_name) {
		this.background_music_name = background_music_name;
	}

	public String getBackground_music() {
		return background_music;
	}

	public void setBackground_music(String background_music) {
		this.background_music = background_music;
	}

	public String getPano_id() {
		return pano_id;
	}

	public void setPano_id(String pano_id) {
		this.pano_id = pano_id;
	}

	public String getPano_path() {
		return pano_path;
	}

	public void setPano_path(String pano_path) {
		this.pano_path = pano_path;
	}

	public String getPano_name() {
		return pano_name;
	}

	public void setPano_name(String pano_name) {
		this.pano_name = pano_name;
	}

	public String getPano_type() {
		return pano_type;
	}

	public void setPano_type(String pano_type) {
		this.pano_type = pano_type;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCover_img() {
		return cover_img;
	}

	public void setCover_img(String cover_img) {
		this.cover_img = cover_img;
	}

	public String getFabu_time() {
		return fabu_time;
	}

	public void setFabu_time(String fabu_time) {
		this.fabu_time = fabu_time;
	}

}
