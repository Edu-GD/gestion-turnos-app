package turnopro.logic;

import turnopro.controllers.FachadaControllers;
import turnopro.entities.Ciudadano;
import turnopro.entities.EstadoTurno;
import turnopro.entities.Turno;
import turnopro.persistence.CiudadanoJPA;
import turnopro.persistence.TurnoJPA;
import turnopro.utils.IdProgresivoGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class TurnoService {
    private final CiudadanoJPA ciudadanoJPA = new CiudadanoJPA();
    private final TurnoJPA turnoJPA = new TurnoJPA();
    private final FachadaControllers fachada = new FachadaControllers();

    public void crearCiudadano(Ciudadano c){
        fachada.crearCiudadano(c);
    }

    public Ciudadano obtenerCiudadano(Long id){
        return fachada.obtenerCiudadano(id);
    }

    public List<Turno> listarTurnos() {
        return fachada.listarTurnos();
    }

    public void registrarNuevoTurno(LocalDateTime fecha, String descripcion, Long idCiudadano){
        Ciudadano ciudadano = ciudadanoJPA.obtenerCiudadano(idCiudadano);

        if (ciudadano == null){
            throw new IllegalArgumentException("Ciudadano no encontrado");
        }
        Long nuevoIdentificador = IdProgresivoGenerator.generarSiguiente(fachada);
        Turno turno = new Turno(nuevoIdentificador, fecha, descripcion, EstadoTurno.EN_ESPERA, ciudadano);

        fachada.guardarTurno(turno);
    }

    public void actualizarEstadoTurno(Long id){
        fachada.actualizarEstadoTurno(id);
    }

    public List<Turno> filtrarPorFecha(LocalDateTime fecha){
        return fachada.filtrarPorFecha(fecha);
    }

    public List<Turno> filtrarPorEstado(EstadoTurno estadoTurno){
        return fachada.filtrarPorEstado(estadoTurno);
    }
}
