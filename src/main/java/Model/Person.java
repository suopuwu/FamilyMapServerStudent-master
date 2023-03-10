package Model;
/**
 * Model for people.
 * */
public class Person extends Model {
  public String personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID;

  public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
    this.personID = personID;
    this.associatedUsername = associatedUsername;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.fatherID = fatherID;
    this.motherID = motherID;
    this.spouseID = spouseID;
  }

  public Person() {}
}
