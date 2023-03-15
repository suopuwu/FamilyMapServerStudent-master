package Server;

import Handlers.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

  private static void runServer() throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);
    server.createContext("/", new FileHandler());
    server.createContext("/clear", new ClearHandler());
    server.createContext("/event", new EventAndRelatedEventsHandler());
    server.createContext("/person", new FamilyAndPersonHandler());
    server.createContext("/fill", new FillHandler());
    server.createContext("/load", new LoadHandler());
    server.createContext("/user", new LoginAndRegisterHandler());

    server.start();
  }


  public static void main(String[] args){
    try {
      runServer();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
