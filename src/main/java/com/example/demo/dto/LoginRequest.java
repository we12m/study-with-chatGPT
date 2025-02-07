package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "로그인 요청 데이터")
public class LoginRequest {

    @NotBlank(message = "Username is mandatory") // 실제 애플리케이션 로직에 유효성 체크
    @Schema(description = "사용자 이름", example = "testuser", required = true) // required 클라이언트에게 알리는 API 문서화시 사용
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Schema(description = "비밀번호", example = "P@ssword", required = true)
    private String password;

}
