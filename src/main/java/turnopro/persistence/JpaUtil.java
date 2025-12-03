package turnopro.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    /**
     * EntityManagerFactory es un objeto pesado, costoso de crear.
     * Por eso se inicializa una única vez como singleton.
     */

     /**
      * "turnosPU" debe coincidir con el nombre del persistence-unit
      * definido en tu archivo persistence.xml.
      */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("turnosPU");

    /**
     * Devuelve un nuevo EntityManager.
     * Cada EntityManager representa una conexión lógica con la base de datos,
     * por lo que debe ser cerrado tras ser utilizado.
     */
    public static EntityManager getEM() {
        return emf.createEntityManager();
    }

    /**
     * Cierra el EntityManager cuando la aplicación se detiene.
     *
     * Debe ejecutarse solo una vez idealmente al finalizar la aplicación.
     */
    public static void closeEM() {
        if (emf != null && emf.isOpen()) emf.close();
    }
}
