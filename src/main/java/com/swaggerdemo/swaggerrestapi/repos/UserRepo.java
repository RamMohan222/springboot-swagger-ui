package com.swaggerdemo.swaggerrestapi.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swaggerdemo.swaggerrestapi.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

}
