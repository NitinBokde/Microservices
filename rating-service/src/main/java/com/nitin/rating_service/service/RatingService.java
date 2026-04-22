package com.nitin.rating_service.service;

import com.nitin.rating_service.dto.CreateRatingRequest;
import com.nitin.rating_service.dto.RatingEvent;
import com.nitin.rating_service.dto.RatingResponse;
import com.nitin.rating_service.dto.UpdateRatingRequest;
import com.nitin.rating_service.model.Rating;
import com.nitin.rating_service.publisher.RatingEventPublisher;
import com.nitin.rating_service.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingEventPublisher ratingEventPublisher;


    public void addRating(CreateRatingRequest createRatingRequest) {
        Rating rating = Rating.builder()
                .productSkuCode(createRatingRequest.getProductSkuCode())
                .ratingValue(createRatingRequest.getRatingValue())
                .description(createRatingRequest.getDescription())
                .build();

        ratingRepository.save(rating);
        RatingEvent ratingEvent = RatingEvent.builder()
                .productSkuCode(rating.getProductSkuCode())
                .ratingValue(rating.getRatingValue())
                .oldRatingValue(null)
                .build();

        ratingEventPublisher.publishRatingEvent(ratingEvent);
    }

    @Transactional
    public void updateRatingById(UpdateRatingRequest updateRatingRequest){
        Rating rating = ratingRepository.findById(updateRatingRequest.getId()).get();

        log.info("Incoming SKU: '{}'", updateRatingRequest.getProductSkuCode());
        if (rating == null) {
            throw new RuntimeException("Rating not found");
        }
        int oldRatingValue = rating.getRatingValue();
        rating.setDescription(updateRatingRequest.getDescription());
        rating.setRatingValue(updateRatingRequest.getRatingValue());
        rating.setProductSkuCode(updateRatingRequest.getProductSkuCode());

        RatingEvent ratingEvent = RatingEvent.builder()
                .productSkuCode(rating.getProductSkuCode())
                .ratingValue(rating.getRatingValue())
                .oldRatingValue(oldRatingValue)
                .build();

        ratingEventPublisher.publishRatingEvent(ratingEvent);
        ratingRepository.save(rating);
    }

    public List<RatingResponse> getAllRatingByProductSkuCode(String skuCode) {
        List<Rating> ratingList = ratingRepository.findAllByProductSkuCode(skuCode);
        return ratingList.stream()
                .map(this::mapToDtoRatingResponse).toList();
    }
    private RatingResponse mapToDtoRatingResponse(Rating rating){
        return RatingResponse.builder()
                .id(rating.getId())
                .description(rating.getDescription())
                .ratingValue(rating.getRatingValue())
                .productSkuCode(rating.getProductSkuCode())
                .build();
    }

    public List<RatingResponse> getAllRating() {
        List<Rating> ratingList =  ratingRepository.findAll();
        return ratingList.stream().map(this::mapToDtoRatingResponse).toList();
    }


    public void deleteAllRating(){
        ratingRepository.deleteAll();
    }
}
