package Service;

import Exchange.ExchangeTypes;
import Exchange.Response;

/**
 * Gets all events related to the family members of the user
 * */
public class RelatedEventsService extends BaseService {
  public RelatedEventsService() {
    exchangeType = ExchangeTypes.RELATED_EVENTS;
  }

  public Response getEvents() {
    try {

      return null;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
