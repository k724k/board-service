package com.example.boardservice.board;

import com.example.boardservice.board.dto.BoardDto;
import com.example.boardservice.board.dto.BoardResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody BoardDto createBoardRequestDto
    ) {
        boardService.create(createBoardRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
        BoardResponseDto boardResponseDto = boardService.getBoard(boardId);
        return ResponseEntity.ok(boardResponseDto);
    }
}