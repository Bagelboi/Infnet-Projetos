package org.daniel.servicoat.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private String id;
    private String username;
    private String password;
    private String role;
}