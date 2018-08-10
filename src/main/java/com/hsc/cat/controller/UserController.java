package com.hsc.cat.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.TO.ResponseTO;
import com.hsc.cat.TO.UserTO;
import com.hsc.cat.VO.ChangePasswordVO;
import com.hsc.cat.VO.ForgetPasswordVO;
import com.hsc.cat.VO.ValidateSecurityQuestionVO;
import com.hsc.cat.VO.ValidateUserVO;
import com.hsc.cat.entity.UserDetailsEntity;
import com.hsc.cat.service.UserSecurityService;
import com.hsc.cat.service.UserService;
import com.hsc.cat.utilities.CATConstants;
import com.hsc.cat.utilities.JSONOutputEnum;
import com.hsc.cat.utilities.JSONOutputModel;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController
//@RequestMapping("/auth")
@RequestMapping("/cat")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
    private UserSecurityService securityService;
	
//	@ResponseBody
//    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
//    public String register(@RequestBody UserTO userForm) {
//		userService.save(userForm);
//		return "Success";
//	}
	
	@ResponseBody
    @RequestMapping(value = "/auth/user", method = RequestMethod.PUT)
    public String passwordReset(@RequestBody UserTO userForm) {
		userService.update(userForm);
		return "Success";
	}
	
	@ResponseBody
    @RequestMapping(value = "/auth/user", method = RequestMethod.DELETE)
    public String userDeletion(@RequestBody UserTO userForm)  {
		userService.delete(userForm.getUsername());
		return "Success";
	}
	
	@ResponseBody
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public JSONOutputModel login(@RequestBody UserTO userForm,HttpServletRequest request)  {
    	JSONOutputModel output = new JSONOutputModel();
    	UserTO user = securityService.authlogin(userForm);
		output.setStatus(JSONOutputEnum.SUCCESS.getValue());;
		output.setData(generateToken( user));
		output.setMessage("Login Successful");
		output.setRole(user.getRole());
//		/System.out.println(securityService.findLoggedInUsername());
        return output;
    }
	
	
	

    @RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response)  {
		securityService.logout( request,  response);
		return "Success";
	}
    
    public String generateToken(UserTO user) {
    	
    	JwtBuilder builder = Jwts.builder().setSubject(user.getUsername()).claim("roles", user.getRole());
		
		//builder.claim("userGrp", user.getGroupId());
		
		Date createdDate = new Date();
		builder.setExpiration(new Date(createdDate.getTime() + CATConstants.EXPIRATION * 1000));
		//System.out.println("\n\n****"+createdDate);
		//System.out.println("\n\n****"+new Date(createdDate.getTime() + CATConstants.EXPIRATION * 1000));
		String jwtToken = builder.setIssuedAt(createdDate)
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		return  jwtToken;
    }
	
	
	
	
	
	
	/*@Autowired
	private UserService userDetailService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	
	//Persist a user's details
		@ResponseBody
		@RequestMapping(value="/users",method=RequestMethod.POST)
	public JSONOutputModel registerUser(@RequestBody UserDetails userDetails){
		JSONOutputModel output= new JSONOutputModel();
		UserTO user=userDetailService.registerUser(userDetails);
		if(user!=null){
			output.setData(user);
			output.setMessage("User registered successfully");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}else{
			output.setData(user);
			output.setMessage("User could not be registered ");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		return output;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/secure/validateSecurityQuestion",method=RequestMethod.POST,produces = "application/json",consumes="application/json")
	@CrossOrigin
	public JSONOutputModel validateSecurityQuestion(@RequestBody ValidateSecurityQuestionVO validateSecurityQuestionVO) {
		JSONOutputModel output= new JSONOutputModel();
		
		boolean matches=userDetailService.validateSecurityQuestion(validateSecurityQuestionVO);
		if(matches)
		{
			output.setData(new String("match found"));
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Security question validated");
		}
		
		else {
			output.setData(new String("match not found"));
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Please enter correct answer!");
		}
		return output;
	}
	
	
	
	
	@RequestMapping(value="/auth/validateUser",method=RequestMethod.POST)
	@ResponseBody
	public ResponseTO validateUser(@RequestBody ValidateUserVO validateUserVO,HttpServletRequest request) {
		boolean validUser=Boolean.FALSE;
		ResponseTO responseTO;
		responseTO=userDetailService.checkValidUser(validateUserVO.getUserName(),validateUserVO.getPassword(),validateUserVO.getRole(),request);
		if(null==responseTO) {
			ResponseTO response=new ResponseTO();
			response.setResponseCode("0");
			response.setResponseMessage("FAILURE");
			return response;
		}
		return responseTO;
	}
	
	
	
	@RequestMapping(value="/secure/changePassword",method=RequestMethod.POST)
	@ResponseBody
	public ResponseTO changePassword(@RequestBody ChangePasswordVO changePasswordVO) {
		
		ResponseTO responseTO=null;
		responseTO=userDetailService.updatePassword(changePasswordVO);
		
		
		return responseTO;
		
	}
	
	@RequestMapping(value="/secure/forgetPassword",method=RequestMethod.POST)
	@ResponseBody
	public ResponseTO forgetPassword(@RequestBody ForgetPasswordVO forgetPasswordVO) {
		
		ResponseTO responseTO=null;
		responseTO=userDetailService.forgetPasswordForAll(forgetPasswordVO);
		
		
		return responseTO;
	}
	
	
	@RequestMapping(value = "/secure/logout", method = RequestMethod.GET)
	@ResponseBody
    public String logout(HttpServletRequest request) {
		userDetailService.logout( request);
		return "Success";
	}
	
	@RequestMapping("/auth/logoutSuccess")
	@ResponseBody
 public String logoutSuccess(){
	 return "Logout done successfully";
 }
    */
	
	
	
	
	
}
