package com.herokusmallapp.services;

import com.herokusmallapp.entity.Runner;
import com.herokusmallapp.repositories.RunnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RunnerService {

    private final RunnerRepository runnerRepository;

    public void saveRunner(Runner runner) {
        runnerRepository.save(runner);
    }

    @Transactional
    public void uploadRunnerImage(byte[] file, Long runnerId) {
        Runner runner = runnerRepository.findOne(runnerId);
        Optional.ofNullable(runner).ifPresent(r -> r.setTeammatePicture(file));
    }

}
