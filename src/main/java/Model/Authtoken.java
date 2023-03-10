package Model;
/**
 * Model for auth tokens.
 * */
public class Authtoken extends Model {
  /**
   * authtoken : Unique auth token <br>
   * username : username associated with the auth token.
   * */
  public final String authToken, username;

  /**
   * @param authToken the authentication token
   * @param username the user who was authenticated
   * */
  public Authtoken(String authToken, String username) {
    this.authToken = authToken;
    this.username = username;
  }
}
