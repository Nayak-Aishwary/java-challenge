package jp.co.axa.apidemo.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.User;
import jp.co.axa.apidemo.repositories.UserRepository;

/**
 * The `UserDetailsServiceImpl` class is a service implementation responsible for
 * custom user details retrieval and authentication. It implements the Spring Security
 * `UserDetailsService` interface to load user details from the database for authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    UserRepository userRepository;

    /**
     * Load user details by email for authentication.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailId(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with emailId " + email + " not found!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(), getGrantedAuthority(user));
    }

    /**
     * Get user's granted authorities based on their role.
     */
    private Collection<GrantedAuthority> getGrantedAuthority(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole().getName().equalsIgnoreCase("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

}
