package com.microservices.reviewms.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findBycompanyid(Long companyId);
}
