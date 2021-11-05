package com.project.security.service.impl;

import com.project.common.dto.PaginationDTO;
import com.project.common.exception.CustomException;
import com.project.repo.dto.RoleDTO;
import com.project.repo.model.UserRole;
import com.project.repo.repository.UserRoleRepo;
import com.project.repo.repository.specifications.PermissionRoleSpecifications;
import com.project.security.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private UserRoleRepo userRoleRepo;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public PaginationDTO<RoleDTO> getAllRoles(int page, int perPage, Map<String, String> searchParams, String sort) {

    Page<UserRole> ssmRoles = null;
    Direction direction = sort.startsWith("-") ? Direction.DESC : Direction.ASC;

    Pageable pageable = null;
//		Sort sortVar = new Sort(direction, sort);
    if (perPage == 0) {
//			pageable = PageRequest.of(page, CommonConstants.MAX_PERPAGE, sortVar);
      pageable = PageRequest.of(page, perPage);
    } else {
      pageable = PageRequest.of(page, perPage);
    }

    if (searchParams == null) {
      ssmRoles = userRoleRepo.findAll(pageable);
    } else {
      ssmRoles = userRoleRepo.findAll(PermissionRoleSpecifications.withSearchParam(searchParams), pageable);
    }

    List<RoleDTO> ssmRoleDTOs = ssmRoles.getContent().stream()
            .map(ssmRole -> modelMapper.map(ssmRole, RoleDTO.class)).collect(Collectors.toList());

    PaginationDTO<RoleDTO> paginationDTO = new PaginationDTO<>();
    paginationDTO.setTotalPages(ssmRoles.getTotalPages());
    paginationDTO.setTotalRecord(ssmRoles.getTotalElements());
    paginationDTO.setData(ssmRoleDTOs);

    return paginationDTO;
  }

  public boolean exist(String name) {
    Optional<UserRole> ssmRole = userRoleRepo.findByRoleName(name);
    return ssmRole.isPresent();
  }

  @Override
  public RoleDTO createRole(RoleDTO ssmRoleDTO) {
    UserRole dBRole = modelMapper.map(ssmRoleDTO, UserRole.class);
    dBRole = userRoleRepo.save(dBRole);
    RoleDTO ssmRole = modelMapper.map(dBRole, RoleDTO.class);
    return ssmRole;
  }

  @Override
  public RoleDTO getRoleById(String roleId) {
    UserRole dBRole = userRoleRepo.findByRoleId(roleId);
    RoleDTO ssmRole = modelMapper.map(dBRole, RoleDTO.class);
    return ssmRole;
  }

  @Override
  public List<RoleDTO> getAllRoleForUser() {
    List<UserRole> dBRole = userRoleRepo.findAll();
    List<RoleDTO> ssmRoleDTOs = dBRole.stream()
            .map(ssmRole -> modelMapper.map(ssmRole, RoleDTO.class)).collect(Collectors.toList());
    return ssmRoleDTOs;
  }

  @Override
  public RoleDTO updateRole(RoleDTO ssmRoleDTO) throws CustomException {
    UserRole dbRole = modelMapper.map(ssmRoleDTO, UserRole.class);
    try {
      userRoleRepo.save(dbRole);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }
    return modelMapper.map(dbRole, RoleDTO.class);
  }
}
