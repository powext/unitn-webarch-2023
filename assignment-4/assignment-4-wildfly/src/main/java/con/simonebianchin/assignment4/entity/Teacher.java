package con.simonebianchin.assignment4.entity;

import dto.EntityDTO.TeacherDTO;

import javax.persistence.*;

@Entity
@Table(name = "TEACHER")
public class Teacher {
    @Id
    @Column(name="ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myseq")
    @SequenceGenerator(name = "myseq", sequenceName = "SEQUENCE_NAME", allocationSize = 1)
    private int id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    public Teacher() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TeacherDTO toTDO() {
        return new TeacherDTO(this.id, this.firstName, this.lastName);
    }
}
