package com.s5.javaback.repository;

import com.s5.javaback.model.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
