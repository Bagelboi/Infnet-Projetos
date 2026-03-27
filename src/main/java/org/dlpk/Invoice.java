package org.dlpk;

public class Invoice {

    public enum Tipo {
        SIMPLES,
        IMPOSTO,
        FANTASMA
    }

    public String clientName;
    public String clientEmail;
    public double amount;
    public Tipo type;

    private class NotaFiscal {
        public NotaFiscal() {}

        private String tipoToString() {
            switch (type) {
                case IMPOSTO -> {return "Com Imposto";}
                case SIMPLES -> {return "Simples";}
                case FANTASMA -> {return "Fantasma";}
                default -> {return "Desconhecido";}
            }
        }

        public String build() {
            StringBuilder builder = new StringBuilder();
            builder.append("---------------------");
            builder.append("\n--- NOTA FISCAL ---");
            builder.append( "\nCliente: " + clientName );
            builder.append("\nValor: " + amount);
            builder.append("\nTipo: " + tipoToString());
            builder.append("\n---------------------");
            return builder.toString();
        }


    }

    public void enviarPorEmail(String email, String conteudo) {
        System.out.println("Enviando email para: " + email);
        System.out.println("Conteúdo:\n" + conteudo);
    }

    public void process() {
        if (clientEmail == null && !clientEmail.contains("@")) {
            System.out.println("Email inválido. Falha no envio.");
        }
        String nota = new NotaFiscal().build();

        System.out.println(nota);

        enviarPorEmail(clientEmail, nota);
    }
}