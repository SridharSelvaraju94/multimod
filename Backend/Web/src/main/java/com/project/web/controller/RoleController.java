
package com.project.web.controller;

import com.project.common.dto.PaginationDTO;
import com.project.common.exception.CustomException;
import com.project.common.exception.EmptyListException;
import com.project.common.exception.ValidationException;
import com.project.common.sideloading.JSONModel;
import com.project.common.sideloading.JSONModelHelper;
import com.project.repo.dto.RoleDTO;
import com.project.security.constants.RoleConstants;
import com.project.security.service.RoleService;
import com.project.security.validator.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/role")
//@SecurityRequirement(name = "javainuseapi")
public class RoleController {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  RoleService roleService;

  @Autowired
  RoleValidator roleValidator;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
//  @PreAuthorize("hasAuthority('PER_VIEW')")
  public JSONModel getRoles(@RequestParam(value = "page[number]", required = false, defaultValue = "0") int page,
                            @RequestParam(value = "page[size]", required = false, defaultValue = "10") int perPage,
                            @RequestParam Map<String, String> searchParams,
                              @RequestParam(value = "sort", required = false, defaultValue = "roleName") String sort)
          throws EmptyListException {
    PaginationDTO<RoleDTO> ssmRoles = roleService.getAllRoles(page, perPage, searchParams, sort);
    if (ssmRoles.getData().isEmpty()) {
      throw new EmptyListException("List is Empty", RoleConstants.R101);
    }
    return JSONModelHelper.processJSONModelForCollection(
            RoleConstants.R100, RoleConstants.R100, ssmRoles.getData(),
            ssmRoles.getTotalPages(), ssmRoles.getTotalRecord());
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
//  @PreAuthorize("hasAuthority('PER_ADD')")
//  @UserLevelAuthenticator(allow = {LEVEL1, LEVEL2})
  public JSONModel createRole(@Valid @RequestBody RoleDTO ssmRoleDTO, BindingResult result,
                              Authentication authentication) throws ValidationException {
    roleValidator.validateRoleCreate(ssmRoleDTO, result);
    if (result.hasErrors()) {
      throw new ValidationException(result);
    }
    RoleDTO ssmRole = roleService.createRole(ssmRoleDTO);
    return JSONModelHelper.processJSONModelForObject(RoleConstants.R102,
            RoleConstants.R102, ssmRole);
  }

  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
//  @PreAuthorize("hasAuthority('PER_UPDATE')")
//  @UserLevelAuthenticator(allow = {LEVEL1, LEVEL2})
  public JSONModel updateRole(@Valid @RequestBody RoleDTO ssmRoleDTO, BindingResult result)
          throws ValidationException, CustomException {
    roleValidator.validateRoleUpdate(ssmRoleDTO, result);
    if (result.hasErrors()) {
      throw new ValidationException(result);
    }
    RoleDTO ssmRole = roleService.updateRole(ssmRoleDTO);
    return JSONModelHelper.processJSONModelForObject(RoleConstants.R105,
            RoleConstants.R105, ssmRole);
  }

  @GetMapping(value = "/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
//  @PreAuthorize("hasAuthority('PER_UPDATE')")
  public JSONModel getRoleById(@PathVariable String roleId) {

    RoleDTO ssmRole = roleService.getRoleById(roleId);
    if (ssmRole == null) {
      throw new NullPointerException(RoleConstants.R104);
    }
    return JSONModelHelper.processJSONModelForObject(RoleConstants.R107,
            RoleConstants.R107, ssmRole);
  }

  @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<RoleDTO> getRoleForUser() {
    List<RoleDTO> ssmRole = roleService.getAllRoleForUser();
    if (ssmRole == null) {
      throw new NullPointerException(RoleConstants.R104);
    }
    return ssmRole;
  }

}
