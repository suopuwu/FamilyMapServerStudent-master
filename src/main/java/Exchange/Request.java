package Exchange;

import Model.Event;
import Model.Person;
import Model.User;


public class Request extends Exchange {
  public ExchangeTypes exchangeType = ExchangeTypes.CLEAR;
  public String username, password, email, firstName, lastName, gender, personID, eventID, Authorization; //todo Authorization must be capitalized in the header but idk how it works.
  public Integer generations;
  public User[] users;
  public Person[] persons;
  public Event[] events;

  public Request(ExchangeTypes exchangeType) {
    this.exchangeType=exchangeType;
  }
  public Request() {}
}
