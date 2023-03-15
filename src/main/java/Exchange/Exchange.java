package Exchange;

import com.google.gson.Gson;

public abstract class Exchange {
  private static final Gson gson = new Gson();
  public String serialize() {
    return gson.toJson(this);
  }

  //returns a new object
  public static Exchange deserialize(String json, ExchangeTypes type) {
    return switch (type) {
      case RESPONSE -> gson.fromJson(json, Response.class);
      case REQUEST -> gson.fromJson(json, Request.class);
      default -> null;
    };
  }
}
