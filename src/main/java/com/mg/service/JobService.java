package com.mg.service;

import com.mg.domain.Job;
import com.mg.domain.Worker;

import java.util.List;
import java.util.Map;

/**
 * Created by Krishna on 20/12/16.
 */
public interface JobService {

    List<Job> getJobs() throws Exception;

    List<Job> findAppropriateJob(Worker worker) throws Exception;
}
