package com.bkdn.cntt.entities;

import java.util.ArrayList;

import com.bkdn.cntt.enums.Role;
import com.bkdn.cntt.models.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer id;

	@Column(nullable = false, length = 63)
	public String username;

	@Column(nullable = false)
	public String password;

	@Column(nullable = true, length = 127)
	public String name;

	@Column(nullable = true, length = 127)
	public String email;

	@Column(nullable = false)
	public Boolean verify;

	@Column(nullable = false)
	public Boolean blocked;

	@Column(nullable = false)
	public Role role;

	@Column(nullable = true)
	public ArrayList<Integer> watched;

	@Column(nullable = true)
	public String avatar;

	@Column(nullable = false, columnDefinition = "varchar(255) default ``")
	public String description;

	public AccountEntity() {
	}

	public AccountEntity(Integer id, String username, String password, String name, String email, Boolean verify,
			Boolean blocked, Role role, ArrayList<Integer> watched, String avatar, String description) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.verify = verify;
		this.blocked = blocked;
		this.role = role;
		this.watched = watched;
		this.avatar = avatar;
		this.description = description;
	}

	public AccountEntity(Account account) {
		this(null, account.username, account.password, account.name, account.email, false, false, account.role,
				account.watched, account.avatar, account.description);
	}

	public void update(Account model) {
		if (model.name != null) {
			this.name = model.name;
		}
		if (model.watched != null) {
			this.watched = model.watched;
		}
		this.avatar = model.avatar;
		this.description = model.description;
	}

}
