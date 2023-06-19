package com.bkdn.cntt.models.create;

import com.bkdn.cntt.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccount {

	public Integer count;
	public Integer start;
	public Integer step;
	public String username;
	public String password;
	public String email;
	public Role role;

	public CreateAccount() {
	}

	public CreateAccount(Integer count, Integer start, Integer step, String username, String password, String email,
			Role role) {
		this.count = count;
		this.start = start;
		this.step = step;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

}
