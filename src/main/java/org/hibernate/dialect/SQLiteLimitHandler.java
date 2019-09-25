//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.hibernate.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.hibernate.dialect.pagination.LimitHandler;
import org.hibernate.dialect.pagination.LimitHelper;
import org.hibernate.engine.spi.RowSelection;

public class SQLiteLimitHandler implements LimitHandler {
  public SQLiteLimitHandler() {
  }

  public boolean supportsLimit() {
    return true;
  }

  public boolean supportsLimitOffset() {
    return true;
  }

  public String processSql(String sql, RowSelection selection) {
    return LimitHelper.hasFirstRow(selection) ? sql + " limit ? offset ?" : sql + " limit ?";
  }

  public int bindLimitParametersAtStartOfQuery(RowSelection selection, PreparedStatement statement, int index) throws SQLException {
    return 0;
  }

  public int bindLimitParametersAtEndOfQuery(RowSelection selection, PreparedStatement statement, int index) throws SQLException {
    return 0;
  }

  public void setMaxRows(RowSelection selection, PreparedStatement statement) throws SQLException {
  }
}
