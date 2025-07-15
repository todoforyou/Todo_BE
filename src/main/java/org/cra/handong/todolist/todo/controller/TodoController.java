package org.cra.handong.todolist.todo.controller;

import lombok.RequiredArgsConstructor;
import org.cra.handong.todolist.todo.dto.TodoCreateRequestDto;
import org.cra.handong.todolist.todo.dto.TodoResponseDto;
import org.cra.handong.todolist.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(
            @RequestHeader("userId") String userId,
            @RequestBody TodoCreateRequestDto requestDto) {

        return ResponseEntity.ok(todoService.createTodo(userId, requestDto));

    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos(@RequestHeader("userId") String userId) {
        return ResponseEntity.ok(todoService.getTodos(userId));
    }

//    @PutMapping
//    public TodoResponseDto updateTodo(
//            @RequestHeader("userId") String userId,
//            @RequestBody TodoUpdateRequestDto requestDto) {
//
//        return todoService.updateTodo(userId, requestDto);
//    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void>deleteTodo(
            @PathVariable Long todoId,
            @RequestParam Long userId) {
        todoService.deleteTodo(userId, todoId);
        return ResponseEntity.ok().build();

    }
}
