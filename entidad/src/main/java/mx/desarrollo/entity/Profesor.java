package mx.desarrollo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprofesor")
    private Integer idprofesor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "appaterno", nullable = false)
    private String appaterno;

    @Column(name = "apmaterno", nullable = false)
    private String apmaterno;

    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;

    public Integer getIdprofesor() { return idprofesor; }
    public void setIdprofesor(Integer idprofesor) { this.idprofesor = idprofesor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAppaterno() { return appaterno; }
    public void setAppaterno(String appaterno) { this.appaterno = appaterno; }

    public String getApmaterno() { return apmaterno; }
    public void setApmaterno(String apmaterno) {  this.apmaterno = apmaterno; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    public Integer getId() { return idprofesor; }
    public void setId(Integer id) { this.idprofesor = id; }



}