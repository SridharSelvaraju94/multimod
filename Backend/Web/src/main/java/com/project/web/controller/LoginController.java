
package com.project.web.controller;

import com.project.common.exception.BusinessException;
import com.project.common.exception.CustomException;
import com.project.common.sideloading.JSONModel;
import com.project.common.sideloading.JSONModelHelper;
import com.project.repo.dto.UserStoreDTO;
import com.project.repo.model.UserPasswordRecovery;
import com.project.repo.model.UserStore;
import com.project.security.constants.LoginConstants;
import com.project.security.exception.handler.CustomGenericException;
import com.project.security.service.LoginService;
import com.project.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/login")
//@SecurityRequirement(name = "javainuseapi")
public class LoginController {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private LoginService loginService;

  @Autowired
  private UserService userService;

  @PostMapping(value = "/forgotPassword", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public JSONModel forgotPassword(@Valid @RequestBody UserStore requestData, BindingResult result)
          throws CustomGenericException {

    UserStoreDTO userData = userService.getUser(requestData.getUsername());
    if (userData == null) {
      throw new NullPointerException(LoginConstants.L101);
    }
    if (userData.isDefaultPassword()) {
      throw new CustomGenericException(LoginConstants.L111,
              LoginConstants.L111);
    }
    UserPasswordRecovery passwordRecvData = loginService.forgotPassword(userData.getUsername());
    if (passwordRecvData.getMaxAttempts() > 3) {
      throw new CustomGenericException(LoginConstants.L111,
              LoginConstants.L107);
    }

    return JSONModelHelper.processJSONModelForObject(LoginConstants.L100,
            LoginConstants.L100);
  }

  @GetMapping(value = "/validateToken", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public JSONModel validateToken(@Valid @RequestParam("token") String token) throws CustomException {
    UserPasswordRecovery isValidToken = loginService.validateToken(token);
    if (isValidToken == null) {
      throw new CustomException(LoginConstants.L110);
    }
    return JSONModelHelper.processJSONModelForObject(LoginConstants.L109,
            LoginConstants.L109, isValidToken);
  }

  @GetMapping(value = "/validateResetOtp", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public JSONModel validateResetOtp(@Valid @RequestParam("otp") String otp) throws CustomException {
    LocalDateTime currentTime = LocalDateTime.now();
    UserPasswordRecovery isValidOtp = loginService.validateResetOtp(otp);
    if (isValidOtp == null) {
      throw new CustomException(LoginConstants.L103);
    }
    boolean isTimeExpired = loginService.validateTimeExpiry(isValidOtp, currentTime);
    if (!isTimeExpired) {
      throw new CustomException(LoginConstants.L104);
    }
    return JSONModelHelper.processJSONModelForObject(LoginConstants.L102,
            LoginConstants.L102);
  }

  @GetMapping(value = "/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public JSONModel resetPassword(@Valid @RequestParam("resetToken") String resetToken,
                                 @RequestParam("password") String password) throws BusinessException, NoSuchMessageException {
    boolean isPasswordChanged = loginService.resetPassword(resetToken, password);
    if (!isPasswordChanged) {
      throw new BusinessException(LoginConstants.L106);
    }
    return JSONModelHelper.processJSONModelForObject(LoginConstants.L105,
            LoginConstants.L105);
  }

  @GetMapping(value = "/resendOtp", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public JSONModel resendOtp(@Valid @RequestParam("userName") String userName) throws CustomGenericException {
    UserPasswordRecovery userData = loginService.resendOtp(userName);
    if (userData.getMaxAttempts() > 3) {
      throw new CustomGenericException(LoginConstants.L111,
              LoginConstants.L111);
    }
    return JSONModelHelper.processJSONModelForObject(LoginConstants.L108,
            LoginConstants.L108);
  }
}
