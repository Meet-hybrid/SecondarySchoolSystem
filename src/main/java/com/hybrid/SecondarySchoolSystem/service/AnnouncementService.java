package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Announcement;
import com.hybrid.SecondarySchoolSystem.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public Announcement publishAnnouncement(String title, String content, String adminId) {
        Announcement announcement = new Announcement(title, content, adminId);
        return announcementRepository.save(announcement);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public List<Announcement> getAnnouncementsByAdmin(String adminId) {
        return announcementRepository.findByAdminId(adminId);
    }

    public Announcement getAnnouncementById(String id) {
        return announcementRepository.findById(id).orElse(null);
    }

    public void deleteAnnouncement(String id) {
        announcementRepository.delete(id);
    }
}
