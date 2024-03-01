package br.edu.ifsp.isaacnwt.dao;

import br.edu.ifsp.isaacnwt.model.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class AlunoDao {
    private EntityManager em;

    public AlunoDao(EntityManager em) {
        this.em = em;
    }

    public void create(Aluno aluno) {
        this.em.persist(aluno);
    }

    public List<Aluno> getAll() {
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }

    public Aluno getByName(String nome) throws NoResultException {
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :nome";
        return em.createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }
}
