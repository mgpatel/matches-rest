package com.mg.controller;

import com.mg.domain.Job;
import com.mg.domain.Worker;
import com.mg.service.JobService;
import com.mg.service.WorkerService;
import com.mg.utils.GeoDistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Krishna on 20/12/16.
 */
@CrossOrigin
@RestController
@RequestMapping("/matches")
public class MatchesController {

    @Autowired
    JobService jobService;

    @Autowired
    WorkerService workerService;

    @RequestMapping(value = "/{workerId}", method = RequestMethod.GET)
    public ResponseEntity<List<Job>> getMatchingJobs(@PathVariable int workerId) throws Exception{

        Worker worker = workerService.getWorker(workerId);

        if(worker == null){
            return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
        }

        List<Job> appropriateJobs = jobService.findAppropriateJob(worker);

        if(appropriateJobs.isEmpty()){
            return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Job>>(appropriateJobs, HttpStatus.OK);
    }
}
