package main.java.com.pruebacomex;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        MainApp ME = new MainApp();
        /* Add few Transaccion records in database */
        Integer transaccionID1 = ME.addTransaccion("Factura", new Date(), "activo", 1);
        Integer transaccionID2 = ME.addTransaccion("Recibo", new Date(), "activo", 1);
        Integer transaccionID3 = ME.addTransaccion("Boleta", new Date(), "inactivo", 2);

        /* Add few Empresa records in database */
        Integer empresaID1 = ME.addEmpresa("12345678", "Empresa1", "Direccion1", "activo");
        Integer empresaID2 = ME.addEmpresa("87654321", "Empresa2", "Direccion2", "activo");
        Integer empresaID3 = ME.addEmpresa("99999999", "Empresa3", "Direccion3", "inactivo");

        /* Add few Respuesta records in database */
        Integer respuestaID1 = ME.addRespuesta(transaccionID1, "001", "Respuesta 1", new Date(), "activo");
        Integer respuestaID2 = ME.addRespuesta(transaccionID1, "002", "Respuesta 2", new Date(), "inactivo");
        Integer respuestaID3 = ME.addRespuesta(transaccionID2, "003", "Respuesta 3", new Date(), "activo");

        /* List down all the transacciones */
        ME.listTransacciones();

        /* Get empresa by ID */
        ME.getEmpresaByID(empresaID1);

        /* Get last respuesta by empresa */
        ME.getLastRespuestaByEmpresa(empresaID1);

        /* Delete a transaccion from the database */
        ME.deleteTransaccion(transaccionID2);

        /* List down new list of the transacciones */
        ME.listTransacciones();

        /* Update a transaccion record */
        ME.updateTransaccion(transaccionID3, "Boleta actualizada", new Date(), "activo", 3);

        /* List down updated list of transacciones */
        ME.listTransacciones();
    }

    /* Method to CREATE a Transaccion in the database */
    public Integer addTransaccion(String nombreDocumento, Date fechaRegistro, String estado, Integer idEmpresa) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaccion transaccion = new Transaccion(nombreDocumento, fechaRegistro, estado, idEmpresa);
        Integer transaccionID = (Integer) session.save(transaccion);
        session.getTransaction().commit();
        session.close();
        return transaccionID;
    }

    /* Method to CREATE an Empresa in the database */
    public Integer addEmpresa(String ruc, String razonSocial, String direccion, String estado) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Empresa empresa = new Empresa(ruc, razonSocial, direccion, estado);
        Integer empresaID = (Integer) session.save(empresa);
        session.getTransaction().commit();
        session.close();
        return empresaID;
    }
}