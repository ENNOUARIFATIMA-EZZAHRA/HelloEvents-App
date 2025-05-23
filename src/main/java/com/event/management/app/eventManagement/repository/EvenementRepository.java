package com.event.management.app.eventManagement.repository;

import com.event.management.app.eventManagement.entity.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<EventModel, Long> {
}
