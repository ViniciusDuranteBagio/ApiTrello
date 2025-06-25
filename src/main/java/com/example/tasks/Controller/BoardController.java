package com.example.tasks.Controller;

import com.example.tasks.Controller.BoardResponseDTO;
import com.example.tasks.Model.Board;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @PostMapping
    public BoardResponseDTO createBoard(@Valid @RequestBody Board board) {
        // Cria manualmente o objeto de resposta
        BoardResponseDTO response = new BoardResponseDTO();
        response.setName(board.getName());
        response.setDescription(board.getDescription());

        return response;
    }
}
