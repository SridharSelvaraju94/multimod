
package com.project.web.controller;

import com.project.common.dto.PaginationDTO;
import com.project.common.exception.CustomException;
import com.project.common.exception.EmptyListException;
import com.project.common.sideloading.JSONModel;
import com.project.common.sideloading.JSONModelHelper;
import com.project.repo.dto.LevelDTO;
import com.project.repo.dto.UserStoreDTO;
import com.project.repo.model.UserStore;
import com.project.repo.repository.UserStoreRepo;
import com.project.security.constants.UserConstants;
import com.project.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
//@SecurityRequirement(name = "javainuseapi")
public class UserController {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  UserService userService;

  @Autowired
  UserStoreRepo userStoreDAO;

  @GetMapping("/current")
  public Principal getPrincipal(Principal principal) {
    return principal;
  }

  @GetMapping(value = "/level", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public JSONModel getLevels(@RequestParam(value = "page[number]", required = false, defaultValue = "0") int page,
                             @RequestParam(value = "page[size]", required = false, defaultValue = "10") int perPage)
          throws EmptyListException {
    PaginationDTO<LevelDTO> ssmLevels = userService.getAllLevel(page, perPage);

    if (ssmLevels.getData().isEmpty()) {
      throw new EmptyListException("List is Empty", UserConstants.U102);
    }

    return JSONModelHelper.processJSONModelForCollection(
            UserConstants.U101, UserConstants.U101, ssmLevels.getData(),
            ssmLevels.getTotalPages(), ssmLevels.getTotalRecord());
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public JSONModel getUser(@RequestParam("userName") String userName) throws NullPointerException {
    UserStoreDTO userData = userService.getUser(userName);
    if (userData == null) {
      throw new NullPointerException(UserConstants.U104);
    }
    return JSONModelHelper.processJSONModelForObject(UserConstants.U103,
            UserConstants.U103, userData);
  }

  @PostMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
  public JSONModel updateUser(@RequestBody UserStoreDTO ssmUserStoreDTO)
          throws NullPointerException, CustomException {
    UserStoreDTO userData = userService.updateUser(ssmUserStoreDTO);
    return JSONModelHelper.processJSONModelForObject(UserConstants.U105,
            UserConstants.U105, userData);
  }

  @PostMapping(value = "/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public JSONModel resetPassword(@RequestBody String userName) throws NullPointerException {
    UserStore ssmUser = userStoreDAO.findByUsername(userName);
    if (ssmUser == null) {
      throw new NullPointerException(UserConstants.U104);
    }
    ssmUser = userService.resetPassword(ssmUser);
    return JSONModelHelper.processJSONModelForObject(UserConstants.U105,
            UserConstants.U105, ssmUser);
  }

}
