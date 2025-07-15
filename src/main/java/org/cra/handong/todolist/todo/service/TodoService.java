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

    @Transactional
    public TodoResponseDto createTodo(final Long userId, final TodoCreateRequestDto requestDto) {
        final User user = userRepository.findByUserId(userId).orElseThrow();
        final Todo todo = Todo.of(requestDto, user);
        final Todo newTodo = todoRepository.save(todo);
        return TodoResponseDto.from(newTodo);
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> getTodos(Long userid) {
        User user = userRepository.findByUserId(userid).orElseThrow();

        return todoRepository.findAllByUser(user).stream()
                .map(TodoResponseDto::from)
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

    public void deleteTodo(Long userId, Long todoId) {
        User user = userRepository.findByUserId(userId).orElseThrow();

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다."));

        if (!todo.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인의 할 일이 아닙니다.");
        }

        todoRepository.delete(todo);
    }

}
