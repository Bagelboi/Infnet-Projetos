package com.dlpk.CrudTP1.utils;

import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.stream.Collectors;

public class FormattingUtil {
    public static String formatNome(String nome) throws RuntimeException {
        if (nome.length() > 1 && nome.matches("[A-Za-zÀ-ÿ ]+")) {
            return Arrays.stream(nome.trim().toLowerCase().split("\\s+"))
                    //separa nome em sobrenomes
                    .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                    //capitaliza o inicio de cada sobrenome
                    .collect(Collectors.joining(" "));
        }
        throw new RuntimeException("Nome com formatação invalida: " + nome);
    }

}
