package turnopro.persistence;

import jakarta.persistence.EntityManager;
import turnopro.entities.EstadoTurno;
import turnopro.entities.Turno;

import java.util.List;

public class TurnoJPA {
    public void guardarTurno(Turno t){
        EntityManager em = JpaUtil.getEM();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    public List<Turno> listarTurnos(){
        EntityManager em = JpaUtil.getEM();
        return em.createQuery("SELECT t FROM Turno ORDER BY t.identificadorProgresivo ASC", Turno.class).getResultList();
    }

    public void actualizarEstadoTurno(long id, EstadoTurno nuevoEstado){
        EntityManager em = JpaUtil.getEM();
        try {
            em.getTransaction().begin();
            Turno turno = em.find(Turno.class, id);
            if(turno == null){
                System.out.println("Turno con ID " + id + " no encontrado.");
                return;
            }
            turno.setEstadoTurno(nuevoEstado);
            em.merge(turno);
            em.getTransaction().commit();

        }catch (Exception e){
            em.getTransaction().rollback();
            System.out.println("Error actualizando estado: " + e.getMessage());
        }finally {
            em.close();
        }
    }
}
