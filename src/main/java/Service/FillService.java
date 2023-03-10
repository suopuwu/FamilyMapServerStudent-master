package Service;

import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;

/**
 * data generating service
 * */
public class FillService extends BaseService {
  public FillService() {
    exchangeType = ExchangeTypes.FILL;
  }

  Response fill(Request request) {
    try {

      return null;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
