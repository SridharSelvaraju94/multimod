package com.project.web.config;

import com.project.repo.model.UserLevel;
import com.project.repo.model.UserPermission;
import com.project.repo.model.UserRole;
import com.project.repo.model.UserStore;
import com.project.repo.repository.LevelRepo;
import com.project.repo.repository.UserPermissionRepo;
import com.project.repo.repository.UserRoleRepo;
import com.project.repo.repository.UserStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
  private boolean alreadySetup = false;
  @Autowired
  private UserStoreRepo userRepository;

  @Autowired
  private UserRoleRepo roleRepository;

  @Autowired
  private LevelRepo levelRepo;

  @Autowired
  private UserPermissionRepo permissionRepo;

  @Override
  @Transactional
  public void onApplicationEvent(final ContextRefreshedEvent event) {
    if (alreadySetup) {
      return;
    }
    createUserIfNotFound("ngssmlab@gmail.com");
    alreadySetup = true;
  }

  @Transactional
  UserStore createUserIfNotFound(final String email) {
    UserStore user = userRepository.findByUsername(email);
    if (user == null) {
      UserLevel level = creatLevelIfNotFound().stream()
              .filter(userLevel -> "LEVEL_1".equalsIgnoreCase(userLevel.getLevelName()))
              .findFirst().get();

//      UserLevel level = new UserLevel();
//      level.setLevelName("LEVEL_1");
      List<UserRole> userRole = new ArrayList<UserRole>();
      userRole.add(createRoleIfNotFound("ADMIN"));
      user = new UserStore(email, getEncryptedPwd(), true, level, userRole, false,
              "ADMIN", "ADMIN", LocalDateTime.now());
      user = userRepository.save(user);
    }
    return user;
  }

  public String getEncryptedPwd() {
    String randomKey = "Admin@123";
    randomKey = BCrypt.hashpw(randomKey, BCrypt.gensalt());
    return randomKey;
  }

  @Transactional
  List<UserLevel> creatLevelIfNotFound() {
    List<UserLevel> levels = levelRepo.findAll();
    if (levels.isEmpty()) {
      levels = new ArrayList<>();
      levels.add(createLevel("LEVEL_1"));
      levels.add(createLevel("LEVEL_2"));
      levels.add(createLevel("LEVEL_3"));
      levels = levelRepo.saveAll(levels);
    }
    return levels;
  }

  @Transactional
  UserRole createRoleIfNotFound(final String name) {
    Optional<UserRole> role = roleRepository.findByRoleName(name);
    if (role.isEmpty()) {
      List<UserPermission> permissions = createIfPermissionNotFound();
      UserRole userRole = new UserRole();
      userRole.setRoleName(name);
      userRole.setPermissions(permissions);
      userRole = roleRepository.save(userRole);
      return userRole;
    }
    return role.get();
  }

  @Transactional
  List<UserPermission> createIfPermissionNotFound() {
    List<UserPermission> permissions = permissionRepo.findAll();
    if (permissions.isEmpty()) {
      permissions = new ArrayList<>();
      permissions.add(createPermission("USR_VIEW"));
      permissions.add(createPermission("USR_ADD"));
      permissions.add(createPermission("USR_UPDATE"));
      permissions.add(createPermission("USR_STATUS"));
      permissions.add(createPermission("PER_VIEW"));
      permissions.add(createPermission("PER_ADD"));
      permissions.add(createPermission("PER_UPDATE"));
      permissions = permissionRepo.saveAll(permissions);
    }
    return permissions;
  }

  UserPermission createPermission(String permissionName) {
    UserPermission permission = new UserPermission();
    permission.setPermisisonName(permissionName);
    return permission;
  }

  UserLevel createLevel(String UserLevel) {
    UserLevel level = new UserLevel();
    level.setLevelName(UserLevel);
    return level;
  }
}
