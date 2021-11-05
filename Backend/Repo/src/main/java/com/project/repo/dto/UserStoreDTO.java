
package com.project.repo.dto;

import java.util.List;

public class UserStoreDTO {
	String username;

	String password;

	boolean enabled;

	LevelDTO level;

	List<RoleDTO> roles;

	boolean defaultPassword;

	String currentPassword;

	boolean credentialsNonExpired;

	public UserStoreDTO() {

	}

	public UserStoreDTO(String userName, String password, boolean enabled, LevelDTO level, List<RoleDTO> roles,
											boolean defaultPassword) {
		this.username = userName;
		this.password = password;
		this.enabled = enabled;
		this.level = level;
		this.roles = roles;
		this.defaultPassword = defaultPassword;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the level
	 */
	public LevelDTO getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(LevelDTO level) {
		this.level = level;
	}

	/**
	 * @return the roles
	 */
	public List<RoleDTO> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	/**
	 * @return the defaultPassword
	 */
	public boolean isDefaultPassword() {
		return defaultPassword;
	}

	/**
	 * @param defaultPassword
	 *            the defaultPassword to set
	 */
	public void setDefaultPassword(boolean defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	/**
	 * @return the currentPassword
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}

	/**
	 * @param currentPassword
	 *            the currentPassword to set
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

}
