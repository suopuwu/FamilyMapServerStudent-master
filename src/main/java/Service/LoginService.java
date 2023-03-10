package Service;


import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;

/**
 * login service
 * */
public class LoginService extends BaseService {
  public LoginService() {
    exchangeType = ExchangeTypes.LOGIN;
  }

  Response login(Request request) {
    try {

      return null;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
