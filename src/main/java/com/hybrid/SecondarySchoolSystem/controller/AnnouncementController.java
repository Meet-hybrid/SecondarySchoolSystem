package com.hybrid.SecondarySchoolSystem.controller;

import com.hybrid.SecondarySchoolSystem.dto.request.AnnouncementRequestDTO;
import com.hybrid.SecondarySchoolSystem.dto.response.AnnouncementResponseDTO;
import com.hybrid.SecondarySchoolSystem.entity.Announcement;
import com.hybrid.SecondarySchoolSystem.service.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/publish")
    public ResponseEntity<AnnouncementResponseDTO> publishAnnouncement(@RequestBody AnnouncementRequestDTO dto) {
        Announcement announcement = announcementService.publishAnnouncement(
                dto.getTitle(),
                dto.getContent(),
                dto.getAdminId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(announcement));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnnouncementResponseDTO>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(announcements.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<AnnouncementResponseDTO>> getAnnouncementsByAdmin(@PathVariable String adminId) {
        List<Announcement> announcements = announcementService.getAnnouncementsByAdmin(adminId);
        return ResponseEntity.ok(announcements.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> getAnnouncementById(@PathVariable String id) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        if (announcement != null) {
            return ResponseEntity.ok(convertToDTO(announcement));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable String id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }

    private AnnouncementResponseDTO convertToDTO(Announcement announcement) {
        return new AnnouncementResponseDTO(
                announcement.getId(),
                announcement.getTitle(),
                announcement.getContent(),
                announcement.getAdminId(),
                announcement.getCreatedAt(),
                announcement.getUpdatedAt()
        );
    }
}
