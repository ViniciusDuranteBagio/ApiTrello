// src/test/java/com/example/tasks/Controller/BoardControllerTest.java
package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Carrega a aplicação Spring completa
@AutoConfigureMockMvc // Configura o MockMvc para simular requisições HTTP
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc; // Objeto para fazer as requisições

    @Autowired
    private ObjectMapper objectMapper; // Objeto para converter Java <-> JSON

    @Test
    @DisplayName("Deve criar um Board com sucesso via POST")
    void deveCriarUmBoardComSucesso() throws Exception {
        // Arrange: Preparamos o objeto que será enviado no corpo da requisição.
        Board novoBoard = new Board();
        novoBoard.setName("Board de Integração");
        novoBoard.setDescription("Teste de integração do controller");

        // Act & Assert: Executamos a requisição e verificamos o resultado em uma única cadeia.
        mockMvc.perform(post("/api/boards") // Faz uma requisição POST para a URL
                        .contentType(MediaType.APPLICATION_JSON) // Define o tipo de conteúdo como JSON
                        .content(objectMapper.writeValueAsString(novoBoard))) // Converte o objeto para uma String JSON e o define como corpo
                .andExpect(status().isCreated()) // Esperamos que o status da resposta seja 201 CREATED
                .andExpect(jsonPath("$.id").exists()) // Esperamos que a resposta JSON tenha um campo "id"
                .andExpect(jsonPath("$.name").value("Board de Integração")); // Esperamos que o campo "name" tenha o valor que enviamos.
    }
}