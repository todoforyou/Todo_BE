package org.cra.handong.todolist.todo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoResponseDto {
    private Long id;
    private String content;
    private Boolean completed;
    private Boolean deleted;
    private Long userId;
}
