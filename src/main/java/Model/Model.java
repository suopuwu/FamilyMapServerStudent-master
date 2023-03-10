package Model;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Abstract class for models
 * */
public abstract class Model {
  /**
   * Checks if the non-null fields within comparisonModel are the same as this model's fields.
   * @return True if the same, false if different.
   * */
  public Field[] getFields() {
    return getClass().getDeclaredFields();
  }
  public boolean nonNullEquals(Model comparisonModel) throws IllegalAccessException {
    boolean isEqualSoFar = true;
    Field[] fields = getFields();
    Field[] comparisonFields = comparisonModel.getFields();
    if(fields.length != comparisonFields.length) {
      return false;
    }
    for(int i = 0; i < fields.length; i++) {
      String valOne = fields[i].get(this).toString();
      String valTwo = comparisonFields[i].get(comparisonModel).toString();
      if(!Objects.equals(valOne, valTwo)) {
        if("".equals(valOne) || "".equals(valTwo)) {
          continue;
        }
        return false;
      }
    }
    return true;
  }
}
