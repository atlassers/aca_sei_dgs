/**
 * @author Stefano De Giorgi
 * @since 2021-09-24
 */

package it.euris.academy.centroSportivo.utils;

import it.euris.academy.centroSportivo.data.enums.ContactType;
import it.euris.academy.centroSportivo.data.enums.Difficulty;
import it.euris.academy.centroSportivo.data.enums.Sport;

import java.time.Instant;

public class UT {

  public static String numberToString(Number value) {
    return value==null ? null : value.toString();
  }
  
  public static Long toLong(String value) {
    return value == null ? null : Long.parseLong(value);
  }

  public static Integer toInt(String value) {
    return value == null ? null : Integer.parseInt(value);
  }

  public static Double toDouble(String value) {
    return value == null ? null : Double.parseDouble(value);
  }

  public static Sport stringToSport(String value) {
    for(Sport sportValue : Sport.values()){
      if (sportValue.name().equalsIgnoreCase(value))
        return sportValue;
    }

    return null;
  }

  public static String sportToString(Sport value) {
    return value==null ? null : value.name();
  }

  public static Difficulty stringToDifficulty(String value) {
    for(Difficulty difficultyValue : Difficulty.values()){
      if (difficultyValue.name().equalsIgnoreCase(value))
        return difficultyValue;
    }

    return null;
  }

  public static String difficultyToString(Difficulty value) {
    return value==null ? null : value.name();
  }

  public static ContactType stringToContactType(String value) {
    for(ContactType contactTypeValue : ContactType.values()){
      if (contactTypeValue.name().equalsIgnoreCase(value))
        return contactTypeValue;
    }

    return null;
  }

  public static String contactTypeToString(ContactType value) {
    return value==null ? null : value.name();
  }

  public static Instant toInstant(String value) {
    return value == null ? null : Instant.parse(value);
  }

  public static String fromInstant(Instant value) {
    return value == null ? null : value.toString();
  }
}
