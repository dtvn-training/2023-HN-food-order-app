package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.User;
import com.mysql.cj.x.protobuf.MysqlxCursor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserById(Integer id);

    Optional<User> findUserByUsername(String username);
    boolean existsUserByUsername(String username);
    User findUserByUsernameAndStatus(String username, User.Status status);

    @Query("update User u set u.status = :status where u.username =:username")
    @Modifying
    @Transactional
    void changeStatusByUsername(String username,User.Status status);

    @Query(
            """
                        select u
                        from User u
                        where (:fullName is null or u.fullName = :fullName)
                        and (:username is null or u.username = :username)
                        and (:role is null or u.role =:role)
                        and (:status is null or u.status = :status)
                    """
    )
    List<User> getUserByCriteria(
            String fullName,
            String username,
            User.Role role,
            User.Status status
    );
}
