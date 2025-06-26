package com.example.tasks.Controller;

import com.example.tasks.Dto.Boards.BoardsDto;
import com.example.tasks.Model.Boards;
import com.example.tasks.Service.BoardsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Boards")
public class BoardsController {
    @Autowired
    private BoardsService boardsService;

    @GetMapping("/all")
    public ResponseEntity<Page<Boards>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(boardsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boards> getTaskById(@PathVariable Long id) {
        return boardsService.findById(id)
                .map(ResponseEntity::ok).orElse(
                        ResponseEntity.notFound().build()
                );
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<Boards> postTasks(@Valid @RequestBody BoardsDto boards) {
        Boards savedBoard = boardsService.create(boards);
        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity putTasks(@PathVariable Long id,@Valid @RequestBody BoardsDto boardsDto){
        Boards boards=boardsService.update(id,boardsDto);
        return ResponseEntity.ok(new BoardsDto(boards.getName(), boards.getDescription()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boardsService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
