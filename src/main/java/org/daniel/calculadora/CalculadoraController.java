package org.daniel.calculadora;

import org.springframework.web.bind.annotation.*;

@RestController
public class CalculadoraController {

    @GetMapping("/soma")
    @PostMapping("/soma")
    public double soma(@RequestParam double num1, @RequestParam double num2) {
        return num1 + num2;
    }

    @GetMapping("/sub")
    @PostMapping("/sub")
    public double subtrair(@RequestParam double num1, @RequestParam double num2) {
        return num1 - num2;
    }

    @GetMapping("/mult")
    @PostMapping("/mult")
    public double multiplicar(@RequestParam double num1, @RequestParam double num2) {
        return num1 * num2;
    }

    @GetMapping("/div")
    @PostMapping("/div")
    public String dividir(@RequestParam double num1, @RequestParam double num2) {
        if (num2 == 0) {
            return "Não pode dividir por 0!";
        }
        return Double.toString( num1 / num2 );
    }

    @GetMapping("/exp")
    @PostMapping("/exp")
    public double exponenciar(@RequestParam double base, @RequestParam double exp) {
        return Math.pow(base, exp);
    }
}
