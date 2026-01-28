package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Announcement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementRepositoryTest {
    AnnouncementRepository announcementRepository;

    @BeforeEach
    void setUp() {
        announcementRepository = new AnnouncementRepository();
    }

    @Test
    public void save_Success_Test() {
        Announcement announcement = new Announcement("Title", "Content", "admin1");
        Announcement saved = announcementRepository.save(announcement);

        assertNotNull(saved.getId());
    }

    @Test
    public void findById_Success_Test() {
        Announcement announcement = new Announcement("Title", "Content", "admin1");
        Announcement saved = announcementRepository.save(announcement);

        Optional<Announcement> found = announcementRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Title", found.get().getTitle());
    }

    @Test
    public void findByAdminId_Success_Test() {
        announcementRepository.save(new Announcement("Title 1", "Content 1", "admin1"));
        announcementRepository.save(new Announcement("Title 2", "Content 2", "admin1"));

        List<Announcement> announcements = announcementRepository.findByAdminId("admin1");
        assertEquals(2, announcements.size());
    }

    @Test
    public void delete_Success_Test() {
        Announcement announcement = new Announcement("Title", "Content", "admin1");
        Announcement saved = announcementRepository.save(announcement);
        announcementRepository.delete(saved.getId());

        Optional<Announcement> found = announcementRepository.findById(saved.getId());
        assertTrue(found.isEmpty());
    }
}
