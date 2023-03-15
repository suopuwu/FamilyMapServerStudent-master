package Service;

import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Authtoken;
import Model.User;

/**
 * registration service.
 * */
public class RegisterService extends BaseService {
  public RegisterService() {
    exchangeType = ExchangeTypes.REGISTER;
  }

  //request has username, password, email, firstname, lastname, gender
  //response has authtoken, username, personID, success
  public Response register(Request request) {
    try {
      String personID = UUIDService.getUUID();
      userDao.addRecord(new User(request.username, request.password, request.email, request.firstName, request.lastName, request.gender, personID));

      Authtoken token = new Authtoken(UUIDService.getUUID(), request.username);
      authtokenDao.addRecord(token);

      FillService fillService = new FillService();
      Request fillRequest = new Request(ExchangeTypes.FILL);
      fillRequest.username = request.username;
      fillService.fill(fillRequest);

      Response response = new Response(ExchangeTypes.REGISTER);
      response.success = true;
      response.authtoken = token.authToken;
      response.username = request.username;
      response.personID = personID;
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
