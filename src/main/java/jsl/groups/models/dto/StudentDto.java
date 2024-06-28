package jsl.groups.models.dto;

import jsl.groups.models.Student;

/**
 * The interface Student dto.
 */
public interface StudentDto {
    /**
     * Create student.
     *
     * @param student the student
     * @return the student
     */
    Student create(Student student);

    /**
     * Find by snn student.
     *
     * @param snn the snn
     * @return the student
     */
    Student findBySNN(String snn);

    /**
     * Update student.
     *
     * @param snn     the snn
     * @param student the student
     * @return the student
     */
    Student updateStudent(String snn, Student student);

    /**
     * Delete by snn.
     *
     * @param snn the snn
     */
    boolean deleteBySNN(String snn);
}
