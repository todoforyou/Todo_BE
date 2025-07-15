package org.cra.handong.todolist.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cra.handong.todolist.todo.dto.TodoCreateRequestDto;
import org.cra.handong.todolist.user.entity.User;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Boolean completed;

    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static Todo toEntity(TodoCreateRequestDto dto){
        return Todo.builder()
                .content(dto.getContent())
                .completed(dto.getCompleted())
                .deleted(false)
        .build();
    }
    public static Todo of(TodoCreateRequestDto dto, User user){
        return Todo.builder()
                .content(dto.getContent())
                .completed(dto.getCompleted())
                .deleted(false)
                .user(user)
        .build();
    }

}
