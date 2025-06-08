package com.event.management.app.eventManagement.service;

import com.event.management.app.eventManagement.entity.Event;
import com.event.management.app.eventManagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;


  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }


  public Optional<Event> getEventById(Long id) {
    return eventRepository.findById(id);
  }


  public Event createEvent(Event event) {
    return eventRepository.save(event);
  }
  public List<Event> searchEvents(String title, String category, String location, Date date) {
    return eventRepository.searchEvents(title, category, location, date);
  }


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


  public void deleteEvent(Long id) {
    Event event = eventRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    eventRepository.delete(event);
  }


  public List<Event> searchByTitle(String keyword) {
    return eventRepository.findByTitleContainingIgnoreCase(keyword);
  }


  public List<Event> searchByCategory(String category) {
    return eventRepository.findByCategory(category);
  }
}
