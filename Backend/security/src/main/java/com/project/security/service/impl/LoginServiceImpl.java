
package com.project.security.service.impl;

import com.project.common.uil.EncryptDecryptUtility;
import com.project.repo.model.UserPasswordRecovery;
import com.project.repo.model.UserStore;
import com.project.repo.repository.UserPasswordRecoveryRepo;
import com.project.repo.repository.UserStoreRepo;
import com.project.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private UserStoreRepo userStoreRepo;

  @Autowired
  private UserPasswordRecoveryRepo userPasswordRecoveryRepo;

/*  @Value("${Otp.expiryTime}")
  private long otpExpiryTime;

  @Value("${UI.Ip}")
  private String IP;

  @Value("${UI.port}")
  private String port;*/
/*
  @Value("${spring.kafka.topic.emailTopic}")
  private String emailTopic;

  @Autowired
  KafkaProducer kafkaProducer;*/

  @Override
  public UserPasswordRecovery forgotPassword(String userName) {

    String resetToken = UUID.randomUUID().toString();
    String randomKey = getRandomKey();
    String passWordResetLink = getLink(resetToken, "passwordReset");

    UserStore userStoreData = userStoreRepo.findByUsername(userName);

/*    EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
    emailNotificationBean.setFirstName(userStoreData.getFirstName());
    emailNotificationBean.setLastName(userStoreData.getLastName());
    emailNotificationBean.setUserName(userStoreData.getFirstName() + ' ' + userStoreData.getLastName());
    emailNotificationBean.setUserEmail(userName);
    emailNotificationBean.setTemplateName("USER_FORGOT_PASSWORD");
    emailNotificationBean.setLink(passWordResetLink);
    emailNotificationBean.setOtp(randomKey);*/

    UserPasswordRecovery userData = null;
    userData = userPasswordRecoveryRepo.findByUsername(userName);
    if (userData == null) {
      userData = new UserPasswordRecovery();
    }
    userData.setOtpValid(true);
    userData.setTokenValid(true);
//    userData.setOtp(SMFEncryptDecryptUtility.encrypt(randomKey));
    userData.setResetToken(resetToken);
    userData.setUsername(userName);
    userData.setOtpCreatedOn(LocalDateTime.now());
    if (userData.getMaxAttempts() <= 3) {
      userData.setMaxAttempts(userData.getMaxAttempts() + 1);
      /*kafkaProducer.sendEmailNotification(emailTopic, emailNotificationBean);*/
      userPasswordRecoveryRepo.save(userData);
    }
    return userData;
  }

  public static String getRandomKey() {
    final SecureRandom random = new SecureRandom();
    return new BigInteger(60, random).toString(40).substring(0, 4);
  }

  @Override
  public UserPasswordRecovery validateResetOtp(String otp) {
    UserPasswordRecovery userData = userPasswordRecoveryRepo.findByOtp(EncryptDecryptUtility.encrypt(otp));
    if (userData != null) {
      return userData;
    }
    return null;
  }

  @Override
  public boolean validateTimeExpiry(UserPasswordRecovery userPasswordRecovery, LocalDateTime observedTimeStamp) {
    long minutes = ChronoUnit.MINUTES.between(userPasswordRecovery.getOtpCreatedOn(), observedTimeStamp);
//    if (minutes > otpExpiryTime) {
//      userPasswordRecovery.setOtpValid(false);
//      userPasswordRecoveryRepo.save(userPasswordRecovery);
//      return false;
//    }
    return true;
  }

  @Override
  public boolean resetPassword(String resetToken, String password) {
    UserPasswordRecovery userData = userPasswordRecoveryRepo.findByResetToken(resetToken);
    if (userData != null) {
      if (userData.isOtpValid()) {
        userData.setOtpValid(false);
        userData.setTokenValid(false);
        UserStore userStoreData = userStoreRepo.findByUsername(userData.getUsername());
        userStoreData.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userStoreRepo.save(userStoreData);
        userPasswordRecoveryRepo.save(userData);
        return true;
      }
    }
    return false;
  }

  @Override
  public UserPasswordRecovery resendOtp(String userName) {

//    EmailNotificationBean emailNotificationBean = new EmailNotificationBean();

    UserPasswordRecovery userData = userPasswordRecoveryRepo.findByUsername(userName);
    if (userData != null) {
      if (userData.getMaxAttempts() <= 3) {
        String randomKey = getRandomKey();
        userData.setOtpValid(true);
        userData.setTokenValid(true);
        userData.setOtp(EncryptDecryptUtility.encrypt(randomKey));
        userData.setUsername(userName);
        userData.setOtpCreatedOn(LocalDateTime.now());
        userData.setMaxAttempts(userData.getMaxAttempts() + 1);

        UserStore userStoreData = userStoreRepo.findByUsername(userData.getUsername());

//        emailNotificationBean.setUserName(userStoreData.getFirstName() + ' ' + userStoreData.getLastName());
//        emailNotificationBean.setFirstName(userStoreData.getFirstName());
//        emailNotificationBean.setLastName(userStoreData.getLastName());
//        emailNotificationBean.setUserEmail(userData.getUsername());
//        emailNotificationBean.setTemplateName("RESEND_OTP");
//        emailNotificationBean.setOtp(randomKey);
//        kafkaProducer.sendEmailNotification(emailTopic, emailNotificationBean);
        userPasswordRecoveryRepo.save(userData);
      }
    }
    return userData;
  }

  @Override
  public UserPasswordRecovery validateToken(String token) {
    UserPasswordRecovery userData = userPasswordRecoveryRepo.findByResetToken(token);
    if (userData != null) {
      return userData;
    }
    return null;
  }

  public String getLink(String token, String linkType) {
//    String passwordlink = "http://" + IP.trim() + ":" + port.trim() + "/SSM" + "/" + linkType + "?token="
//            + token;
//    return passwordlink;
    return "";
  }

}
