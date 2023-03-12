package DataAccess;

import Model.Model;
import Model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {
  public UserDao () {
     tableName = "Users";
     idColumn = "username";
  }

  @Override
  protected Model parseResultSet(ResultSet result) throws SQLException {
      User user = new User();
      user.username = result.getString("username");
      user.password = result.getString("password");
      user.email = result.getString("email");
      user.firstName = result.getString("firstName");
      user.lastName = result.getString("lastName");
      user.gender = result.getString("gender");
      user.personID = result.getString("personID");
      return user;
  }

  public User getUser(String username) throws SQLException, DataAccessException {
    return (User) getRecord(username);
  }
}
