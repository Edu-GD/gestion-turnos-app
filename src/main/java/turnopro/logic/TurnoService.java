package turnopro.logic;

import turnopro.entities.Ciudadano;
import turnopro.entities.EstadoTurno;
import turnopro.entities.Turno;
import turnopro.persistence.CiudadanoJPA;
import turnopro.persistence.TurnoJPA;

import java.time.LocalDateTime;
import java.util.List;

public class TurnoService {
    private final CiudadanoJPA ciudadanoJPA = new CiudadanoJPA();
    private final TurnoJPA turnoJPA = new TurnoJPA();

    public void registrarNuevoTurno(LocalDateTime fecha, String descripcion, Long idCiudadano){

        // 1. Obtener ciudadano (usando la persistencia)
        Ciudadano ciudadano = ciudadanoJPA.obtenerCiudadano(idCiudadano);

        if (ciudadano == null){
            // Lanza una excepción que será capturada por el Servlet
            throw new IllegalArgumentException("Ciudadano con ID " + idCiudadano + " no encontrado.");
        }

        // 2. Generar ID progresivo
        Long maxId = turnoJPA.obtenerMaximoIdentificadorProgresivo();
        Long nuevoIdentificador = (maxId == null) ? 1L : maxId + 1;

        // 3. Crear el Turno
        Turno turno = new Turno(nuevoIdentificador, fecha, descripcion, EstadoTurno.EN_ESPERA, ciudadano);

        // 4. Guardar el Turno
        turnoJPA.guardarTurno(turno);
    }

    // Actualiza el estado del turno a YA_ATENDIDO
    public void actualizarEstadoTurno(Long id){
        turnoJPA.actualizarEstadoTurno(id, EstadoTurno.YA_ATENDIDO);
    }

    // Metodos de consulta
    public List<Turno> listarTurnos() {
        return turnoJPA.listarTurnos();
    }

    public List<Turno> filtrarPorFecha(LocalDateTime fecha){
        return turnoJPA.filtrarPorFecha(fecha);
    }

    public List<Turno> filtrarPorEstado(EstadoTurno estadoTurno){
        return turnoJPA.filtrarPorEstado(estadoTurno);
    }

    public void guardarTurno(Turno t) {
        turnoJPA.guardarTurno(t);
    }

    // Permite a la fachada obtener el máximo ID progresivo
    public Long obtenerMaximoIdentificadorProgresivo(){
        return turnoJPA.obtenerMaximoIdentificadorProgresivo();
    }
}
