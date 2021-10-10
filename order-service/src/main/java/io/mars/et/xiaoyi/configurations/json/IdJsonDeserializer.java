package io.mars.et.xiaoyi.configurations.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.mars.et.xiaoyi.common.ID;
import io.mars.et.xiaoyi.common.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class IdJsonDeserializer extends JsonDeserializer<ID> {

  private IdGenerator idGenerator;

  public IdJsonDeserializer(@Autowired IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  @Override
  public ID deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {
    String id = jsonParser.getValueAsString();
    return idGenerator.fromString(id);
  }
}