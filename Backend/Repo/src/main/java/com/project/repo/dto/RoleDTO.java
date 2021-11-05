
package com.project.repo.dto;

import java.util.List;

public class RoleDTO {

	/** Role ID */
	String roleId;

	/** Role Name */
	String roleName;

	/** Permission */
	List<PermissionDTO> permissions;

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the permissions
	 */
	public List<PermissionDTO> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions(List<PermissionDTO> permissions) {
		this.permissions = permissions;
	}

}
