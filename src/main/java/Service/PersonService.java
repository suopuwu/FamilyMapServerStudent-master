package Service;

import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Authtoken;
import Model.Person;

import java.util.Objects;

/**
 * person getting service
 * */
public class PersonService extends BaseService {
  public PersonService() {
    exchangeType = ExchangeTypes.PERSON;
  }

  //todo make all that require auth tokens, require auth tokens
  public Response getPerson(Request request) {
    try {
      if(!validateAuthtoken(request.Authorization)) {
        throw new Exception("Non valid auth token");
      }
      Authtoken token = (Authtoken) authtokenDao.getRecord(request.Authorization);
      Person person = personDao.getPerson(request.personID);
      if(!Objects.equals(token.username, person.associatedUsername)) {
        throw new Exception("Requested person not associated with current user");
      }
      Response response = new Response(ExchangeTypes.PERSON);
      response.success = true;
      response.associatedUsername = token.username;
      response.personID = person.personID;
      response.firstName = person.firstName;
      response.lastName = person.lastName;
      response.gender = person.gender;
      response.fatherID = person.fatherID;
      response.motherID = person.motherID;
      response.spouseID = person.spouseID;
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
