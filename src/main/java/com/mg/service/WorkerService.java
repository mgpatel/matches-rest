package com.mg.service;

import com.mg.domain.Worker;

import java.util.List;
import java.util.Map;

/**
 * Created by mayank.patel on 21/12/2016.
 */
public interface WorkerService {

    List<Worker> getWorkers() throws Exception;

    Worker getWorker(int workerId) throws Exception;
}
