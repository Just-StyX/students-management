package jsl.groups.controllers;

import io.javalin.http.Handler;
import jsl.groups.models.dto.EnrollmentDto;
import jsl.groups.models.dto.implementations.EnrollmentDtoImplementation;
import jsl.groups.utilities.DeleteResponse;

import java.util.Objects;

public class EnrollmentController {
    private static final EnrollmentDto enrollmentDto = new EnrollmentDtoImplementation();

    public static Handler enroll = context -> {
        context.json(enrollmentDto.add(context.queryParam("ssn"), context.pathParam("courseId")));
    };

    public static Handler addGrade = context -> {
        var grade = Objects.requireNonNull(context.queryParam("grade")).charAt(0);
        var course = context.pathParam("courseId");
        var student = context.queryParam("ssn");
        context.json(enrollmentDto.addGrade(student, course, grade));
    };

    public static Handler findCourse = context -> {
        context.json(enrollmentDto.findCourse(context.queryParam("ssn")));
    };

    public static Handler deleteEnrollment = context -> {
        var courseId = context.pathParam("courseId");
        var message = enrollmentDto.deleteEnrollment(context.queryParam("ssn"), courseId) ? "Course deleted safely" : "Could not delete course";
        context.json(new DeleteResponse(courseId, message));
    };
}
