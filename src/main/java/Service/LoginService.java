package Service;


import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Authtoken;
import Model.User;

import java.util.Objects;

/**
 * login service
 * */
public class LoginService extends BaseService {
  public LoginService() {
    exchangeType = ExchangeTypes.LOGIN;
  }
  //request has username and password
  public Response login(Request request) {
    try {
      User actualUser = userDao.getUser(request.username);
      if(request.username == null) {
        throw new Exception("No username provided");
      }
      if(request.password == null) {
        throw new Exception("No password provided");
      }
      if(!Objects.equals(actualUser.password, request.password)) {
        throw new Exception("Password does not match.");
      }
      Authtoken token = new Authtoken(UUIDService.getUUID(), request.username);
      Response response = new Response(ExchangeTypes.LOGIN);
      authtokenDao.addRecord(token);
      response.success = true;
      response.authtoken= token.authToken;
      response.personID = userDao.getUser(request.username).personID;
      response.username = request.username;
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
