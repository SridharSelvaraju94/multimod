
package com.project.security.service;

import com.project.repo.model.UserPasswordRecovery;

import java.time.LocalDateTime;

public interface LoginService {

  UserPasswordRecovery forgotPassword(String userName);

  UserPasswordRecovery validateToken(String token);

  UserPasswordRecovery validateResetOtp(String otp);

  boolean validateTimeExpiry(UserPasswordRecovery otp, LocalDateTime currentTime);

  boolean resetPassword(String resetToken, String password);

  UserPasswordRecovery resendOtp(String userName);

  String getLink(String token, String linkType);

}
