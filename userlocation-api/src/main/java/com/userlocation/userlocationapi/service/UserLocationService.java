package com.userlocation.userlocationapi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userlocation.userlocationapi.model.UserLocation;
import com.userlocation.userlocationapi.repository.UserLocationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserLocationService {
	
	 @Autowired
	 private UserLocationRepository userLocationRepository;
	 
	 //Method to get All Users details
	 public List<UserLocation> getAllUsers() {
		 List<UserLocation> users = new ArrayList<UserLocation>();
		 userLocationRepository.findAll().forEach(users::add);
	     return users;
	    }

	 // Method to Save user Details
	 @Transactional
	 public void createData(UserLocation userLocation) {
	     userLocationRepository.save(userLocation);
	 }
	 
	// Method to Update user Details
	 @Transactional
	 public void updateData(Long id, UserLocation userLocation) {
		 Optional<UserLocation> optionalUser = userLocationRepository.findById(id);
		 UserLocation user = optionalUser.get();

	     // Update user data with the provided values
	     user.setName(userLocation.getName());
	     user.setLatitude(userLocation.getLatitude());
	     user.setLongitude(userLocation.getLongitude());
	     user.setExcluded(userLocation.isExcluded());

	     // Save the updated user to the database
	     userLocationRepository.save(user);
	 }

	 //Method to retrieve the nearest users.
	 public List<UserLocation> getNearestUsers(int N) {
		 
		 // Fetch all users from the database
	     List<UserLocation> allUsers = (List<UserLocation>) userLocationRepository.findAll();

	     // Filter users by excluded = false and limit to the first N users
	     List<UserLocation> includedUsers = allUsers.stream()
	     .filter(user -> !user.isExcluded()) // Filter by status = false
	     .limit(N)                     		// Limit the result to N users
	     .collect(Collectors.toList());
	     return includedUsers;
	 }

}
