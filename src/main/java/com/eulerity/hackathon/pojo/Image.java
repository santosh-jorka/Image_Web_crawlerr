package com.eulerity.hackathon.pojo;

public class Image {
	
	private String imageUrl;
	
	private String type;

	public Image(String imageUrl, String type) {
		super();
		this.imageUrl = imageUrl;
		this.type = type;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
