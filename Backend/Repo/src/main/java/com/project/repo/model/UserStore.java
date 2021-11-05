
package com.project.repo.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_store")
public class UserStore {

	@Id
	String username;

	String password;

	boolean enabled;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_level", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "level_id"))
	UserLevel userLevel;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	List<UserRole> roles;

	boolean credentialsNonExpired;

	private String firstName;

	private String lastName;

	private LocalDateTime passwordCreatedDate;

	public UserStore() {

	}

	public UserStore(String userName, String password, boolean enabled, UserLevel userLevel, List<UserRole> roles,
									 boolean credentialsNonExpired, String firstName, String lastName, LocalDateTime passwordCreatedDate) {
		this.username = userName;
		this.password = password;
		this.enabled = enabled;
		this.userLevel = userLevel;
		this.roles = roles;
		this.credentialsNonExpired = credentialsNonExpired;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passwordCreatedDate = passwordCreatedDate;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
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
	 * @param password the password to set
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
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the level
	 */
	public UserLevel getLevel() {
		return userLevel;
	}

	/**
	 * @param userLevel the level to set
	 */
	public void setLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * @return the roles
	 */
	public List<UserRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param credentialsNonExpired the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the passwordCreatedDate
	 */
	public LocalDateTime getPasswordCreatedDate() {
		return passwordCreatedDate;
	}

	/**
	 * @param passwordCreatedDate the passwordCreatedDate to set
	 */
	public void setPasswordCreatedDate(LocalDateTime passwordCreatedDate) {
		this.passwordCreatedDate = passwordCreatedDate;
	}

}
