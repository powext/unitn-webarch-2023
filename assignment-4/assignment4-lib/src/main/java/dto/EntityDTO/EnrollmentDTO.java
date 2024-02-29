package dto.EntityDTO;

import java.io.Serializable;

public class EnrollmentDTO implements Serializable {
    int studentMatriculation;
    int courseId;
    String courseName;
    int rating;

    public EnrollmentDTO(int studentMatriculation, int courseId, String courseName, int rating) {
        this.studentMatriculation = studentMatriculation;
        this.courseId = courseId;
        this.courseName = courseName;
        this.rating = rating;
    }

    public int getStudentMatriculation() {
        return studentMatriculation;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getRating() {
        return rating;
    }

    public String getCourseName() {
        return courseName;
    }
}
