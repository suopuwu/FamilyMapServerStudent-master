package Service;

import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Person;

/**
 * person getting service
 * */
public class PersonService extends BaseService {
  public PersonService() {
    exchangeType = ExchangeTypes.PERSON;
  }

  public Response getPerson(Request request) {
    try {

      return null;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
