package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.entity.Event;
import com.event.management.app.eventManagement.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

  private final EventRepository eventRepository;

  public EventController(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @GetMapping
  public List<com.event.management.app.eventManagement.entity.Event> getAllEvents() {
    return eventRepository.findAll();
  }

  @GetMapping("/search")
  public List<Event> searchEvents(
    @RequestParam(required = false) String category,
    @RequestParam(required = false) String location

  ) {
    if (category != null) return eventRepository.findByCategory(category);
    if (location != null) return eventRepository.findByLocation(location);

    return eventRepository.findAll();
  }
}


