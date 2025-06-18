package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroups;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskGroupsService {
    private final TaskGroupsRepository taskGroupsRepository;
    private final BoardRepository boardRepository;

    //Constructor
    public TaskGroupsService(TaskGroupsRepository taskGroupsRepository, BoardRepository boardRepository) {
        this.taskGroupsRepository = taskGroupsRepository;
        this.boardRepository = boardRepository;
    }

    //Gets
    public Optional<TaskGroups> buscarPorId(Long id){
        return taskGroupsRepository.findById(id);
    }
    public List<TaskGroups> buscarPorNome(String nome){
        return taskGroupsRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<TaskGroups>buscarPorBoardId(Long id){
        return taskGroupsRepository.findByBoardId(id);
    }

    public List<TaskGroups>buscarPorBoardNome(String nome){
        return taskGroupsRepository.findByBoardNomeContainingIgnoreCase(nome);
    }

    //Post
    public TaskGroups salvar(TaskGroups taskGroups) {
        Long boardId = taskGroups.getBoard().getId();
        Optional<Board> board = boardRepository.findById(boardId);
        taskGroups.setBoard(board.orElse(null));
        return taskGroupsRepository.save(taskGroups);
    }

    //Deletar
    public void deletar(Long id) {
        taskGroupsRepository.deleteById(id);
    }
}
