package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Fee;

import java.util.List;
import java.util.Optional;

public interface IFeeRepository {
    Fee save(Fee fee);
    List<Fee> findAll();
    Optional<Fee> findById(String id);
    List<Fee> findByStudentId(String studentId);
    List<Fee> findByStatus(String status);
}
