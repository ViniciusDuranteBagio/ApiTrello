package com.example.tasks;

import com.example.tasks.Model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

/*
NÃO CONSEGUI FAZER ISSO FUNCIONAR, USEI CHAT GPT, REALIZEI MODIFICAÇÕES EM OUTRAS CLASSES E AQUI... 
PORÉM NÃO CONSEGUI RESOLVER O PROBLEMA
*/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllTasks() {
        ResponseEntity<List<Task>> response = restTemplate.exchange(
                "/api/task",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Task>>() {
                }
        );

        List<Task> tasks = response.getBody();
    }
}

