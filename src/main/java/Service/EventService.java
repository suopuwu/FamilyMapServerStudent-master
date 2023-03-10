package Service;

import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;

/**
 * event getting service
 * */
public class EventService extends BaseService {
  public EventService() {
    exchangeType = ExchangeTypes.EVENT;
  }

  Response getEvent(Request request) {
    try {

      return null;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
