package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {

    void save(Role role);

    void update(Role updatedRole);

    Role getRoleById(Integer id);

    void delete(Integer id);

    List<Role> getDemandedRoles();
}

