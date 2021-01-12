package com.emgram.noticeboard.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	
	private String id;
	private String name;
	private String PW;
<<<<<<< HEAD:00. default/Spring/src/main/java/com/emgram/noticeboard/Model/UserModel.java
	private int permission;

=======
	private String permission;
>>>>>>> origin/Seonghun:choseonghun/Spring/src/main/java/com/emgram/noticeboard/Model/UserModel.java
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
	public String getPW() {
		return PW;
	}
	public void setPW(String PW) {
		this.PW = PW;
	}
<<<<<<< HEAD:00. default/Spring/src/main/java/com/emgram/noticeboard/Model/UserModel.java
	public int getPermission() {
=======
	public String getPermission() {
>>>>>>> origin/Seonghun:choseonghun/Spring/src/main/java/com/emgram/noticeboard/Model/UserModel.java
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
<<<<<<< HEAD:00. default/Spring/src/main/java/com/emgram/noticeboard/Model/UserModel.java
=======
	
>>>>>>> origin/Seonghun:choseonghun/Spring/src/main/java/com/emgram/noticeboard/Model/UserModel.java
}
