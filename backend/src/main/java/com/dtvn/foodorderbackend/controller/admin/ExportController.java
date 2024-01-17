package com.dtvn.foodorderbackend.controller.admin;

import com.dtvn.foodorderbackend.service.ExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/export")
public class ExportController {
    final ExportService exportService;

    @GetMapping("/get-bills")
    public ResponseEntity<?> getBillExport() {
        byte[] workbook = exportService.getBillExport();
        ByteArrayResource resource = new ByteArrayResource(workbook);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("bill.xlsx").build().toString())
                .body(resource);
    }

    @GetMapping("/get-users")
    public ResponseEntity<?> getUserExport() {
        byte[] workbook = exportService.getUserExport();
        ByteArrayResource resource = new ByteArrayResource(workbook);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("user.xlsx").build().toString())
                .body(resource);
    }
}
