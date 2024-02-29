package con.simonebianchin.assignment4.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EnrollmentKey implements Serializable {
    @Column(name = "STUDENT_ID")
    int studentId;

    @Column(name = "COURSE_ID")
    int courseId;
}
