package org.cra.handong.todolist.todo.service;

import lombok.RequiredArgsConstructor;
import org.cra.handong.todolist.todo.dto.TodoCreateRequestDto;
import org.cra.handong.todolist.todo.dto.TodoResponseDto;
import org.cra.handong.todolist.todo.entity.Todo;
import org.cra.handong.todolist.todo.repository.TodoRepo;
import org.cra.handong.todolist.user.repository.UserRepo;
import org.cra.handong.todolist.user.entity.User;
import org.cra.handong.todolist.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final UserRepo userRepository;
    private final TodoRepo todoRepository;
    private final UserService userService;


    public TodoResponseDto createTodo(String uuid, TodoCreateRequestDto requestDto) {
        User user = userService.findByUuid(uuid);

        Todo todo = Todo.builder()
                .content(requestDto.getContent())
                .completed(requestDto.getCompleted())
                .deleted(requestDto.getDeleted())
                .user(user)
                .build();

        todoRepository.save(todo);

        return toResponseDto(todo);
    }

    public List<TodoResponseDto> getTodos(String uuid) {
        User user = userService.findByUuid(uuid);

        return todoRepository.findAllByUser(user).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

//    public TodoResponseDto updateTodo(String uuid, TodoUpdateRequestDto requestDto) {
//        User user = userService.findByUuid(uuid);
//
//        Todo todo = todoRepository.findById(requestDto.getId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다."));
//
//        if (!todo.getUser().getId().equals(user.getId())) {
//            throw new IllegalArgumentException("본인의 할 일이 아닙니다.");
//        }
//
//        todo.update(
//                requestDto.getContent(),
//                requestDto.getCompleted(),
//                requestDto.getDeleted()
//        );
//
//        return toResponseDto(todo);
//    }

    public void deleteTodo(String uuid, Long todoId) {
        User user = userService.findByUuid(uuid);

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다."));

        if (!todo.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인의 할 일이 아닙니다.");
        }

        todoRepository.delete(todo);
    }

    private TodoResponseDto toResponseDto(Todo todo) {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .content(todo.getContent())
                .completed(todo.getCompleted())
                .deleted(todo.getDeleted())
                .userId(todo.getUser().getId())
                .build();
    }
}
