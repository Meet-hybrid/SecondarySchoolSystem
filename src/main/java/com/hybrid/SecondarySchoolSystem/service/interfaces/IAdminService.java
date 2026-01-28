package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Admin;

import java.util.List;

public interface IAdminService {
    Admin createAdmin(String name, String email, String password);
    List<Admin> getAllAdmins();
}
