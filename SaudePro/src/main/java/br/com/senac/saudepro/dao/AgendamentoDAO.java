package br.com.senac.saudepro.dao;

import br.com.senac.saudepro.model.Agendamento;
import br.com.senac.saudepro.model.Medico;
import br.com.senac.saudepro.model.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import persistence.JPAUtil;

public class AgendamentoDAO {
    
    // Salvar agendamento
    public void salvar(Agendamento agendamento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(agendamento);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    // Buscar agendamento por ID
    public Agendamento buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Agendamento.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    // Buscar todos agendamentos
    public List<Agendamento> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Agendamento> lista = new ArrayList<>();
        try {
            String jpql = "SELECT a FROM Agendamento a ORDER BY a.dataAgendamento DESC, a.horario ASC";
            TypedQuery<Agendamento> query = em.createQuery(jpql, Agendamento.class);
            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar agendamentos: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager();
        }
        return lista;
    }
    
    // Buscar agendamentos por médico
    public List<Agendamento> buscarPorMedico(Medico medico) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Agendamento> lista = new ArrayList<>();
        try {
            String jpql = "SELECT a FROM Agendamento a WHERE a.medico = :medico ORDER BY a.dataAgendamento DESC, a.horario ASC";
            TypedQuery<Agendamento> query = em.createQuery(jpql, Agendamento.class);
            query.setParameter("medico", medico);
            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar agendamentos por médico: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager();
        }
        return lista;
    }
    
    // Buscar agendamentos por paciente
    public List<Agendamento> buscarPorPaciente(Paciente paciente) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Agendamento> lista = new ArrayList<>();
        try {
            String jpql = "SELECT a FROM Agendamento a WHERE a.paciente = :paciente ORDER BY a.dataAgendamento DESC, a.horario ASC";
            TypedQuery<Agendamento> query = em.createQuery(jpql, Agendamento.class);
            query.setParameter("paciente", paciente);
            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar agendamentos por paciente: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager();
        }
        return lista;
    }
    
    // Buscar agendamentos por data
    public List<Agendamento> buscarPorData(LocalDate data) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Agendamento> lista = new ArrayList<>();
        try {
            String jpql = "SELECT a FROM Agendamento a WHERE a.dataAgendamento = :data ORDER BY a.horario ASC";
            TypedQuery<Agendamento> query = em.createQuery(jpql, Agendamento.class);
            query.setParameter("data", data);
            lista = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar agendamentos por data: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager();
        }
        return lista;
    }
    
    // Buscar horários ocupados de um médico em uma data específica
    public List<String> buscarHorariosOcupados(Medico medico, LocalDate data) {
        EntityManager em = JPAUtil.getEntityManager();
        List<String> horarios = new ArrayList<>();
        try {
            String jpql = "SELECT a.horario FROM Agendamento a WHERE a.medico = :medico AND a.dataAgendamento = :data";
            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setParameter("medico", medico);
            query.setParameter("data", data);
            horarios = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar horários ocupados: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager();
        }
        return horarios;
    }
    
    // Atualizar agendamento
    public void atualizar(Agendamento agendamento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(agendamento);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    // Deletar agendamento
    public void deletar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Agendamento a = em.find(Agendamento.class, id);
            if (a != null) {
                em.getTransaction().begin();
                em.remove(a);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    // Atualizar status do agendamento
    public void atualizarStatus(int id, String status) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Agendamento a = em.find(Agendamento.class, id);
            if (a != null) {
                a.setStatus(status);
                em.merge(a);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
}