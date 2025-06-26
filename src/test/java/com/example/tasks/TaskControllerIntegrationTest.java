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

/* NÃO SEI O QUE ESTÁ ERRADO, NÃO ESTOU A CONSEGUIR FAZER O TESTE INTEGRADO,
NÃO SEI SE É A CONFIGURAÇÃO DO SPRING OU SE É O TESTE EM SI

TENTEI ARRUAR COM CHAT POREM NÃO FUNCIONOU, NÃO SEI O QUE ESTÁ ERRADO DE VERDADE

FOI VERIFICADO AS CLASSES POREM ESTA TUDO OK E FICA A DAR ERRO
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

