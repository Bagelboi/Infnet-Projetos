package com.dlpk.CrudTP1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuzzRoute {

    @GetMapping("/admin")
    public String adminRoute() {
        return "Rota de administrador acessada!";
    }

}
