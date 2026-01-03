package com.dlpk.CrudTP1.model.DTO;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public record TransferenciaDTO(
        Long id_src,
        @JsonSerialize(using = ToStringSerializer.class)
        Double valor,
        Long id_dest
) {
    public String toJsonString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
