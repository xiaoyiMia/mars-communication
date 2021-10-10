package io.mars.et.xiaoyi.controllers.mappers;

import io.mars.et.xiaoyi.controllers.dto.Conference;
import io.mars.et.xiaoyi.domains.ConferenceEntity;
import org.springframework.stereotype.Component;

@Component
public class ConferenceMapperImpl implements ConferenceMapper {
  @Override
  public Conference fromConferenceEntity(ConferenceEntity entity) {
    if(entity == null) return null;

    Conference conference = new Conference();
    conference.setId(entity.getId());
    conference.setCode(entity.getCode());
    conference.setName(entity.getName());
    conference.setDescription(entity.getDescription());
    return conference;
  }
}
