package Service;

import java.util.UUID;

public class UUIDService {
  public static String getUUID() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
}
