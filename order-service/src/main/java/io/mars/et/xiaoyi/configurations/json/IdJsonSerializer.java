package io.mars.et.xiaoyi.configurations.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.mars.et.xiaoyi.common.ID;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class IdJsonSerializer extends JsonSerializer<ID> {

  @Override
  public void serialize(ID id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException, JsonProcessingException {

    jsonGenerator.writeString(id.toString());
  }
}