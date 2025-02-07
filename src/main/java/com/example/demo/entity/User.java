package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
@Schema(description = "회원정보 Entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "회원 ID", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Username is mandatory") // required = true 대신 not blank, not null과 같은 어노테이션으로 파악하는 것으로 변경됨
    @Column(unique = true, nullable = false)
    @Schema(description = "사용자 이름", example = "testuser")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    @Schema(description = "암호화된 사용자 비밀번호", example = "EncryptedPassword")
    private String password;

    @Email(message = "Email should be valid")
    @Column(unique = true)
    @Schema(description = "사용자 이메일", example = "test@example.com")
    private String email;

    @Schema(description = "사용자 역할", example = "ROLE_USER", accessMode = Schema.AccessMode.READ_ONLY)
    private String role; // login 권한
}
