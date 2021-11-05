
package com.project.security.service;

import com.project.common.dto.PaginationDTO;
import com.project.common.exception.CustomException;
import com.project.repo.dto.LevelDTO;
import com.project.repo.dto.UserDTO;
import com.project.repo.dto.UserStoreDTO;
import com.project.repo.model.UserStore;

public interface UserService {

  PaginationDTO<LevelDTO> getAllLevel(int page, int perPage);

  UserStoreDTO getUser(String UserName);

  UserStoreDTO createUser(UserDTO userData);

  UserStoreDTO updateUser(UserStoreDTO userData) throws NullPointerException, CustomException;

  UserStore resetPassword(UserStore ssmUser);

  void sendEmailNotification(UserStore user, String string, String string2);
}
