package com.hsc.cat.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsc.cat.TO.ResponseTO;
import com.hsc.cat.TO.UserTO;
import com.hsc.cat.VO.ChangePasswordVO;
import com.hsc.cat.VO.ForgetPasswordVO;
import com.hsc.cat.VO.ValidateSecurityQuestionVO;
import com.hsc.cat.entity.EmployeeDetails;
import com.hsc.cat.entity.UserDetailsEntity;
import com.hsc.cat.repository.EmployeeDetailRepository;
import com.hsc.cat.repository.UserRepository;


@Service
public class UserService implements UserDetailsService{

	
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public void save(UserTO user) {
		UserDetailsEntity newUser = new UserDetailsEntity();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		entityManager.persist(newUser);
    }
	
	@Transactional
	public void update(UserTO user) {
		UserDetailsEntity newUser = entityManager.find(UserDetailsEntity.class,user.getUsername());
		UserDetails userDetails = loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
        if(authentication.isAuthenticated()) {
        	newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
		entityManager.merge(newUser);
    }
	
	@Transactional
	public void delete(String username) {
		UserDetailsEntity user = entityManager.find(UserDetailsEntity.class,username);
		entityManager.remove(user);
    }
	
    @Override
    @Cacheable("user")
    @Transactional(readOnly = true)
    public UserTO loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserDetailsEntity user = entityManager.find(UserDetailsEntity.class,username);
    	System.out.println("\n\n\n\n\n\n\n*****"+user);
	    if(user!=null) {
	    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
	    return new UserTO(user.getUsername(), user.getPassword(),user.getRole(), grantedAuthorities);
	    }
	    else {
	    	throw new UsernameNotFoundException("User Name Not Found");
	    }
    }
	/*@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeDetailRepository employeeDetailRepository;
	
	
	
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	public void setEncoder(BCryptPasswordEncoder encoder){
		this.encoder=encoder;
	}
	
	
	 @Autowired
	    private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("customAuthenticationProvider")
	private AuthenticationProvider authenticationProvider;
//	
//	public UserTO registerUser(UserDetails userDetails){
//		
//	UserDetails u=	userRepository.save(userDetails);
//		UserTO userTO = modelConversion(u);
//		return userTO;
//	}
//	
//	
//	public UserTO modelConversion(UserDetails userDetails){
//		UserTO userTO = new UserTO();
//		userTO.setUsername(userDetails.getUsername());
//		userTO.setPassword(userDetails.getPassword());
//		userTO.setRole(userDetails.getRole());
//		
//		return userTO;
//	}
	
	public boolean validateSecurityQuestion(ValidateSecurityQuestionVO validateSecurityQuestionVO) {
		UserDetailsEntity user = userRepository.findOne(validateSecurityQuestionVO.getEmpId());
		EmployeeDetails emp=user.getEmployeeDetails();
		//if(emp!=null && (emp.getSecurityQues1().equals(validateSecurityQuestionVO.getSecurityQuestion()) && emp.getSecurityAns1().equals(validateSecurityQuestionVO.getAnswer() ) ) ||  (emp.getSecurityQues2().equals(validateSecurityQuestionVO.getSecurityQuestion() )&& emp.getSecurityAns2().equals(validateSecurityQuestionVO.getAnswer()  )  ) ) 
		if(emp!=null && (emp.getSecurityQues1().equals(validateSecurityQuestionVO.getSecurityQuestion()) && encoder.matches(validateSecurityQuestionVO.getAnswer(), emp.getSecurityAns1()) )  ||  (emp.getSecurityQues2().equals(validateSecurityQuestionVO.getSecurityQuestion() )&& encoder.matches(validateSecurityQuestionVO.getAnswer(), emp.getSecurityAns2())  ) )	
		{
			return true;
		}
		
		return false;
	}
	
	
	public ResponseTO checkValidUser(String userName, String password, String role,HttpServletRequest request) {
		boolean userAvailable = Boolean.FALSE;
		boolean userPassword = Boolean.FALSE;
		
		//session.invalidate();

		ResponseTO responseTO = new ResponseTO();
		responseTO.setResponseCode("0");
		responseTO.setResponseMessage("FAILURE");
		//List<UserDetailsEntity> userDetailsList = userRepository.findAll();
		//Iterator itr = userDetailsList.iterator();
	//	while (itr.hasNext()) {
		
			UserTO user = loadUserByUsername(userName);
			
			System.out.println(user.getUsername()+" "+user.getRole());
			if (user.getUsername().equalsIgnoreCase(userName)) {
				userAvailable = Boolean.TRUE;
				//if (user.getPassword().equals(password)) {
				if (encoder.matches(password, user.getPassword())) {
					userPassword = Boolean.TRUE;
					if (user.getRole().equalsIgnoreCase(role)) {
						UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
						System.out.println(token.getPrincipal());
//						token.setDetails(new WebAuthenticationDetails(request));
//						  SecurityContext sc = SecurityContextHolder.getContext();
//						Authentication auth = authenticationProvider.authenticate(token);
//					    sc.setAuthentication(auth);
						 authenticationManager.authenticate(token);
						  HttpSession newSession = request.getSession(); // create session
						  newSession.setMaxInactiveInterval(120);
					
			
					  System.out.println("Logged in user:"+findLoggedInUsername());
					   // System.out.println(sc);
					  //  HttpSession session = request.getSession(true);
					   // session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
					   // System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
					  
					  if (token.isAuthenticated()) {
						  System.out.println(token.getAuthorities());
						responseTO.setResponseCode("1");
						responseTO.setResponseMessage("SUCCESS");
					  }
						
						//break;
					} else {

						responseTO.setResponseCode("3");
						responseTO.setResponseMessage("Incorrect Role");
						//break;
					}
				}

			}
	//	}
		if (userAvailable && userPassword == false) {
			responseTO.setResponseCode("2");
			responseTO.setResponseMessage("Password Incorrect");

		}
		return responseTO;
	}
	
	
public ResponseTO updatePassword(ChangePasswordVO changePasswordVO) {
		
		int updatdRow=0;
		ResponseTO responseTO=new ResponseTO();
		String newPassword=changePasswordVO.getNewPassword();
		String userName=changePasswordVO.getUserName();
		String currentPassword=changePasswordVO.getCurrentPassword();
		
		if(validateParameter(currentPassword) && validateParameter(userName) && validateParameter(newPassword)) {
			UserDetailsEntity userDetails=userRepository.findOne(userName);
			if(null!=userDetails) {
				if(!currentPassword.equals(newPassword)) {
				//if(userDetails.getPassword().equals(currentPassword)) {
					if(encoder.matches(currentPassword, userDetails.getPassword())) {
					 updatdRow =userRepository.updatePasswordInDB(userName,encoder.encode(newPassword));
					 if(updatdRow>0) {
						 responseTO.setResponseCode("1");
						 responseTO.setResponseMessage("SUCCESS");
					 }else {
						
							 responseTO.setResponseCode("0");
							 responseTO.setResponseMessage("FAILURE");
						
					 }
				}else {
					
					 responseTO.setResponseCode("10");
					 responseTO.setResponseMessage("Password mismatch");
				
			 }}else {
				
				 responseTO.setResponseCode("11");
				 responseTO.setResponseMessage("Current paasword and new password should be different");
			
		 }
			}else {
				
				 responseTO.setResponseCode("0");
				 responseTO.setResponseMessage("FAILURE");
			
		 }
		
			
		}else {
			responseTO.setResponseCode("5");
			responseTO.setResponseMessage("Invalid Parameter");
		}
		
		return responseTO;
	}
	
	public ResponseTO forgetPasswordForAll(ForgetPasswordVO forgetPasswordVO) {
		int updatdRow=0;
		ResponseTO responseTO=new ResponseTO();

		 String userName=forgetPasswordVO.getUserName();
		 String newPassword=forgetPasswordVO.getPassword();
		 
		 if(validateParameter(userName) && validateParameter(newPassword)  ) {
			 EmployeeDetails employeeDetails=employeeDetailRepository.findOne(userName);
			 if(null!=employeeDetails) {
			
				 updatdRow =userRepository.updatePasswordInDB(userName,encoder.encode(newPassword));
				 if(updatdRow>0) {
					 responseTO.setResponseCode("1");
					 responseTO.setResponseMessage("SUCCESS");
				 }else {
					
						 responseTO.setResponseCode("0");
						 responseTO.setResponseMessage("FAILURE");
					
				 }
			 }else{
					responseTO.setResponseCode("9");
					responseTO.setResponseMessage("No such employee exist");
				 
			 }
		 }else {
				responseTO.setResponseCode("5");
				responseTO.setResponseMessage("Invalid Parameter");
		 }

		return responseTO;
	}
	
	private boolean validateParameter(String parameter) {
		boolean result=Boolean.FALSE;
		
		if(null!=parameter && !parameter.isEmpty()) {
			result=Boolean.TRUE;
		}
		
		return result;
		
	}

@Override
public UserTO loadUserByUsername(String username) throws UsernameNotFoundException {
	
	UserDetailsEntity userEntity= userRepository.findOne(username);
UserTO user = new UserTO();
user.setUsername(username);
user.setPassword(userEntity.getPassword());
user.setRole(userEntity.getRole());

if(user!=null) {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
    return new UserTO(user.getUsername(), user.getPassword(),user.getRole(), grantedAuthorities);
    }
 return user;

}


public void logout(HttpServletRequest request) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){    
    	SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false); 
    //	request.getSession().invalidate();
//    	try {
//			//request.logout();
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        //new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    //SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false); 
	
	
    System.out.println("Logout Successfuly");
}


public String findLoggedInUsername() {
    Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
    if (userDetails instanceof UserDetails) {
        return ((UserDetails)userDetails).getUsername();
    }
    return null;
}

*/

}
