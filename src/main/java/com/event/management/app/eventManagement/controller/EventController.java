package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.entity.Event;
import com.event.management.app.eventManagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

  @Autowired
  private EventService eventService;


  @GetMapping
  public List<Event> getAll(@RequestParam(required = false) String title,
                               @RequestParam(required = false) String category) {
    if (title != null) {
      return eventService.searchByTitle(title);
    } else if (category != null) {
      return eventService.searchByCategory(category);
    } else {
      return eventService.getAllEvents();
    }
  }
  @GetMapping("/search")
  public List<Event> searchEvents(
    @RequestParam(required = false) String title,
    @RequestParam(required = false) String category,
    @RequestParam(required = false) String location,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date
  ) {
    return eventService.searchEvents(title, category, location, (java.sql.Date) date);
  }


  @GetMapping("/{id}")
  public ResponseEntity<Event> getEvent(@PathVariable Long id) {
    Event event = eventService.getEventById(id)
      .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    return ResponseEntity.ok(event);
  }


  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Event> createEvent(@RequestBody Event event) {
    Event createdEvent = eventService.createEvent(event);
    return ResponseEntity.ok(createdEvent);
  }


  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event event) {
    Event updatedEvent = eventService.updateEvent(id, event);
    return ResponseEntity.ok(updatedEvent);
  }


  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    eventService.deleteEvent(id);
    return ResponseEntity.ok().build();
  }
}
