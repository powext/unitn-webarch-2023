package con.simonebianchin.assignment4.ejb;

import con.simonebianchin.assignment4.entity.Student;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class StudentBean implements Serializable {
    @PersistenceContext
    private EntityManager entityManager;

    public Student getStudent(int matriculation) {
        Query q = entityManager.createQuery("from Student where matriculation = :matriculation", Student.class);
        List<Student> students = q.setParameter("matriculation", matriculation)
                .getResultList();
        return students.isEmpty() ? null : students.get(0);
    }

    public StudentBean () {}
}
