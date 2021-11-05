
package com.project.repo.repository;

import com.project.repo.model.UserStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoreRepo extends JpaRepository<UserStore, String>, JpaSpecificationExecutor<UserStore> {

  UserStore findByUsername(String username);
}
