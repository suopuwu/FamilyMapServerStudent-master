package Service;

import DataAccess.DataAccessException;
import Exchange.ExchangeTypes;
import Exchange.Request;
import Exchange.Response;
import Model.Event;
import Model.Location;
import Model.Person;
import Model.User;

import java.sql.SQLException;
import java.util.*;

/**
 * data generating service
 * */
public class FillService extends BaseService {
  public FillService() {
    exchangeType = ExchangeTypes.FILL;
  }
  public static final Random random = new Random();
  public static final int DEFAULT_GENERATIONS = 4;

  private static final String[] fNames = {"Torgeson", "Maston", "Lisenby", "Ocheltree", "Cadden", "Garden", "Delcastillo", "Mccawley", "Albertson", "Chiang", "Sydnor", "Mcnulty", "Bowdoin", "Quiroz", "Waechter", "Schaber", "Nalley", "Voris", "Kliebert", "Woosley", "Mandelbaum", "Lindahl", "Freitag", "Van", "Vinyard", "Heidelberg", "Petrosino", "Kissee", "Heckel", "Gutierez", "Compton", "Stehlik", "Curington", "Lockley", "Wilhite", "Meuser", "Marko", "Critchfield", "Kelm", "Favreau", "Ivie", "Demarco", "Ingalls", "Holoman", "Livingston", "Mcdougald", "Darner", "Mcpheeters", "Dooling", "Blo", "Crain", "Caffee", "Cubbage", "Music", "Honea", "Chait", "Sorensen", "Westerlund", "Paxton", "Klocke", "Nicola", "Lazaro", "Seeger", "Northington", "Nibert", "Coachman", "Wishon", "Mccants", "Zieman", "Simien", "Gaudet", "Stringfellow", "Maus", "Byer", "Liao", "Cullinan", "Messmer", "Mcroberts", "Cardiel", "Pink", "Ogorman", "Minott", "Lightfoot", "Hornung", "Amsler", "Kelson", "Mcfarlain", "Rakes", "Wasinger", "Cessna", "Emory", "Mccane", "Fluellen", "Cosgrove", "Laford", "Taggert", "Hudkins", "Anker", "Hardt", "Lemus", "Simington", "Carmouche", "Larusso", "Warnick", "Estell", "Turck", "Jeppesen", "Nigro", "Chirico", "Murchison", "Rule", "Beal", "Broman", "Lugo", "Silverman", "Redmond", "Pera", "Orlowski", "Zemlicka", "Ackerley", "Gies", "Bleich", "Medina", "Housman", "Goggans", "Silvey", "Goguen", "Mcpeek", "Brace", "Hetherington", "Smolen", "Dennis", "Ruark", "Line", "Meiers", "Schroyer", "Clear", "Hellard", "Buckalew", "Tu", "Schill", "Ridge", "Sacco", "Babbitt", "Durrell", "Vitagliano", "Mullan", "Villegas", "Kehr", "Pleasant", "Loss", "Mitten"};

  private static final String[] mNames = {"Kevin", "Zack", "Ken", "Benjamin", "Alonzo", "Sam", "Stanley", "Kermit", "Augustine", "Silas", "Sol", "Franklyn", "Clement", "Ezra", "Lee", "Hal", "Bruce", "Clifford", "Wilbert", "Lonny", "Marco", "Vaughn", "Brandon", "Odell", "Rudolf", "Teodoro", "Rico", "Tyree", "Eliseo", "Brice", "Jacinto", "Hunter", "Errol", "Bernardo", "Hayden", "Duncan", "Darron", "Tyler", "Haywood", "Lazaro", "Charles", "Maynard", "Everette", "Roosevelt", "Allen", "Willie", "Abraham", "Elijah", "Jody", "Stefan", "Bennett", "Rodger", "Xavier", "Osvaldo", "Rigoberto", "Kareem", "Chi", "Mohammed", "Justin", "Al", "Victor", "Spencer", "Irving", "Paris", "Theodore", "Malcom", "Houston", "Nick", "Jerald", "Huey", "Wade", "Paul", "Toby", "Mauricio", "Gino", "Valentin", "Santiago", "Son", "Seeger", "Daniel", "Larry", "Robbie", "Arthur", "Stuart", "Jeffry", "Blaine", "Jerrod", "Lyman", "Carey", "Giuseppe", "Ahmad", "Warner", "Thanh", "Cleveland", "Riley", "Carlo", "Richie", "Carmen", "Freddie", "Roberto", "Tyrell", "Sidney", "Christopher", "Stewart", "Dylan", "Scott", "Reuben", "Junior", "Jarrett", "Levi", "Irwin", "Hollis", "Agustin", "Dave", "Dalton", "Tracey", "Earnest", "Jewell", "Chauncey", "Gaylord", "Francisco", "Jake", "Everett", "Fabian", "Mary", "Ezekiel", "Arden", "Stacy", "Marlon", "Lucien", "Woodrow", "Elvin", "Davis", "Ricardo", "Hilton", "Jasper", "Barton", "Anthony", "Lucio", "Deshawn", "Granville", "Burton", "Lawrence", "Santo"};

