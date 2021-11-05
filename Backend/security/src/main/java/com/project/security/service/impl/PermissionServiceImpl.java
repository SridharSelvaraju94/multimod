package com.project.security.service.impl;

import com.project.repo.dto.PermissionDTO;
import com.project.repo.model.UserPermission;
import com.project.repo.repository.UserPermissionRepo;
import com.project.security.service.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

  @Autowired
  UserPermissionRepo userPermissionRepo;
  @Autowired
  ModelMapper modelMapper;
  @Override
  public List<PermissionDTO> getAllPermission() {

    List<UserPermission> ssmPermission = userPermissionRepo.findAll();
    List<PermissionDTO> ssmPermissionDTO = ssmPermission.stream()
            .map(permission -> modelMapper.map(permission,PermissionDTO.class)).collect(Collectors.toList());
    return ssmPermissionDTO;
  }

}
