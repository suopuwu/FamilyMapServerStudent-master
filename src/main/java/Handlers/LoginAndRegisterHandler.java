package Handlers;

import Service.EventService;
import Service.LoginService;
import Service.RegisterService;
import Service.RelatedEventsService;

import java.io.IOException;

public class LoginAndRegisterHandler extends BaseHandler {
  @Override
  protected void handlePost() throws IOException {
    String[] uriParts = splitURI();
    if(uriParts.length == 2) {
      request = deserializeToRequest(exchange);
      switch (uriParts[1]) {
        case "login":
          LoginService loginService = new LoginService();
          respond(loginService.login(request));
          break;
        case "register":
          RegisterService registerService = new RegisterService();
          respond(registerService.register(request));
          break;
      }
    } else {
      badResponse();
    }
  }
}
