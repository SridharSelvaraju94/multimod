
package com.project.security.service.impl;

import com.project.common.dto.PaginationDTO;
import com.project.common.exception.CustomException;
import com.project.repo.dto.LevelDTO;
import com.project.repo.dto.UserDTO;
import com.project.repo.dto.UserStoreDTO;
import com.project.repo.model.UserLevel;
import com.project.repo.model.UserRole;
import com.project.repo.model.UserStore;
import com.project.repo.repository.LevelRepo;
import com.project.repo.repository.UserRoleRepo;
import com.project.repo.repository.UserStoreRepo;
import com.project.security.constants.UserConstants;
import com.project.security.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  MessageSource messageSource;

  @Autowired
  LevelRepo levelRepo;

  @Autowired
  UserStoreRepo userStoreRepo;

  @Autowired
  UserRoleRepo userRoleRepo;

  @Autowired
  ModelMapper modelMapper;

  static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  static SecureRandom rnd = new SecureRandom();

  @Override
  public PaginationDTO<LevelDTO> getAllLevel(int page, int perPage) {

    Page<UserLevel> userLevels = null;

    Pageable pageable = null;

    pageable = PageRequest.of(page, perPage);

    userLevels = levelRepo.findAll(pageable);


    List<LevelDTO> ssmLevelDTOs = userLevels.getContent().stream()
            .map(ssmLevel -> modelMapper.map(ssmLevel, LevelDTO.class)).collect(Collectors.toList());

    PaginationDTO<LevelDTO> paginationDTO = new PaginationDTO<>();
    paginationDTO.setTotalPages(userLevels.getTotalPages());
    paginationDTO.setTotalRecord(userLevels.getTotalElements());
    paginationDTO.setData(ssmLevelDTOs);

    return paginationDTO;
  }

  @Override
  public UserStoreDTO getUser(String userName) {
    UserStore userData = userStoreRepo.findByUsername(userName);
    return modelMapper.map(userData, UserStoreDTO.class);
  }

  @Override
  public UserStoreDTO createUser(UserDTO userData) {
    UserStore ssmUser = null;
    String Otp = "";
    if (userExist(userData.getUserName()) == null) {
      List<UserRole> userRole = new ArrayList<UserRole>();
      UserLevel userLevel = levelRepo.getByLevelId(userData.getUserLevelId());
      userRole.add(userRoleRepo.findByRoleId(userData.getUserRoleId()));
      UserStore ssmuserStore = new UserStore(userData.getUserName(), Otp, true, userLevel, userRole, false,
              userData.getFirstName(), userData.getLastName(), LocalDateTime.now());
      Otp = setOtp(ssmuserStore);

      ssmUser = userStoreRepo.save(ssmuserStore);
      userData.setUserStatus("USER_ACTIVATION_PENDING");
//			kafkaProducer.updateUserStatus(userRegisteredtopic, userData);
//			sendEmailNotification(ssmUser, Otp, "NEW_USER_ACTIVATION");
    }
    return modelMapper.map(ssmUser, UserStoreDTO.class);
  }

  public UserStore userExist(String name) {
    UserStore ssmuserStore = userStoreRepo.findByUsername(name);
    return ssmuserStore;
  }

  @Override
  public UserStoreDTO updateUser(UserStoreDTO userData) throws NullPointerException, CustomException {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    UserStore user = userStoreRepo.findByUsername(userData.getUsername());
    if (user != null && encoder.matches(userData.getCurrentPassword(), user.getPassword())) {
      user.setPassword(BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt()));
      user.setCredentialsNonExpired(userData.isCredentialsNonExpired());
      user.setPasswordCreatedDate(LocalDateTime.now());
      if (!user.isCredentialsNonExpired()) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userData.getUsername());
        userDTO.setUserStatus("USER_ACTIVATED");
//				kafkaProducer.updateUserStatus(userRegisteredtopic, userDTO);
      }
      userStoreRepo.save(user);
    } else if (user == null) {
      throw new NullPointerException(UserConstants.U104);
    } else if (!encoder.matches(userData.getCurrentPassword(), user.getPassword())) {
      throw new CustomException(messageSource.getMessage(UserConstants.U107, null, Locale.US));
    }
    return modelMapper.map(user, UserStoreDTO.class);
  }

  private String randomString(int len) {
    StringBuilder stringBuilder = new StringBuilder(len);
    for (int index = 0; index < len; index++) {
      stringBuilder.append(AB.charAt(rnd.nextInt(AB.length())));
    }
    return stringBuilder.toString();
  }


  public UserStore resetPassword(UserStore ssmUser) {
    String Otp = setOtp(ssmUser);
    ssmUser.setCredentialsNonExpired(false);
    userStoreRepo.save(ssmUser);
//		sendEmailNotification(ssmUser, Otp, "ADMIN_RESET_PASSWORD");
    return ssmUser;
  }

  /**
   * @param ssmUser
   * @return
   */
  public String setOtp(UserStore ssmUser) {
    String randomKey = randomString(6);
    ssmUser.setPassword(BCrypt.hashpw(randomKey, BCrypt.gensalt()));
    return randomKey;
  }

  public void sendEmailNotification(UserStore ssmUser, String otp, String templateName) {
//		EmailNotificationBean emailNotificationBean = new EmailNotificationBean();
//		emailNotificationBean.setUserName(ssmUser.getFirstName() + ' ' + ssmUser.getLastName());
//		emailNotificationBean.setFirstName(ssmUser.getFirstName());
//		emailNotificationBean.setLastName(ssmUser.getLastName());
//		emailNotificationBean.setUserEmail(ssmUser.getUsername());
//		emailNotificationBean.setTemplateName(templateName);
//		emailNotificationBean.setOtp(otp);
//		kafkaProducer.sendEmailNotification(emailTopic, emailNotificationBean);
  }

}
