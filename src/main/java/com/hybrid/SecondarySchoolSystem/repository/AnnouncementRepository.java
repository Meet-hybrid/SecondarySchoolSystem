package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Announcement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnnouncementRepository {

    private final List<Announcement> announcements = new ArrayList<>();

    public Announcement save(Announcement announcement) {
        if (announcement.getId() == null) {
            announcement.setId(String.valueOf(announcements.size() + 1));
        }
        announcements.add(announcement);
        return announcement;
    }

    public List<Announcement> findAll() {
        return announcements;
    }

    public Optional<Announcement> findById(String id) {
        return announcements.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public List<Announcement> findByAdminId(String adminId) {
        return announcements.stream().filter(a -> a.getAdminId().equals(adminId)).toList();
    }

    public void delete(String id) {
        announcements.removeIf(a -> a.getId().equals(id));
    }
}
