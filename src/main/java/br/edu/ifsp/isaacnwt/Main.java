package br.edu.ifsp.isaacnwt;

import br.edu.ifsp.isaacnwt.dao.AlunoDao;
import br.edu.ifsp.isaacnwt.model.Aluno;
import br.edu.ifsp.isaacnwt.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;
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
                case 1 -> registerNew();
                case 2 -> System.out.println("** Excluir aluno **");
                case 3 -> System.out.println("** Alterar aluno **");
                case 4 -> findByName();
                case 5 -> showAll();
                case 6 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    public static void registerNew() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("** CADASTRO DE ALUNO **");
        System.out.println("-----------------------");

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o RA: ");
        String ra = scanner.nextLine();

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        System.out.print("Digite a nota 1: ");
        BigDecimal nota1 = scanner.nextBigDecimal();

        System.out.print("Digite a nota 2: ");
        BigDecimal nota2 = scanner.nextBigDecimal();

        System.out.print("Digite a nota 3: ");
        BigDecimal nota3 = scanner.nextBigDecimal();

        Aluno aluno = new Aluno(nome, ra, email, nota1, nota2, nota3);

        EntityManager em = JPAUtil.getEntityManager();

        AlunoDao alunoDao = new AlunoDao(em);

        em.getTransaction().begin();

        alunoDao.create(aluno);

        em.getTransaction().commit();
        em.close();

        System.out.println("\nAluno cadastrado com sucesso!");
    }

    public static void findByName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("** BUSCAR ALUNO POR NOME **");
        System.out.println("-----------------------");

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        EntityManager em = JPAUtil.getEntityManager();

        AlunoDao alunoDao = new AlunoDao(em);

        try {
            Aluno aluno = alunoDao.getByName(nome);
            em.close();

            System.out.println("\nAluno encontrado: \n" + aluno);
        } catch (NoResultException e) {
            System.out.println("\nAluno não encontrado!");
        }
    }



    public static void showAll() {
        System.out.println("** LISTAGEM DE ALUNOS **");
        System.out.println("-----------------------");

        EntityManager em = JPAUtil.getEntityManager();

        AlunoDao alunoDao = new AlunoDao(em);

        em.getTransaction().begin();

        List<Aluno> alunos = alunoDao.getAll();

        em.getTransaction().commit();
        em.close();

        if (alunos.isEmpty()) {
            System.out.println("\nNenhum aluno registrado!");
            return;
        }

        for (Aluno aluno : alunos)
            System.out.println("\n" + aluno);
    }
}