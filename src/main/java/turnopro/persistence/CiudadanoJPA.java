package turnopro.persistence;

import jakarta.persistence.EntityManager;
import turnopro.entities.Ciudadano;

import java.util.List;

public class CiudadanoJPA {

    /**
     * Crea un nuevo ciudadano en la base de datos.
     * - Se usa try-with-resources para garantizar que el EntityManager se cierra automáticamente
     * - Se inicia una transacción porque persist() modifica el estado de la base de datos.
     * - Se persiste el objeto Ciudadano.
     * - Se confirma la transacción
     */
    public void crearCiudadano(Ciudadano c) {
        try (EntityManager em = JpaUtil.getEM()) {
            em.getTransaction().begin();
            em.persist(c); // Inserta ciudadano a la base de datos.
            em.getTransaction().commit(); // Confirma los cambios.
        }
    }

    /**
     * Obtiene un ciudadano por su ID.
     * - No requiere transacción porque es una operación de lectura.
     * - em.find() devuelve el objeto si existe, o null si no se encuentra.
     */
    public Ciudadano obtenerCiudadano(Long id) {
        try (EntityManager em = JpaUtil.getEM()) {
            return em.find(Ciudadano.class, id);
        }
    }

    /**
     * Lista todos los ciudadanos registrados.
     * - Realiza una consulta JPQL simple.
     * - Ordena los resultados por nombre de forma ascendente.
     * - No requiere transacción porque es solo lectura.
     */
    public List<Ciudadano> listarCiudadanos() {
        try (EntityManager em = JpaUtil.getEM()) {
            return em.createQuery("SELECT c FROM Ciudadano c ORDER BY c.nombre ASC", Ciudadano.class).getResultList();
        }
    }

    public void eliminarCiudadano(Long idCiudadano) {
        EntityManager em = JpaUtil.getEM();
        try {
            em.getTransaction().begin();
            Ciudadano c = em.find(Ciudadano.class, idCiudadano);
            if (c != null){
                em.remove(c);
            }
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar el ciudadano: " + e.getMessage());
        }

    }

    public void modificarCiudadano(Ciudadano ciudadano) {
        EntityManager em = JpaUtil.getEM();
        try {
            em.getTransaction().begin();
            em.merge(ciudadano);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Error al modificar el ciudadano: " + e.getMessage());
        }
    }
}
