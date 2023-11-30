package com.lcwd.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.service.RatingService;

@SpringBootTest
class UserMicroServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
//	@Test
//	void creating() {
//		Rating rating = Rating.builder().rating(21).userId("").hotelId("").feedback("Very Nice").build();
//		ResponseEntity<Rating> savedRating = ratingService.create(rating);
//		
//		System.out.println("new rating created");
//		System.out.println(savedRating.toString());
//	}

	@Test
	void gettingAll() {
		ResponseEntity<List<Rating>> ratings=ratingService.getRatings();
		System.out.println("Getting all Ratings are..");
		System.out.println(ratings);
	}
}
