package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.dto.request.TransactionRequest;
import com.dtvn.foodorderbackend.model.entity.Transaction;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.repository.TransactionRepository;
import com.dtvn.foodorderbackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    final TransactionRepository transactionRepository;
    final HttpServletRequest httpServletRequest;
    final UserService userService;
    final UserRepository userRepository;

    public void saveTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = Transaction.builder().approved(false).value(transactionRequest.getValue()).build();
        long userId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));

        transaction.update(userId);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransaction(long userId) {
        return transactionRepository.findAllByCreatedById(userId);
    }

    public List<Transaction> getTransactionNotApproved(long userId) {
        return transactionRepository.findAllByCreatedByIdAndApprovedFalse(userId);
    }

    public List<Transaction> getTransactionApproved(long userId) {
        return transactionRepository.findAllByCreatedByIdAndApprovedTrue(userId);
    }

    public List<Transaction> getTransactionNotApproved() {
        return transactionRepository.findAllByApprovedFalse();
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionApprovedBy(long userId) {
        return transactionRepository.findAllByApprovedTrueAndUpdatedById(userId);
    }

    public boolean deleteTransaction(long transactionId) {
        if (transactionRepository.existsByIdAndApprovedFalse(transactionId)) {
            transactionRepository.deleteById(transactionId);
            return true;
        }
        return false;
    }

    public boolean approveTransaction(long transactionId) {
        Transaction transaction = transactionRepository.findByIdAndApprovedFalse(transactionId);

        if (transaction != null) {
            long adminId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("user_id")));
            transaction.setApproved(true);
            transaction.update(adminId);
            transactionRepository.save(transaction);

            User user = userService.loadUserById(transaction.getCreatedById());
            user.setBalance(user.getBalance() + transaction.getValue());
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
