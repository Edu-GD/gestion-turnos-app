package turnopro.controllers;

import turnopro.entities.Ciudadano;
import turnopro.entities.EstadoTurno;
import turnopro.entities.Turno;
import turnopro.persistence.CiudadanoJPA;
import turnopro.persistence.TurnoJPA;

import java.util.List;

public class FachadaControllers {

    private final CiudadanoJPA ciudadanoJPA = new CiudadanoJPA();
    private final TurnoJPA turnoJPA = new TurnoJPA();

    public void crearCiudadano(Ciudadano c){
        ciudadanoJPA.crearCiudadano(c);
    }

    public Ciudadano obtenerCiudadano(Long id){
        return ciudadanoJPA.obtenerCiudadano(id);
    }

    public void guardarTurno(Turno t){
        turnoJPA.guardarTurno(t);
    }

    public List<Turno> listarTurnos() {
        return turnoJPA.listarTurnos();
    }
    public void actualizarEstadoTurno(long id, EstadoTurno nuevoEstado){
        turnoJPA.actualizarEstadoTurno(id, nuevoEstado);
    }

}
