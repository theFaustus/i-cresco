package com.evil.inc.icresco.repo;

import com.evil.inc.icresco.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    @Query("select r from UserRole r where r.user.userName = :userName")
    List<UserRole> findUserRolesByUserName(@Param("userName") String userName);
}
