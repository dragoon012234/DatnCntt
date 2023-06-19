package com.bkdn.cntt.configs.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bkdn.cntt.entities.AccountEntity;

public class AccountModel implements UserDetails {

	private static final long serialVersionUID = 1L;

	private AccountEntity account;
	private Collection<RoleModel> roles;

	public AccountModel(AccountEntity account, Collection<RoleModel> roles) {
		super();
		this.account = account;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return account.password;
	}

	@Override
	public String getUsername() {
		return account.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public Collection<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleModel> roles) {
		this.roles = roles;
	}

}
