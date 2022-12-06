package com.sahce.ufcg.repositories;

import com.sahce.ufcg.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    @Query(value = "select * from my_user  u where u.email = :pemail", nativeQuery = true)
    Optional<MyUser> findByEmail(@Param("pemail") String email);

    @Query(value = "select * from my_user u where u.active = false", nativeQuery = true)
    Optional<List<MyUser>> findAllInactiveUsers();
}
