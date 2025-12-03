package turnopro.persistence;

import jakarta.persistence.EntityManager;
import turnopro.entities.Ciudadano;

import java.util.List;

public class CiudadanoJPA {
    public void crearCiudadano(Ciudadano c) {
        try (EntityManager em = JpaUtil.getEM()) {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }
    }

    public Ciudadano obtenerCiudadano(Long id) {
        try (EntityManager em = JpaUtil.getEM()) {
            return em.find(Ciudadano.class, id);
        }
    }

    public List<Ciudadano> listarCiudadanos() {
        try (EntityManager em = JpaUtil.getEM()) {
            return em.createQuery("SELECT c FROM Ciudadano c ORDER BY c.nombre ASC", Ciudadano.class).getResultList();
        }
    }
}
