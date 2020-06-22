package com.swaggerdemo.swaggerrestapi.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swaggerdemo.swaggerrestapi.models.User;
import com.swaggerdemo.swaggerrestapi.repos.UserRepo;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private UserRepo userRepo;

	public UserController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping
	public ResponseEntity<Iterable<User>> findAll() {
		Iterable<User> users = userRepo.findAll();
		return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Integer id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
		return new ResponseEntity<User>(user.orElseGet(null), HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
		userRepo.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		user = userRepo.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<User> update(@RequestBody User user) {
		user = userRepo.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
