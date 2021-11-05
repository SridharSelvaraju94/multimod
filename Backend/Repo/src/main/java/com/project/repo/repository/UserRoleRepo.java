
package com.project.repo.repository;

import com.project.repo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, String>, JpaSpecificationExecutor<UserRole> {

  Optional<UserRole> findByRoleName(String roleName);

  UserRole findByRoleId(String roleId);
}
