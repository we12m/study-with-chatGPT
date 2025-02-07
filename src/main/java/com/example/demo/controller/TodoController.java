package com.example.demo.controller;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@Validated
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @Operation(summary = "To Do List", description = "전체 To Do List 출력")
    @GetMapping
    public List<Todo> getAllTodo() {
        return todoService.findAll();
    }

    @Operation(summary = "To Do 조회", description = "id로 To Do 조회")
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(
            @Parameter(description = "To do id", example = "1")
            @PathVariable Long id) {
        Todo todo = todoService.findById(id);
        return ResponseEntity.ok(todo);
    }

    @Operation(summary = "To Do 저장", description = "To Do 추가하기")
    @PostMapping
    public ResponseEntity<Todo> createTodo(
            @Parameter(description = "To do 정보", example = "title, description, completed(Boolean)")
            @Valid @RequestBody Todo todo){
        Todo createTodo = todoService.save(todo);
        return ResponseEntity.ok(createTodo);
    }

    @Operation(summary = "To Do 수정", description = "To do 정보 수정하기")
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @Parameter(description = "To do 정보 수정", example = "id, title, description, completed(Boolean)")
            @PathVariable Long id, @Valid @RequestBody Todo todoDetails) {
        Todo todo = todoService.findById(id);
        todo.setTitle(todoDetails.getTitle());
        todo.setCompleted(todoDetails.getCompleted());
        todo.setDescription(todoDetails.getDescription());

        Todo updateTodo = todoService.save(todo);
        return ResponseEntity.ok(updateTodo);
    }

    @Operation(summary = "To Do 삭제", description = "To Do 삭제하기")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @Parameter(description = "To Do 삭제하기", example = "1")
            @PathVariable Long id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
