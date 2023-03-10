package Exchange;

import Model.Model;

public class Response {
  public final transient ExchangeTypes exchangeType;
  public String message, authtoken, username, personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID, eventID, country, city, eventType;
  public int year;
  public float latitude, longitude;
  public Model[] data;
  public boolean success;

  public Response(ExchangeTypes exchangeType) {
    this.exchangeType = exchangeType;
  }
}
