package com.example.boardservice.board;

import com.example.boardservice.board.client.UserClient;
import com.example.boardservice.board.dto.BoardDto;
import com.example.boardservice.board.dto.BoardResponseDto;
import com.example.boardservice.board.dto.UserDto;
import com.example.boardservice.board.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserClient userClient;

    public BoardService(BoardRepository boardRepository, UserClient userClient) {
        this.boardRepository = boardRepository;
        this.userClient = userClient;
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


    public BoardResponseDto getBoard(Long boardId) {
        // 게시글 불러오기
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 사용자 정보 불러오기
        Optional<UserResponseDto> optionalUserResponseDto = userClient.fetchUser(board.getUserId());

        // userDto 생성
        UserDto userDto = null;
        if (optionalUserResponseDto.isPresent()) {
            UserResponseDto userResponseDto = optionalUserResponseDto.get();
            userDto = new UserDto(
                    userResponseDto.getUserId(),
                    userResponseDto.getName()
            );

        }
            BoardResponseDto boardResponseDto = new BoardResponseDto(
                    board.getBoardId(),
                    board.getTitle(),
                    board.getContent(),
                    userDto
            );

            return boardResponseDto;
    }

    public List<BoardResponseDto> getBoards(){
        List<Board> boards = boardRepository.findAll();

        // userId 목록 추출
        List<Long> userIds = boards.stream()
                .map(Board::getUserId)
                .distinct()
                .toList();

        // user-serive로부터 사용자 정보 불러오기
        List<UserResponseDto> userResponseDtos = userClient.fetchUserByIds(userIds);

        // userId를 key로 하는 Map 생성
        Map<Long, UserDto> userMap = new HashMap<>();
        for(UserResponseDto userResponseDto : userResponseDtos){
            Long userId = userResponseDto.getUserId();
            String name = userResponseDto.getName();
            userMap.put(userId,new UserDto(userId,name));
        }

        // 게시글 정보와 사용자 정보를 조합하여 BoardResponseDto 생성
        return boards.stream()
                .map(board -> new BoardResponseDto(
                        board.getBoardId(),
                        board.getTitle(),
                        board.getContent(),
                        userMap.get(board.getUserId()) // 맵에서 userDto 가져오기
                ))
                .toList();
    }


}