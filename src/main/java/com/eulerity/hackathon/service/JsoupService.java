package com.eulerity.hackathon.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupService {
	
	public Document getDocument(String url) {
		Connection con = Jsoup.connect(url);
		Document doc = null;
		try {
			 doc = con.get();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return doc;
	}
	
	public String resolveBaseUrl(String documentUrl) {
		try {
			URI uri = new URI(documentUrl);

			String scheme = uri.getScheme();
			String host = uri.getHost();

			StringBuilder baseUrlBuilder = new StringBuilder();
			baseUrlBuilder.append(scheme).append("://").append(host);

			return baseUrlBuilder.toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public  boolean isSameDomain(String url, String baseURI) {
		try {
			URI baseUri = new URI(baseURI);
			URI uri = new URI(url);
			return baseUri.getHost().equalsIgnoreCase(uri.getHost());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		}
	}
			
	public Set<String> getHyperLinks(Document doc, String baseUrl) {
		Set<String> hyperLinks = new HashSet<>();
		Elements hyperLinkElements = doc.select("a[href]");
		for (Element hyperLinkElement : hyperLinkElements) {
			String hyperLink = hyperLinkElement.attr("href");
			if (!hyperLink.startsWith("https")) {
				hyperLink = baseUrl + hyperLink;
			}
			if (isSameDomain(hyperLink, baseUrl)) {
				hyperLinks.add(hyperLink);
			}
		}
		return hyperLinks;
	}

}
