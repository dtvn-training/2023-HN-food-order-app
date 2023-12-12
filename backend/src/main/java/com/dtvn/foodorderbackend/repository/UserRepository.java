package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.User;
import com.mysql.cj.x.protobuf.MysqlxCursor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserById(Integer id);
    Optional<User> findUserByUsername(String username);
    boolean existsUserByUsername(String username);

    User findUserByUsernameAndPasswordAndStatus(String username,String password,User.Status status);

    User findUserByUsernameAndStatus(String username, User.Status status);
}
