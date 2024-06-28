package jsl.groups.models.dto.implementations;

import jsl.groups.db.JDBCConnection;
import jsl.groups.errors.DataInsertionError;
import jsl.groups.models.dto.EnrollmentDto;
import jsl.groups.utilities.EnrollmentResponse;
import jsl.groups.utilities.EnrollmentUpdateResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDtoImplementation implements EnrollmentDto {
    @Override
    public EnrollmentUpdateResponse add(String ssn, String courseId) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var query = """
                    INSERT INTO enrollment(ssn, course_id) values (?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ssn);
            preparedStatement.setString(2, courseId);

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {
                throw new DataInsertionError("Course was not entered into records");
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                return new EnrollmentUpdateResponse(resultSet.getLong(1), "Course Enrolled");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public EnrollmentUpdateResponse addGrade(String ssn, String courseId, char grade) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var query = """
                    UPDATE enrollment SET grade = ? WHERE ssn = ? AND course_id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(grade));
            preparedStatement.setString(2, ssn);
            preparedStatement.setString(3, courseId);

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {
                throw new DataInsertionError("Course was not entered into records");
            }

            return new EnrollmentUpdateResponse(checkInsert, "Course grade added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EnrollmentResponse> findCourse(String ssn) {
        try (Connection connection = JDBCConnection.getConnection()) {
            List<EnrollmentResponse> enrollmentResponses = new ArrayList<>();
            var query = """
                    SELECT subject_id, title, number_of_credit, grade FROM course INNER JOIN enrollment ON course.course_id = enrollment.course_id WHERE ssn = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ssn);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                enrollmentResponses.add(
                        new EnrollmentResponse(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getString(4).charAt(0)
                        )
                );
            }
            return enrollmentResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteEnrollment(String ssn, String courseId) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var query = """
                    DELETE FROM enrollment WHERE ssn = ? AND course_id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ssn);
            preparedStatement.setString(2, courseId);
            int checkInsert = preparedStatement.executeUpdate();

            if(checkInsert == 0){
                throw new DataInsertionError("Course was not entered into the database.");
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
