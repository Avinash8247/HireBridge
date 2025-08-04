package com.microservices.jobms.job.mapper;

import com.microservices.jobms.job.Job;
import com.microservices.jobms.job.dto.jobDto;
import com.microservices.jobms.job.external.Review;
import com.microservices.jobms.job.external.company;

import java.util.List;

public class jobmapper {
    public static jobDto maptojobwithcompanydto(Job job, company company, List<Review> reviews){
        jobDto jobDto =new jobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setLocation(job.getLocation());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setCompany(company);
        jobDto.setReview(reviews);
        return jobDto;
    }

}
