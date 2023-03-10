package Exchange;

import Model.Event;
import Model.Person;
import Model.User;


public class Request {
  public final ExchangeTypes exchangeType;
  public String username, password, email, firstName, lastName, gender, personID, eventID;
  public int generations = 4;
  public User[] users;
  public Person[] persons;
  public Event[] events;

  public Request(ExchangeTypes exchangeType) {
    this.exchangeType=exchangeType;
  }
}
