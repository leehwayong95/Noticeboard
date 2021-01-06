package com.emgram.noticeboard.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentModel {
	private String comment_index;
	private String postindex;
	private String recomment;
	private String content;
	private String date;
	private String id;
	private String name;
	private String enable;
	public String getCommnet_index() {
		return comment_index;
	}
	public void setCommnet_index(String commnet_index) {
		this.comment_index = commnet_index;
	}
	public String getPostindex() {
		return postindex;
	}
	public void setPostindex(String postindex) {
		this.postindex = postindex;
	}
	public String getRecomment() {
		return recomment;
	}
	public void setRecomment(String recomment) {
		this.recomment = recomment;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
}
