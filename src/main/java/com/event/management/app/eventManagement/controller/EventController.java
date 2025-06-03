package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.entity.Event;
import com.event.management.app.eventManagement.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

public class EventController {
  @RestController
  @RequestMapping("/events")
  @RequiredArgsConstructor
  public class EventController {

    private final EventRepository eventRepository;

    @GetMapping
    public List<com.event.management.app.eventManagement.entity.Event> getAllEvents() {
      return eventRepository.findAll();
    }

    @GetMapping("/search")
    public List<Event> searchEvents(
      @RequestParam(required = false) String category,
      @RequestParam(required = false) String location,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
      if (category != null) return eventRepository.findByCategory(category);
      if (location != null) return eventRepository.findByLocation(location);
      if (startDate != null && endDate != null) return eventRepository.findByDateBetween(startDate, endDate);
      return eventRepository.findAll();
    }
  }

}
