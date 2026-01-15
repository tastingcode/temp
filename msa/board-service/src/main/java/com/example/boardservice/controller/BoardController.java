package com.example.boardservice.controller;

import com.example.boardservice.dto.BoardResponseDto;
import com.example.boardservice.dto.CreateBoardRequestDto;
import com.example.boardservice.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

	private final BoardService boardService;

	public BoardController(BoardService boardService){
		this.boardService = boardService;
	}

	@PostMapping
	public ResponseEntity<Void> create(
			@RequestBody CreateBoardRequestDto createBoardRequestDto,
			@RequestHeader("X-User-Id") Long userId
	) {
		boardService.create(createBoardRequestDto, userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId){
//		BoardResponseDto boardResponseDto = boardService.getBoard(boardId);
		BoardResponseDto boardResponseDto = boardService.getBoardV2(boardId);
		return ResponseEntity.ok(boardResponseDto);
	}

	@GetMapping()
	public ResponseEntity<List<BoardResponseDto>> getBoards(){
//		List<BoardResponseDto> boardResponseDtos = boardService.getBoards();
		List<BoardResponseDto> boardResponseDtos = boardService.getBoardsV2();
		return ResponseEntity.ok(boardResponseDtos);
	}
}