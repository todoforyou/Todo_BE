package org.cra.handong.todolist.todo.controller;

import lombok.RequiredArgsConstructor;
import org.cra.handong.todolist.todo.dto.TodoCreateRequestDto;
import org.cra.handong.todolist.todo.dto.TodoResponseDto;
import org.cra.handong.todolist.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoResponseDto createTodo(
            @RequestHeader("uuid") String uuid,
            @RequestBody TodoCreateRequestDto requestDto) {

        return todoService.createTodo(uuid, requestDto);
    }

    @GetMapping
    public List<TodoResponseDto> getTodos(@RequestHeader("uuid") String uuid) {
        return todoService.getTodos(uuid);
    }

//    @PutMapping
//    public TodoResponseDto updateTodo(
//            @RequestHeader("uuid") String uuid,
//            @RequestBody TodoUpdateRequestDto requestDto) {
//
//        return todoService.updateTodo(uuid, requestDto);
//    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(
            @RequestHeader("uuid") String uuid,
            @PathVariable Long todoId) {

        todoService.deleteTodo(uuid, todoId);
    }
}
