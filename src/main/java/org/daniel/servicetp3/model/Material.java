package org.daniel.servicetp3.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("material")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    @Id
    String id;
    String titulo;
    String curso;
    String resumo;
}
