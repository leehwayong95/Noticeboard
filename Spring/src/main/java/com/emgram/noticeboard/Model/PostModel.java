package com.emgram.noticeboard.Model;

import lombok.Getter;
import lombok.Setter;

public class PostModel {
	private int postindex;
	private String title;
	private String content;
	private String date;
	private String writer;
	
	public int getPostindex() {
		return postindex;
	}
	public void setPostindex(int postindex) {
		this.postindex = postindex;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
