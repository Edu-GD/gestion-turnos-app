package turnopro.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ciudadanos")
public class Ciudadano {

    // ID autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre obligatorio
    @Column(nullable = false, length = 25)
    private String nombre;

    // Apellidos obligatorios
    @Column(nullable = false, length = 25)
    private String apellidos;

    // DNI único
    @Column(unique = true, nullable = false)
    private String dni;

    // Teléfono obligatorio
    @Column(nullable = false)
    private String telefono;

    // Email obligatorio
    @Column(nullable = false)
    private String correoElectronico;

    // Relación 1-N con Turno
    @OneToMany(mappedBy = "ciudadano", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public Ciudadano() {
    }

    public Ciudadano(String nombre, String apellidos, String dni, String telefono, String correoElectronico) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "CIUDADANO: " +
                "Id:" + id +
                "| Nombre:" + nombre + '\'' +
                "| Apellidos:" + apellidos + '\'' +
                "| DNI:" + dni + '\'';
    }
}
