package Service;

import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;

/**
 * loading service
 * */
public class LoadService extends BaseService {
  public LoadService() {
    exchangeType = ExchangeTypes.LOAD;
  }

  Response load(Request request) {
    try {

      return null;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
