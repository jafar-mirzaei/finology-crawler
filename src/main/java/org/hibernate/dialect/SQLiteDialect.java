package org.hibernate.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.SQLiteSQLExceptionConversionDelegate;
import org.hibernate.dialect.function.AbstractAnsiTrimEmulationFunction;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.exception.spi.SQLExceptionConversionDelegate;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author Jafar Mirzaei (mirzaie@caspco.ir)
 * @version 1.0 2019.0925
 * @since 1.0
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


public class SQLiteDialect extends Dialect {
  public SQLiteDialect() {
    this.registerColumnType(-7, "boolean");
    this.registerColumnType(-6, "tinyint");
    this.registerColumnType(5, "smallint");
    this.registerColumnType(4, "integer");
    this.registerColumnType(-5, "bigint");
    this.registerColumnType(6, "float");
    this.registerColumnType(7, "real");
    this.registerColumnType(8, "double");
    this.registerColumnType(2, "numeric($p, $s)");
    this.registerColumnType(3, "decimal");
    this.registerColumnType(1, "char");
    this.registerColumnType(12, "varchar($l)");
    this.registerColumnType(-1, "longvarchar");
    this.registerColumnType(91, "date");
    this.registerColumnType(92, "time");
    this.registerColumnType(93, "datetime");
    this.registerColumnType(-2, "blob");
    this.registerColumnType(-3, "blob");
    this.registerColumnType(-4, "blob");
    this.registerColumnType(2004, "blob");
    this.registerColumnType(2005, "clob");
    this.registerColumnType(16, "boolean");
    this.registerFunction("concat", new VarArgsSQLFunction(StandardBasicTypes.STRING, "", "||", ""));
    this.registerFunction("mod", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 % ?2"));
    this.registerFunction("quote", new StandardSQLFunction("quote", StandardBasicTypes.STRING));
    this.registerFunction("random", new NoArgSQLFunction("random", StandardBasicTypes.INTEGER));
    this.registerFunction("round", new StandardSQLFunction("round"));
    this.registerFunction("substr", new StandardSQLFunction("substr", StandardBasicTypes.STRING));
    this.registerFunction("trim", new AbstractAnsiTrimEmulationFunction() {
      protected SQLFunction resolveBothSpaceTrimFunction() {
        return new SQLFunctionTemplate(StandardBasicTypes.STRING, "trim(?1)");
      }

      protected SQLFunction resolveBothSpaceTrimFromFunction() {
        return new SQLFunctionTemplate(StandardBasicTypes.STRING, "trim(?2)");
      }

      protected SQLFunction resolveLeadingSpaceTrimFunction() {
        return new SQLFunctionTemplate(StandardBasicTypes.STRING, "ltrim(?1)");
      }

      protected SQLFunction resolveTrailingSpaceTrimFunction() {
        return new SQLFunctionTemplate(StandardBasicTypes.STRING, "rtrim(?1)");
      }

      protected SQLFunction resolveBothTrimFunction() {
        return new SQLFunctionTemplate(StandardBasicTypes.STRING, "trim(?1, ?2)");
      }

      protected SQLFunction resolveLeadingTrimFunction() {
        return new SQLFunctionTemplate(StandardBasicTypes.STRING, "ltrim(?1, ?2)");
      }

      protected SQLFunction resolveTrailingTrimFunction() {
        return new SQLFunctionTemplate(StandardBasicTypes.STRING, "rtrim(?1, ?2)");
      }
    });
  }

  public boolean supportsCurrentTimestampSelection() {
    return true;
  }

  public boolean isCurrentTimestampSelectStringCallable() {
    return false;
  }

  public String getCurrentTimestampSelectString() {
    return "select current_timestamp";
  }

  public SQLExceptionConversionDelegate buildSQLExceptionConversionDelegate() {
    return new SQLiteSQLExceptionConversionDelegate();
  }

  public boolean supportsUnionAll() {
    return true;
  }

  public boolean hasAlterTable() {
    return false;
  }

  public boolean dropConstraints() {
    return false;
  }

  public String getForUpdateString() {
    return "";
  }

  public boolean supportsOuterJoinForUpdate() {
    return false;
  }

  public String getDropForeignKeyString() {
    throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
  }

  public String getAddForeignKeyConstraintString(
      String constraintName,
      String[] foreignKey,
      String referencedTable,
      String[] primaryKey,
      boolean referencesPrimaryKey) {
    throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
  }

  public String getAddPrimaryKeyConstraintString(String constraintName) {
    throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
  }

  public boolean supportsIfExistsBeforeTableName() {
    return true;
  }

  public boolean supportsTupleDistinctCounts() {
    return false;
  }

  public String getSelectGUIDString() {
    return "select hex(randomblob(16))";
  }
}