  private static final String[] sNames = {"Torgeson", "Maston", "Lisenby", "Ocheltree", "Cadden", "Garden", "Delcastillo", "Mccawley", "Albertson", "Chiang", "Sydnor", "Mcnulty", "Bowdoin", "Quiroz", "Waechter", "Schaber", "Nalley", "Voris", "Kliebert", "Woosley", "Mandelbaum", "Lindahl", "Freitag", "Van", "Vinyard", "Heidelberg", "Petrosino", "Kissee", "Heckel", "Gutierez", "Compton", "Stehlik", "Curington", "Lockley", "Wilhite", "Meuser", "Marko", "Critchfield", "Kelm", "Favreau", "Ivie", "Demarco", "Ingalls", "Holoman", "Livingston", "Mcdougald", "Darner", "Mcpheeters", "Dooling", "Blo", "Crain", "Caffee", "Cubbage", "Music", "Honea", "Chait", "Sorensen", "Westerlund", "Paxton", "Klocke", "Nicola", "Lazaro", "Seeger", "Northington", "Nibert", "Coachman", "Wishon", "Mccants", "Zieman", "Simien", "Gaudet", "Stringfellow", "Maus", "Byer", "Liao", "Cullinan", "Messmer", "Mcroberts", "Cardiel", "Pink", "Ogorman", "Minott", "Lightfoot", "Hornung", "Amsler", "Kelson", "Mcfarlain", "Rakes", "Wasinger", "Cessna", "Emory", "Mccane", "Fluellen", "Cosgrove", "Laford", "Taggert", "Hudkins", "Anker", "Hardt", "Lemus", "Simington", "Carmouche", "Larusso", "Warnick", "Estell", "Turck", "Jeppesen", "Nigro", "Chirico", "Murchison", "Rule", "Beal", "Broman", "Lugo", "Silverman", "Redmond", "Pera", "Orlowski", "Zemlicka", "Ackerley", "Gies", "Bleich", "Medina", "Housman", "Goggans", "Silvey", "Goguen", "Mcpeek", "Brace", "Hetherington", "Smolen", "Dennis", "Ruark", "Line", "Meiers", "Schroyer", "Clear", "Hellard", "Buckalew", "Tu", "Schill", "Ridge", "Sacco", "Babbitt", "Durrell", "Vitagliano", "Mullan", "Villegas", "Kehr", "Pleasant", "Loss", "Mitten"};

  private String getRandomItem(String[] array) {
    return array[random.nextInt(array.length)];
  }

  public String swapGender(String gender) {
    return (gender == "m") ? "f" : "m";
  }

  private Person rootPerson;
  private int peopleAdded = 0;
  private int eventsAdded = 0;
  /**
   * @param request
   * the request. Contains username and generations (default 4)
   * */
  private void addPeople(Request request) throws SQLException, DataAccessException, IllegalAccessException {
    request.generations = (request.generations == null) ? DEFAULT_GENERATIONS : request.generations;
    ArrayList<Person> justAddedPeople = new ArrayList<>();

    User user = userDao.getUser(request.username);
    justAddedPeople.add(new Person(user.personID, user.username, user.firstName, user.lastName, user.gender, null, null, null));

    //add people, not events yet
    for(int i = 0; i < request.generations; i++) {//for each generation added
      ArrayList<Person> currentlyAddingPeople = new ArrayList<>();
      for(Person person : justAddedPeople) {//for each person in the prior added generation, add their parents
        person.fatherID = UUIDService.getUUID();
        person.motherID = UUIDService.getUUID();
        Person father = new Person(person.fatherID, request.username, getRandomItem(mNames), getRandomItem(sNames), "m", null, null, person.motherID);
        Person mother = new Person(person.motherID, request.username, getRandomItem(mNames), getRandomItem(sNames), "f", null, null, person.fatherID);
        if(i == request.generations - 1) {//on the last iteration, add remaining people
          personDao.addRecord(father);
          personDao.addRecord(mother);
          peopleAdded += 2;
        }
        if(!personDao.editRecord(person)) {//if no record exists to edit, add it.
          personDao.addRecord(person);
          peopleAdded++;
        }
        if(i == 0) {
          rootPerson = person;
        }
        currentlyAddingPeople.add(father);
        currentlyAddingPeople.add(mother);
      }
      justAddedPeople = currentlyAddingPeople;

    }
  }

