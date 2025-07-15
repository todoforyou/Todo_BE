package org.cra.handong.todolist.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.cra.handong.todolist.todo.entity.Todo;

@Getter
@Setter
@Builder
public class TodoResponseDto {
    private Long id;
    private String content;
    private Boolean completed;
    private Boolean deleted;
    private Long userId;

    public static TodoResponseDto from(Todo todo) {
        return TodoResponseDto.builder()
                .id(todo.getId())
                .content(todo.getContent())
                .completed(todo.getCompleted())
                .deleted(todo.getDeleted())
                .userId(todo.getUser().getId())
                .build();
    }
}
