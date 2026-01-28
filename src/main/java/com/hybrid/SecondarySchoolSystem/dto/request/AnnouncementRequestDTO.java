package com.hybrid.SecondarySchoolSystem.dto.request;

public class AnnouncementRequestDTO {
    private String title;
    private String content;
    private String adminId;

    public AnnouncementRequestDTO() {
    }

    public AnnouncementRequestDTO(String title, String content, String adminId) {
        this.title = title;
        this.content = content;
        this.adminId = adminId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
