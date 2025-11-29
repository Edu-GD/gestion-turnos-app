package turnopro.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String registro;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private EstadoTurno estadoTurno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;

    public Turno() {
    }

    public Turno(String registro, LocalDateTime fecha, EstadoTurno estadoTurno, Ciudadano ciudadano) {
        this.registro = registro;
        this.fecha = fecha;
        this.estadoTurno = estadoTurno;
        this.ciudadano = ciudadano;
    }

    public Long getId() {
        return id;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
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

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", registro='" + registro + '\'' +
                ", fecha=" + fecha +
                ", estadoTurno=" + estadoTurno +
                ", ciudadano=" + ciudadano +
                '}';
    }
}
