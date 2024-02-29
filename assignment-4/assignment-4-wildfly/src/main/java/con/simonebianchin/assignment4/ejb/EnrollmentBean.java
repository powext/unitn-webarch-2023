package con.simonebianchin.assignment4.ejb;

import con.simonebianchin.assignment4.entity.Enrollment;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class EnrollmentBean implements Serializable {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Enrollment> getStudentEnrollment(int matriculation) {
        Query q = entityManager.createQuery("from Enrollment where student.id = :matriculation", Enrollment.class);
        List<Enrollment> enrollments = q.setParameter("matriculation", matriculation)
                .getResultList();
        return enrollments.isEmpty() ? new ArrayList<>() : enrollments;
    }

    public EnrollmentBean () {}
}
