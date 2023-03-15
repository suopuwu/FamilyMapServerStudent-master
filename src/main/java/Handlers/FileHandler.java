package Handlers;

import java.io.*;
import java.net.HttpURLConnection;

public class FileHandler extends BaseHandler {
  private static final String basePath = System.getProperty("user.home") +  "/Documents/GitHub/FamilyMapServerStudent-master/web";
  private static final String notFoundPath = "/HTML/404.html";

  @Override
  protected void handleGet() throws IOException {
    String path = exchange.getRequestURI().getPath();
    path = path.equals("/") ? "/index.html" : path;

    File file = new File(basePath + path);
    //if not found
    if (!file.isFile()) {
      file = new File(basePath + notFoundPath);
      notFound();
    } else {
      goodResponse();
    }

    String fileContent = fileToString(file);
    writeResponseBody(fileContent);
  }

  private String fileToString(File file) throws IOException {
    try(FileInputStream stream = new FileInputStream(file)) {
      return streamToString(stream);
    }
  }
}