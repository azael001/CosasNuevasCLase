package defaul;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado {
    @Id
    @Column(name = "EMPNO", nullable = false)
    private Long id;

    @Column(name = "NOMBREEMPNO", length = 20)
    private String nombreempno;

    @Column(name = "CARGO", length = 10)
    private String cargo;

    @Column(name = "FECHAING")
    private LocalDate fechaing;

    @Column(name = "SALARIO")
    private Long salario;

    @Column(name = "COMISION")
    private Long comision;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "DEPTNO")
    private Departamento deptno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreempno() {
        return nombreempno;
    }

    public void setNombreempno(String nombreempno) {
        this.nombreempno = nombreempno;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaing() {
        return fechaing;
    }

    public void setFechaing(LocalDate fechaing) {
        this.fechaing = fechaing;
    }

    public Long getSalario() {
        return salario;
    }

    public void setSalario(Long salario) {
        this.salario = salario;
    }

    public Long getComision() {
        return comision;
    }

    public void setComision(Long comision) {
        this.comision = comision;
    }

    public Departamento getDeptno() {
        return deptno;
    }

    public void setDeptno(Departamento deptno) {
        this.deptno = deptno;
    }

}