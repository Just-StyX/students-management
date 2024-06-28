package jsl.groups;

import io.javalin.Javalin;
import jsl.groups.controllers.CourseController;
import jsl.groups.controllers.EnrollmentController;
import jsl.groups.controllers.StudentController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args)  {
        Javalin.createAndStart(
                javalinConfig -> {
                    javalinConfig.router.apiBuilder(() -> {
                        path("/api/v1" , () -> {
                            path("/student", () -> {
                                post(StudentController.createStudent);
                                path("/<ssn>", () -> {
                                    get(StudentController.findBySSN);
                                    put(StudentController.updateStudent);
                                    delete(StudentController.deleteStudent);
                                });
                            });
                            path("/course", () -> {
                                post(CourseController.createCourse);
                                path("/<courseId>", () -> {
                                    get(CourseController.findById);
                                    put(CourseController.update);
                                    delete(CourseController.deleteById);
                                });
                            });
                            path("/enrollment/<courseId>", () -> {
                                post(EnrollmentController.enroll);
                                path("/course", () -> {
                                    put(EnrollmentController.addGrade);
                                    get(EnrollmentController.findCourse);
                                    delete(EnrollmentController.deleteEnrollment);
                                });
                            });
                        });
                    });
                }
        );
    }
}