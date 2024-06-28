package jsl.groups.errors;

import java.sql.SQLException;

public class InvalidUserInputError extends SQLException {
    public InvalidUserInputError(String message) {
        super(message);
    }
}
