package br.com.senac.saudepro.dao;

import br.com.senac.saudepro.model.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import persistence.JPAUtil;

/**
 * Classe MedicoDAO para DB
 * @author bruno-teixeira
 */
public class MedicoDAO {
    
    // Cadastrando medicos
    public void cadastrarMedico(Medico medico){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            em.getTransaction().begin();
            em.persist(medico);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    
    // Buscar todos os médicos
    public List<Medico> getAllMedicos(){
        EntityManager em = JPAUtil.getEntityManager();
        List<Medico> listMedicos = new ArrayList<>();
        
        try {
            
            String jpql = "SELECT m FROM Medico m ORDER BY m.nome ASC";
            TypedQuery<Medico> query = em.createQuery(jpql, Medico.class);
            
            listMedicos = query.getResultList();
            
            
            
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar médicos: " + e.getMessage());
        } finally {
            JPAUtil.closeEntityManager();
        }
        
        return listMedicos;
    }
    
    // Buscar Medico por ID
    public Medico getMedicoId(int id){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            return em.find(Medico.class, id); 
        } catch (Exception e) {
            System.err.println("Erro ao buscar Medico por Id " + e.getMessage());
            return  null;
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    // Deletar médico
    public void deletarMedico(int id){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            Medico m = em.find(Medico.class, id);
            if(m != null){
                em.getTransaction().begin();
                em.remove(id);
                em.getTransaction().commit();
            }
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
}
