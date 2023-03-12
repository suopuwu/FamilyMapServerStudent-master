package Model;


public class Location extends Model {
  public String country, city;
  public float latitude, longitude;

  public Location(String country, String city, float latitude, float longitude) {
    this.country = country;
    this.city = city;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Location() {}
}
