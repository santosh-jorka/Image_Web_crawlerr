package com.eulerity.hackathon.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsoupUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetImageUrls() {
		Set<String> imageUrls = new HashSet<String>();
		Document doc=null;
		try {
			doc = Jsoup.parse(new File("src/test/resources/HTMLFilesTest/startPage.html"),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> result =JsoupUtil.getImageUrls(doc, imageUrls, "https://www.dcmiller.com");
		
		Assert.assertEquals(result.size(), 5);
	}

	@Test
	public void testIsBase64Encoded() {
		Assert.assertEquals(true, JsoupUtil.isBase64Encoded("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII="));
	}

}
