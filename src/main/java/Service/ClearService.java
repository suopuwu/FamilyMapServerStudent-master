package Service;

import DataAccess.*;
import Exchange.ExchangeTypes;
import Exchange.Response;

import java.sql.SQLException;

/**
 * Service that deletes all data from the database
 * */
public class ClearService extends BaseService {
  public ClearService() {
    exchangeType = ExchangeTypes.CLEAR;
  }


  public Response clear() {
    try {
      Dao[] allDaos = {userDao, eventDao, authtokenDao, personDao};
      for(Dao dao : allDaos) {
        dao.clear();
      }
      Response response = new Response(exchangeType);
      response.message = "Clear succeeded.";
      response.success = true;
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
