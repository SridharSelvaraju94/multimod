package com.project.repo.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class UserPermission implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8499087050006872492L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "permission_id")
	String permissionId;

	@Column(name = "permission_name")
	String permisisonName;

	@Override
	public String getAuthority() {
		return permisisonName;
	}

	/**
	 * @return the permissionId
	 */
	public String getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId
	 *            the permissionId to set
	 */
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * @return the permisisonName
	 */
	public String getPermisisonName() {
		return permisisonName;
	}

	/**
	 * @param permisisonName
	 *            the permisisonName to set
	 */
	public void setPermisisonName(String permisisonName) {
		this.permisisonName = permisisonName;
	}

}
