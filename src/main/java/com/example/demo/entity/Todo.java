package com.example.demo.entity;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID

    @NotBlank(message = "Title is mandotory")
    private String title;  //제목

    private String description; // 내용
    private Boolean completed; // 완료여부

}
