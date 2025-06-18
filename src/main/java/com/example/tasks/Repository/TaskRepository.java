package com.example.tasks.Repository;
import com.example.tasks.Model.Task.Situacao;
import com.example.tasks.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Override
    Optional<Task> findById(Long id);
    List<Task> findByTituloContainingIgnoreCase(String titulo);
    List<Task>findBySituacao(Situacao situacao);
    List<Task>  findByTaskGroupsNomeContainingIgnoreCase(String nome);
    List<Task>findByTaskGroupsId(Long Id);
}

