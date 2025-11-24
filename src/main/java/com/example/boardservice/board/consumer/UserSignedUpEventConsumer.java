package com.example.boardservice.board.consumer;

import com.example.boardservice.board.domain.UserService;
import com.example.boardservice.board.dto.SaveUserRequestDto;
import com.example.boardservice.board.event.UserSignedUpEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserSignedUpEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(UserSignedUpEventConsumer.class);
    private final UserService userService;

    public UserSignedUpEventConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(
            topics = "user.signed-up",
            groupId = "board-service"
    )
    public void consume(String message) {
        log.info("consume : "+ message);
        UserSignedUpEvent userSignedUpEvent
                = UserSignedUpEvent.fromJson(message);

        // 사용자 정보 저장
        SaveUserRequestDto saveUserRequestDto
                = new SaveUserRequestDto(
                userSignedUpEvent.getUserId(),
                userSignedUpEvent.getName()
        );
        userService.save(saveUserRequestDto);
    }
}