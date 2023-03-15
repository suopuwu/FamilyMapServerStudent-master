package Exchange;

import Model.Model;

public class Response extends Exchange {
  public transient ExchangeTypes exchangeType = ExchangeTypes.CLEAR;
  public String message, authtoken, username, personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID, eventID, country, city, eventType;
  public Integer year;
  public Float latitude, longitude;
  public Model[] data;
  public boolean success;

  public Response(ExchangeTypes exchangeType) {
    this.exchangeType = exchangeType;
  }

  public Response() {}
}
