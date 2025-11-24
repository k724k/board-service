package com.example.boardservice.board.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.ObjectMapper;

public class UserSignedUpEvent {
    private static final Logger log = LoggerFactory.getLogger(UserSignedUpEvent.class);
    private Long userId;
    private String name;

    // 역직렬화(String 형태의 카프카 메시지 -> Java 객체)시 빈생성자 필요함
    public UserSignedUpEvent() {
    }

    // Json 값을 UserSignedUpEvent로 역직렬화하는 메서드
    public static UserSignedUpEvent fromJson(String json) {
        try {
            log.info("fromJson + " + json);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, UserSignedUpEvent.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패");
        }
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
