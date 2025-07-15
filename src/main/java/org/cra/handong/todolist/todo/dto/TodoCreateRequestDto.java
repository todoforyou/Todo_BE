package org.cra.handong.todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateRequestDto {
    private String content;
    private Boolean completed;
    private Boolean deleted;
}
