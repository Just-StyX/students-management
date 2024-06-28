package jsl.groups.models.dto.implementations;

import jsl.groups.db.JDBCConnection;
import jsl.groups.errors.DataInsertionError;
import jsl.groups.errors.InvalidUserInputError;
import jsl.groups.models.Student;
import jsl.groups.models.dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Student dto implementation.
 *
 * @author github.com/Just-StyX
 */
public class StudentDtoImplementation implements StudentDto {
    @Override
    public Student create(Student student) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var query = """
                    INSERT INTO student(ssn, first_name, middle_initial, last_name, phone, birth_date, street, zip_code, dept_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getSsn());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, String.valueOf(student.getMiddleInitial()));
            preparedStatement.setString(4, student.getLastName());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setDate(6, student.getBirthDate());
            preparedStatement.setString(7, student.getStreet());
            preparedStatement.setString(8, student.getZipCode());
            preparedStatement.setString(9, student.getDeptId());

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {
                throw new DataInsertionError("Student was not entered into records");
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findBySNN(String ssn) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var query = """
                SELECT * FROM student WHERE ssn = ?
                """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ssn);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new InvalidUserInputError("Invalid student input");
            }
            return Student.init()
                    .setSsn(resultSet.getString(1))
                    .setFirstName(resultSet.getString(2))
                    .setMiddleInitial(resultSet.getString(3).charAt(0))
                    .setLastName(resultSet.getString(4))
                    .setPhoneNumber(resultSet.getString(5))
                    .setBirthDate(resultSet.getDate(6))
                    .setStreet(resultSet.getString(7))
                    .setZipCode(resultSet.getString(8))
                    .setDeptId(resultSet.getString(9));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student updateStudent(String ssn, Student newStudent) {
        try (Connection connection = JDBCConnection.getConnection()) {
            var oldStudent = findBySNN(ssn);
            var firstname = newStudent.getFirstName() == null ? oldStudent.getFirstName() : newStudent.getFirstName();
            var middle = newStudent.getMiddleInitial() == ' ' ? oldStudent.getMiddleInitial() : newStudent.getMiddleInitial();
            var lastname = newStudent.getLastName() == null ? oldStudent.getLastName() : newStudent.getLastName();
            var phone = newStudent.getPhoneNumber() == null ? oldStudent.getPhoneNumber() : newStudent.getPhoneNumber();
            var birth = newStudent.getBirthDate() == null ? oldStudent.getBirthDate() : newStudent.getBirthDate();
            var street = newStudent.getStreet() == null ? oldStudent.getStreet() : newStudent.getStreet();
            var zip = newStudent.getZipCode() == null ? oldStudent.getZipCode() : newStudent.getZipCode();
            var dept = newStudent.getDeptId() == null ? oldStudent.getDeptId() : newStudent.getDeptId();

            var query = """
                    UPDATE student SET ssn = ?, first_name = ?, middle_initial = ?, last_name = ?, phone = ?, birth_date = ?, street = ?, zip_code = ?, dept_id = ? WHERE ssn = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ssn);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, String.valueOf(middle));
            preparedStatement.setString(4, lastname);
            preparedStatement.setString(5, phone);
            preparedStatement.setDate(6, birth);
            preparedStatement.setString(7, street);
            preparedStatement.setString(8, zip);
            preparedStatement.setString(9, dept);
            preparedStatement.setString(10, ssn);

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {
                throw new DataInsertionError("Student was not entered into records");
            }
            return newStudent;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteBySNN(String ssn) {
        try (Connection connection = JDBCConnection.getConnection()){
            String sql = "DELETE FROM student WHERE ssn = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, ssn);

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new DataInsertionError("Member was not entered into the database.");
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
