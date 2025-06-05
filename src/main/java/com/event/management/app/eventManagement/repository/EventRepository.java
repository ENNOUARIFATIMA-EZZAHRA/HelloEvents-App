package com.event.management.app.eventManagement.repository;

import com.event.management.app.eventManagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date; // ✅ هذا هو السطر المهم
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

  List<Event> findByCategory(String category);
  List<Event> findByLocation(String location);
  List<Event> findByTitleContainingIgnoreCase(String keyword);

  // البحث باستخدام أكثر من معيار
  @Query("SELECT e FROM Event e WHERE " +
    "(:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
    "(:category IS NULL OR e.category = :category) AND " +
    "(:location IS NULL OR e.location = :location) AND " +
    "(:date IS NULL OR e.date = :date)")
  List<Event> searchEvents(@Param("title") String title,
                           @Param("category") String category,
                           @Param("location") String location,
                           @Param("date") Date date);
}
