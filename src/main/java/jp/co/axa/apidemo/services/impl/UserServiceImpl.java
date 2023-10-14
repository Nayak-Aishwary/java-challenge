package jp.co.axa.apidemo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.User;
import jp.co.axa.apidemo.exception.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.UserRepository;
import jp.co.axa.apidemo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
	}

	@Override
	public User saveUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		userRepository.deleteById(userId);
	}

	@Override
	public User updateUser(User user, Long id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		existingUser.setMobile(user.getMobile());
		existingUser.setRole(user.getRole());
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		userRepository.save(existingUser);
		return existingUser;
	}

}
