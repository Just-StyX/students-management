package jsl.groups.controllers;

import io.javalin.http.Handler;
import jsl.groups.models.Student;
import jsl.groups.models.dto.StudentDto;
import jsl.groups.models.dto.implementations.StudentDtoImplementation;
import jsl.groups.utilities.DeleteResponse;

/**
 * The type Student controller.
 *
 * @author github.com /Just-StyX
 */
public class StudentController {
    /**
     * The constant studentDto.
     */
    public static StudentDto studentDto = new StudentDtoImplementation();

    /**
     * The constant createStudent. Persist data
     */
    public static Handler createStudent = context -> {
        var student = context.bodyAsClass(Student.class);
        context.json(studentDto.create(student));
    };

    /**
     * The constant findBySSN.
     */
    public static Handler findBySSN = context -> {
        context.json(studentDto.findBySNN(context.pathParam("ssn")));
    };

    /**
     * The constant updateStudent.
     */
    public static Handler updateStudent = context -> {
        var student = context.bodyAsClass(Student.class);
        var ssn = context.pathParam("ssn");
        context.json(studentDto.updateStudent(ssn, student));
    };

    /**
     * The constant deleteStudent.
     */
    public static Handler deleteStudent = context -> {
        var ssn = context.pathParam("ssn");
        var message = studentDto.deleteBySNN(ssn) ? "Student deleted safely" : "Could not delete student";
        context.json(new DeleteResponse(ssn, message));
    };
}
