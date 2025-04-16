package com.Adminsampledemo.Service;

import com.Adminsampledemo.Model.Admin;
import com.Adminsampledemo.Model.User;
import com.Adminsampledemo.Repositiry.UserRepository;
import com.Adminsampledemo.Repositiry.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void assignUserToAdmin(Long userId, Long adminId) {
        User user = getUserById(userId);
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + adminId));
        user.setAdmin(admin);
        userRepository.save(user);
    }

    public List<User> searchUsers(String username) {
        return userRepository.findByUsernameContaining(username);
    }

    public void bulkDeleteUsers(List<Long> userIds) {
        userRepository.deleteAllById(userIds);
    }
}
