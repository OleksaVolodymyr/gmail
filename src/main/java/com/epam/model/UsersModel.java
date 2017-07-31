<<<<<<< HEAD:src/main/java/com/epam/model/UsersModel.java
package com.epam.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class UsersModel {
	private List<UserModel> users = null;

	@XmlElement(name = "user")
	public List<UserModel> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

}
=======
package com.epam.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class Users {
	private List<UserModel> users = null;

	@XmlElement(name = "user")
	public List<UserModel> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

}
>>>>>>> faa2e69c9d394942576889f588433aac5413dff5:src/main/java/com/epam/model/Users.java
