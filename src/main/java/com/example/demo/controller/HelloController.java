package com.example.demo.controller;

import com.example.demo.dto.GreetingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Operation(summary = "환영인사", description = "환영인사")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @Operation(summary = "인사말 이름", description = "입력된 이름을 넣어 인사를 반환합니다.")
    @GetMapping("/greeting")
    public GreetingDTO sayHello(
            @Parameter(description = "사용자 이름", example = "홍길동")
            @RequestParam(value = "name", defaultValue = "james") String name) {
        return new GreetingDTO("Hello " + name + "!");
    }

    @Operation(summary = "인사 Post", description = "헤더에 name을 넣어서 Post 형식으로 인사 반환")
    @PostMapping("/greeting")
    public GreetingDTO sayHello2(
            @Parameter(in = ParameterIn.HEADER, description = "사용자 이름", required = false, example = "홍길동")
            @RequestHeader(value="name", defaultValue = "kate") String name) {
        return new GreetingDTO("Hello " + name + "!, Have a nice day");
    }
}
