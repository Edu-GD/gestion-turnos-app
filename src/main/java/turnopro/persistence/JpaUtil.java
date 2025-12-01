package turnopro.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("turnosPU");

    public static EntityManager getEM(){
        return emf.createEntityManager();
    }

    public static void closeEM(){
        if (emf != null && emf.isOpen()) emf.close();
    }
}
