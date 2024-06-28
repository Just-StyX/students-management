package jsl.groups.models;

import java.util.Objects;

/**
 * The type Course.
 *
 * @author github.com/Just-StyX
 */
public class Course {
    private String courseId;
    private String subjectId;
    private String courseNumber;
    private String title;
    private int numberOfCredit;

    public static Course init() {
        return new Course();
    }

    public String getCourseId() {
        return courseId;
    }

    public Course setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public Course setSubjectId(String subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public Course setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public Course setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Course course = (Course) object;
        return numberOfCredit == course.numberOfCredit && Objects.equals(courseId, course.courseId) && Objects.equals(subjectId, course.subjectId) && Objects.equals(courseNumber, course.courseNumber) && Objects.equals(title, course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, subjectId, courseNumber, title, numberOfCredit);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", title='" + title + '\'' +
                ", numberOfCredit=" + numberOfCredit +
                '}';
    }
}
