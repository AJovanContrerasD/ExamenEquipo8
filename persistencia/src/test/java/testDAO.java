import mx.desarrollo.entity.Alumno;
import mx.desarrollo.persistence.dao.AlumnoDAO;
import mx.desarrollo.persistence.persistence.HibernateUtil;

public class testDAO {

    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO(HibernateUtil.getEntityManager());



        for (Alumno alumno : alumnoDAO.findAll()) {
            System.out.println(alumno + "|| id [" + alumno.getId()+ "]");
        }
    }
}
