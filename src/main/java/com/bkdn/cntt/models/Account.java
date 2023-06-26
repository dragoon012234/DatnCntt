package com.bkdn.cntt.models;

import java.util.ArrayList;

import com.bkdn.cntt.configs.models.AccountModel;
import com.bkdn.cntt.entities.AccountEntity;
import com.bkdn.cntt.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

	public String username;
	public String password;
	public String name;
	public String email;
	public Role role;
	public String token;
	public ArrayList<Integer> watched;
	public String avatar;
	public String description;

	public Account() {
	}

	public Account(String username, String password, String name, String email, Role role, String token,
			ArrayList<Integer> watched, String avatar, String description) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.role = role;
		this.token = token;
		this.watched = watched;
		this.avatar = avatar;
		this.description = description;
	}

	public Account(AccountModel accountModel) {
		configure(accountModel.getAccount());
	}

	public Account(AccountEntity e) {
		configure(e);
	}

	public void configure(AccountEntity e) {
		this.username = e.username;
		this.password = "";
		this.name = e.name;
		this.email = e.email;
		this.role = e.role;
		this.token = "";
		this.watched = e.watched;
		this.avatar = e.avatar;
		this.description = e.description;
	}

}
