package io.mars.et.xiaoyi.repositories;

import io.mars.et.xiaoyi.domain.ConferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {
  Optional<ConferenceEntity> findByCode(String Code);
}
