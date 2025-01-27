package src.db;

import java.io.Serial;

public class DbException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DbException (String message) {

        super(message);

    }

}
