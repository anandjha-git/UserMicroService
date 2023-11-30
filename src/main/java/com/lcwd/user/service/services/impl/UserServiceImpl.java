/**
* 
*/
package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.service.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

/**
 * @author ANAND
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// generate unique userId randomly
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found in server " + userId));
		//------------------------------------------------------------------------------------------------
		// fetch the rating of above user from rating service
		// http://localhost:8083/ratings/users/c617e8a5-b669-48d1-8e8d-6fac036e0423
		// ArrayList<Rating> ratingsOfUser =
		// restTemplate.getForObject("http://localhost:8083/ratings/users/c617e8a5-b669-48d1-8e8d-6fac036e0423",
		// ArrayList.class);
		// ArrayList<Rating> ratingsOfUser =
		// restTemplate.getForObject("http://localhost:8083/ratings/users/" +
		// user.getUserId(), ArrayList.class);
		// logger.info("{} ", ratingsOfUser);
		// user.setRatings(ratingsOfUser);
		//---------------------------------------------------------------------------------------------------

		Rating[] ratingsOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		logger.info("{} ", ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel by hotelId
			// http://localhost:8082/hotels/81a18ce7-16e1-4851-8a42-4ce02eafb305
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			//Hotel hotel = forEntity.getBody();
			//logger.info("response status code : {} ", forEntity.getStatusCode());
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// set the hotel to the rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
