package Service;

import DataAccess.DataAccessException;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Exchange.ExchangeTypes;
import Exchange.Response;
import Model.Authtoken;
import Model.Person;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * family member getting service
 * */
public class FamilyService extends BaseService {
  public FamilyService() {
    exchangeType = ExchangeTypes.FAMILY;
  }
  private PersonDao personDao = new PersonDao();
//  private UserDao userDao = new UserDao();
//  private HashSet<Person> people;
//  private void branch(Person person) throws SQLException, DataAccessException {
//    if(person == null || !people.add(person)) {
//      return;
//    }
//    //adds spouse and parents
//    branch(personDao.getPerson(person.fatherID));
//    branch(personDao.getPerson(person.motherID));
//    branch(personDao.getPerson(person.spouseID));
//    processConnections(person.personID, "fatherID");
//    processConnections(person.personID, "motherID");
//    processConnections(person.personID, "spouseID");
//  }
//  private void processConnections(String personID, String column) throws SQLException, DataAccessException {
//    Person[] connectedPeople = personDao.getMultipleRecordsByColumn(column, personID).toArray(new Person[0]);
//    for(Person person : connectedPeople) {
//      branch(person);
//    }
//  }
// just read the documentation and all family members of a user have the same associated username
  public Response familyMembers(Authtoken authtoken) {
    try {
//      User user = userDao.getUser(authtoken.username);
//      Person person = personDao.getPerson(user.personID);
//      branch(person);
//      Response response = new Response(ExchangeTypes.FAMILY);
//      response.data = people.toArray(new Person[0]);
//      response.success = true;
      Response response = new Response(ExchangeTypes.FAMILY);
      response.data = personDao.getMultipleRecordsByColumn("associatedUsername", authtoken.username).toArray(new Person[0]);
      response.success = true;
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }  }
}
