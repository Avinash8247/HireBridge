package com.microservices.jobms.job.client;

import com.microservices.jobms.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWS-SERVICE")
public interface ReviewsClient {
    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam("companyid") Long companyid);
}
