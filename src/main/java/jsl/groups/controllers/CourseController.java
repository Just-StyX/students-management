package jsl.groups.controllers;

import io.javalin.http.Handler;
import jsl.groups.models.Course;
import jsl.groups.models.dto.CourseDto;
import jsl.groups.models.dto.implementations.CourseDtoImplementation;
import jsl.groups.utilities.DeleteResponse;

/**
 * The type Course controller.
 */
public class CourseController {
    private static final CourseDto courseDto = new CourseDtoImplementation();

    /**
     * The constant createCourse.
     */
    public static Handler createCourse = context -> {
        var course = context.bodyAsClass(Course.class);
        context.json(courseDto.create(course));
    };

    /**
     * The constant findById.
     */
    public static Handler findById = context -> {
        context.json(courseDto.findById(context.pathParam("courseId")));
    };

    /**
     * The constant update.
     */
    public static Handler update = context -> {
        var courseId = context.pathParam("courseId");
        var course = context.bodyAsClass(Course.class);
        context.json(courseDto.update(courseId, course));
    };

    /**
     * The constant deleteById.
     */
    public static Handler deleteById = context -> {
        var courseId = context.pathParam("courseId");
        var message = courseDto.deleteById(courseId) ? "Course deleted safely" : "Could not delete course";
        context.json(new DeleteResponse(courseId, message));
    };
}
