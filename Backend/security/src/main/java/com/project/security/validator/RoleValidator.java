
package com.project.security.validator;

import com.project.repo.dto.RoleDTO;
import com.project.security.constants.RoleConstants;
import com.project.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Locale;

@Component
public class RoleValidator {

  @Autowired
  MessageSource messageSource;

  @Autowired
  RoleService roleService;

  public void validateRoleCreate(RoleDTO ssmRoleDTO, Errors error) {
    if (!roleService.exist(ssmRoleDTO.getRoleName())) {
      error.rejectValue("roleName", RoleConstants.R103, String.format(
              messageSource.getMessage(RoleConstants.R103, null, Locale.US), ssmRoleDTO.getRoleName()));
    }
  }

  public void validateRoleUpdate(RoleDTO ssmRoleDTO, Errors error) {
    if (roleService.getRoleById(ssmRoleDTO.getRoleId()) == null) {
      error.rejectValue("roleId", RoleConstants.R104, String.format(
              messageSource.getMessage(RoleConstants.R104, null, Locale.US), ssmRoleDTO.getRoleName()));
    }
  }
}
