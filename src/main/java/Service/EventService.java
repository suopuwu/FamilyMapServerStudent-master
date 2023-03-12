package Service;

import DataAccess.EventDao;
import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Event;

/**
 * event getting service
 * */
public class EventService extends BaseService {
  public EventService() {
    exchangeType = ExchangeTypes.EVENT;
  }

  public Response getEvent(Request request) {
    try {
      EventDao eventDao = new EventDao();
      Event retrievedEvent = (Event) eventDao.getRecord(request.eventID);
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
