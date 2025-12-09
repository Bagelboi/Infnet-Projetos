package org.dlpk.model.valueobject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Destinario {
    private final String primeiroNome;
    private final String segundoNome;

    public Destinario(String nome) {
        nome = nome.trim();
        if (nome.isEmpty())
            throw new IllegalArgumentException("Nome vazio");
        Matcher matcher = Pattern.compile("[a-zA-Z]+").matcher(nome);

        String primeiroNomeTemp = "";
        List<String> segundoNomes = new ArrayList<>();

        while (matcher.find()) {
            if (matcher.group().isEmpty())
                throw new IllegalArgumentException("Nome ou sobrenome vazio");
            if (primeiroNomeTemp.isEmpty())
                primeiroNomeTemp = matcher.group();
            else {
                segundoNomes.add(matcher.group());
            }
        }

        this.primeiroNome = primeiroNomeTemp;
        this.segundoNome = String.join(" ", segundoNomes);
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getSegundoNome() {
        return segundoNome;
    }
}
