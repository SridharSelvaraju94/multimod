
package com.project.repo.repository;

import com.project.repo.model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepo extends JpaRepository<UserPermission, String>,
        JpaSpecificationExecutor<UserPermission> {

}
