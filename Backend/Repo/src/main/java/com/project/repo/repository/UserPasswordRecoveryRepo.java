
package com.project.repo.repository;

import com.project.repo.model.UserPasswordRecovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPasswordRecoveryRepo
        extends JpaRepository<UserPasswordRecovery, String>, JpaSpecificationExecutor<UserPasswordRecovery> {
  UserPasswordRecovery findByUsername(String username);

  UserPasswordRecovery findByOtp(String otp);

  UserPasswordRecovery findByResetToken(String resetToken);
}
