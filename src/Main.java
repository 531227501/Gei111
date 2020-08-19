import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.MenuItem;
import utils.WebPageParser;

import static utils.LogUtils.print;

public class Main {

	public static void main(String[] args) {
		//        String url = "https://www.111gei.com/";
		//		String result = NetworkGo.get(url);
		//		System.out.println("result:" + result);

//		String webSiteUrl = WebPageParser.parseDynamicURL();
//		print(webSiteUrl);
		String webSiteUrl = "https://333ccd.com";
		HashMap<String, ArrayList<MenuItem>> stringArrayListHashMap = WebPageParser.parseCategory(webSiteUrl);
		for(Map.Entry<String, ArrayList<MenuItem>> entry : stringArrayListHashMap.entrySet()){
			String categoryName = entry.getKey();
			ArrayList<MenuItem> menus = entry.getValue();
			for (MenuItem menu : menus){
				print(categoryName + ", " + menu.getTitle());
				WebPageParser.parseMoviePage("https://333ccd.com", menu.getUrl());
			}
		}
		//
		// WebPageParser.parseMoviePage(webSiteUrl, "/m02/index.html");
//		WebPageParser.parseMoviePage("https://333ccd.com", "/m02/index.html");
	}
}
