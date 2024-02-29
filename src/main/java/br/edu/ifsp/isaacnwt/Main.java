package br.edu.ifsp.isaacnwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n** CADASTRO DE ALUNOS **");
            System.out.println("-----------------------");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos por status");
            System.out.println("6 - Sair");
            System.out.println("-----------------------");
            System.out.print("Digite a opção desejada: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> System.out.println("** Cadastrar aluno **");
                case 2 -> System.out.println("** Excluir aluno **");
                case 3 -> System.out.println("** Alterar aluno **");
                case 4 -> System.out.println("** Buscar aluno **");
                case 5 -> System.out.println("** Listar alunos **");
                case 6 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}