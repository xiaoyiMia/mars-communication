package io.mars.et.xiaoyi.controllers.mappers;

import io.mars.et.xiaoyi.controllers.dto.Conference;
import io.mars.et.xiaoyi.domains.ConferenceEntity;

public interface ConferenceMapper {
  Conference fromConferenceEntity(ConferenceEntity entity);
}
