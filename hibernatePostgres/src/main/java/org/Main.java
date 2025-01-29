package org;

import Util.HibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        // Abrir una sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Departamento dl = new Departamento();;
        dl=(Departamento) session.get(Departamento.class, 1);
        System.out.println("nombre" + dl.getNombredeptno());
        session.close();
    }
}
