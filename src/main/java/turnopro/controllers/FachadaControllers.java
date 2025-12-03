package turnopro.controllers;

import turnopro.entities.Ciudadano;
import turnopro.entities.EstadoTurno;
import turnopro.entities.Turno;
import turnopro.logic.TurnoService;
import turnopro.logic.CiudadanoService;

import java.time.LocalDateTime;
import java.util.List;

public class FachadaControllers {

    // 1. Instancias de los Servicios para delegar la lógica de negocio y persistencia
    private final CiudadanoService ciudadanoService = new CiudadanoService();
    private final TurnoService turnoService = new TurnoService();

    // Operaciones de Ciudadano
    public void crearCiudadano(Ciudadano c) {
        ciudadanoService.crearCiudadano(c);
    }

    public Ciudadano obtenerCiudadano(Long id) {
        return ciudadanoService.obtenerCiudadano(id);
    }

    // Operaciones de Turno
    public void registrarNuevoTurno(LocalDateTime fechaHora, String descripcion, Long idCiudadano) {
        turnoService.registrarNuevoTurno(fechaHora, descripcion, idCiudadano);
    }

    public void guardarTurno(Turno t) {
        turnoService.guardarTurno(t);
    }

    public List<Turno> listarTurnos() {
        return turnoService.listarTurnos();
    }

    public Long obtenerMaximoIdentificadorProgresivo() {
        return turnoService.obtenerMaximoIdentificadorProgresivo();
    }

    public void actualizarEstadoTurno(Long id) {
        turnoService.actualizarEstadoTurno(id);
    }

    public List<Turno> filtrarPorFecha(LocalDateTime fecha) {
        return turnoService.filtrarPorFecha(fecha);
    }

    public List<Turno> filtrarPorEstado(EstadoTurno estadoTurno) {
        return turnoService.filtrarPorEstado(estadoTurno);
    }

    public List<Ciudadano> listarCiudadanos() {
        return ciudadanoService.listarCiudadanos();
    }
}
