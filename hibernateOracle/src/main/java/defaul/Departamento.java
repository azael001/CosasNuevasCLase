package defaul;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento {
    @Id
    @Column(name = "DEPTNO", nullable = false)
    private Long id;

    @Column(name = "NOMBREDEPTNO", length = 15)
    private String nombredeptno;

    @Column(name = "LOCALIDAD", length = 15)
    private String localidad;

    @OneToMany(mappedBy = "deptno")
    private Set<defaul.Empleado> empleados = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<defaul.Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<defaul.Empleado> empleados) {
        this.empleados = empleados;
    }

}