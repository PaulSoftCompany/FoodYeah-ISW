package com.example.service.impl;

import com.example.entity.Role;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository RoleRepository;

    @Override
    public List<Role> findRoleAll() {
        return RoleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return RoleRepository.findById(id).orElse(null);
    }

    @Override
    public Role createRole(Role customer_category) {

        customer_category.setState("CREATED");
        return RoleRepository.save(customer_category);
    }

    @Override
    public Role updateRole(Role customer_category) {
        Role RoleDB = this.getRole(customer_category.getId());
        if (RoleDB == null) {
            return null;
        }
        RoleDB.setRoleName(customer_category.getRoleName());
        RoleDB.setRoleDescription(customer_category.getRoleDescription());
        RoleDB.setState("UPDATED");
        return RoleRepository.save(RoleDB);
    }

    @Override
    public Role deleteRole(Long id) {
        Role RoleDB = this.getRole(id);
        if (RoleDB == null) {
            return null;
        }
        RoleDB.setState("DELETED");
        return RoleRepository.save(RoleDB);
    }
}
