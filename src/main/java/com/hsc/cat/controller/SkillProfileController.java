package com.hsc.cat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.VO.DeleteSkillsFromProfileVO;
import com.hsc.cat.service.SkillProfileService;

@RestController
public class SkillProfileController {

	@Autowired
	private SkillProfileService skillProfileService;
	

	/*
	 * Method to delete skills from profile
	 */
	@ResponseBody
	@RequestMapping(value="cat/deleteSkillsFromProfile",method=RequestMethod.DELETE)
	@CrossOrigin
	public String deleteSkillsFromProfile(@RequestBody DeleteSkillsFromProfileVO incomingVO) {
	return	skillProfileService.deleteSkillsFromProfile(incomingVO);
		
	}
}
