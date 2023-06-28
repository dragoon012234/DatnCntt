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

	@Column
	public String username;

	@Column
	public String password;

	@Column
	public String name;

	@Column
	public String email;

	@Column
	public Boolean verify;

	@Column
	public Boolean blocked;

	@Column
	public Role role;

	@Column
	public ArrayList<Integer> watched;

	@Column
	public String avatar;

	@Column
	public String description;

	@Column
	public Boolean watcher;

	public AccountEntity() {
	}

	public AccountEntity(Integer id, String username, String password, String name, String email, Boolean verify,
			Boolean blocked, Role role, ArrayList<Integer> watched, String avatar, String description,
			Boolean watcher) {
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
		this.watcher = watcher;
	}

	public AccountEntity(Account account) {
		this(null, account.username, account.password, account.name, account.email, true, false, account.role,
				account.watched, account.avatar, account.description, account.watcher);
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
		this.watcher = model.watcher;
	}

}
