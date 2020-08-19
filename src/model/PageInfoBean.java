package model;

import java.util.ArrayList;

public class PageInfoBean {

	private ArrayList<PageItem> itemList = new ArrayList<>();
	private String nextPageUrl;

	public ArrayList<PageItem> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<PageItem> itemList) {
		this.itemList = itemList;
	}

	public String getNextPageUrl() {
		return nextPageUrl;
	}

	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}

	public static class PageItem {

		private String title;
		private String picUrl;
		private String contentUrl;

		public PageItem(String title, String picUrl, String contentUrl) {
			this.title = title;
			this.picUrl = picUrl;
			this.contentUrl = contentUrl;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getContentUrl() {
			return contentUrl;
		}

		public void setContentUrl(String contentUrl) {
			this.contentUrl = contentUrl;
		}
	}
}
