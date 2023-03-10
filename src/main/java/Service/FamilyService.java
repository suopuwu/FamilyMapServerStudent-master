package Service;

import Exchange.ExchangeTypes;
import Exchange.Response;

/**
 * family member getting service
 * */
public class FamilyService extends BaseService {
  public FamilyService() {
    exchangeType = ExchangeTypes.FAMILY;
  }

  Response familyMembers() {
    try {

      return null;
    } catch (Exception e) {
      return failure(e.getMessage());
    }  }
}
