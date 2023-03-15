package DataAccess;

import Exchange.Exchange;
import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.*;
import Service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class ServiceTests {
  UserDao testUserDao = new UserDao();
  PersonDao testPersonDao = new PersonDao();
  AuthtokenDao testAuthtokenDao = new AuthtokenDao();
  EventDao testEventDao = new EventDao();
  User testUser = new User("username", "password", "email@email.com", "first", "last", "f", "personID");
  Person testPerson = new Person("personID", "username", "first", "last", "m", "fatherID", "motherID", "spouseID");
  Authtoken testAuthtoken = new Authtoken("token", "username");
  Event testEvent = new Event("eventID", "username", "personID", "country", "city", "event type", 1000.0f, 1000.0f, 0);

//  @BeforeAll
//  void Setup() {
//
//  }
  @BeforeEach
  void setup() throws SQLException, DataAccessException {
    testUserDao.clear();
    testPersonDao.clear();
    testEventDao.clear();
    testAuthtokenDao.clear();
  }

  @Test
  void addTestData() {
    try{
    testUserDao.addRecord(testUser);
    testPersonDao.addRecord(testPerson);
    testEventDao.addRecord(testEvent);
    testAuthtokenDao.addRecord(testAuthtoken);
    } catch (Exception e) {

    }
  }

  //just check the database to verify
  @Test
  void clearTest() {
    addTestData();
    ClearService clear = new ClearService();
    clear.clear();
  }

  @Test
  void eventTest() {
    addTestData();
    EventService eventService = new EventService();
    Request request = new Request(ExchangeTypes.EVENT);
    request.eventID = "eventID";
    Response response = eventService.getEvent(request);
    //not an in depth test, just checks country.
    Assertions.assertTrue(Objects.equals(response.country, testEvent.country));
  }

  @Test
  void familyTest() throws IllegalAccessException, SQLException, DataAccessException {
    addTestData();
    testPersonDao.addRecord(new Person("second person", "username", "first", "last", "f", "fatherID", "motherID", "spouseID"));
    testPersonDao.addRecord(new Person("third person", "non associated username", "first", "last", "f", "fatherID", "motherID", "spouseID"));
    FamilyService familyService = new FamilyService();
    Request request = new Request(ExchangeTypes.FAMILY);
    request.eventID = "eventID";
    Model[] data = familyService.familyMembers(new Authtoken("not important", "username")).data;
    Assertions.assertTrue(data[0].nonNullEquals(testPerson));
    Assertions.assertEquals(2, data.length);
  }

  @Test
  void uuidTest() {
    UUIDService uuidService = new UUIDService();
    System.out.println(UUIDService.getUUID());
    System.out.println(UUIDService.getUUID());
  }

  @Test
  void randomLocationTest() {
    RandomLocationService location = new RandomLocationService();
    System.out.println(location.getRandomLocation().city);
    System.out.println(location.getRandomLocation().city);
    System.out.println(location.getRandomLocation().city);
  }

  @Test
  void fillTest() throws IllegalAccessException, SQLException, DataAccessException {
    addTestData();
    testPersonDao.clear();
    FillService fillService = new FillService();
    Request request = new Request(ExchangeTypes.FILL);
    request.username = testUser.username;
    Response response = fillService.fill(request);
    Assertions.assertTrue(response.success);//todo add more lines like this in other tests
    Assertions.assertEquals(91, testEventDao.getMultipleRecordsByColumn("associatedUsername", testUser.username).size());
    Assertions.assertEquals(31, testPersonDao.getMultipleRecordsByColumn("associatedUsername", testUser.username).size());
    System.out.println(response.message);
  }

  @Test
  void loadTest() {
    addTestData();
    LoadService loadService = new LoadService();
    Request request = new Request(ExchangeTypes.LOAD);
    request.users = new User[]{testUser, new User("new user", "pass", "e", "fir", "last", "f", "lskdjflk")};
    request.persons = new Person[]{testPerson};
    Response response = loadService.load(request);
    System.out.println(response.message);
    Assertions.assertTrue(response.success);
  }
  @Test
  void loginTest() {
    addTestData();
    LoginService loginService = new LoginService();
    Request request = new Request(ExchangeTypes.LOGIN);
    request.username = "username";
    request.password = "password";
    Response response = loginService.login(request);
    Assertions.assertTrue(response.success);
  }

  @Test
  void wrongPasswordTest() {
    addTestData();
    LoginService loginService = new LoginService();
    Request request = new Request(ExchangeTypes.LOGIN);
    request.username = "username";
    request.password = "wrong password";
    Response response = loginService.login(request);
    System.out.println(response.message);
    Assertions.assertFalse(response.success);
  }

  @Test
  void personTest() {
    addTestData();
    PersonService personService = new PersonService();
    Request request = new Request(ExchangeTypes.PERSON);
    request.Authorization = "token";
    request.personID = "personID";
    Response response = personService.getPerson(request);
    System.out.println(response.message);
    Assertions.assertTrue(response.success);
  }

  @Test
  void registerTest() {
    addTestData();
    RegisterService registerService = new RegisterService();
    Request request = new Request(ExchangeTypes.REGISTER);
    request.username = "username two";
    request.password = "password";
    request.email = "email";
    request.firstName = "first";
    request.lastName = "last";
    request.gender = "f";
    Response response = registerService.register(request);
    System.out.println(response.message);
    Assertions.assertTrue(response.success);
  }

  @Test
  void registerDuplicateTest() {
    addTestData();
    RegisterService registerService = new RegisterService();
    Request request = new Request(ExchangeTypes.REGISTER);
    request.username = "username";
    request.password = "password";
    request.email = "email";
    request.firstName = "first";
    request.lastName = "last";
    request.gender = "f";
    Response response = registerService.register(request);
    System.out.println(response.message);
    Assertions.assertFalse(response.success);
  }

  @Test
  void relatedEventsTest() throws SQLException, DataAccessException, IllegalAccessException {
    addTestData();
    testEventDao.addRecord(new Event("first extra", "username", "lsdkjf", "country", "city", "type", 0.0f, 0.0f, 1));
    testEventDao.addRecord(new Event("not associated", "not associated", "lsdkjf", "country", "city", "type", 0.0f, 0.0f, 1));
    RelatedEventsService relatedEventsService = new RelatedEventsService();
    Request request = new Request(ExchangeTypes.RELATED_EVENTS);
    request.Authorization = "token";
    Response response = relatedEventsService.getEvents(request);
    System.out.println(response.message);
    Assertions.assertEquals(2, response.data.length);
  }

  @Test
  void relatedEventsBadToken() throws SQLException, DataAccessException, IllegalAccessException {
    addTestData();
    testEventDao.addRecord(new Event("first extra", "username", "lsdkjf", "country", "city", "type", 0.0f, 0.0f, 1));
    testEventDao.addRecord(new Event("not associated", "not associated", "lsdkjf", "country", "city", "type", 0.0f, 0.0f, 1));
    RelatedEventsService relatedEventsService = new RelatedEventsService();
    Request request = new Request(ExchangeTypes.RELATED_EVENTS);
    request.Authorization = "bad token";
    Response response = relatedEventsService.getEvents(request);
    System.out.println(response.message);
    Assertions.assertFalse(response.success);
  }

  @Test
  void JSONTest() throws SQLException, DataAccessException, IllegalAccessException {
    addTestData();
    testPersonDao.clear();
    FillService fillService = new FillService();
    Request request = new Request(ExchangeTypes.FILL);
    request.username = testUser.username;
    Response response = fillService.fill(request);
    String json = response.serialize();
    Response deserialized = (Response) Exchange.deserialize(json, ExchangeTypes.RESPONSE);
    Assertions.assertEquals(deserialized.message, response.message);
  }
}
