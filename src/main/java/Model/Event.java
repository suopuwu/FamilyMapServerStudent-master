package Model;

public class Event extends Model {
  public String eventID, associatedUsername, personID, country, city, eventType;
  public float latitude, longitude;
  public int year;

  public Event(String eventID, String associatedUsername, String personID, String country, String city, String eventType, float latitude, float longitude, int year) {
    this.eventID = eventID;
    this.associatedUsername = associatedUsername;
    this.personID = personID;
    this.country = country;
    this.city = city;
    this.eventType = eventType;
    this.latitude = latitude;
    this.longitude = longitude;
    this.year = year;
  }

  public Event() {}
}
