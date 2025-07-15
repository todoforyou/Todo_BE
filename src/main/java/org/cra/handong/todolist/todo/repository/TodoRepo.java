package org.cra.handong.todolist.todo.repository;

import org.cra.handong.todolist.todo.entity.Todo;
import org.cra.handong.todolist.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}