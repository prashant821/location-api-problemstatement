package com.userlocation.userlocationapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.userlocation.userlocationapi.model.UserLocation;
import com.userlocation.userlocationapi.service.UserLocationService;


import java.util.List;

@RestController
public class UserLocationController {
	
	@Autowired
    private UserLocationService userLocationService;
	
	@RequestMapping( method = RequestMethod.GET, value ="/getAllUsers")
    public List<UserLocation> getAllUsers() {
       return userLocationService.getAllUsers();
    }

	@RequestMapping( method = RequestMethod.POST, value ="/create_data")
    public void createData(@RequestBody UserLocation userLocation) {
        userLocationService.createData(userLocation);
    }

	@RequestMapping( method = RequestMethod.PUT, value ="/update_data/{id}")
    public void updateData(@RequestBody UserLocation userLocation, @PathVariable Long id) {
       userLocationService.updateData(id, userLocation);
    }

	@RequestMapping( method = RequestMethod.GET, value ="/get_users/{N}")
    public List<UserLocation> getNearestUsers(@PathVariable int N) {
        return userLocationService.getNearestUsers(N);
    }
}
