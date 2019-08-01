package com.herokusmallapp.controlers;

import com.herokusmallapp.entity.Runner;
import com.herokusmallapp.repositories.RunnerRepository;
import com.herokusmallapp.services.RunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RunnerController {

    private final RunnerRepository runnerRepository;

    private final RunnerService runnerService;


    @PostMapping(value = "/{runnerId}/runner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void uploadRunnerFile(@RequestParam(value = "file", required = false) MultipartFile file,
                        @PathVariable("runnerId") Long runnerId) throws IOException {
        runnerService.uploadRunnerImage(file.getBytes(), runnerId);
    }

    @PostMapping("/runner")
    void persistRunner(@RequestBody Runner runner) {
        runnerRepository.save(runner);
    }

    @GetMapping("/runners")
    List<Runner> getRunners(){
        return this.runnerRepository.findAll();
    }

    @DeleteMapping("/deleterunner")
    void removeCalendarEvent(@RequestParam Long id) {
        this.runnerRepository.delete(id);
    }



}
