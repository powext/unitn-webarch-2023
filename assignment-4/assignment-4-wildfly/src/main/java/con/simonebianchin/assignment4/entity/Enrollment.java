package con.simonebianchin.assignment4.entity;


import dto.EntityDTO.EnrollmentDTO;

import javax.persistence.*;

@Entity
public class Enrollment {

    @EmbeddedId
    EnrollmentKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "STUDENT_ID")
    Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "COURSE_ID")
    Course course;

    @Column(name = "RATING")
    int rating;

    public EnrollmentDTO toDTO() {
        return new EnrollmentDTO(this.student.getMatriculation(), this.course.getId(), this.course.getName(), this.rating);
    }

    public EnrollmentKey getId() {
        return id;
    }

    public void setId(EnrollmentKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
