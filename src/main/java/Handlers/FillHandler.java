package Handlers;

import Service.EventService;
import Service.FillService;
import Service.RelatedEventsService;

import java.io.IOException;

public class FillHandler extends BaseHandler{
  @Override
  protected void handlePost() throws IOException {
    String[] uriParts = splitURI();
    if(uriParts.length == 2 || uriParts.length == 3) {
      FillService fillService = new FillService();
      request.username = uriParts[1];
      request.generations = uriParts.length == 3 ? Integer.parseInt(uriParts[2]) : 4;
      respond(fillService.fill(request));
    } else {
      badResponse();
    }
  }
}
