package Model;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * Model for users.
 * */
public class User extends Model {
  public String username, password, email, firstName, lastName, gender, personID;

  public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.personID = personID;
  }

  public User() {}
}
