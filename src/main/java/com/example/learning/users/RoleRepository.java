package com.example.learning.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

        Role findRoleByRoleName(String roleName);

        @Query(value = "select count(c) from Category c")
        Long checkSize();

}
