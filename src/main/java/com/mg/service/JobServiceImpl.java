package com.mg.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mg.domain.Job;
import com.mg.domain.Worker;
import com.mg.utils.GeoDistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 20/12/16.
 */
@Service
public class JobServiceImpl implements JobService{

    @Autowired
    WorkerService workerService;

    public static final String JOBS_URL = "http://swipejobs.azurewebsites.net/api/jobs";
    public static final String FILE_NAME = "jobs.json";
    public static final int MAX_APPROPRIATE_JOB = 3;

    @Override
    public List<Job> getJobs() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());

        List<Job> jobs = mapper.readValue(file, typeFactory.constructCollectionType(List.class, Job.class));

        return jobs;

//        RestTemplate restTemplate = new RestTemplate();
//
//        ParameterizedTypeReference<List<Job>> responseType = new ParameterizedTypeReference <List<Job>>() {};
//        ResponseEntity<List<Job>> responseEntity = restTemplate.exchange(JOBS_URL, HttpMethod.GET, null, responseType);
//        List<Job> allJobs = responseEntity.getBody();
//        return allJobs;
    }

    @Override
    public List<Job> findAppropriateJob(Worker worker) throws Exception {
        List<Job> appropriateJobs = new ArrayList<Job>();

        List<Job> jobs = getJobs();

        List<Worker> workers = workerService.getWorkers();

        for(Job job : jobs){
            if(appropriateJobs.size() >= MAX_APPROPRIATE_JOB ){
                break;
            }

            //Worker has driver license
            if(job.isDriverLicenseRequired() && !worker.isHasDriverLicense()){
                continue;
            }

            String jobTitle = job.getJobTitle();
            //Job Title and skills match
            if(worker.getSkills().contains(jobTitle)){
                List<String> jobCertificates = job.getRequiredCertificates();

                for(String jobCertificate : jobCertificates){

                    //has at least one required certificate
                    if(worker.getCertificates().contains(jobCertificate)){

                        double workerLongitude = worker.getJobSearchAddress().getLongitude();
                        double workerLatitude  = worker.getJobSearchAddress().getLatitude();
                        double jobLongitude = job.getLocation().getLongitude();
                        double jobLatitude = job.getLocation().getLatitude();

                        int distance = GeoDistanceCalculator.getDistance(workerLongitude, workerLatitude, jobLongitude, jobLatitude);

                        // Within max job distance radius
                        if(worker.getJobSearchAddress().getMaxJobDistance() <= distance){
                            appropriateJobs.add(job);
                            break;
                        }
                    }
                }
            }
        }
        return appropriateJobs;
    }
}
