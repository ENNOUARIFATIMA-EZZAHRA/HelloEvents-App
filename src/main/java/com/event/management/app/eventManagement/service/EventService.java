package com.event.management.app.eventManagement.service;

import com.event.management.app.eventManagement.entity.Event;
import com.event.management.app.eventManagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService {
  @Autowired
  private EventRepository eventRepository;

  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }
}


