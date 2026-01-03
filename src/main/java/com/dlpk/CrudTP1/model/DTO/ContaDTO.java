package com.dlpk.CrudTP1.model.DTO;

import com.dlpk.CrudTP1.model.Conta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public record ContaDTO(
        Long id,
        String nome,
        @JsonSerialize(using = ToStringSerializer.class)
        Double saldo
) {
    public static ContaDTO fromConta(Conta conta) {
        return new ContaDTO(conta.getId(), conta.getNome(), conta.getSaldo());
    }

    public String toJsonString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
