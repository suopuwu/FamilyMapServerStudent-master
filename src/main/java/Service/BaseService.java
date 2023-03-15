package Service;

import DataAccess.*;
import Exchange.ExchangeTypes;
import Exchange.Response;
import Model.Authtoken;

import java.sql.SQLException;

public abstract class BaseService {
  protected ExchangeTypes exchangeType;

  protected static final UserDao userDao = new UserDao();
  protected static final PersonDao personDao = new PersonDao();
  protected static final AuthtokenDao authtokenDao = new AuthtokenDao();
  protected static final EventDao eventDao = new EventDao();

  protected boolean validateAuthtoken(String token) throws SQLException {
    try {
      //if it's found, it is a valid authtoken.
      Authtoken retrievedToken = (Authtoken) authtokenDao.getRecord(token);
      return true;
    } catch (DataAccessException e) {
      return false;
    }
  }

  Response failure(String message) {
    Response response = new Response(exchangeType);
    response.success = false;
    response.message = "Error: " + message;
    return response;
  }
}
