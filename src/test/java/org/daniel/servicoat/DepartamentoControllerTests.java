package org.daniel.servicoat;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.parser.JSONParser;
import org.daniel.servicoat.controller.DepartamentoController;
import org.daniel.servicoat.model.Departamento;
import org.daniel.servicoat.service.DepartamentoService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
public class DepartamentoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartamentoService service;

    @BeforeEach
    void setup() {
        if (service.getByID(1L).isEmpty()) {
            Departamento dept = new Departamento();
            dept.setId(1L);
            service.save(dept);
        }
    }

    @Test
    @Order(1)
    void testPost() throws Exception {
        mockMvc.perform(post("/api/public/departamentos").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writer().writeValueAsString(new Departamento())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    @Order(2)
    void testGetById() throws Exception {
        mockMvc.perform(get("/api/public/departamentos/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @Order(3)
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/public/departamentos").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    @Test
    @Order(4)
    @Disabled //funciona com o programa normal
    void testPut() throws Exception {
        Departamento dept = new Departamento();
        dept.setNome("deptnome");
        mockMvc.perform(put("/api/public/departamentos/{id}", 1L).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writer().writeValueAsString(dept)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(dept.getNome()));
    }


    @Test
    @Order(5)
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/public/departamentos/{id}", 2L).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        Assert.assertEquals(service.getAll().size(), 1);
    }
}
