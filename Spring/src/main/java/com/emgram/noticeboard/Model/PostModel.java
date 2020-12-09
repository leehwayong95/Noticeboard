package com.emgram.noticeboard.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostModel {
	private String postindex;
	private String title;
	private String content;
	private String date;
	private String id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostindex() {
		return postindex;
	}
	public void setPostindex(String postindex) {
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
