package jsl.groups.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Enrollment.
 *
 * @author github.com/Just-StyX
 */
public class Enrollment {
    private long id;
    private String ssn;
    private String courseId;
    private LocalDateTime dateRegistered;
    private char grade;

    public static Enrollment init() {
        return new Enrollment();
    }

    public String getSsn() {
        return ssn;
    }

    public Enrollment setSsn(String ssn) {
        this.ssn = ssn;
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public Enrollment setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public Enrollment setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
        return this;
    }

    public char getGrade() {
        return grade;
    }

    public Enrollment setGrade(char grade) {
        this.grade = grade;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Enrollment that = (Enrollment) object;
        return grade == that.grade && Objects.equals(ssn, that.ssn) && Objects.equals(courseId, that.courseId) && Objects.equals(dateRegistered, that.dateRegistered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssn, courseId, dateRegistered, grade);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "ssn='" + ssn + '\'' +
                ", courseId='" + courseId + '\'' +
                ", dateRegistered=" + dateRegistered +
                ", grade=" + grade +
                '}';
    }

    public long getId() {
        return id;
    }

    public Enrollment setId(long id) {
        this.id = id;
        return this;
    }
}
