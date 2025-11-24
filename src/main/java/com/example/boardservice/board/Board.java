package com.example.boardservice.board;

import com.example.boardservice.board.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false) // 조회용
    private User user;

    @Column(name = "user_id")
    private Long userId; // FK 설정 안하고 그냥 컬럼으로 선언

    public Board() {
    }

    public Board(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }
}
