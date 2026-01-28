package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Announcement;

import java.util.List;

public interface IAnnouncementService {
    Announcement publishAnnouncement(String title, String content, String adminId);
    List<Announcement> getAllAnnouncements();
    List<Announcement> getAnnouncementsByAdmin(String adminId);
    Announcement getAnnouncementById(String id);
    void deleteAnnouncement(String id);
}
