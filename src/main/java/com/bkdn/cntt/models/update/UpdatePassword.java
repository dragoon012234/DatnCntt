package com.bkdn.cntt.models.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePassword {

	public String oldPassword;
	public String newPassword;

	public UpdatePassword() {
	}

	public UpdatePassword(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

}
