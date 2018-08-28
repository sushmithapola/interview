package com.interview.spring.controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;
import com.interview.spring.bean.User;
import com.interview.spring.enumeration.ErrorCodeEnum;
import com.interview.spring.exception.BadRequestException;
import com.interview.spring.exception.SystemException;
import com.interview.spring.logging.service.LoggingService;
import com.interview.spring.service.UserService;

/**
 * 
 * @author Sushmitha
 *
 */
@RestController
@RequestMapping(value = { "/user" })
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	private LoggingService loggingService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) throws SystemException {
		loggingService.info("Calling service getUserById(), id=" + id);
		if (id <= 0) {
			throw new BadRequestException(ErrorCodeEnum.ID_REQUEST_VALIDATION_EXCEPTION);
		}
		User user = userService.findById(id);
		if (user == null) {
			throw new SystemException(ErrorCodeEnum.NO_DATA_FOUND_EXCEPTION);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder)
			throws SystemException {
		loggingService.info("Calling service createUser(), user=" + user);
		if (user == null) {
			throw new SystemException(ErrorCodeEnum.NO_DATA_FOUND_EXCEPTION);
		}
		validateCreateUser(user);
		userService.createUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	private void validateCreateUser(User user) throws BadRequestException {
		if (StringUtils.isBlank(user.getName()) && StringUtils.isBlank(user.getLastName())
				&& StringUtils.isBlank(user.getCountry())) {
			throw new BadRequestException(ErrorCodeEnum.REQUEST_VALIDATION_EXCEPTION);
		} else if (StringUtils.isBlank(user.getName())) {
			throw new BadRequestException(ErrorCodeEnum.NAME_REQUEST_VALIDATION_EXCEPTION);
		} else if (StringUtils.isBlank(user.getLastName())) {
			throw new BadRequestException(ErrorCodeEnum.LASTNAME_REQUEST_VALIDATION_EXCEPTION);
		} else if (StringUtils.isBlank(user.getCountry())) {
			throw new BadRequestException(ErrorCodeEnum.COUNTRY_REQUEST_VALIDATION_EXCEPTION);
		}
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<User> getAllUser() {
		List<User> tasks = userService.getUser();
		return tasks;
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody User currentUser) throws SystemException {
		loggingService.info("Calling service updateUser(), user=" + currentUser);
		User user = userService.findById(currentUser.getId());
		if (user == null) {
			throw new SystemException(ErrorCodeEnum.NO_DATA_FOUND_EXCEPTION);
		}
		userService.update(currentUser, currentUser.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) throws SystemException {
		loggingService.info("Calling service deleteUser(), id=" + id);
		User user = userService.findById(id);
		if (user == null) {
			throw new SystemException(ErrorCodeEnum.NO_DATA_FOUND_EXCEPTION);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
