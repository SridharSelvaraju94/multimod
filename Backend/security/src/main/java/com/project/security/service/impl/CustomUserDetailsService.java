
package com.project.security.service.impl;

import com.project.repo.model.UserPermission;
import com.project.repo.model.UserRole;
import com.project.repo.model.UserStore;
import com.project.repo.model.UserStoreDetails;
import com.project.repo.repository.UserStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserStoreRepo repository;

  private Optional<UserStore> userData = null;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    userData = repository.findById(username);

    if (userData == null) {
      throw new UsernameNotFoundException(username);
    }

    return new UserStoreDetails(userData.get().getUsername(), userData.get().getPassword(),
            userData.get().isEnabled(), true, userData.get().isCredentialsNonExpired(), true, getAuthorities(userData.get().getRoles()),
            userData.get().getLevel().getLevelName());
  }

  private Collection<? extends GrantedAuthority> getAuthorities(Collection<UserRole> roles) {
    return getGrantedAuthorities(getPrivileges(roles));
  }

  private List<String> getPrivileges(Collection<UserRole> roles) {

    List<String> privileges = new ArrayList<>();
    List<UserPermission> collection = new ArrayList<>();
    for (UserRole role : roles) {
      collection.addAll(role.getPermissions());
      privileges.add("ROLE_" + role.getRoleName());
    }
    for (UserPermission item : collection) {
      privileges.add(item.getPermisisonName());
    }
    return privileges;
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
