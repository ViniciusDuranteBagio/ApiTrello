package com.example.tasks;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Service.TaskGroupService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskGroupServiceTest {
    private TaskGroupRepository taskGroupRepository;
    private BoardRepository boardRepository;
    private TaskGroupService taskGroupService;

    @BeforeEach
    public void setUp() {
        taskGroupRepository = mock(TaskGroupRepository.class);
        boardRepository = mock(BoardRepository.class);
        taskGroupService = new TaskGroupService(boardRepository, taskGroupRepository);
    }

    @Test
    public void testCreateTaskGroupValidBoard() {
        Board board = new Board();
        board.setId(1L);

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName("Grupo Teste");
        taskGroup.setBoard(board);

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        when(taskGroupRepository.save(taskGroup)).thenReturn(taskGroup);

        TaskGroup result = taskGroupService.create(taskGroup);

        assertNotNull(result);
        assertEquals("Grupo Teste", result.getName());
        verify(taskGroupRepository).save(taskGroup);
    }

    @Test
    public void testCreateTaskGroupInvalidBoard() {
        Board board = new Board();
        board.setId(99L);

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setBoard(board);

        when(boardRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            taskGroupService.create(taskGroup);
        });
    }

    @Test
    public void testFindById() {
        TaskGroup group = new TaskGroup();
        group.setId(1L);
        group.setName("Grupo A");

        when(taskGroupRepository.findById(1L)).thenReturn(Optional.of(group));

        TaskGroup result = taskGroupService.findById(1L);

        assertNotNull(result);
        assertEquals("Grupo A", result.getName());
    }

    @Test
    public void testFindByIdNotFound() {
        when(taskGroupRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taskGroupService.findById(1L));
    }

    @Test
    public void testUpdate() {
        TaskGroup existing = new TaskGroup();
        existing.setId(1L);
        existing.setName("Antigo");

        TaskGroup updated = new TaskGroup();
        updated.setName("Novo");

        when(taskGroupRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(taskGroupRepository.save(existing)).thenReturn(existing);

        TaskGroup result = taskGroupService.update(1L, updated);

        assertEquals("Novo", result.getName());
        verify(taskGroupRepository).save(existing);
    }

    @Test
    public void testDelete() {
        TaskGroup group = new TaskGroup();
        group.setId(1L);

        when(taskGroupRepository.findById(1L)).thenReturn(Optional.of(group));

        taskGroupService.delete(1L);

        verify(taskGroupRepository).delete(group);
    }
}