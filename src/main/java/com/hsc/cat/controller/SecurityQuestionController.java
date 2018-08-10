package com.hsc.cat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.entity.SecurityQuestions;
import com.hsc.cat.service.SecurityQuestionService;
import com.hsc.cat.utilities.Roles;

@RestController
public class SecurityQuestionController {

	
	@Autowired
	private SecurityQuestionService securityQuestionService;
	
	
	@ResponseBody
	@RequestMapping(value="/cat/securityQuestions",method=RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public ResponseEntity<List<SecurityQuestions>> getSecurityQuestions(){
		List<SecurityQuestions> list= securityQuestionService.getSecurityQuestions();
		return new ResponseEntity<List<SecurityQuestions>>(list, HttpStatus.OK);
		
	}
}
