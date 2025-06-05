package com.event.management.app.eventManagement.service;

import com.event.management.app.eventManagement.entity.Event;
import com.event.management.app.eventManagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  // جلب كل الأحداث
  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  // جلب حدث واحد حسب الـ id
  public Optional<Event> getEventById(Long id) {
    return eventRepository.findById(id);
  }

  // إنشاء حدث جديد
  public Event createEvent(Event event) {
    return eventRepository.save(event);
  }

  // تحديث حدث موجود
  public Event updateEvent(Long id, Event eventDetails) {
    Event event = eventRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

    event.setTitle(eventDetails.getTitle());
    event.setDescription(eventDetails.getDescription());
    event.setLocation(eventDetails.getLocation());
    event.setDate(eventDetails.getDate());
    event.setCategory(eventDetails.getCategory());

    return eventRepository.save(event);
  }

  // حذف حدث
  public void deleteEvent(Long id) {
    Event event = eventRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    eventRepository.delete(event);
  }

  // بحث بالأحداث بالعنوان
  public List<Event> searchByTitle(String keyword) {
    return eventRepository.findByTitleContainingIgnoreCase(keyword);
  }

  // بحث بالأحداث حسب التصنيف
  public List<Event> searchByCategory(String category) {
    return eventRepository.findByCategory(category);
  }
}
