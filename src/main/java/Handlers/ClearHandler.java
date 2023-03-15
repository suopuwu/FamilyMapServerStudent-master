package Handlers;

import Exchange.Response;
import Service.ClearService;

import java.io.*;

public class ClearHandler extends BaseHandler {
  @Override
  protected void handlePost() throws IOException {
    ClearService clearService = new ClearService();
    Response response = clearService.clear();
    respond(response);
  }
}