package com.microservices.jobms.job.impl;


import com.microservices.jobms.job.Job;
import com.microservices.jobms.job.JobRepository;
import com.microservices.jobms.job.JobService;
import com.microservices.jobms.job.client.CompanyClient;
import com.microservices.jobms.job.client.ReviewsClient;
import com.microservices.jobms.job.dto.jobDto;
import com.microservices.jobms.job.external.Review;
import com.microservices.jobms.job.external.company;
import com.microservices.jobms.job.mapper.jobmapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImplementation implements JobService {
//    private List<Job> jobs =new ArrayList<>();

   private final JobRepository jobRepository;
   private final CompanyClient companyClient;
   private final ReviewsClient reviewsClient;
   @Autowired
   RestTemplate restTemplate;




    public JobServiceImplementation(JobRepository jobRepository, CompanyClient companyClient, ReviewsClient reviewsClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewsClient = reviewsClient;
    }

    @Override
    @CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerMethod")
    public List<jobDto> findAll() {
        List<Job> jobs=jobRepository.findAll();
        return jobs.stream().map(this::converttodto).collect(Collectors.toList());
    }

    public List<String> companyBreakerMethod(Exception e){
        List<String> l=new ArrayList<>();
        l.add("SomeThing Went Wrong Try After Some Time");
        return l;
    }
    private jobDto converttodto(Job job){
       //jobWithcompanyDto jobWithcompanyDto=new jobWithcompanyDto();
       //jobWithcompanyDto.setJob(job);
        //RestTemplate restTemplate=new RestTemplate();


//        company company=restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/" + job.getCompanyid(), com.microservices.jobms.job.external.company.class);
//        ResponseEntity<List<Review>> reviewResponse=restTemplate.exchange("http://REVIEWS-SERVICE:8083/reviews?companyid=" + job.getCompanyid(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>(){
//        });

        company company=companyClient.getCompany(job.getCompanyid());
        List<Review> reviews=reviewsClient.getReviews(job.getCompanyid());
//        List<Review> reviews=reviewResponse.getBody();
        jobDto jobDto = jobmapper.maptojobwithcompanydto(job,company,reviews);

        return jobDto;

    }


    @Override

    public void createJob(Job job) {
            jobRepository.save(job);
    }

    @Override
    public jobDto findbyid(Long id) {
       Job job= jobRepository.findById(id).orElse(null);
       return converttodto(job);
    }

    @Override
    public boolean deletebyid(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }

    }

    @Override
    public boolean update(Long id, Job updatejob) {
        Optional<Job> jobOptional=jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job=jobOptional.get();
            job.setTitle(updatejob.getTitle());
            job.setDescription(updatejob.getDescription());
            job.setMinSalary(updatejob.getMinSalary());
            job.setMaxSalary(updatejob.getMaxSalary());
            job.setLocation(updatejob.getLocation());
            jobRepository.save(job);
            return true;

        }
        return false;
    }
}
