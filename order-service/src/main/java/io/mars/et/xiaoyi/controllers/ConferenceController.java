package io.mars.et.xiaoyi.controllers;

import io.mars.et.xiaoyi.controllers.dto.Conference;
import io.mars.et.xiaoyi.controllers.mappers.ConferenceMapper;
import io.mars.et.xiaoyi.repositories.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceController {

  private final ConferenceRepository conferenceRepository;
  private final ConferenceMapper conferenceMapper;

  @Autowired
  public ConferenceController(ConferenceMapper conferenceMapper, ConferenceRepository conferenceRepository) {
    this.conferenceMapper = conferenceMapper;
    this.conferenceRepository = conferenceRepository;
  }

  @GetMapping("/conferences/{conferenceCode}")
  public Conference readConference(@PathVariable String conferenceCode) {
    return conferenceMapper.fromConferenceEntity(
        conferenceRepository.findByCode(conferenceCode).orElse(null)
    );
  }
}
