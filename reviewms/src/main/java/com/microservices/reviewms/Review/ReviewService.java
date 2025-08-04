package com.microservices.reviewms.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getallreviews(Long companyid);
    boolean addreview(Long companyid,Review review);

    Review getreviewbyid(Long reviewId);

    boolean updatereview(Long reviewId,Review Updatedreview);

    boolean deletereview(Long reviewId);

}
