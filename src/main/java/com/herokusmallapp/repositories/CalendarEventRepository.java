package com.herokusmallapp.repositories;

import com.engineer.entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {
}
