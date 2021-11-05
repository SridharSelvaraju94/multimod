
package com.project.web.controller;

import com.project.common.constants.CommonConstants;
import com.project.common.exception.EmptyListException;
import com.project.common.sideloading.JSONModel;
import com.project.common.sideloading.JSONModelHelper;
import com.project.repo.dto.PermissionDTO;
import com.project.security.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/permission")
//@SecurityRequirement(name = "javainuseapi")
public class PermissionController {

  @Autowired
  MessageSource messageSource;

  @Autowired
  PermissionService permissionService;

//  @PreAuthorize("hasAnyAuthority('PER_ADD','PER_UPDATE')")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public JSONModel getAllPermission() throws EmptyListException {

    List<PermissionDTO> permissionDTO = permissionService.getAllPermission();
    if (permissionDTO.isEmpty()) {
      throw new EmptyListException("List is Empty", CommonConstants.P101);
    }
    return JSONModelHelper.processJSONModelForObject(CommonConstants.P100,
            CommonConstants.P100, permissionDTO);
  }
}
