package com.spring.codeblog.repository;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_user SET token =:token WHERE email = :email", nativeQuery = true)
    void updateToken (@Param("token") String token, @Param("email") String email);
}
