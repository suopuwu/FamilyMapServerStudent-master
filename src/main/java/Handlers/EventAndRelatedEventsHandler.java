package Handlers;

import Exchange.Request;
import Exchange.Response;
import Service.EventService;
import Service.RelatedEventsService;

import java.io.IOException;

public class EventAndRelatedEventsHandler extends BaseHandler {
  @Override
  protected void handleGet() throws IOException {
    if(!validAuthToken()) {
      return;
    }
    String[] uriParts = splitURI();
    if(uriParts.length == 1) {
      RelatedEventsService relatedEventsService = new RelatedEventsService();
      respond(relatedEventsService.getEvents(request));
    } else if (uriParts.length == 2) {
      EventService eventService = new EventService();
      request.eventID = uriParts[1];
      respond(eventService.getEvent(request));
    } else {
      badResponse();
    }
  }
}
