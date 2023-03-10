package DataAccess;

import Model.Model;
import Model.Authtoken;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthtokenDao extends Dao {
  public AuthtokenDao() {
    tableName = "Authtokens";
    idColumn = "username";
  }

  @Override
  protected Model parseResultSet(ResultSet result) throws SQLException {
    return new Authtoken(result.getString("authtoken"), result.getString("username"));
  }
}
