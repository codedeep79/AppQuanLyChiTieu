package com.example.entity;

public class News {
		String title;
		String link;
		String linkImage;
		String pubDate;
		String description;
		
		
		public News() {
		}

		public News(String title, String link, String linkImage, String pubDate, String description) {
			this.title = title;
			this.link = link;
			this.linkImage = linkImage;
			this.pubDate = pubDate;
			this.description = description;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getLinkImage() {
			return linkImage;
		}

		public void setLinkImage(String linkImage) {
			this.linkImage = linkImage;
		}

		public String getPubDate() {
			return pubDate;
		}

		public void setPubDate(String pubDate) {
			this.pubDate = pubDate;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
}
