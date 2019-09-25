package org.hibernate.dialect;

import java.sql.SQLException;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.spi.SQLExceptionConversionDelegate;
import org.hibernate.exception.spi.TemplatedViolatedConstraintNameExtracter;
import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;
import org.hibernate.internal.util.JdbcExceptionHelper;

public class SQLiteSQLExceptionConversionDelegate implements SQLExceptionConversionDelegate {
  private static final int SQLITE_BUSY = 5;
  private static final int SQLITE_LOCKED = 6;
  private static final int SQLITE_IO_ERR = 10;
  private static final int SQLITE_PROTOCOL = 15;
  private static final int SQLITE_TOO_BIG = 18;
  private static final int SQLITE_CONSTRAINT = 19;
  private static final int SQLITE_MISMATCH = 20;
  private static final int SQLITE_NOT_ADB = 26;
  private static final ViolatedConstraintNameExtracter EXTRACTER = new TemplatedViolatedConstraintNameExtracter() {
    protected String doExtractConstraintName(SQLException sqle) throws NumberFormatException {
      return this.extractUsingTemplate("constraint ", " failed", sqle.getMessage());
    }
  };

  public SQLiteSQLExceptionConversionDelegate() {
  }

  public JDBCException convert(SQLException sqlException, String message, String sql) {
    int errorCode = JdbcExceptionHelper.extractErrorCode(sqlException);
    if (errorCode == 19) {
      String constraintName = EXTRACTER.extractConstraintName(sqlException);
      return new ConstraintViolationException(message, sqlException, sql, constraintName);
    } else if (errorCode != 18 && errorCode != 20) {
      if (errorCode != 5 && errorCode != 6) {
        return (JDBCException)((errorCode < 10 || errorCode > 15) && errorCode != 26 ? new GenericJDBCException(message, sqlException, sql) : new JDBCConnectionException(message, sqlException, sql));
      } else {
        return new LockAcquisitionException(message, sqlException, sql);
      }
    } else {
      return new DataException(message, sqlException, sql);
    }
  }
}
