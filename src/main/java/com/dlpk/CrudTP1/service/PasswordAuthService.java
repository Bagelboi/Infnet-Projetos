package com.dlpk.CrudTP1.service;

import com.dlpk.CrudTP1.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PasswordAuthService {
    @Autowired
    AppConfig appconfig;

    public boolean senhaCorreta(String senha_dada) {
        return appconfig.getSenha().equals(senha_dada.trim());
    }
}
