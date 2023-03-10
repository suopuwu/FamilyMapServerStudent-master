package Service;

import Exchange.ExchangeTypes;
import Exchange.Response;

public abstract class BaseService {
  protected ExchangeTypes exchangeType;

  Response failure(String message) {
    Response response = new Response(exchangeType);
    response.success = false;
    response.message = "Error: " + message;
    return response;
  }
}
