package com.epam.model;

public class UserModel {

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String login;
	private String password;

	public UserModel() {

	}

	public UserModel(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserModel [login=" + this.login + ", password=" + this.password + "]";
	}

}
