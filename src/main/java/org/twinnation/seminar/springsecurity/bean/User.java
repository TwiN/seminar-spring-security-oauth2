package org.twinnation.seminar.springsecurity.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;


@Entity
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	private String password;
	
	private boolean isAdmin;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	public User(Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		this.id = user.getId();
		this.username = user.getUsername();
		this.isAdmin = user.isAdmin();
	}
	
	
	public User() {}
	
	
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	
	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}
	
	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
	@Transient
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Transient
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Transient
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Transient
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	public static User fromOAuth2User(OAuth2User oAuth2User) {
		User user = new User();
		user.setUsername(oAuth2User.getName());
		Map<String, Object> attributes = oAuth2User.getAttributes();
		user.setUsername((String)attributes.getOrDefault("login", attributes.getOrDefault("username", attributes.get("email"))));
		return user;
	}
}
