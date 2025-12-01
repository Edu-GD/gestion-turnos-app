package turnopro.persistence;

import jakarta.persistence.EntityManager;
import turnopro.entities.Ciudadano;

public class CiudadanoJPA {
    public void crearCiudadano(Ciudadano c){
        try(EntityManager em = JpaUtil.getEM()) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        }
    }

    public Ciudadano obtenerCiudadano(Long id){
        try (EntityManager em = JpaUtil.getEM()){
        return em.find(Ciudadano.class, id);
        }
    }
}
