package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Fee;
import com.hybrid.SecondarySchoolSystem.repository.FeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeeServiceTest {
    FeeService feeService;
    FeeRepository feeRepository;

    @BeforeEach
    void setUp() {
        feeRepository = new FeeRepository();
        feeService = new FeeService(feeRepository);
    }

    @Test
    public void manageFee_Success_Test() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(30);
        Fee fee = feeService.manageFee("student1", 50000.0, "PENDING", dueDate);

        assertNotNull(fee);
        assertEquals("student1", fee.getStudentId());
        assertEquals(50000.0, fee.getAmount());
        assertEquals("PENDING", fee.getStatus());
    }

    @Test
    public void getStudentFees_Success_Test() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(30);
        feeService.manageFee("student1", 50000.0, "PENDING", dueDate);
        feeService.manageFee("student1", 25000.0, "PAID", dueDate);

        List<Fee> fees = feeService.getStudentFees("student1");
        assertEquals(2, fees.size());
    }

    @Test
    public void getFeesByStatus_Success_Test() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(30);
        feeService.manageFee("student1", 50000.0, "PENDING", dueDate);
        feeService.manageFee("student2", 25000.0, "PAID", dueDate);

        List<Fee> pendingFees = feeService.getFeesByStatus("PENDING");
        assertEquals(1, pendingFees.size());
    }

    @Test
    public void getAllFees_Success_Test() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(30);
        feeService.manageFee("student1", 50000.0, "PENDING", dueDate);
        feeService.manageFee("student2", 25000.0, "PENDING", dueDate);

        List<Fee> fees = feeService.getAllFees();
        assertEquals(2, fees.size());
    }

    @Test
    public void markFeeAsPaid_Success_Test() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(30);
        Fee fee = feeService.manageFee("student1", 50000.0, "PENDING", dueDate);
        feeService.markFeeAsPaid(fee.getId());

        Fee retrieved = feeRepository.findById(fee.getId()).orElse(null);
        assertEquals("PAID", retrieved.getStatus());
        assertNotNull(retrieved.getPaidDate());
    }
}
