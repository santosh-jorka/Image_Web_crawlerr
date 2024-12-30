package com.eulerity.hackathon.pojo;

import java.util.Set;

public class URLDetails {

	private Set<String> imageUrls;
	
	private Set<String> visitedUrls;

	public URLDetails( Set<String> imageUrls, Set<String> visitedUrls) {
		super();
		this.imageUrls = imageUrls;
		this.visitedUrls = visitedUrls;
	}

	public Set<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(Set<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public Set<String> getVisitedUrls() {
		return visitedUrls;
	}

	public void setVisitedUrls(Set<String> visitedUrls) {
		this.visitedUrls = visitedUrls;
	}

}
