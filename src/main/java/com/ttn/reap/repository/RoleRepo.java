package com.ttn.reap.repository;

import com.ttn.reap.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
    Role findByRole(String role);

    List<Role> findByUserId(Integer integer);
}
