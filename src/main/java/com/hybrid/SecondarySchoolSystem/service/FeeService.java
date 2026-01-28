package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Fee;
import com.hybrid.SecondarySchoolSystem.repository.FeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {

    private final FeeRepository feeRepository;

    public FeeService(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    public Fee manageFee(String studentId, Double amount, String status, java.time.LocalDateTime dueDate) {
        Fee fee = new Fee(studentId, amount, status, dueDate);
        return feeRepository.save(fee);
    }

    public List<Fee> getStudentFees(String studentId) {
        return feeRepository.findByStudentId(studentId);
    }

    public List<Fee> getFeesByStatus(String status) {
        return feeRepository.findByStatus(status);
    }

    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    public void markFeeAsPaid(String feeId) {
        feeRepository.findById(feeId).ifPresent(fee -> {
            fee.setStatus("PAID");
            fee.setPaidDate(java.time.LocalDateTime.now());
            feeRepository.save(fee);
        });
    }
}
