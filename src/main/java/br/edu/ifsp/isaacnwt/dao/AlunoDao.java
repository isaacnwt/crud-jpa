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

    public Aluno getById(Long id) {
        return em.find(Aluno.class, id);
    }

    public List<Aluno> getAll() {
        String jpql = "SELECT a FROM aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }

    public List<Aluno> getByName(String nome) {
        String jpql = "SELECT a FROM aluno a WHERE a.nome = ?nome";
        return em.createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getResultList();
    }
}
