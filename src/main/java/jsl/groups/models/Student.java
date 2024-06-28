package jsl.groups.models;

import java.sql.Date;
import java.util.Objects;

/**
 * The type Student.
 *
 * @author github.com/Just-StyX
 */
public class Student {
    private String ssn;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private String street;
    private String zipCode;
    private String deptId;

    public static Student init() {
        return new Student();
    }

    public String getSsn() {
        return ssn;
    }

    public Student setSsn(String ssn) {
        this.ssn = ssn;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public Student setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Student setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Student setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Student setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Student setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Student setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getDeptId() {
        return deptId;
    }

    public Student setDeptId(String deptId) {
        this.deptId = deptId;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Student student = (Student) object;
        return middleInitial == student.middleInitial && Objects.equals(ssn, student.ssn) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(birthDate, student.birthDate) && Objects.equals(street, student.street) && Objects.equals(zipCode, student.zipCode) && Objects.equals(deptId, student.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssn, firstName, middleInitial, lastName, phoneNumber, birthDate, street, zipCode, deptId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "snn='" + ssn + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial=" + middleInitial +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}
