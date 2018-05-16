package org.twinnation.seminar.springsecurity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.twinnation.seminar.springsecurity.bean.User;
import org.twinnation.seminar.springsecurity.repository.UserRepository;
import org.twinnation.seminar.springsecurity.util.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
public class UserService extends DefaultOAuth2UserService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	
	
	public String createUser(String username, String password) {
		if (password.length() < 6) {
			return Utils.jsonReply("ERROR", true, "MESSAGE", "Password is too short");
		} else if (getUserByUsername(username) != null) {
			return Utils.jsonReply("ERROR", true, "MESSAGE", "Username is already taken");
		}
		User user = userRepository.save(new User(username, passwordEncoder.encode(password)));
		return Utils.jsonReply("ERROR", false, "MESSAGE", "User has been created successfully");
	}
	
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("[loadUserByUsername] username="+username);
		User user = getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("There is no user with the username '"+username+"'.");
		}
		Set<GrantedAuthority> authorities = new HashSet<>();
		if (user.isAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		user.setAuthorities(authorities);
		return user;
	}
	
	
	/**
	 * This method loads users connected through OAuth2.
	 * For now, all users will have the role ROLE_USER to prevent complications.
	 */
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User user = super.loadUser(userRequest);
		Map<String, Object> attributes = user.getAttributes();
		Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());
		
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		String userNameAttributeName =
			userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		
		return new DefaultOAuth2User(authorities, attributes, userNameAttributeName);
	}
	
	
	public User getCurrentUser() {
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			logger.debug("[getCurrentUser] class = " + authentication.getClass().getSimpleName() + "; name = " + authentication.getName());
		}
		if (authentication instanceof OAuth2AuthenticationToken) {
			OAuth2AuthenticationToken at = (OAuth2AuthenticationToken)authentication;
			user = User.fromOAuth2User(at.getPrincipal());
		} else if (authentication instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken at = (UsernamePasswordAuthenticationToken)authentication;
			user = getUserByUsername(at.getName());
			user.setPassword(null);
		}
		return user;
	}
	
}
