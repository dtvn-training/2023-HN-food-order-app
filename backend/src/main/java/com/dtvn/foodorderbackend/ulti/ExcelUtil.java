package com.dtvn.foodorderbackend.ulti;

import com.dtvn.foodorderbackend.model.dto.response.UserDTO;
import com.dtvn.foodorderbackend.model.entity.Bill;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {
    public static byte[] writeExcelBill(List<Bill> data) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream fileOut = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Bill Data");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Order ID");
            headerRow.createCell(2).setCellValue("Price");
            headerRow.createCell(3).setCellValue("Discount");
            headerRow.createCell(4).setCellValue("Ship Fee");
            headerRow.createCell(5).setCellValue("Final Cost");
            headerRow.createCell(6).setCellValue("Created By ID");
            headerRow.createCell(7).setCellValue("Created Time");
            headerRow.createCell(8).setCellValue("Updated By ID");
            headerRow.createCell(9).setCellValue("Last Update Time");

            int rowNum = 1;
            for (Bill bill : data) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(bill.getId());
                row.createCell(1).setCellValue(bill.getOrderId());
                row.createCell(2).setCellValue(bill.getPrice());
                row.createCell(3).setCellValue(bill.getDiscount());
                row.createCell(4).setCellValue(bill.getShipFee());
                row.createCell(5).setCellValue(bill.getFinalCost());
                row.createCell(6).setCellValue(bill.getCreatedById());
                row.createCell(7).setCellValue(bill.getCreatedTime().toString());
                row.createCell(8).setCellValue(bill.getUpdatedById());
                row.createCell(9).setCellValue(bill.getLastUpdateTime().toString());
            }
            workbook.write(fileOut);
            return fileOut.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] writeExcelUser(List<UserDTO> data) {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream fileOut = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("User Data");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Full Name");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Status");
            headerRow.createCell(4).setCellValue("Role");
            headerRow.createCell(5).setCellValue("Balance");

            int rowNum = 1;
            for (UserDTO user : data) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getFullName());
                row.createCell(2).setCellValue(user.getEmail());
                row.createCell(3).setCellValue(user.getStatus().toString());
                row.createCell(4).setCellValue(user.getRole().toString());
                row.createCell(5).setCellValue(user.getBalance());
            }
            workbook.write(fileOut);
            return fileOut.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] workbookToByteArray(XSSFWorkbook workbook) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
