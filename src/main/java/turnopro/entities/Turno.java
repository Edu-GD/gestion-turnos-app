package turnopro.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Turno {

    // Id autogenerado en la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Número progresivo único del turno
    @Column(name = "identificador", nullable = false, length = 5, unique = true)
    private Long identificadorProgresivo;

    // Fecha del turno
    @Column(nullable = false)
    private LocalDateTime fecha;

    // Descripción del turno
    @Column(nullable = false)
    private String descripcion;

    // Estado del turno
    @Column(nullable = false)
    private EstadoTurno estadoTurno;

    // Turno pertenece a un ciudado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;

    //Constructor vacío requerido por JPA
    public Turno() {
    }

    // Constructor principal
    public Turno(Long identificadorProgresivo, LocalDateTime fecha, String descripcion, EstadoTurno estadoTurno, Ciudadano ciudadano) {
        this.identificadorProgresivo = identificadorProgresivo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estadoTurno = estadoTurno;
        this.ciudadano = ciudadano;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public Long getIdentificadorProgresivo() {
        return identificadorProgresivo;
    }

    public void setIdentificadorProgresivo(Long identificadorProgresivo) {
        this.identificadorProgresivo = identificadorProgresivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoTurno getEstadoTurno() {
        return estadoTurno;
    }

    public void setEstadoTurno(EstadoTurno estadoTurno) {
        this.estadoTurno = estadoTurno;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Representación en texto
    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", identificadorProgresivo=" + identificadorProgresivo +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", estadoTurno=" + estadoTurno +
                ", ciudadano=" + ciudadano +
                '}';
    }
}
