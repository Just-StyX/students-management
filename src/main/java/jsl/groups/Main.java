package jsl.groups;

import io.javalin.Javalin;
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
                        });
                    });
                }
        );
    }
}