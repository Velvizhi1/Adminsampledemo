package com.Adminsampledemo.Service;

import com.Adminsampledemo.Model.Admin;
import com.Adminsampledemo.Model.User;
import com.Adminsampledemo.Repositiry.AdminRepository;
import com.Adminsampledemo.Repositiry.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Admin existingAdmin = getAdminById(id);
        existingAdmin.setName(updatedAdmin.getName());
        existingAdmin.setEmail(updatedAdmin.getEmail());
        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Optional<User> getUsersByAdmin(Long id) {
        // Ensure the admin exists before fetching users
        getAdminById(id);
        return userRepository.findById(id);
    }
}
