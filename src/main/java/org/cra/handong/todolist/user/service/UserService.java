package org.cra.handong.todolist.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cra.handong.todolist.user.entity.User;
import org.cra.handong.todolist.user.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepo userRepository;

    public User findByUuid(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }
}

