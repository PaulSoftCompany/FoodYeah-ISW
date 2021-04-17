package com.example.service;

import com.example.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findRoleAll();
    Role getRole(Long id);

    Role createRole(Role role);
    Role updateRole(Role role);
    Role deleteRole(Long id);
}
