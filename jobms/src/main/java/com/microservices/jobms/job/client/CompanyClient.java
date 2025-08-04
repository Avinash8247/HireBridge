package com.microservices.jobms.job.client;

import com.microservices.jobms.job.external.company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyClient {

    @GetMapping("companies/{id}")
    company getCompany(@PathVariable Long id);

}
