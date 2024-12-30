package com.eulerity.hackathon.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	
	 private static final Pattern BASE64_PATTERN = Pattern.compile(
	            "^data:image/(png|jpeg|jpg|gif);base64,([A-Za-z0-9+/]+={0,2})$");

	
	public static Set<String> getImageUrls(Document doc, Set<String> imageUrls, String baseUrl) {
		Elements imageElements = doc.select("img");
		for (Element imageElement : imageElements) {
			String imageUrl = imageElement.attr("src");
			//System.out.println(imageUrl);
			if (imageUrl.startsWith("https")) {
				imageUrls.add(imageUrl);
			} else if(isBase64Encoded(imageUrl)){
				imageUrls.add(imageElement.attr("data-src"));
			}else {
				imageUrls.add(baseUrl + imageUrl);
			}
		}
		
		return imageUrls;
	}
	
	public static boolean isBase64Encoded(String src) {
        return BASE64_PATTERN.matcher(src).matches();
    }

}
