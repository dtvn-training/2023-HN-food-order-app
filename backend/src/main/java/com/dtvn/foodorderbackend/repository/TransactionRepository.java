package com.dtvn.foodorderbackend.repository;

import com.dtvn.foodorderbackend.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCreatedById(long createdById);

    List<Transaction> findAllByCreatedByIdAndApprovedFalse(long createdById);

    List<Transaction> findAllByCreatedByIdAndApprovedTrue(long userId);

    List<Transaction> findAllByApprovedFalse();

    boolean existsByIdAndApprovedFalse(long transactionId);

    Transaction findByIdAndApprovedFalse(long transactionId);

    @Transactional
    @Modifying
    @Query("UPDATE Transaction t SET t.approved = true WHERE t.id = :transactionId")
    void setApproved(long transactionId);

    List<Transaction> findAllByApprovedTrueAndUpdatedById(long adminId);
}
