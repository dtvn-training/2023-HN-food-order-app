package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.Bill;
import com.dtvn.foodorderbackend.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BillService {
    final BillRepository billRepository;

    public List<Bill> getBill(long userId) {
        return billRepository.findAllByCreatedById(userId);
    }

    public List<Bill> getBill() {
        return billRepository.findAll();
    }
}
