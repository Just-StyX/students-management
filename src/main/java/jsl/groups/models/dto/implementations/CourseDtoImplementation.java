package jsl.groups.models.dto.implementations;

import jsl.groups.db.JDBCConnection;
import jsl.groups.errors.DataInsertionError;
import jsl.groups.errors.InvalidUserInputError;
import jsl.groups.models.Course;
import jsl.groups.models.dto.CourseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDtoImplementation implements CourseDto {
    @Override
    public Course create(Course course) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var query = """
                    INSERT INTO course(course_id, subject_id, course_number, title, number_of_credit) values (?, ?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseId());
            preparedStatement.setString(2, course.getSubjectId());
            preparedStatement.setString(3, course.getCourseNumber());
            preparedStatement.setString(4, course.getTitle());
            preparedStatement.setInt(5, course.getNumberOfCredit());

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {
                throw new DataInsertionError("Course was not entered into records");
            }
            return course;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course findById(String courseId) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var query = """
                SELECT * FROM course WHERE course_id = ?
                """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new InvalidUserInputError("Invalid course input");
            }
            return Course.init()
                    .setCourseId(resultSet.getString(1))
                    .setSubjectId(resultSet.getString(2))
                    .setCourseNumber(resultSet.getString(3))
                    .setTitle(resultSet.getString(4))
                    .setNumberOfCredit(resultSet.getInt(5));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course update(String courseId, Course newCourse) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var oldCourse = findById(courseId);
            var subject = newCourse.getSubjectId() == null ? oldCourse.getSubjectId() : newCourse.getSubjectId();
            var number = newCourse.getCourseNumber() == null ? oldCourse.getCourseNumber() : newCourse.getCourseNumber();
            var title = newCourse.getTitle() == null ? oldCourse.getTitle() : newCourse.getTitle();
            var credit = newCourse.getNumberOfCredit() == 0 ? oldCourse.getNumberOfCredit() : newCourse.getNumberOfCredit();

            var query = """
                    UPDATE course SET course_id = ?, subject_id = ?, course_number = ?, title = ?, number_of_credit = ?, WHERE course_id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseId);
            preparedStatement.setString(2, subject);
            preparedStatement.setString(3, number);
            preparedStatement.setString(4, title);
            preparedStatement.setInt(5, credit);
            preparedStatement.setString(6, courseId);

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {
                throw new DataInsertionError("Course was not entered into records");
            }
            return newCourse;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(String courseId) {
        try (Connection connection = JDBCConnection.getConnection()){
            String sql = "DELETE FROM course WHERE course_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, courseId);

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new DataInsertionError("Course was not entered into the database.");
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
