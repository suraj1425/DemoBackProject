package com.bank;

import jakarta.servlet.http.HttpSession;

public class UserInfo {
	
	public int account_no;
	public String username;
	public String gender;
	public String name;
	public String date_of_birth;

	
	public UserInfo(int account_no, String username, String gender, String name, String date_of_birth) {
		this.account_no = account_no;
		this.username = username;
		this.gender = gender;
		this.name = name;
		this.date_of_birth = date_of_birth;
	}
	
	
	
	
}
