package turnopro.logic;

import turnopro.entities.Ciudadano;
import turnopro.entities.EstadoTurno;
import turnopro.entities.Turno;
import turnopro.persistence.CiudadanoJPA;
import turnopro.persistence.TurnoJPA;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TurnoService {
    private final CiudadanoJPA ciudadanoJPA = new CiudadanoJPA();
    private final TurnoJPA turnoJPA = new TurnoJPA();

    public void crearCiudadano(Ciudadano c){
        ciudadanoJPA.crearCiudadano(c);
    }

    public Ciudadano obtenerCiudadano(Long id){
        return ciudadanoJPA.obtenerCiudadano(id);
    }

    public List<Turno> listarTurnos() {
        return turnoJPA.listarTurnos();
    }

    public void registrarNuevoTurno(LocalDateTime fecha, String descripcion, Long idCiudadano){
        Ciudadano ciudadano = ciudadanoJPA.obtenerCiudadano(idCiudadano);

        if (ciudadano == null){
            throw new IllegalArgumentException("Ciudadano no encontrado");
        }
        Long nuevoIdentificador = turnoJPA.obtenerMaximoIdentificadorProgresivo() + 1;
        Turno turno = new Turno(nuevoIdentificador, fecha, descripcion, EstadoTurno.EN_ESPERA, ciudadano);
    }

    public void actualizarEstadoTurno(Long id){
        turnoJPA.actualizarEstadoTurno(id, EstadoTurno.YA_ATENDIDO);
    }

    public List<Turno> filtrarPorFecha(LocalDateTime fecha){
        return turnoJPA.filtrarPorFecha(fecha);
    }

    public List<Turno> filtrarPorEstado(EstadoTurno estadoTurno){
        return turnoJPA.filtrarPorEstado(estadoTurno);
    }
}
