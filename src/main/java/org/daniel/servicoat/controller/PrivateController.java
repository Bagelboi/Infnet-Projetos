package org.daniel.servicoat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
public class PrivateController {
    @GetMapping("ola")
    public String ola() {
        return "hey!";
    }
}
