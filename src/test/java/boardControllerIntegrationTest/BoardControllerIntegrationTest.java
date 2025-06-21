package boardControllerIntegrationTest;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextoDeveCarregar() {
        System.out.println("Aplicação carregou com sucesso!");
    }

    @Test
    public void criar_deletar() {
        // Criar TaskGroup primeiro (pré-requisito)
        ResponseEntity<String> groupResponse = restTemplate.postForEntity(
                "/task-groups?boardId=1",
                new TaskGroup(1, "Grupo Teste", null, null),
                String.class
        );
        assertEquals(HttpStatus.OK, groupResponse.getStatusCode());

        // Criar Task
        Task novaTask = new Task();
        novaTask.setTaskDescription("Fazer teste");
        novaTask.setTaskStatus(String.valueOf(TaskStatus.TODO));

        ResponseEntity<Task> taskResponse = restTemplate.postForEntity("/tasks?taskGroupId=1", novaTask, Task.class);
        assertEquals(HttpStatus.OK, taskResponse.getStatusCode());
        assertNotNull(taskResponse.getBody());

        Long taskId = taskResponse.getBody().getTaskId();

        // Deletar Task
        restTemplate.delete("/tasks/" + taskId);
        System.out.println("Task criada e deletada com sucesso. ID: " + taskId);
    }


}