  private HashMap<String, Person> personMap;
  private void recursiveAddEvents(Person child, Person father, Person mother, int birthYear) throws SQLException, DataAccessException, IllegalAccessException {//note: random is not inclusive of the top value.
    final int minimumMarriageAge = 13;
    final int maxPregnantAge = 50;
    final int maxAge = 120;
    //mom must be born at least 13 years before child, at most 50 years before child
    int momBirth = birthYear - random.nextInt(minimumMarriageAge, maxPregnantAge);
    //dad must be born at least 13 years before child, at most 120 years before
    int dadBirth = birthYear - random.nextInt(minimumMarriageAge, maxAge);
    //mom and dad must live at least til their child is born, and no more than 120
    int momDeath = random.nextInt(birthYear, momBirth + maxAge);
    int dadDeath = random.nextInt(birthYear, dadBirth + maxAge);
    //marriage must have been any time between the highest of mom and dad birth + 13 years, and before either dies
    int marriageYear = random.nextInt(Math.max(momBirth, dadBirth) + 13, Math.min(momDeath, dadDeath));

    addBirthAndDeath(mother, momBirth, momDeath);
    addBirthAndDeath(father, dadBirth, dadDeath);
    Location location = RandomLocationService.getRandomLocation();
    eventDao.addRecord(new Event(UUIDService.getUUID(), mother.associatedUsername, mother.personID, location.country, location.city, "marriage", location.latitude, location.longitude, marriageYear));
    eventDao.addRecord(new Event(UUIDService.getUUID(), father.associatedUsername, father.personID, location.country, location.city, "marriage", location.latitude, location.longitude, marriageYear));
    eventsAdded += 2;

    callAddEventsOnParents(father, dadBirth);
    callAddEventsOnParents(mother, momBirth);
  }

  void callAddEventsOnParents(Person person, int birthYear) throws SQLException, DataAccessException, IllegalAccessException {
    Person father = personMap.get(person.fatherID);
    Person mother = personMap.get(person.motherID);
    if(father != null && mother != null) {
      recursiveAddEvents(person, father, mother, birthYear);
    }
  }

  private void addBirthAndDeath(Person person, int birth, int death) throws SQLException, DataAccessException, IllegalAccessException {
    Location location = RandomLocationService.getRandomLocation();
    eventDao.addRecord(new Event(UUIDService.getUUID(), person.associatedUsername, person.personID, location.country, location.city, "birth", location.latitude, location.longitude, birth));
    location = RandomLocationService.getRandomLocation();
    eventDao.addRecord(new Event(UUIDService.getUUID(), person.associatedUsername, person.personID, location.country, location.city, "death", location.latitude, location.longitude, death));
    eventsAdded += 2;
  }

  /**
   * @param username the username of the user for which the fill service is being used to generate data for.
   * */
  private void addEvents(String username) throws SQLException, DataAccessException, IllegalAccessException {
    Person[] people = personDao.getMultipleRecordsByColumn("associatedUsername", username).toArray(new Person[0]);
    HashMap<String, Integer> birthYears = new HashMap<>();

    //generate first event data, from which the loop will branch off of.
    Location location = RandomLocationService.getRandomLocation();
    int startingYear = 2000;
    birthYears.put(rootPerson.personID, startingYear);
    eventDao.addRecord(new Event(UUIDService.getUUID(), username, rootPerson.personID, location.country, location.city, "birth", location.latitude, location.longitude, startingYear));
    eventsAdded++;
    personMap = new HashMap<>();
    for(Person person : people) {
      personMap.put(person.personID, person);
    }
    recursiveAddEvents(rootPerson, personMap.get(rootPerson.fatherID), personMap.get(rootPerson.motherID), startingYear);
  }

  public Response fill(Request request) {
    try {
      peopleAdded = 0;
      eventsAdded = 0;
      personDao.removeRecordsWhere("associatedUsername", request.username);
      eventDao.removeRecordsWhere("associatedUsername", request.username);
      addPeople(request);
      addEvents(request.username);

      Response response = new Response(ExchangeTypes.FILL);
      response.success = true;
      response.message = "Successfully added " + peopleAdded + " persons and " + eventsAdded + " events to the database.";
      return response;
    } catch (Exception e) {
      return failure(e.getMessage());
    }
  }
}
