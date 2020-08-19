package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.MenuItem;

import static utils.LogUtils.print;

public class WebPageParser {

	public static String parseDynamicURL() {
		return parseDynamicURL("https://www.111gei.com/");
	}

	public static String parseDynamicURL(String websitePath) {

		try {
			ArrayList<String> url2 = new ArrayList<>();

			Document doc = Jsoup.connect(websitePath).get();
			Element head = doc.head();

			List<Node> nodes = head.childNodes();
			for (Node node : nodes) {

				if ("script".equals(node.nodeName())) {
					String jsCode = node.toString();

					String targetStartStr = "url2.push(\"";
					String targetEndStr = "\");";
					int indexOfStart;
					int indexOfEnd;
					int startIndex = 0;
					while ((indexOfStart = jsCode.indexOf(targetStartStr, startIndex)) != -1) {

						indexOfEnd = jsCode.indexOf(targetEndStr, indexOfStart);
						String targetURL = jsCode.substring(indexOfStart + targetStartStr.length(), indexOfEnd);
						url2.add(targetURL);
						System.out.println(targetURL);
						startIndex = indexOfEnd;
					}

					break;
				}
			}

			long timestamp = System.currentTimeMillis();
			int day1 = (int) ((timestamp + 8 * 3600000) / 86400000);
			String url3 = "https://" + url2.get(day1 % url2.size());
			System.out.println("final: " + url3);
			return url3;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static HashMap<String, ArrayList<MenuItem>> parseCategory(String indexSiteURL) {

		HashMap<String, ArrayList<MenuItem>> menuMap = new HashMap<>();

		try {

			Document doc = Jsoup.connect(indexSiteURL).get();
			Element body = doc.body();

			Elements as = body.select(".menu-wrap");
			Elements aas = as.get(0).select("a");

			ArrayList<MenuItem> itemList = new ArrayList<>();
			for (Element element : aas) {
				String url = element.attr("href");
				String name = element.text();

				if ("#".equals(url)) {
					itemList = new ArrayList<>();
					menuMap.put(name, itemList);
				} else {
					itemList.add(new MenuItem(name, url));
				}
			}

//			for (HashMap.Entry<String, ArrayList<MenuItem>> entry : menuMap.entrySet()) {
//				String categoryName = entry.getKey();
//				ArrayList<MenuItem> categoryContent = entry.getValue();
//
//				print(categoryName);
//
//				for (MenuItem item : categoryContent) {
//					print("└---- " + item.getTitle() + " : " + item.getUrl());
//
//				}
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return menuMap;
	}

	public static void parseMoviePage(String pageUrl) {

		try {

			Document doc = Jsoup.connect(pageUrl).get();
//			print(doc);
//
			Elements modElements = doc.select(".mod");
//			print(modElements.size());
			Elements aElements = modElements.get(0).select("a");

//			ArrayList<MenuItem> itemList = new ArrayList<>();
			for (Element element : aElements) {

				if ("dt".equals(element.parent().nodeName())){
					String url = element.attr("href");
					String name = element.attr("title");

					print("pic  : " + element.select(".nature").attr("data-original"));
					print("title: " + name);
					print("url  : " + url);
				}

				print("------------");
//				if ("#".equals(url)) {
//					itemList = new ArrayList<>();
//					menuMap.put(name, itemList);
//				} else {
//					itemList.add(new MenuItem(name, url));
//				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void parsePicPage(String url) {

	}

	public static void parseVideoPage(String url) {

	}

	public static void parseStoryPage(String url) {

	}
}
