package DataAccess;

import Model.Model;
import Model.Event;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventDao extends Dao {
  public EventDao() {
    tableName = "Events";
    idColumn = "eventID";
  }

  @Override
  protected Model parseResultSet(ResultSet result) throws SQLException {
    Event event = new Event();
    event.eventID = result.getString("eventID");
    event.associatedUsername = result.getString("associatedUsername");
    event.personID = result.getString("personID");
    event.country = result.getString("country");
    event.city = result.getString("city");
    event.eventType = result.getString("eventType");
    event.latitude = result.getFloat("latitude");
    event.longitude = result.getFloat("longitude");
    event.year = result.getInt("year");
    return event;
  }
}
