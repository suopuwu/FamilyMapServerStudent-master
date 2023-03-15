package Service;

import DataAccess.EventDao;
import Exchange.Exchange;
import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Authtoken;
import Model.Event;

import java.util.Objects;

/**
 * event getting service
 * */
public class EventService extends BaseService {
  public EventService() {
    exchangeType = ExchangeTypes.EVENT;
  }

  public Response getEvent(Request request) {
    try {
      Authtoken token = (Authtoken) authtokenDao.getRecord(request.Authorization);

      Event retrievedEvent = (Event) eventDao.getRecord(request.eventID);
      if(!Objects.equals(token.username, retrievedEvent.associatedUsername)) {
        throw new Exception("The current user does not have access to this event");
      }

      Response response = new Response(exchangeType);
      response.associatedUsername = retrievedEvent.associatedUsername;
      response.eventID = retrievedEvent.eventID;
      response.personID = retrievedEvent.personID;
      response.latitude = retrievedEvent.latitude;
      response.longitude = retrievedEvent.longitude;
      response.country = retrievedEvent.country;
      response.city = retrievedEvent.city;
      response.eventType = retrievedEvent.eventType;
      response.year = retrievedEvent.year;
      response.success = true;
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
