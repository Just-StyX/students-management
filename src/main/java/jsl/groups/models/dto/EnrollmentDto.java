package jsl.groups.models.dto;

import jsl.groups.models.Enrollment;
import jsl.groups.utilities.EnrollmentResponse;
import jsl.groups.utilities.EnrollmentUpdateResponse;

import java.util.List;

/**
 * The interface Enrollment dto.
 */
public interface EnrollmentDto {
    /**
     * Add enrollment.
     *
     * @param ssn      the ssn
     * @param courseId the course id
     * @return the enrollment
     */
    EnrollmentUpdateResponse add(String ssn, String courseId);

    /**
     * Add grade enrollment.
     *
     * @param ssn      the ssn
     * @param courseId the course id
     * @param grade    the grade
     * @return the enrollment
     */
    EnrollmentUpdateResponse addGrade(String ssn, String courseId, char grade);

    /**
     * Find course list.
     *
     * @param ssn the ssn
     * @return the list
     */
    List<EnrollmentResponse> findCourse(String ssn);

    /**
     * Delete enrollment boolean.
     *
     * @param ssn      the ssn
     * @param courseId the course id
     * @return the boolean
     */
    boolean deleteEnrollment(String ssn, String courseId);
}
