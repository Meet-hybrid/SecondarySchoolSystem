package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Announcement;

import java.util.List;
import java.util.Optional;

public interface IAnnouncementRepository {
    Announcement save(Announcement announcement);
    List<Announcement> findAll();
    Optional<Announcement> findById(String id);
    List<Announcement> findByAdminId(String adminId);
    void delete(String id);
}
