package DataAccess;

import Model.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Person;

public class PersonDao extends Dao {
  public PersonDao() {
    tableName = "People";
    idColumn = "personID";
  }

  @Override
  protected Model parseResultSet(ResultSet result) throws SQLException {
    Person person = new Person();
    person.personID = result.getString("personID");
    person.associatedUsername = result.getString("associatedUsername");
    person.firstName = result.getString("firstName");
    person.lastName = result.getString("lastName");
    person.gender = result.getString("gender");
    person.fatherID = result.getString("fatherID");
    person.motherID = result.getString("motherID");
    person.spouseID = result.getString("spouseID");
    return person;
  }

  public Person getPerson(String personID) throws SQLException, DataAccessException {
    return (Person) getRecord(personID);
  }
}
