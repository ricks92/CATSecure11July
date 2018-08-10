package com.hsc.cat.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.hsc.cat.TO.UserTO;
import com.hsc.cat.entity.UserDetailsEntity;
import com.hsc.cat.repository.UserRepository;




@Service
public class UserSecurityService {
	
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserService userDetailsService;
	
/*	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsEntity user = userRepository.findByUsername(username);
		
		if(null == user) {
			throw new UsernameNotFoundException("Username not found");
		}
		UserTO userTO=new UserTO();
		userTO.setUsername(user.getUsername());
		userTO.setPassword(user.getPassword());
		userTO.setRole(user.getRole());
		if(userTO!=null) {
		    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		    grantedAuthorities.add(new SimpleGrantedAuthority(userTO.getRole()));
		    return new UserTO(userTO.getUsername(), userTO.getPassword(),userTO.getRole(), grantedAuthorities);
		    }
		 return userTO;
		
	}*/

	
	 public void logout(HttpServletRequest request, HttpServletResponse response) {
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null){    
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	        }
		    //SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false); 
		    //LOGGER.debug("logout Successfuly");
	    }
	 
	 
	 
	 
	 
	 
	 public UserTO authlogin(UserTO userDetails)  {
	    	String userName = userDetails.getUsername();
			String password = userDetails.getPassword();
			//LOGGER.debug("login attemp for "+userName);
			if (userName == null || password == null) {
				System.out.println("Please fill in username and password");
				//throw new VGSException("Please fill in username and password");
			}
			UserTO userDetailsAuth = userDetailsService.loadUserByUsername(userDetails.getUsername());
	    	if (userDetailsAuth == null) {
	    		System.out.println("User not found"+userName);
				//throw new VGSException("User not found"+userName);
			}
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetailsAuth, userDetails.getPassword()
	        		, userDetailsAuth.getAuthorities());
	        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	            //SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	           // LOGGER.debug(String.format("Auth login %s successfully!", userDetails.getUsername()));
	        }
		    return userDetailsAuth;
	    }
	 
	 
	 
	 
	 
	 
	   public String findLoggedInUsername() {
	        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
	        if (userDetails instanceof UserDetails) {
	            return ((UserDetails)userDetails).getUsername();
	        }
	        return null;
	    }
}
