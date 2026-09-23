package mx.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@Entity
@Table(name = "asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idasignacion", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idprofesor", nullable = false)
    private Profesor idProfesor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idunidad", nullable = false)
    private UnidadAprendizaje idUnidad;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idadministrador", nullable = false)
    private Administrador idAdministrador;

    @Size(max = 10)
    @NotNull
    @Column(name = "grupo", nullable = false, length = 10)
    private String grupo;

    @Size(max = 10)
    @NotNull
    @Column(name = "semestre", nullable = false, length = 10)
    private String semestre;

    @Size(max = 15)
    @NotNull
    @Column(name = "diasemana", nullable = false, length = 15)
    private String diaSemana;

    @Size(max = 15)
    @NotNull
    @Column(name = "tipo", nullable = false, length = 15)
    private String tipo;

    @NotNull
    @Column(name = "horainicio", nullable = false)
    private LocalTime horaInicio;

    @NotNull
    @Column(name = "horafin", nullable = false)
    private LocalTime horaFin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    public UnidadAprendizaje getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(UnidadAprendizaje idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Administrador getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Administrador idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

}
