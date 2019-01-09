package org.flywind.business.entities.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class LoginData implements Serializable {
	
	private static final long serialVersionUID = -8383228513861731034L;
	
	private List<?> menus;
	
	private Set<?> permissions;
	
	private Set<?> roles;
	
	private Object loginUser;
	
	private Integer code;
	
	private String message;
	
	private String token;

	/**
	 * @return the permissions
	 */
	public Set<?> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Set<?> permissions) {
		this.permissions = permissions;
	}

	public List<?> getMenus() {
		return menus;
	}

	public void setMenus(List<?> menus) {
		this.menus = menus;
	}

	public Object getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Object loginUser) {
		this.loginUser = loginUser;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<?> getRoles() {
		return roles;
	}

	public void setRoles(Set<?> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	

}
