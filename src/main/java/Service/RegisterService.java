package Service;

import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;

/**
 * registration service.
 * */
public class RegisterService extends BaseService {
  public RegisterService() {
    exchangeType = ExchangeTypes.REGISTER;
  }

  Response register(Request request) {
    try {

      return null;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
