package com.microservices.reviewms.Review.Impl;



import com.microservices.reviewms.Review.Review;
import com.microservices.reviewms.Review.ReviewRepository;
import com.microservices.reviewms.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private  ReviewRepository reviewRepository;




    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public List<Review> getallreviews(Long companyid) {
        List<Review> reviews=reviewRepository.findBycompanyid(companyid);
        return reviews;
    }

    @Override
    public boolean addreview(Long companyid, Review review) {

        if(companyid!=null && review!=null){
            review.setCompanyid(companyid);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getreviewbyid(Long reviewId) {
         return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updatereview(Long reviewId, Review Updatedreview) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(reviewId!=null){
            review.setTitle(Updatedreview.getTitle());
            review.setDescription(Updatedreview.getDescription());
            review.setCompanyid(Updatedreview.getCompanyid());
            review.setRating(Updatedreview.getCompanyid());
            reviewRepository.save(review);
            return true;

        }
        return false;
    }

    @Override
    public boolean deletereview(Long reviewId) {
        Review review=reviewRepository.findById(reviewId).orElse(null);

       if(review!=null){

           reviewRepository.deleteById(reviewId);
           return true;

       }
       return false;
    }

}
