package Service;

import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Authtoken;
import Model.Event;

/**
 * Gets all events related to the family members of the user
 * */
public class RelatedEventsService extends BaseService {
  public RelatedEventsService() {
    exchangeType = ExchangeTypes.RELATED_EVENTS;
  }

  //request has authorization
  public Response getEvents(Request request) {
    try {
      if(!validateAuthtoken(request.Authorization)) {
        throw new Exception("Invalid auth token");
      }
      Authtoken token = (Authtoken) authtokenDao.getRecord(request.Authorization);//todo make this more efficient.
      Event[] events = eventDao.getMultipleRecordsByColumn("associatedUsername", token.username).toArray(new Event[0]);

      Response response = new Response(ExchangeTypes.RELATED_EVENTS);
      response.data = events;
      response.success = true;
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
