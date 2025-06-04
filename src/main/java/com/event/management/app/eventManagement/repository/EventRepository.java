//package com.event.management.app.eventManagement.repository;
//
//import com.event.management.app.eventManagement.entity.Event;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//public interface EventRepository extends JpaRepository<Event, Long> {
//    List<Event> findByCategory(String category);
//    List<Event> findByLocation(String location);
//    List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);
//  }
//
//
