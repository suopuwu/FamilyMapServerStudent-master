package DataAccess;

import Model.Model;
import Model.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Accesses database tables
 *  */
public abstract class Dao {
  /**
   * The connection to the database
   * */
  private Connection connection;
  private Statement sqlStatement;
  private static final Database db = new Database();

  //Data specific to individual daos.
  protected String tableName;
  protected String idColumn;

  protected abstract Model parseResultSet(ResultSet result) throws SQLException;

  //end specific data

  private void connect() throws DataAccessException, SQLException {
    db.openConnection();
    connection = db.getConnection();
    sqlStatement = connection.createStatement();
  }

  private void disconnect() {
    db.closeConnection(true);
    connection = null;
    sqlStatement = null;
  }
  /**
   * Adds a record of the model to the database.
   * @param record the data to add.
   * @throws SQLException if there is a database error
   * @return true if successful, false if not.
   * */
  public boolean addRecord(Model record) throws SQLException, DataAccessException, IllegalAccessException {
    connect();
    try {
      StringBuilder sqlString = new StringBuilder("INSERT INTO " + tableName + " (");
      StringBuilder columns = new StringBuilder();
      StringBuilder values = new StringBuilder();

      //Create sql string using fields of the record being added
      for (Field f : record.getClass().getDeclaredFields()) {
        String name = f.getName();
        Object value = f.get(record);

        columns.append(name).append(", ");
        values.append("'").append(value.toString()).append("', ");
      }
      //Remove extra comma, unless the string is empty
      columns.setLength(Math.max(columns.length() - 2, 0));
      values.setLength(Math.max(values.length() - 2, 0));

      //combine columns and values
      sqlString.append(columns).append(") VALUES (").append(values).append(");");
      return sqlStatement.executeUpdate(sqlString.toString()) > 0;
    } finally {//ensure the database is closed, no matter what.
      disconnect();
    }
  }

  /**
   * Gets the single record indicated by value.
   * @param value the value to search for.
   * @return a model containing the data requested.
   * @throws SQLException if there is a database error
   * */
  public Model getRecord(String value) throws SQLException, DataAccessException {
    connect();
    try {
      String sqlString = "SELECT * FROM \"" + tableName + "\" WHERE \"" + idColumn + "\" = \"" + value + "\";";
      ResultSet result = sqlStatement.executeQuery(sqlString);
      if(!result.next()) {
        throw new DataAccessException("No " + idColumn + " " + value + " in table " + tableName);
      }
      return parseResultSet(result);
    } finally {//ensure the database is closed, no matter what.
      disconnect();
    }
  }

  /**
   * Gets all records within the table that match value in column
   * @param column the column to search
   * @param value the value to search for
   * @return a list of records that contain value
   * @throws SQLException if there is a database error
   * */
  public ArrayList<Model> getMultipleRecordsByColumn(String column, String value) throws SQLException, DataAccessException {
    connect();
    try {
      ArrayList<Model> results = new ArrayList<>();
      String sqlString = "SELECT * FROM \"" + tableName + "\" WHERE \"" + column + "\" = \"" + value + "\";";
      ResultSet result = sqlStatement.executeQuery(sqlString);
      if(!result.next()) {
        throw new DataAccessException("No " + idColumn + " " + value + " in table " + tableName);
      }
      results.add(parseResultSet(result));
      while(result.next()) {
        results.add(parseResultSet(result));
      }
      return results;
    } finally {//ensure the database is closed, no matter what.
      disconnect();
    }  }

  /**
   * Deletes the record.
   * @param value the record to delete
   * @throws SQLException if there is a database error
   * */
  public void removeRecord(String value) throws SQLException, DataAccessException {
    connect();
    try {
      String sqlString = "DELETE FROM \"" + tableName + "\" WHERE \"" + idColumn + "\" = \"" + value + "\";";
      int updates = sqlStatement.executeUpdate(sqlString);
      if(updates == 0) {
        throw new DataAccessException("No " + idColumn + " " + value + " in table " + tableName);
      }
    } finally {//ensure the database is closed, no matter what.
      disconnect();
    }
  }

  /**
   * Mutates the record in question to the new non-null values passed in record, if it exists.
   * @param record the record to update
   * @throws SQLException if there is a database error
   * */
  public boolean editRecord(Model record) throws SQLException, DataAccessException, IllegalAccessException {
    connect();
    try {
      StringBuilder sqlString = new StringBuilder("UPDATE " + tableName + " SET ");
      StringBuilder additionalString = new StringBuilder();
      String id=null;
      //Create sql string using fields of the record being added
      for (Field f : record.getClass().getDeclaredFields()) {
        String name = f.getName();
        Object value = f.get(record);
        if(value != null && name != idColumn) {
          String valueSurroundings = (value instanceof String ? "'" : "");
          additionalString.append(name).append(" = ").append(valueSurroundings).append(value).append(valueSurroundings).append(", ");
        }
        if(name.equals(idColumn)) {
          id = value.toString();
        }
      }
      //Remove extra comma, unless the string is empty
      additionalString.setLength(Math.max(additionalString.length() - 2, 0));
      sqlString.append(additionalString).append(" WHERE ").append(idColumn).append(" = '").append(id).append("';");
      //combine columns and values
      return sqlStatement.executeUpdate(sqlString.toString()) > 0;
    } finally {//ensure the database is closed, no matter what.
      disconnect();
    }
  }

  /**
   * Clears all data within the table.
   * @throws SQLException if there is a database error
   * */
  public boolean clear() throws SQLException, DataAccessException {
    connect();
    try {
      String sqlString = "DELETE FROM " + tableName + ";";
      return sqlStatement.executeUpdate(sqlString) > 0;
    } finally {
      disconnect();
    }
  }
}
