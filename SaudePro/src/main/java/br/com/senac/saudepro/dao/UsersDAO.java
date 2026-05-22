package br.com.senac.saudepro.dao;

import br.com.senac.saudepro.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import persistence.JPAUtil;

/**
 * Classe para validaçao dos dados ao DB
 * @author bruno-teixeira
 */
public class UsersDAO {
    
    /**
     * Valida as credenciais do usuário no banco de dados.
     * @param login O login informado pelo usuário.
     * @param senha A senha informada pelo usuário.
     * @return Objeto Usuario se as credenciais forem válidas, ou null caso contrário.
     */
    public Users validarLogin(String login, String senha){
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            // tratando a query para nao have SQL Injection
            String jsql = "SELECT u FROM Users u WHERE u.login = :login AND u.senha = :senha";
            
            TypedQuery<Users> query = em.createQuery(jsql, Users.class);
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            
            return query.getSingleResult(); // Retorna o usuário se encontrar
                    
                    
        } catch (Exception e) {
            return null;
        }finally {
            
            JPAUtil.closeEntityManager();
        }
    } 
    
}
