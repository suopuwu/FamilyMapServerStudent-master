package Handlers;

import Exchange.Exchange;
import Exchange.ExchangeTypes;
import Exchange.Request;
import Service.LoadService;

import java.io.IOException;

public class LoadHandler extends BaseHandler {//todo make sure all handlers that require auth have it
  @Override
  protected void handlePost() throws IOException {
    LoadService loadService = new LoadService();
    request = deserializeToRequest(exchange);
    respond(loadService.load(request));
  }
}
