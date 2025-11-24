package com.example.boardservice.board.domain;

import com.example.boardservice.board.dto.SaveUserRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(SaveUserRequestDto saveUserRequestDto) {
        User user = new User(
                saveUserRequestDto.getUserId(),
                saveUserRequestDto.getName()
        );

        this.userRepository.save(user);
    }

}
