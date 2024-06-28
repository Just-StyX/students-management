package jsl.groups.models.dto;

import jsl.groups.models.Course;

/**
 * The interface Course dto.
 */
public interface CourseDto {
    /**
     * Create course.
     *
     * @param course the course
     * @return the course
     */
    Course create(Course course);

    /**
     * Find by id course.
     *
     * @param courseId the course id
     * @return the course
     */
    Course findById(String courseId);

    /**
     * Update course.
     *
     * @param courseId the course id
     * @param course   the course
     * @return the course
     */
    Course update(String courseId, Course course);

    /**
     * Delete by id boolean.
     *
     * @param courseId the course id
     * @return the boolean
     */
    boolean deleteById(String courseId);
}
