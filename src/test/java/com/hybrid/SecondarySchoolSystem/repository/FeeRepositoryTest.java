package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Fee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FeeRepositoryTest {

    private FeeRepository feeRepository;

    @BeforeEach
    void setUp() {
        feeRepository = new FeeRepository();
    }

    @Test
    public void testSaveFee() {
        Fee fee = new Fee("S001", 5000.0, "PENDING", LocalDateTime.now());
        Fee savedFee = feeRepository.save(fee);

        assertNotNull(savedFee);
        assertNotNull(savedFee.getId());
        assertEquals("S001", savedFee.getStudentId());
        assertEquals(5000.0, savedFee.getAmount());
    }

    @Test
    public void testFindAll() {
        Fee fee1 = new Fee("S001", 5000.0, "PENDING", LocalDateTime.now());
        Fee fee2 = new Fee("S002", 3000.0, "PAID", LocalDateTime.now());

        feeRepository.save(fee1);
        feeRepository.save(fee2);

        List<Fee> allFees = feeRepository.findAll();

        assertNotNull(allFees);
        assertEquals(2, allFees.size());
    }

    @Test
    public void testFindById() {
        Fee fee = new Fee("S001", 5000.0, "PENDING", LocalDateTime.now());
        Fee savedFee = feeRepository.save(fee);

        Optional<Fee> foundFee = feeRepository.findById(savedFee.getId());

        assertTrue(foundFee.isPresent());
        assertEquals(savedFee.getId(), foundFee.get().getId());
    }

    @Test
    public void testFindByStudentId() {
        Fee fee1 = new Fee("S001", 5000.0, "PENDING", LocalDateTime.now());
        Fee fee2 = new Fee("S001", 3000.0, "PAID", LocalDateTime.now());
        Fee fee3 = new Fee("S002", 4000.0, "PENDING", LocalDateTime.now());

        feeRepository.save(fee1);
        feeRepository.save(fee2);
        feeRepository.save(fee3);

        List<Fee> student1Fees = feeRepository.findByStudentId("S001");

        assertNotNull(student1Fees);
        assertEquals(2, student1Fees.size());
    }

    @Test
    public void testFindByStatus() {
        Fee pendingFee1 = new Fee("S001", 5000.0, "PENDING", LocalDateTime.now());
        Fee pendingFee2 = new Fee("S002", 3000.0, "PENDING", LocalDateTime.now());
        Fee paidFee = new Fee("S003", 4000.0, "PAID", LocalDateTime.now());

        feeRepository.save(pendingFee1);
        feeRepository.save(pendingFee2);
        feeRepository.save(paidFee);

        List<Fee> pendingFees = feeRepository.findByStatus("PENDING");

        assertNotNull(pendingFees);
        assertEquals(2, pendingFees.size());
    }

    @Test
    public void testEmptyRepository() {
        List<Fee> allFees = feeRepository.findAll();

        assertNotNull(allFees);
        assertEquals(0, allFees.size());
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<Fee> foundFee = feeRepository.findById("NONEXISTENT");

        assertFalse(foundFee.isPresent());
    }

    @Test
    public void testMultipleSavesWithAutoIncrement() {
        Fee fee1 = new Fee("S001", 5000.0, "PENDING", LocalDateTime.now());
        Fee fee2 = new Fee("S002", 3000.0, "PAID", LocalDateTime.now());
        Fee fee3 = new Fee("S003", 4000.0, "PENDING", LocalDateTime.now());

        Fee saved1 = feeRepository.save(fee1);
        Fee saved2 = feeRepository.save(fee2);
        Fee saved3 = feeRepository.save(fee3);

        assertNotEquals(saved1.getId(), saved2.getId());
        assertNotEquals(saved2.getId(), saved3.getId());
    }
}
