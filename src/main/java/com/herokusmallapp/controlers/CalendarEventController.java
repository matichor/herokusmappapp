package com.herokusmallapp.controlers;

import com.herokusmallapp.entity.CalendarEvent;
import com.herokusmallapp.repositories.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalendarEventController {

    private final CalendarEventRepository calendarEventRepository;

    @PostMapping("/addevent")
    void saveCalendarEvents(@RequestBody CalendarEvent calendarEvent) {
        calendarEventRepository.save(calendarEvent);
    }

    @GetMapping("/getevents")
    List<CalendarEvent> getCalendarEvents(){
        List<CalendarEvent> calendarEvents = calendarEventRepository.findAll();
        return calendarEvents;
    }

    @DeleteMapping("/deleteevent")
    void removeCalendarEvent(@RequestParam Long id) {
        calendarEventRepository.delete(id);
    }
}
