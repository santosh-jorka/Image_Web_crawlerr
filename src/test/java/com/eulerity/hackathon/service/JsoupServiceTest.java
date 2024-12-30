package com.eulerity.hackathon.service;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsoupServiceTest {

	private JsoupService jsoupService;

	@Before
	public void setUp() throws Exception {
		jsoupService = new JsoupService();
	}

	@Test
	public void testResolveBaseUrl() {
		Assert.assertEquals("https://www.dcmiller.com", jsoupService.resolveBaseUrl("https://www.dcmiller.com/"));;
		
	}

	@Test
	public void testIsSameDomain() {
		Assert.assertEquals(true, jsoupService.isSameDomain("https://www.dcmiller.com", "https://www.dcmiller.com"));
	}

	@Test
	public void testGetHyperLinks() {
		Document doc=null;
		try {
			doc = Jsoup.parse(new File("src/test/resources/HTMLFilesTest/startPage.html"),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Set<String> result =jsoupService.getHyperLinks(doc, "https://www.dcmiller.com");
		Assert.assertEquals(result.size(), 0);
	}
}
