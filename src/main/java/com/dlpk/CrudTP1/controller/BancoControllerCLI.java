package com.dlpk.CrudTP1.controller;

import com.dlpk.CrudTP1.service.BancoService;
import com.dlpk.CrudTP1.service.ContaService;

import jakarta.annotation.PostConstruct;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BancoControllerCLI {
    @Autowired
    BancoService bancoService;
    @Autowired
    ContaService contaService;

    private enum MenuOption {
        EXIT,
        DEFAULT,
        MOSTRAR_CONTAS,
        PESQUISAR_CONTA_POR_NOME,
        APAGAR_CONTA,
        CRIAR_CONTA,
        TRANSFERENCIA;

        static MenuOption fromInt(int value) {
            return switch (value) {
                case 0 -> EXIT;
                case 1 -> MOSTRAR_CONTAS;
                case 2 -> PESQUISAR_CONTA_POR_NOME;
                case 3 -> APAGAR_CONTA;
                case 4 -> CRIAR_CONTA;
                case 5 -> TRANSFERENCIA;
                default -> DEFAULT;
            };
        }

    }

    private Scanner scanner = new Scanner(System.in);

    private String randomNome() {
        String[] nomes = {"Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda", "Gabriel", "Helena", "Igor", "Juliana"};
        String[] sobrenomes = {"Silva", "Souza", "Costa", "Oliveira", "Pereira", "Rodrigues", "Almeida", "Nascimento", "Lima", "Gomes"};

        String nome = nomes[(int)(Math.random() * nomes.length)];
        String sobrenome = sobrenomes[(int)(Math.random() * sobrenomes.length)];

        return nome + " " + sobrenome;
    }

    private void initTeste() {

        for (int i = 0; i < 5; i++) {
            Long id = contaService.criarConta(randomNome());
            contaService.adicionarSaldo(id, 100.0 * i);
        }
    }


    private void exibirContas() {
        System.out.println("Contas:");
        contaService.getAllContas().forEach(conta -> 
            System.out.println(conta)
        );
    }

    private void pesquisarContaPorNome() {
        System.out.println("Insira o nome para pesquisar:");
        String nome = scanner.nextLine();
        System.out.println("Conta com nome: " + nome + ":");
        contaService.getContasPorNome(nome).forEach(conta ->
            System.out.println(conta)
        );
    }

    private void deletarContaPorId() {
        System.out.println("Insira o ID para apagar:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        contaService.apagarConta(id);
        System.out.println("Conta com ID " + id + " apagada.");
    }

    private void criarConta() {
        System.out.println("Insira o nome da conta:");
        String nome = scanner.nextLine();
        Long id = contaService.criarConta(nome);
        System.out.println("Insira o saldo inicial:");
        Double saldoInicial = scanner.nextDouble();
        scanner.nextLine();
        contaService.atualizarSaldo(id, saldoInicial);
        System.out.println("Conta criada: " + nome + " com saldo inicial de " + saldoInicial);
    }

    private void transferirDeContaParaConta() {

        System.out.println("Insira o ID de origem:");
        Long idOrigem = scanner.nextLong();
        System.out.println("Insira o ID de destino:");
        Long idDestino = scanner.nextLong();
        System.out.println("Insira a quantia a transferir:");
        double quantidade = scanner.nextDouble();
        scanner.nextLine();
        if (bancoService.transferir(idOrigem, idDestino, quantidade))
            System.out.println("Transferência de " + quantidade + " de conta " + idOrigem + " para conta " + idDestino + " realizada.");
        else
            System.out.println("A transferencia não foi sucedida...");
    }

    @PostConstruct
    public void init() {
        initTeste();
        MenuOption state = MenuOption.DEFAULT;
        while (state != MenuOption.EXIT) {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Mostrar todas as contas");
                System.out.println("2. Pesquisar conta por nome");
                System.out.println("3. Apagar conta");
                System.out.println("4. Criar nova conta");
                System.out.println("5. Transferir entre contas");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                state = MenuOption.fromInt( scanner.nextInt() );
                scanner.nextLine();

                switch (state) {
                    case MOSTRAR_CONTAS:
                        exibirContas();
                        break;
                    case PESQUISAR_CONTA_POR_NOME:
                        pesquisarContaPorNome();
                        break;
                    case APAGAR_CONTA:
                        deletarContaPorId();
                        break;
                    case CRIAR_CONTA:
                        criarConta();
                        break;
                    case TRANSFERENCIA:
                        transferirDeContaParaConta();
                        break;
                    case EXIT:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            }
            catch (RuntimeException e) {
                System.out.println("Um erro ocorreu durante a operação!: " + e.getMessage());

            }
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }
}
