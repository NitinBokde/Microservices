package com.nitin.rating_service.controller;

import com.nitin.rating_service.dto.CreateRatingRequest;
import com.nitin.rating_service.dto.RatingResponse;
import com.nitin.rating_service.dto.UpdateRatingRequest;
import com.nitin.rating_service.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String addRating(@RequestBody CreateRatingRequest createRatingRequest){
        ratingService.addRating(createRatingRequest);
        return "Rating Saved Succesfully";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping
    String updateRatingById(@RequestBody UpdateRatingRequest updateRatingRequest){
        ratingService.updateRatingById(updateRatingRequest);
        return "Rating updated Successfully";
    }

    @GetMapping("/{skuCode}")
    List<RatingResponse> getAllRatingBySkuCode(@PathVariable String skuCode){
        return ratingService.getAllRatingByProductSkuCode(skuCode);
    }

    @GetMapping
    List<RatingResponse> getAllRating(){
        return ratingService.getAllRating();
    }

    @DeleteMapping
    public void deleteAllRating(){
         ratingService.deleteAllRating();
    }

}

