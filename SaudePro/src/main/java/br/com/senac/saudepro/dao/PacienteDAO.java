package br.com.senac.saudepro.dao;

import br.com.senac.saudepro.model.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import persistence.JPAUtil;

/**
 * 
 * @author bruno-teixeira
 */
public class PacienteDAO {
   
    // Cadastrar
    public void cadastrarPaciente(Paciente p){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
            
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    // Buscar Paciente pelo CPF
    public Paciente getPacienteByCPF(String cpf){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            String jpql = "SELECT p FROM Paciente p WHERE p.cpf = :cpf";
            TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class);
            query.setParameter("cpf", cpf);
            
            return query.getSingleResult();
            
            
        } catch (Exception e) {
            System.err.println("Erro : " + e.getMessage());
            return null;
            
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    public Paciente getPacienteByNane(String nome){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            String jpql = "SELECT p FROM Paciente p WHERE p.nome = :nome";
            TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class);
            query.setParameter("nome", nome);
            
            return query.getSingleResult();
            
            
        } catch (Exception e) {
            System.err.println("Erro : " + e.getMessage());
            return null;
            
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    // Buscar Paciente por ID
    public Paciente getPacientePorId(int id){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.find(Paciente.class, id);
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar paciente: " + e.getMessage());
            return null;
            
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
 
    // Atualizar
    public void updatePaciente(Paciente p){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    // Deletar
    public void deletar(String cpf){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            // Buscar pelo CPF primeiro
            String jpql = "SELECT p FROM Paciente p WHERE p.cpf = :cpf";
            TypedQuery<Paciente> query = em.createQuery(jpql, Paciente.class);
            query.setParameter("cpf", cpf);
            
            Paciente p = query.getSingleResult();
            
            if(p != null){
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
            }
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    
    // Deletar por ID
    public void deletarPorId(int id){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            Paciente paciente = em.find(Paciente.class, id);
            
            if(paciente != null){
                em.getTransaction().begin();
                em.remove(paciente);
                em.getTransaction().commit();
            }
            
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    
    // Verifica se CPF já existe no banco
    public boolean isExist(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            String jpql = "SELECT COUNT(p) FROM Paciente p WHERE p.cpf = :cpf";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("cpf", cpf);

            Long count = query.getSingleResult();
            return count > 0;

        } catch (Exception e) {
            return false;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
}
