package com.eulerity.hackathon.webcrawl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

import org.jsoup.nodes.Document;

import com.eulerity.hackathon.pojo.URLDetails;
import com.eulerity.hackathon.service.JsoupService;
import com.eulerity.hackathon.util.JsoupUtil;

public class WebCrawl extends RecursiveTask<URLDetails> {

	public Set<String> urlSet;

	private URLDetails urlDetails;

	private JsoupService jsoupService;

	public WebCrawl(Set<String> urlSet, URLDetails urlDetails, JsoupService jsoupService) {
		this.urlDetails = urlDetails;
		this.urlSet = urlSet;
		this.jsoupService = jsoupService;
	}

	public Set<String> getUrlSet() {
		return urlSet;
	}

	public URLDetails getUrlDetails() {
		return urlDetails;
	}

	public void setUrlDetails(URLDetails urlDetails) {
		this.urlDetails = urlDetails;
	}

	public void setUrlSet(Set<String> urlSet) {
		this.urlSet = urlSet;
	}

	@Override
	protected URLDetails compute() {
		Set<String> visitedUrls = this.getUrlDetails().getVisitedUrls();
		Set<String> imageUrls = this.getUrlDetails().getImageUrls();
		Set<String> uRLset = this.getUrlSet();
		if (uRLset.size() == 1 && !visitedUrls.contains(uRLset.stream().findFirst().orElse(""))) {
			String url = uRLset.stream().findFirst().orElse("");

			visitedUrls.add(url);
			String baseUrl = jsoupService.resolveBaseUrl(url);
			Document doc = jsoupService.getDocument(url);
			if (doc != null) {
				imageUrls.addAll(JsoupUtil.getImageUrls(doc, imageUrls, baseUrl));
				Set<String> hyperLinks = jsoupService.getHyperLinks(doc, baseUrl);
				hyperLinks.removeAll(visitedUrls);
				System.out.println("Visiting Url: " + url + "  HyperLinks:" + hyperLinks.size());
				if (!hyperLinks.isEmpty()) {
					List<String> elementList = new ArrayList<>(hyperLinks);
					if (elementList.size() == 1) {
						return singleElementInUrlSet(elementList);
					} else {
						return forkAndJoin(elementList, visitedUrls);
					}
				}
			}
		} else {
			List<String> elementList = new ArrayList<>(uRLset);
			if (elementList.size() == 1) {
				return singleElementInUrlSet(elementList);
			} else {
				return forkAndJoin(elementList, visitedUrls);
			}
		}
		return this.getUrlDetails();
	}

	private URLDetails singleElementInUrlSet(List<String> elementList) {
		Set<String> singleElementSet = new HashSet<>();
		singleElementSet.add(elementList.get(0));
		WebCrawl webcrawl = new WebCrawl(singleElementSet, this.getUrlDetails(), jsoupService);
		webcrawl.fork();
		return webcrawl.join();
	}

	private URLDetails forkAndJoin(List<String> elementList, Set<String> visitedUrls) {
		if (!elementList.isEmpty()) {
			int numberOfElementsInSet = 0;
			int size = elementList.size();
			int n = 100;
			if (size >= n) {
				if (size % n == 0) {
					numberOfElementsInSet = size / n;
				} else {
					int remainder = size % n;
					numberOfElementsInSet = (size - remainder) / n;
				}

				int mid = elementList.size() / 2;
				/// System.out.println(size);
				int initialIdex = 0;
				List<WebCrawl> webCrawlList = new ArrayList();
				Set<String> resultImages = new HashSet<>();
				for (int i = 1; i <= 10; i++) {
					Set<String> set = new HashSet<>(elementList.subList(initialIdex, i * numberOfElementsInSet));
					WebCrawl webcrawl = new WebCrawl(set, this.getUrlDetails(), jsoupService);
					initialIdex = i * numberOfElementsInSet;
					webCrawlList.add(webcrawl);
				}

				invokeAll(webCrawlList);
				for (WebCrawl webCrawl : webCrawlList) {
					resultImages.addAll(webCrawl.join().getImageUrls());
				}
				return new URLDetails(resultImages, visitedUrls);
			} else {
				List<WebCrawl> webCrawlList = new ArrayList();
				Set<String> resultImages = new HashSet<>();
				for (String element : elementList) {
					Set<String> set = new HashSet<>();
					set.add(element);
					WebCrawl webcrawl = new WebCrawl(set, this.getUrlDetails(), jsoupService);
					webCrawlList.add(webcrawl);
				}
				invokeAll(webCrawlList);

				for (WebCrawl webCrawl : webCrawlList) {
					resultImages.addAll(webCrawl.join().getImageUrls());
				}
				return new URLDetails(resultImages, visitedUrls);
			}

		}
		return new URLDetails(this.getUrlDetails().getImageUrls(), this.getUrlDetails().getVisitedUrls());
	}

}
