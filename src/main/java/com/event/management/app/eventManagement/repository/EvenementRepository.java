package com.event.management.app.eventManagement.repository;

import com.event.management.app.eventManagement.model.EventModel;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Event, Long> {
}
