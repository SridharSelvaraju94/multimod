
package com.project.web.annotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentUserPrincipal {

	private String username;

	private Boolean enabled;

	private String userLevel;

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
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the userLevel
	 */
	public String getUserLevel() {
		return userLevel;
	}

	/**
	 * @param userLevel
	 *            the userLevel to set
	 */
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	

}
