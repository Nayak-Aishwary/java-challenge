package jp.co.axa.apidemo.services;

import java.util.List;

import jp.co.axa.apidemo.entities.User;

public interface UserService {

	public List<User> retrieveAllUsers();

    public User getUserById(Long UserId);
    
    public User saveUser(User User);

    public void deleteUser(Long UserId);

    public User updateUser(User employee, Long id);
}
