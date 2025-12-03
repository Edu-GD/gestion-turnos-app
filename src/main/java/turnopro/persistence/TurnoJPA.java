package turnopro.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import turnopro.entities.EstadoTurno;
import turnopro.entities.Turno;

import java.time.LocalDateTime;
import java.util.List;

public class TurnoJPA {

    // Guarda un turno
    public void guardarTurno(Turno t) {
        EntityManager em = JpaUtil.getEM();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    // Lista todos los turnos con su ciudadano
    public List<Turno> listarTurnos() {
        try (EntityManager em = JpaUtil.getEM()) {
            return em.createQuery("SELECT t FROM Turno t JOIN FETCH t.ciudadano ORDER BY t.identificadorProgresivo ASC", Turno.class).getResultList();
        }
    }

    // Actualiza el estado de un turno
    public void actualizarEstadoTurno(long id, EstadoTurno nuevoEstado) {
        EntityManager em = JpaUtil.getEM();
        try {
            em.getTransaction().begin();

            // Busca el turno en la base de datos
            Turno turno = em.find(Turno.class, id);

            // Verifica si el turno existe
            if (turno == null) {
                System.out.println("Turno con ID " + id + " no encontrado.");
                return;
            }

            // Actualiza el estado del turno
            turno.setEstadoTurno(nuevoEstado);

            // Merge asegura que el objeto actualizado sea gestionado por el contexto de persistencia
            em.merge(turno);

            em.getTransaction().commit();

        } catch (Exception e) {
            // En caso de error se revierte la transacción
            em.getTransaction().rollback();
            System.out.println("Error actualizando estado: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Obtiene el mayor identificador progresivo
    public Long obtenerMaximoIdentificadorProgresivo() {
        EntityManager em = JpaUtil.getEM();
        Long maxId;
        try {
            maxId = em.createQuery("SELECT MAX(t.identificadorProgresivo) FROM Turno t", Long.class).getSingleResult();
        } catch (NoResultException e) {
            // No hay registros en la tabla
            maxId = null;
        } catch (Exception e) {
            System.out.println("Error obteniendo máximo ID progresivo: " + e.getMessage());
            maxId = null;
        } finally {
            em.close();
        }
        return maxId;
    }

    // Filtra turnos por fecha (en memoria)
    public List<Turno> filtrarPorFecha(LocalDateTime fecha) {
        return listarTurnos().stream()
                .filter(t -> t.getFecha().equals(fecha))
                .toList();
    }

    // Filtra turnos por estado (en memoria)
    public List<Turno> filtrarPorEstado(EstadoTurno estadoTurno) {
        return listarTurnos().stream()
                .filter(t -> t.getEstadoTurno() == estadoTurno)
                .toList();
    }
}
