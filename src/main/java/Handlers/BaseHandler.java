package Handlers;

import DataAccess.AuthtokenDao;
import DataAccess.DataAccessException;
import Exchange.*;
import Model.Authtoken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public abstract class BaseHandler implements HttpHandler {
  protected HttpExchange exchange;
  protected AuthtokenDao authtokenDao = new AuthtokenDao();
  protected Authtoken authToken;
  protected Request request;
  protected void badResponse() throws IOException {
    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
  }
  protected void notFound() throws IOException {
    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
  }
  protected void goodResponse() throws IOException {
    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
  }
  protected void handleGet() throws IOException{
    badResponse();
  }
  protected void handlePost() throws IOException {
    badResponse();
  }
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    request = new Request();
    this.exchange = exchange;
    try {
      if (exchange.getRequestMethod().equalsIgnoreCase("get")) {
        handleGet();
      } else if (exchange.getRequestMethod().equalsIgnoreCase("post")) {
        handlePost();
      } else {
        badResponse();
      }
    } catch (IOException e) {
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
      System.out.println(e.getMessage());
    } finally {
      exchange.getResponseBody().close();
    }
  }

  public String[] splitURI() {
    return exchange.getRequestURI().getPath().substring(1).split("/");
  }

  public String streamToString(InputStream stream) throws IOException {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    for(int length; (length = stream.read(buffer)) != -1;) {
      result.write(buffer, 0, length);
    }
    return result.toString(StandardCharsets.UTF_8);
  }

  protected void writeResponseBody(String content) throws IOException {
    OutputStream outputStream = exchange.getResponseBody();
    try(OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream)) {
      streamWriter.write(content);
    }
  }

  protected void respond(Response response) throws IOException {
    if(response.success) {
      goodResponse();
    } else {
      badResponse();
    }
    writeResponseBody(response.serialize());
  }

  protected boolean validAuthToken() throws IOException {
    if(!exchange.getRequestHeaders().containsKey("Authorization")) {
      Response response = new Response();
      response.success = false;
      response.message = "Error: no auth token";
      respond(response);
      return false;
    }
    String token = exchange.getRequestHeaders().getFirst("Authorization");
    try {
      authToken = (Authtoken) authtokenDao.getRecord(token);
      request.Authorization = authToken.authToken;
      return true;
    } catch (Exception e) {
      Response response = new Response();
      response.success = false;
      response.message = "Error: invalid / no auth token";
      respond(response);
      return false;
    }

  }

  protected Request deserializeToRequest(HttpExchange exchange) throws IOException {
    return (Request) Exchange.deserialize(streamToString(exchange.getRequestBody()), ExchangeTypes.REQUEST);
  }
}
