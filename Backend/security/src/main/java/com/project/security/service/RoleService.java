package com.project.security.service;

import com.project.common.dto.PaginationDTO;
import com.project.common.exception.CustomException;
import com.project.repo.dto.RoleDTO;

import java.util.List;
import java.util.Map;

public interface RoleService {
  PaginationDTO<RoleDTO> getAllRoles(int page, int perPage, Map<String, String> searchParams, String sort);

  boolean exist(String roleName);

  RoleDTO createRole(RoleDTO ssmRoleDTO);

  RoleDTO getRoleById(String roleId);

  RoleDTO updateRole(RoleDTO ssmRoleDTO) throws CustomException;

  List<RoleDTO> getAllRoleForUser();
}
