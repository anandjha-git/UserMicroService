package com.lcwd.user.service.external.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lcwd.user.service.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	// create
		@PostMapping("/ratings")
		public ResponseEntity<Rating> create(@RequestBody Rating rating);
		
		// get all
		@GetMapping("/ratings")
		public ResponseEntity<List<Rating>> getRatings();
		
		//get single rating
		@GetMapping("/ratings/{ratingId}")
		public ResponseEntity<Rating> getRating(@PathVariable("ratingId") String ratingId);
		
		
		//update rating
		@PutMapping("/ratings/{ratingId}")
		public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
		
		//delete rating
		@DeleteMapping("/ratings/{ratingId}")
		public void deleteRating(@PathVariable("ratingId") String ratingId);
		
		

}
