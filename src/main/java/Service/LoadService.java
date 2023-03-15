package Service;

import DataAccess.*;
import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Model;

import java.sql.SQLException;

/**
 * loading service
 * */
public class LoadService extends BaseService {
  public LoadService() {
    exchangeType = ExchangeTypes.LOAD;
  }

  private int addModels(Model[] models, Dao dao) throws SQLException, DataAccessException, IllegalAccessException {
    if(models == null) {
      return 0;
    }
    int counter = 0;
    for(Model model : models) {
      dao.addRecord(model);
      counter++;
    }
    return counter;
  }

  public Response load(Request request) {
    try {
      ClearService clearService = new ClearService();
      clearService.clear();
      int events = addModels(request.events, new EventDao());
      int persons = addModels(request.persons, new PersonDao());
      int users = addModels(request.users, new UserDao());

      Response response = new Response(ExchangeTypes.LOAD);
      response.success = true;
      response.message = "Successfully added " + users + " users, " + persons + " persons, and " + events + " events to the database.";
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
