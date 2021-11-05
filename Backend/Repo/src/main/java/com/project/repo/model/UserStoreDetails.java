
package com.project.repo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserStoreDetails extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1568683033929082839L;

	public UserStoreDetails(String username, String password, boolean enabled, boolean accountNonExpired,
													boolean credentialsNonExpired, boolean accountNonLocked,
													Collection<? extends GrantedAuthority> authorities, String userLevel) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userLevel = userLevel;
	} 
	
	String userLevel;

	/**
	 * @return the userLevel
	 */
	public String getUserLevel() {
		return userLevel;
	}

	/**
	 * @param userLevel the userLevel to set
	 */
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}	

}
