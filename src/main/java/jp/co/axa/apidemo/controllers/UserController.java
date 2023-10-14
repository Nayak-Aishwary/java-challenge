package jp.co.axa.apidemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.User;
import jp.co.axa.apidemo.services.UserService;

/**
 * The UserController class is a RESTful controller responsible for managing
 * user-related operations. It offers endpoints for retrieving, creating,
 * updating, and deleting user information. This controller acts as the
 * interface between the API and the underlying service layer, facilitating user
 * data management.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Retrieve a list of all users.
	 * 
	 * @return List of users.
	 */
	@GetMapping("/getAll")
	public List<User> getAllUsers() {
		List<User> users = userService.retrieveAllUsers();
		return users;
	}

	/**
	 * Retrieve a user by their unique ID.
	 * 
	 * @param userId The ID of the user to retrieve.
	 * @return The user with the specified ID.
	 */
	@GetMapping("/getById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long userId) {
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}

	/**
	 * Register a new user.
	 * 
	 * @param user The user to register.
	 * @return A ResponseEntity with the registered user and an HTTP status code.
	 */
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	/**
	 * Delete a user by their ID.
	 * 
	 * @param userId The ID of the user to delete.
	 * @return A ResponseEntity with a message confirming the deletion and an HTTP
	 *         status code.
	 */
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("User deleted successfully with User ID: " + userId);
	}

	/**
	 * Update a user by their ID.
	 * 
	 * @param user   The updated user information.
	 * @param userId The ID of the user to update.
	 * @return A ResponseEntity with the updated user and an HTTP status code.
	 */
	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(name = "userId") Long userId) {
		return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.OK);
	}

}
