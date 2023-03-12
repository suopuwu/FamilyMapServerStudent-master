package DataAccess;

import Model.Person;
import Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTests {
  private UserDao testUserDao;
  private PersonDao testPersonDao;
  private final User exampleUser  = new User("testUsername", "testPassword", "common email", "first", "Last", "m", "ID");;
  private final Person examplePerson = new Person("person id", "example username", "first", "last", "f", "father", "mother", "spouse");
  @BeforeEach
  @DisplayName("Instantiate and clear daos")
  public void makeDatabase() throws DataAccessException, SQLException {
    testUserDao = new UserDao();
    testUserDao.clear();
    testPersonDao = new PersonDao();
    testPersonDao.clear();
  }

  @Test
  @DisplayName("Add user record test")
  void addUser() throws SQLException, DataAccessException, IllegalAccessException {
    assertTrue(testUserDao.addRecord(exampleUser));
  }

  @Test
  @DisplayName("Add duplicate user record test")
  void addDuplicateUser() throws SQLException, DataAccessException, IllegalAccessException {
    Assertions.assertThrows(SQLException.class, ()-> {
      assertTrue(testUserDao.addRecord(exampleUser));
      assertTrue(testUserDao.addRecord(exampleUser));
    });
  }

  @Test
  @DisplayName("Get user")
  void getUser() throws SQLException, DataAccessException, IllegalAccessException {
    testUserDao.addRecord(exampleUser);
    User retrievedUser = (User) testUserDao.getRecord("testUsername");
    Assertions.assertTrue(retrievedUser.nonNullEquals(exampleUser));
  }

  @Test
  @DisplayName("Get nonexistent user")
  void getNonexistentUser() throws SQLException, DataAccessException, IllegalAccessException {
    testUserDao.addRecord(exampleUser);
    Assertions.assertThrows(DataAccessException.class, ()-> {
      User retrievedUser = (User) testUserDao.getRecord("nonexistent user");
    });
  }

  @Test
  @DisplayName("Clear user data test")
  void clearDataTest() throws SQLException, DataAccessException, IllegalAccessException {
    testUserDao.addRecord(exampleUser);
    //returns true if it clears any records, false if it was already empty
    assertTrue(testUserDao.clear());
  }



  @Test
  @DisplayName("Add person record test")
  void addPerson() throws SQLException, DataAccessException, IllegalAccessException {
    assertTrue(testPersonDao.addRecord(examplePerson));
  }

  @Test
  @DisplayName("Add duplicate person")
  void addDuplicatePerson() throws SQLException, DataAccessException, IllegalAccessException {
    Assertions.assertThrows(SQLException.class, ()-> {
      testPersonDao.addRecord(examplePerson);
      testPersonDao.addRecord(examplePerson);
    });
  }

  @Test
  @DisplayName("Get person")
  void getPerson() throws SQLException, DataAccessException, IllegalAccessException {
    testPersonDao.addRecord(examplePerson);
    Person retrievedUser = (Person) testPersonDao.getRecord("person id");
    Assertions.assertTrue(retrievedUser.nonNullEquals(examplePerson));
  }

  @Test
  @DisplayName("Get nonexistent person")
  void getNonexistentPerson() throws SQLException, DataAccessException, IllegalAccessException {
    testPersonDao.addRecord(examplePerson);
    Assertions.assertThrows(DataAccessException.class, ()-> {
      User retrievedUser = (User) testPersonDao.getRecord("nonexistent person");
    });
  }
//  @Test
//  @DisplayName("Edit nonexistent person")
//  void editNonexistentPerson() throws SQLException, DataAccessException, IllegalAccessException {
//    testPersonDao.addRecord(examplePerson);
//    Assertions.assertThrows(DataAccessException.class, ()-> {
//      System.out.println(testPersonDao.editRecord(new Person("person id", "username", "first", "last", "m", "father", "mother", "spouse")));
//    });
//  }

  @Test
  @DisplayName("Clear person data test")
  void clearPeople() throws SQLException, DataAccessException, IllegalAccessException {
    testPersonDao.addRecord(examplePerson);
    //returns true if it clears any records, false if it was already empty
    assertTrue(testPersonDao.clear());
  }
  @Test
  @DisplayName("Add two users, edit one, delete one")
  void editDeleteUserTest() throws SQLException, DataAccessException, IllegalAccessException {
    User exampleUserTwo = new User("usernameTwo", "pass", "common email", "Joseph", "Lu", "m", "person id but two");
    testUserDao.addRecord(exampleUser);
    testUserDao.addRecord(exampleUserTwo);
    //the added records exist, and are the same.
    assertTrue(testUserDao.getRecord("usernameTwo").nonNullEquals(exampleUserTwo));
    assertTrue(testUserDao.getRecord("testUsername").nonNullEquals(exampleUser));
    //records that do not exist are not found.
    assertThrows(DataAccessException.class, ()-> {
      testUserDao.getRecord("nonexistent user");
    });

    //edit one field
    User editingUser = new User();
    editingUser.username = "usernameTwo";
    editingUser.gender = "f";
    testUserDao.editRecord(editingUser);
    User returnedUser = (User) testUserDao.getRecord("usernameTwo");
    assertFalse(returnedUser.nonNullEquals(exampleUserTwo));

    //get all records with email "common email"
    User[] emailsWithCommonEmail = testUserDao.getMultipleRecordsByColumn("email", "common email").toArray(new User[0]);
    for(User user : emailsWithCommonEmail) {
      System.out.println(user.username);
    }
    //delete a record.
    testUserDao.getRecord("testUsername");
    testUserDao.removeRecord("testUsername");
    assertThrows(DataAccessException.class, ()-> {
      testUserDao.getRecord("testUsername");
    });
  }


}
