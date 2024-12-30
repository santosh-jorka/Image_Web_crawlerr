package com.eulerity.hackathon.webcrawl;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.eulerity.hackathon.pojo.URLDetails;
import com.eulerity.hackathon.service.JsoupService;

import junit.framework.Assert;


public class WebCrawlTest {
	
	JsoupService jsoupService;

	@Before
	public void setUp() throws Exception {
		jsoupService = Mockito.mock(JsoupService.class);
	}
	
	@Test
	public void testCompute_no_hyperlinks() {
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		String url = "https:\\www.apple.com";
		System.out.print(url);
		URLDetails urlDetails = new URLDetails(new HashSet<>(), new HashSet<>());
		Set<String> urlSet =  new HashSet<>();
		urlSet.add(url);
		WebCrawl webCrawl = new WebCrawl(urlSet, urlDetails,jsoupService);
		
		Document doc=null;
		try {
			doc = Jsoup.parse(new File("E:/imagefinder-2022-06-01/imagefinder/src/test/resources/HTMLFilesTest/startPage.html"),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Mockito.when(jsoupService.resolveBaseUrl(Mockito.anyString())).thenReturn("https:\\\\www.apple.com");
		Mockito.when(jsoupService.getDocument(Mockito.anyString())).thenReturn(doc);
		
		
		URLDetails result = webCrawl.compute();
		
		Assert.assertEquals(result.getImageUrls().size(), 5);
	}	
	
	@Test
	public void testCompute_100_hyperlinks() {
		ForkJoinPool commonPool = new ForkJoinPool(10000);
		String url = "https:\\www.apple.com";
		System.out.print(url);
		URLDetails urlDetails = new URLDetails( new HashSet<>(), new HashSet<>());
		Set<String> urlSet =  new HashSet<>();
		urlSet.add(url);
		WebCrawl webCrawl = new WebCrawl(urlSet, urlDetails,jsoupService);
		
		Document doc=null;
		try {
			doc = Jsoup.parse(new File("E:/imagefinder-2022-06-01/imagefinder/src/test/resources/HTMLFilesTest/startPage.html"),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> hyperLinks = new HashSet();
		for(int i=1;i<=110;i++) {
			hyperLinks.add("page"+i+".html");
		}
		
 		Mockito.when(jsoupService.resolveBaseUrl(Mockito.anyString())).thenReturn("https:\\\\www.apple.com");
		Mockito.when(jsoupService.getDocument(Mockito.anyString())).thenReturn(doc);
		Mockito.when(jsoupService.getHyperLinks(Mockito.any(),Mockito.anyString())).thenReturn(hyperLinks).thenReturn(new HashSet<>());
		
		
		URLDetails result = commonPool.invoke(webCrawl);
		
		Assert.assertEquals(result.getImageUrls().size(), 5);
	}
	
	@Test
	public void testCompute_25_hyperlinks() {
		ForkJoinPool commonPool = new ForkJoinPool(10000);
		String url = "https:\\www.apple.com";
		System.out.print(url);
		URLDetails urlDetails = new URLDetails( new HashSet<>(), new HashSet<>());
		Set<String> urlSet =  new HashSet<>();
		urlSet.add(url);
		WebCrawl webCrawl = new WebCrawl(urlSet, urlDetails,jsoupService);
		
		Document doc=null;
		try {
			doc = Jsoup.parse(new File("E:/imagefinder-2022-06-01/imagefinder/src/test/resources/HTMLFilesTest/startPage.html"),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> hyperLinks = new HashSet();
		for(int i=1;i<=25;i++) {
			hyperLinks.add("page"+i+".html");
		}
		
 		Mockito.when(jsoupService.resolveBaseUrl(Mockito.anyString())).thenReturn("https:\\\\www.apple.com");
		Mockito.when(jsoupService.getDocument(Mockito.anyString())).thenReturn(doc);
		Mockito.when(jsoupService.getHyperLinks(Mockito.any(),Mockito.anyString())).thenReturn(hyperLinks).thenReturn(new HashSet<>());
		
		
		URLDetails result = commonPool.invoke(webCrawl);
		
		Assert.assertEquals(result.getImageUrls().size(), 5);
	}

}
