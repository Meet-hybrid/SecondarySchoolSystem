package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Fee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FeeRepository {

    private final List<Fee> fees = new ArrayList<>();

    public Fee save(Fee fee) {
        if (fee.getId() == null) {
            fee.setId(String.valueOf(fees.size() + 1));
        }
        fees.add(fee);
        return fee;
    }

    public List<Fee> findAll() {
        return fees;
    }

    public Optional<Fee> findById(String id) {
        return fees.stream().filter(f -> f.getId().equals(id)).findFirst();
    }

    public List<Fee> findByStudentId(String studentId) {
        return fees.stream().filter(f -> f.getStudentId().equals(studentId)).toList();
    }

    public List<Fee> findByStatus(String status) {
        return fees.stream().filter(f -> f.getStatus().equals(status)).toList();
    }
}
