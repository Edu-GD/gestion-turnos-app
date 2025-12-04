package turnopro.logic;

import turnopro.entities.Ciudadano;
import turnopro.persistence.CiudadanoJPA;

import java.util.List;

public class CiudadanoService {

    // Dependencia de la capa de persistencia
    private final CiudadanoJPA ciudadanoJPA = new CiudadanoJPA();

    // Crear un nuevo ciudadano
    public void crearCiudadano(Ciudadano c) {
        ciudadanoJPA.crearCiudadano(c);
    }

    // Obtener ciudadano por su ID.
    public Ciudadano obtenerCiudadano(Long id) {
        return ciudadanoJPA.obtenerCiudadano(id);
    }

    // Listado de los ciudadanos
    public List<Ciudadano> listarCiudadanos() {
        return ciudadanoJPA.listarCiudadanos();
    }

    // Eliminar un ciudadano de la BD
    public void eliminarCiudadano(Long idCiudadano) {
        ciudadanoJPA.eliminarCiudadano(idCiudadano);
    }

    // Modificar un ciudadano ya registrado
    public void modificarCiudadano(Ciudadano ciudadano) {
        ciudadanoJPA.modificarCiudadano(ciudadano);
    }
}
