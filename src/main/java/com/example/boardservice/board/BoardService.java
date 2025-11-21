package com.example.boardservice.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void create(BoardDto createBoardRequestDto) {
        Board board = new Board(
                createBoardRequestDto.getTitle(),
                createBoardRequestDto.getContent(),
                createBoardRequestDto.getUserId()
        );

        this.boardRepository.save(board);
    }
}