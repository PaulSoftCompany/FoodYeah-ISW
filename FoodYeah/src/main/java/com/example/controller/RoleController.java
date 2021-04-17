package com.example.controller;

import com.example.entity.Role;
import com.example.service.RoleService;
import com.example.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService RoleService;

    @GetMapping
    public ResponseEntity<List<Role>> listAllCustomerCategories(@RequestParam(name = "RoleId", required = false) Long RoleId){
        List<Role> customerCategories = new ArrayList<>();
        if(null == RoleId){
            customerCategories = RoleService.findRoleAll();
            if(customerCategories.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(customerCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") long id){
        log.info("Fetching Role with Id {}", id);

        Role Role = RoleService.getRole(id);

        if( null == Role){
            log.error("Role with id {} not found.",id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Role);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role Role, BindingResult result){
        log.info("Creating Role : {}", Role);
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Role RoleDB = RoleService.createRole(Role);
        return  ResponseEntity.status(HttpStatus.CREATED).body(RoleDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") long id, @RequestBody Role Role){
        log.info("Updating Role with id {}",id);
        Role currentRole = RoleService.getRole(id);
        if(null == currentRole){
            log.error("Unable to update Role with id {} not founded",id);
            return ResponseEntity.notFound().build();
        }
        Role.setId(id);
        currentRole = RoleService.updateRole(Role);
        return ResponseEntity.ok(currentRole);
    }

    @DeleteMapping(value =  "/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable("id") long id){
        log.info("Fetching & Deleting Role with id {}", id);
        Role Role = RoleService.getRole(id);
        if(null == Role){
            log.error("Unable to delete. Role with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        Role = RoleService.deleteRole(id);
        return  ResponseEntity.ok(Role);
    }
}
