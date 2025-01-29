package defaul;

import Util.HibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        // Abrir una sesión de Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Empleado dl = new Empleado();
        dl=(Empleado) session.get(Empleado.class, 101);
        System.out.println("nombre: " + dl.getNombreempno());
        session.close();
    }
}
