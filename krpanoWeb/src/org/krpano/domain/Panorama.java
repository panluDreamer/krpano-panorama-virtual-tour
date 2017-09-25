package org.krpano.domain;

public class Panorama {
	private String pano_id;
	private String pano_path;
	private String pano_name;
	private String pano_type;
	private String info;
	private String cover_img;
	
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


}
