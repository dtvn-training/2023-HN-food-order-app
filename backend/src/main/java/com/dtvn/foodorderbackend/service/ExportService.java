package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.response.UserDTO;
import com.dtvn.foodorderbackend.repository.BillRepository;
import com.dtvn.foodorderbackend.ulti.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportService {
    final BillRepository billRepository;
    final UserService userService;
    final Mapper mapper;

    public byte[] getBillExport() {
        return ExcelUtil.writeExcelBill(billRepository.findAll());
    }

    public byte[] getUserExport() {
        return ExcelUtil.writeExcelUser(mapper.mapList(userService.getUserVerified(), UserDTO.class));
    }
}
