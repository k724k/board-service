package com.example.boardservice.board;

import com.example.boardservice.board.dto.BoardDto;
import com.example.boardservice.board.dto.BoardResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards") // 외부 api
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
        BoardResponseDto boardResponseDto = boardService.getBoard2(boardId);
        return ResponseEntity.ok(boardResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> boardResponseDtos = boardService.getBoards2();
        return ResponseEntity.ok(boardResponseDtos);
    }

}