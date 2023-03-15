package Handlers;

import Service.EventService;
import Service.FamilyService;
import Service.PersonService;
import Service.RelatedEventsService;

import java.io.IOException;

public class FamilyAndPersonHandler extends BaseHandler {
  @Override
  protected void handleGet() throws IOException {
    if(!validAuthToken()) {
      return;
    }
    String[] uriParts = splitURI();
    if(uriParts.length == 1) {
      FamilyService familyService = new FamilyService();
      respond(familyService.familyMembers(authToken));
    } else if (uriParts.length == 2) {
      PersonService personService = new PersonService();
      request.personID = uriParts[1];
      respond(personService.getPerson(request));
    } else {
      badResponse();
    }
  }
}
