package DataAccess;

import Model.*;
import Service.ClearService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ServiceTests {
  UserDao testUserDao = new UserDao();
  PersonDao testPersonDao = new PersonDao();
  AuthtokenDao testAuthtokenDao = new AuthtokenDao();
  EventDao testEventDao = new EventDao();
  User testUser = new User("username", "password", "email@email.com", "first", "last", "f", "personID");
  Person testPerson = new Person("personID", "username", "first", "last", "m", "fatherID", "motherID", "spouseID");
  Authtoken testAuthtoken = new Authtoken("token", "username");
  Event testEvent = new Event("eventID", "username", "personID", "country", "city", "event type", 0.0f, 0.0f, 0);

//  @BeforeAll
//  void Setup() {
//
//  }
  @BeforeEach
  void setup() throws SQLException, DataAccessException, IllegalAccessException {
    testUserDao.clear();
    testPersonDao.clear();
    testEventDao.clear();
    testAuthtokenDao.clear();
  }

  @Test
  void addTestData() throws SQLException, DataAccessException, IllegalAccessException {
    testUserDao.addRecord(testUser);
    testPersonDao.addRecord(testPerson);
    testEventDao.addRecord(testEvent);
    testAuthtokenDao.addRecord(testAuthtoken);
  }

  //just check the database to verify
  @Test
  void clearTest() throws SQLException, DataAccessException, IllegalAccessException {
    addTestData();
    ClearService clear = new ClearService();
    clear.clear();
  }
}
