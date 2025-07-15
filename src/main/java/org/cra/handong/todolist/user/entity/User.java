package org.cra.handong.todolist.user.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.cra.handong.todolist.todo.entity.Todo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();
}
