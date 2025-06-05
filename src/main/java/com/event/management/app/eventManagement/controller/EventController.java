package com.event.management.app.eventManagement.controller;

import com.event.management.app.eventManagement.entity.Event;
import com.event.management.app.eventManagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

  @Autowired
  private EventService eventService;

  // عرض كل الأحداث أو البحث عن طريق keyword أو category
  @GetMapping
  public List<Event> getEvents(@RequestParam(required = false) String title,
                               @RequestParam(required = false) String category) {
    if (title != null) {
      return eventService.searchByTitle(title);
    } else if (category != null) {
      return eventService.searchByCategory(category);
    } else {
      return eventService.getAllEvents();
    }
  }

  // جلب حدث واحد
  @GetMapping("/{id}")
  public ResponseEntity<Event> getEventById(@PathVariable Long id) {
    Event event = eventService.getEventById(id)
      .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    return ResponseEntity.ok(event);
  }

  // إنشاء حدث جديد (محمي فقط للمدير)
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Event> createEvent(@RequestBody Event event) {
    Event createdEvent = eventService.createEvent(event);
    return ResponseEntity.ok(createdEvent);
  }

  // تعديل حدث (محمي فقط للمدير)
  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
    Event updatedEvent = eventService.updateEvent(id, event);
    return ResponseEntity.ok(updatedEvent);
  }

  // حذف حدث (محمي فقط للمدير)
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
    eventService.deleteEvent(id);
    return ResponseEntity.ok().build();
  }
}
