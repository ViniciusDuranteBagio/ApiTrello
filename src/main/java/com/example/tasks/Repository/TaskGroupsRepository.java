package com.example.tasks.Repository;

import com.example.tasks.Model.TaskGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskGroupsRepository extends JpaRepository<TaskGroups, Long> {
    @Override
    Optional<TaskGroups> findById(Long id);
    List<TaskGroups> findByNomeContainingIgnoreCase(String nome);
    List<TaskGroups>  findByBoardNomeContainingIgnoreCase(String nome);
    List<TaskGroups>findByBoardId(Long Id);
}
