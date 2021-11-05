
package com.project.web.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.common.constants.CommonConstants;
import com.project.common.exception.UserLevelAuthenticatorException;
import com.project.security.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Component
@Aspect
public class UserLevelAuthenticatorAspect {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  UserService userService;

  @Pointcut(value = "@annotation(userLevelAuth)")
  public void annotationPointCutDefinition(UserLevelAuthenticator userLevelAuth) {
  }

  @Around("annotationPointCutDefinition(userLevelAuth)")
  public Object around(ProceedingJoinPoint proceedingJoinPoint, UserLevelAuthenticator userLevelAuth)
          throws Throwable {

    ObjectMapper mapper = new ObjectMapper();
    CurrentUserPrincipal currentUser = mapper.convertValue(getPrincipal(), CurrentUserPrincipal.class);

    Optional<String> userLevel = Arrays.asList(userLevelAuth.allow()).stream()
            .filter(usrLevel -> usrLevel.equals(currentUser.getUserLevel())).findFirst();

    if (!userLevel.isPresent()) {
      throw new UserLevelAuthenticatorException(messageSource.getMessage(CommonConstants.U100, null, Locale.US));
    }

    return proceedingJoinPoint.proceed();
  }

  private Object getPrincipal() {
    return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
