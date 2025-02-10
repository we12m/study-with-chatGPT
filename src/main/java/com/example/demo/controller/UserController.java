package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository){

        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    //프로필 조회
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElse(null);

        if(user == null){
            return ResponseEntity.status(404).body("User Not Found");
        }

        user.setPassword(null); // 민감 정보 삭제
        return ResponseEntity.ok(user);
    }

    // 프로필 수정
    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody User updatedUser, Authentication authentication) {

        if(authentication == null) {
            return ResponseEntity.status(401).body("Unanthorized");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null) {
            return ResponseEntity.status(404).body("User not Found");
        }

        // email만 수정가능, 지금 비번 검사는 안하고 있음
        user.setEmail(updatedUser.getEmail());

        User savedUser = userRepository.save(user);
        savedUser.setPassword(null); // 민감정보 제거
        return ResponseEntity.ok(savedUser);
    }

    //비밀번호 변경
    @PutMapping("/me/password")
    public ResponseEntity<?> changePassword(@RequestParam String currentPassword, @RequestParam String newPassword, Authentication authentication) {
        if(authentication == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null){
            return ResponseEntity.status(404).body("User Not Found");
        }

        if(!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return ResponseEntity.status(400).body("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Password updated successfully");
    }
}
