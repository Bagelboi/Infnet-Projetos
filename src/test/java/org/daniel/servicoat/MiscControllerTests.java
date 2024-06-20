package org.daniel.servicoat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.daniel.servicoat.model.Departamento;
import org.daniel.servicoat.model.Usuario;
import org.daniel.servicoat.service.DepartamentoService;
import org.daniel.servicoat.service.UsuarioDetailsService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
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
public class MiscControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testPrivate() throws Exception {
        mockMvc.perform(get("/api/private")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    void testPostUsuario() throws Exception {
        Usuario user = new Usuario();
        user.setUsername("Macaco");
        user.setPassword("Albino");
        user.setRole("");
        mockMvc.perform(post("/api/public/user").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writer().writeValueAsString(user)))
                    .andDo(print())
                    .andExpect(status().isCreated());
    }
}
