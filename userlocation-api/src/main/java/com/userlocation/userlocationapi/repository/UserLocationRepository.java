package com.userlocation.userlocationapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.userlocation.userlocationapi.model.UserLocation;

public interface UserLocationRepository extends CrudRepository<UserLocation, Long> {

}
