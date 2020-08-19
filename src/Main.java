import utils.WebPageParser;

public class Main {

	public static void main(String[] args) {
		//        String url = "https://www.111gei.com/";
		//		String result = NetworkGo.get(url);
		//		System.out.println("result:" + result);

//		String WebSiteUrl = WebPageParser.parseDynamicURL();

		String WebSiteUrl = "https://333ccb.com";
//		WebPageParser.parseCategory(WebSiteUrl);
		WebPageParser.parseMoviePage(WebSiteUrl + "/m01/index.html");
//		WebPageParser.parsePicPage("https://333bbm.com/p01/index.html");
//		WebPageParser.parseVideoPage("https://333bbm.com/s01/index.html");
//		WebPageParser.parseStoryPage("https://333bbm.com/t01/index.html");
	}
}
