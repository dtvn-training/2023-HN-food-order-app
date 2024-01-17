package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserById(long id);

    Optional<User> findUserByEmail(String email);
//    @Query("SELECT EXISTS (SELECT User FROM User u WHERE u.email = :email)")
    boolean existsByEmail(String email);
    User findUserByEmailAndStatus(String email, User.Status status);

    @Query("update User u set u.status = :status where u.email =:email")
    @Modifying
    @Transactional
    void changeStatusByEmail(String email, User.Status status);

    @Query(
            """
                        select u
                        from User u
                        where (:fullName is null or u.fullName = :fullName)
                        and (:email is null or u.email = :email)
                        and (:role is null or u.role =:role)
                        and (:status is null or u.status = :status)
                    """
    )
    List<User> getUserByCriteria(
            String fullName,
            String email,
            User.Role role,
            User.Status status
    );

    List<User> findAllByStatus(User.Status status);
    @Modifying
    @Transactional
    @Query(value = "update User u set u.status = :status where u.email = :email")
    void updateStatusByEmail(User.Status status, String email);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.balance = u.balance + :balanceAdd where u.id = :userId")
    void addBalanceById(long userId,int balanceAdd);

    List<User> findUserByApprovedAndStatus(boolean approved,User.Status status);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.approved = :approved where u.email = :email")
    void changeApprovedByEmail(String email, boolean approved);
}
