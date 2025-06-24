// TaskGroupServiceTest.java
package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskGroupServiceTest {

    private TaskGroupService taskGroupService;
    private TaskGroupRepository taskGroupRepository;
    private BoardRepository boardRepository;

    @BeforeEach
    public void setUp() {
        taskGroupRepository = mock(TaskGroupRepository.class);
        boardRepository = mock(BoardRepository.class);
        taskGroupService = new TaskGroupService(taskGroupRepository, boardRepository);
    }

    @Test
    public void testCreateTaskGroup_Success() {
        Board board = new Board();
        board.setBoardId(1L);

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setTaskGroupName("Grupo válido");
        taskGroup.setBoard(board);

        when(boardRepository.existsById(1L)).thenReturn(true);
        when(taskGroupRepository.save(taskGroup)).thenReturn(taskGroup);

        TaskGroup result = taskGroupService.createTaskGroup(taskGroup);

        assertNotNull(result);
        verify(taskGroupRepository).save(taskGroup);
    }

    @Test
    public void testCreateTaskGroup_NameTooShort() {
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setTaskGroupName("A");
        taskGroup.setBoard(new Board());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskGroupService.createTaskGroup(taskGroup);
        });

        assertEquals("O nome do task group não pode ser vazio ou ter menos que 3 caracteres.", exception.getMessage());
    }

    @Test
    public void testCreateTaskGroup_BoardNull() {
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setTaskGroupName("Grupo Válido");
        taskGroup.setBoard(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskGroupService.createTaskGroup(taskGroup);
        });

        assertEquals("O task group não pode ter um board vazio.", exception.getMessage());
    }

    @Test
    public void testCreateTaskGroup_BoardInexistente() {
        Board board = new Board();
        board.setBoardId(999L);

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setTaskGroupName("Grupo Válido");
        taskGroup.setBoard(board);

        when(boardRepository.existsById(999L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskGroupService.createTaskGroup(taskGroup);
        });

        assertEquals("O Board precisa ser válido.", exception.getMessage());
    }
}