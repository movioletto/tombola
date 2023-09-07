package it.movioletto.model.converter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;

public class StringToPremiCustom implements AttributeConverter<Map<Integer, String>, String> {

  @Override
  public String convertToDatabaseColumn(Map<Integer, String> attribute) {
    return StringUtils.join(
        attribute.entrySet().stream().map((entry) -> entry.getKey() + "|" + entry.getValue())
            .collect(Collectors.toList()), "£");
  }

  @Override
  public Map<Integer, String> convertToEntityAttribute(String dbData) {
    return Stream.of(dbData.split("£"))
        .map(pair -> pair.split("\\|"))
        .filter(keyValue -> keyValue.length == 2)
        .collect(Collectors.toMap(
            keyValue -> Integer.parseInt(keyValue[0]),
            keyValue -> keyValue[1]
        ));
  }
}