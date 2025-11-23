package com.example.boardservice.board.event;

public class BoardCreatedEvent {
    private Long userId;

    public BoardCreatedEvent(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}