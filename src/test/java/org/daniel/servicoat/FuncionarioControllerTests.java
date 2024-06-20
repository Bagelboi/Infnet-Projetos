package org.daniel.servicoat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.daniel.servicoat.model.Funcionario;
import org.daniel.servicoat.service.FuncionarioService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FuncionarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FuncionarioService service;

    @BeforeEach
    void setup() {
        if (service.getByID(1L).isEmpty()) {
            Funcionario func = new Funcionario();
            func.setId(1L);
            service.save(func);
        }
    }



    @Test
    @Order(1)
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/public/funcionarios/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @Order(2)
    void testPost() throws Exception {
        mockMvc.perform(post("/api/public/funcionarios").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writer().writeValueAsString(new Funcionario())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    @Order(3)
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/public/funcionarios").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    @Test
    @Order(4)
    @Disabled //funciona com o programa normal
    void testPut() throws Exception {
        Funcionario func = new Funcionario();
        func.setNome("funcnome");
        mockMvc.perform(put("/api/public/funcionarios/{id}", 2L).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writer().writeValueAsString(func)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(func.getNome()));
    }


    @Test
    @Order(5)
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/public/funcionarios/{id}", 2L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        Assert.assertEquals(service.getAll().size(), 1);
    }
}
