package com.example.tasks.Service;
import com.example.tasks.Dto.TaskGroup.TaskGroupCreateDto;
import com.example.tasks.Dto.TaskGroup.TaskGroupUpdateDto;
import com.example.tasks.Model.Boards;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardsRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TaskGroupService implements ITaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final BoardsRepository boardsRepository;   // precisamos buscar o Board

    public TaskGroupService(TaskGroupRepository taskGroupRepository,
                            BoardsRepository boardsRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.boardsRepository = boardsRepository;
    }


    public TaskGroup create(TaskGroupCreateDto dto) {
        Boards board = boardsRepository.findById(dto.boardId())
                .orElseThrow(() -> new RuntimeException("Board não encontrado: " + dto.boardId()));

        TaskGroup tg = new TaskGroup();
        tg.setNome(dto.name());
        tg.setBoard(board);

        return taskGroupRepository.save(tg);
    }


    public Page<TaskGroup> findAll(Pageable pageable) {
        return taskGroupRepository.findAll(pageable);
    }


    public Optional<TaskGroup> findById(Long id) {
        return taskGroupRepository.findById(id);
    }


    public TaskGroup update(Long id, TaskGroupUpdateDto dto) {
        TaskGroup tg = taskGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado: " + id));

        if (dto.name() != null && !dto.name().isBlank()) {
            tg.setNome(dto.name());
        }

        if (!dto.boardId().equals(tg.getBoard().getId())) {
            Boards novoBoard = boardsRepository.findById(dto.boardId())
                    .orElseThrow(() -> new RuntimeException("Board não encontrado: " + dto.boardId()));
            tg.setBoard(novoBoard);
        }

        return taskGroupRepository.save(tg);
    }


    public void delete(Long id) {
        taskGroupRepository.deleteById(id);
    }
}
