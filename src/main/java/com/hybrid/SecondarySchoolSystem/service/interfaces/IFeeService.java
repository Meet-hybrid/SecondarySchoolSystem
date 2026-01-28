package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Fee;

import java.util.List;

public interface IFeeService {
    Fee manageFee(String studentId, Double amount, String status, java.time.LocalDateTime dueDate);
    List<Fee> getStudentFees(String studentId);
    List<Fee> getFeesByStatus(String status);
    List<Fee> getAllFees();
    void markFeeAsPaid(String feeId);
}
