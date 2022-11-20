package com.wp.board;

public class UsersDO {
	private String user_id;
	private String user_pw;
	private String nickname;
	private String email;
	private boolean admin;
	
	public UsersDO() {
		
	}
	
	public UsersDO(String user_id, String user_pw, String nickname, String email, boolean admin) {
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.nickname = nickname;
		this.email = email;
		this.admin = admin;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}	

}
