package org;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento {
    @Id
    @Column(name = "deptno", nullable = false)
    private Integer id;

    @Column(name = "nombredeptno", length = 15)
    private String nombredeptno;

    @Column(name = "localidad", length = 15)
    private String localidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombredeptno() {
        return nombredeptno;
    }

    public void setNombredeptno(String nombredeptno) {
        this.nombredeptno = nombredeptno;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

}