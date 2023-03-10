package DataAccess;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import DataAccess.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
  private Database db;
  private Connection conn;
  @Test
  @BeforeEach
  @DisplayName("Make a database")
  public void makeDatabase() throws DataAccessException {
    db = new Database();
    db.openConnection();
    conn = db.getConnection();

  }

  @Test
  public void testQuery() throws SQLException {
    Statement query = conn.createStatement();
    Boolean result = query.execute("INSERT INTO authtokens VALUES (\"test\", \"Username\");");
    System.out.println(result);
  }

  @AfterEach
  public void tearDown() {
    // Here we close the connection to the database file, so it can be opened again later.
    // We will set commit to false because we do not want to save the changes to the database
    // between test cases.
    db.closeConnection(false);
  }
}
