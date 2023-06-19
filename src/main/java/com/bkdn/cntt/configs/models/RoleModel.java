package com.bkdn.cntt.configs.models;

import org.springframework.security.core.GrantedAuthority;

import com.bkdn.cntt.enums.Role;

public class RoleModel implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private Role roleId;
	private String role;

	public RoleModel(Role role) throws Exception {
		setRoleId(role);
		this.role = role.name().toUpperCase();
	}

	@Override
	public String getAuthority() {
		return role;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

}
