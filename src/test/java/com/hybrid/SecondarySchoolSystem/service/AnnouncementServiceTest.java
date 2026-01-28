package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Announcement;
import com.hybrid.SecondarySchoolSystem.repository.AnnouncementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementServiceTest {
    AnnouncementService announcementService;
    AnnouncementRepository announcementRepository;

    @BeforeEach
    void setUp() {
        announcementRepository = new AnnouncementRepository();
        announcementService = new AnnouncementService(announcementRepository);
    }

    @Test
    public void publishAnnouncement_Success_Test() {
        Announcement announcement = announcementService.publishAnnouncement(
                "School Closure",
                "School will be closed on 2025-02-01",
                "admin1"
        );

        assertNotNull(announcement);
        assertEquals("School Closure", announcement.getTitle());
        assertEquals("School will be closed on 2025-02-01", announcement.getContent());
        assertEquals("admin1", announcement.getAdminId());
    }

    @Test
    public void getAllAnnouncements_Success_Test() {
        announcementService.publishAnnouncement("Announcement 1", "Content 1", "admin1");
        announcementService.publishAnnouncement("Announcement 2", "Content 2", "admin1");

        List<Announcement> announcements = announcementService.getAllAnnouncements();
        assertEquals(2, announcements.size());
    }

    @Test
    public void getAnnouncementsByAdmin_Success_Test() {
        announcementService.publishAnnouncement("Announcement 1", "Content 1", "admin1");
        announcementService.publishAnnouncement("Announcement 2", "Content 2", "admin2");

        List<Announcement> announcements = announcementService.getAnnouncementsByAdmin("admin1");
        assertEquals(1, announcements.size());
    }

    @Test
    public void getAnnouncementById_Success_Test() {
        Announcement created = announcementService.publishAnnouncement("Title", "Content", "admin1");
        Announcement retrieved = announcementService.getAnnouncementById(created.getId());

        assertNotNull(retrieved);
        assertEquals("Title", retrieved.getTitle());
    }

    @Test
    public void deleteAnnouncement_Success_Test() {
        Announcement announcement = announcementService.publishAnnouncement("Title", "Content", "admin1");
        announcementService.deleteAnnouncement(announcement.getId());

        Announcement retrieved = announcementService.getAnnouncementById(announcement.getId());
        assertNull(retrieved);
    }

    @Test
    public void getAllAnnouncements_EmptyList_WhenNoAnnouncements_Test() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        
        assertNotNull(announcements);
        assertEquals(0, announcements.size());
    }

    @Test
    public void getAnnouncementsByAdmin_EmptyList_WhenAdminHasNoAnnouncements_Test() {
        announcementService.publishAnnouncement("Announcement 1", "Content 1", "admin1");
        
        List<Announcement> announcements = announcementService.getAnnouncementsByAdmin("admin2");
        
        assertEquals(0, announcements.size());
    }

    @Test
    public void publishAnnouncement_HasValidId_Test() {
        Announcement announcement = announcementService.publishAnnouncement("Title", "Content", "admin1");
        
        assertNotNull(announcement.getId());
        assertFalse(announcement.getId().isEmpty());
    }

    @Test
    public void publishAnnouncement_HasTimestamps_Test() {
        Announcement announcement = announcementService.publishAnnouncement("Title", "Content", "admin1");
        
        assertNotNull(announcement.getCreatedAt());
        assertNotNull(announcement.getUpdatedAt());
    }

    @Test
    public void publishMultipleAnnouncements_ByDifferentAdmins_Test() {
        announcementService.publishAnnouncement("Ann1", "Content1", "admin1");
        announcementService.publishAnnouncement("Ann2", "Content2", "admin1");
        announcementService.publishAnnouncement("Ann3", "Content3", "admin2");
        announcementService.publishAnnouncement("Ann4", "Content4", "admin3");
        
        List<Announcement> all = announcementService.getAllAnnouncements();
        List<Announcement> admin1List = announcementService.getAnnouncementsByAdmin("admin1");
        List<Announcement> admin2List = announcementService.getAnnouncementsByAdmin("admin2");
        
        assertEquals(4, all.size());
        assertEquals(2, admin1List.size());
        assertEquals(1, admin2List.size());
    }

    @Test
    public void getAnnouncementById_ReturnsNull_WhenNotFound_Test() {
        Announcement announcement = announcementService.getAnnouncementById("nonexistent-id");
        
        assertNull(announcement);
    }

    @Test
    public void deleteAnnouncement_RemovesFromRepository_Test() {
        Announcement ann1 = announcementService.publishAnnouncement("Ann1", "Content1", "admin1");
        Announcement ann2 = announcementService.publishAnnouncement("Ann2", "Content2", "admin1");
        
        announcementService.deleteAnnouncement(ann1.getId());
        
        List<Announcement> remaining = announcementService.getAllAnnouncements();
        assertEquals(1, remaining.size());
        assertEquals(ann2.getId(), remaining.get(0).getId());
    }

    @Test
    public void announcement_ContainsAllFields_Test() {
        Announcement announcement = announcementService.publishAnnouncement(
                "Important Notice",
                "School will open on Monday",
                "admin_school"
        );
        
        assertNotNull(announcement.getId());
        assertNotNull(announcement.getTitle());
        assertNotNull(announcement.getContent());
        assertNotNull(announcement.getAdminId());
        assertNotNull(announcement.getCreatedAt());
        assertNotNull(announcement.getUpdatedAt());
        
        assertEquals("Important Notice", announcement.getTitle());
        assertEquals("School will open on Monday", announcement.getContent());
        assertEquals("admin_school", announcement.getAdminId());
    }

    @Test
    public void announcement_CanBeRetrieved_Consistently_Test() {
        Announcement ann1 = announcementService.publishAnnouncement("Title", "Content", "admin1");
        
        Announcement retrieved = announcementService.getAnnouncementById(ann1.getId());
        assertNotNull(retrieved);
        assertEquals(ann1.getId(), retrieved.getId());
    }
}
