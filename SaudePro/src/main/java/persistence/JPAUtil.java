package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Classe para ligaçao ao DB
 * @author bruno-teixeira
 */
public class JPAUtil {
    
    private static Properties props = new Properties();
    
    private static String DRIVER;
    private static String URL;
    private static String DIALECT;
    private static String USER;
    private static String PASS;
    
    /**
    * Bloco estático para carregar o arquivo ASSIM QUE A CLASSE FOR USADA.
    */
    static {
        try(InputStream input = JPAUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if(input == null){
                throw new RuntimeException("Arquivo db.properties não encontrado!");
            }
            
            props.load(input); // carregar os dados do arquivo db.properties
            
            DRIVER = props.getProperty("db.driver");
            URL = props.getProperty("db.url");
            DIALECT = props.getProperty("db.dialect");
            USER = props.getProperty("db.user");
            PASS = props.getProperty("db.password");
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar configurações: " + e.getMessage());
        }
    }
    
    private static final String PERSISTENCE_UNIT = "saudepro-PU";
    
    private static EntityManager em;
    private static EntityManagerFactory factory;
    
    /**
    * Criando a entidade se estiver nula e a retorna.
    */
    public static EntityManager getEntityManager(){
        if(factory == null || !factory.isOpen()){
            
            //Criando o mapa de propriedades.
            Map<String, String> config = new HashMap<>();
            
            //lendo os db.properties!
            config.put("jakarta.persistence.jdbc.user", USER);
            config.put("jakarta.persistence.jdbc.password", PASS);
            config.put("jakarta.persistence.jdbc.driver", DRIVER);
            config.put("jakarta.persistence.jdbc.url", URL);
            config.put("hibernate.dialect", DIALECT);
            
            //Passando o mapa para a factory.
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, config);
  
        }
        
        if(em == null || !em.isOpen()){
            em = factory.createEntityManager();
        }
        
        return em;
    }
    
    
    /**
    * Fecha o EntityManager e o factory.
    */
    public void closeEntityManager(){
        if(em != null && !em.isOpen()){
            em.close();
        }
        
        if(factory != null && factory.isOpen()){
           factory.close();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== TESTE DE CONEXÃO COM O BANCO ===");

        EntityManager em = null;

        try {
            em = getEntityManager();

            if (em != null && em.isOpen()) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
                System.out.println("   URL: " + URL);
                System.out.println("   Usuário: " + USER);
                System.out.println("   Driver: " + DRIVER);

                Object result = em.createNativeQuery("SELECT 'Conexão OK!' AS mensagem").getSingleResult();
                System.out.println("📝 Resultado do teste: " + result);

            } else {
                System.out.println("❌ Falha ao conectar!");
            }

        } catch (Exception e) {
            System.err.println("❌ ERRO: " + e.getMessage());
            e.printStackTrace();

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
                System.out.println("🔌 Conexão fechada.");
            }
        }
    }
    
}
