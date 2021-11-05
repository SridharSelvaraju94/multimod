
package com.project.repo.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class UserRole implements GrantedAuthority {

  /**
   *
   */
  private static final long serialVersionUID = 5594544304066893301L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "role_id")
  String roleId;

  @Column(name = "role_name")
  String roleName;

  @ManyToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
  List<UserPermission> permissions;

  @Override
  public String getAuthority() {
    return roleName;
  }

  /**
   * @return the roleId
   */
  public String getRoleId() {
    return roleId;
  }

  /**
   * @param roleId the roleId to set
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
   * @param roleName the roleName to set
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  /**
   * @return the permissions
   */
  public List<UserPermission> getPermissions() {
    return permissions;
  }

  /**
   * @param permissions the permissions to set
   */
  public void setPermissions(List<UserPermission> permissions) {
    this.permissions = permissions;
  }

}
