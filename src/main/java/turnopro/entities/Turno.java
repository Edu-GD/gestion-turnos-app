package turnopro.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificador", nullable = false, length = 5, unique = true)
    private Integer identificadorProgresivo;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private EstadoTurno estadoTurno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;

    public Turno() {
    }

    public Turno(Integer identificadorProgresivo, LocalDateTime fecha, String descripcion, EstadoTurno estadoTurno, Ciudadano ciudadano) {
        this.identificadorProgresivo = identificadorProgresivo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estadoTurno = estadoTurno;
        this.ciudadano = ciudadano;
    }

    public Long getId() {
        return id;
    }

    public Integer getIdentificadorProgresivo() {
        return identificadorProgresivo;
    }

    public void setIdentificadorProgresivo(Integer identificadorProgresivo) {
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
