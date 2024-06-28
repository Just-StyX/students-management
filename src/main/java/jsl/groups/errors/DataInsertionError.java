package jsl.groups.errors;

import java.sql.SQLException;

public class DataInsertionError extends SQLException {
    public DataInsertionError(String message) {
        super(message);
    }
}
