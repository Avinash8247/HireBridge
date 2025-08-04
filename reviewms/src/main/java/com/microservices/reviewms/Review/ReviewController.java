package com.microservices.reviewms.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyid){
        return new ResponseEntity<>(reviewService.getallreviews(companyid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyid, @RequestBody Review review){
        boolean isReviewSaved=reviewService.addreview(companyid,review);
        if(isReviewSaved)
             return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Review not added",HttpStatus.NOT_FOUND);


    }
    @GetMapping("/{reviewId}")

    public ResponseEntity<Review> getreviewbyid(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getreviewbyid(reviewId),HttpStatus.OK);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updatereview(@PathVariable Long reviewId,@RequestBody Review updatedreview){
        boolean isupdated=reviewService.updatereview(reviewId,updatedreview);
        if(isupdated){
            return new ResponseEntity<>("review updateted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("review not found",HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deletereview(@PathVariable Long reviewId){
        boolean isdeleted=reviewService.deletereview(reviewId);
        if(isdeleted){
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(" not deleted ",HttpStatus.NOT_FOUND);
    }
}
