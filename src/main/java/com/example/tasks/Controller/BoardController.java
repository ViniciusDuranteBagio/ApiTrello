package com.example.tasks.Controller;

import com.example.tasks.DTO.BoardDTO;
import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping
	public ResponseEntity<List<BoardDTO>> getAllBoards() {
		List<BoardDTO> boards = boardService.listAll().stream().map(BoardDTO::new) // converte cada Board para BoardDTO
																					// (incluindo id)
				.toList();
		return ResponseEntity.ok(boards);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BoardDTO> getBoardById(@PathVariable Long id) {
		Board board = boardService.findById(id);
		return ResponseEntity.ok(new BoardDTO(board));
	}

	@PostMapping
	public ResponseEntity<BoardDTO> createBoard(@RequestBody @Valid BoardDTO dto) {
		Board board = new Board();
		board.setName(dto.getName());
		board.setDescription(dto.getDescription());

		Board saved = boardService.create(board);
		return ResponseEntity.status(HttpStatus.CREATED).body(new BoardDTO(saved));
	}

	@PutMapping("/{id}")
	public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long id, @RequestBody @Valid BoardDTO dto) {
		Board board = boardService.findById(id);
		board.setName(dto.getName());
		board.setDescription(dto.getDescription());

		Board updated = boardService.update(id, board);
		return ResponseEntity.ok(new BoardDTO(updated));
	}
}
