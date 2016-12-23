package com.mg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mg.domain.Worker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

/**
 * Created by mayank.patel on 21/12/2016.
 */
@Service
public class WorkerServiceImpl implements WorkerService {

    public static final String WORKERS_URL = "http://swipejobs.azurewebsites.net/api/workers";
    public static final String FILE_NAME = "workers.json";

    @Override
    public List<Worker> getWorkers() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());

        List<Worker> workers = mapper.readValue(file, typeFactory.constructCollectionType(List.class, Worker.class));

        return workers;

//        RestTemplate restTemplate = new RestTemplate();
//
//        ParameterizedTypeReference<List<Worker>> responseType = new ParameterizedTypeReference <List<Worker>>() {};
//        ResponseEntity<List<Worker>> responseEntity = restTemplate.exchange(WORKERS_URL, HttpMethod.GET, null, responseType);
//        List<Worker> allWorkers = responseEntity.getBody();
//        return allWorkers;
    }

    @Override
    public Worker getWorker(int workerId) throws Exception {
        return getWorkers().get(workerId);
    }
}
