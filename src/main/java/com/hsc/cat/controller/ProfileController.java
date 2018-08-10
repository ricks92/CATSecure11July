package com.hsc.cat.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.TO.GetResourcesForEachProjRoleResponse;
import com.hsc.cat.TO.ViewProfileTO;
import com.hsc.cat.VO.UpdateProfileVO;
import com.hsc.cat.service.EmployeeSkillService;
import com.hsc.cat.service.ProfileService;
import com.hsc.cat.utilities.JSONOutputEnum;
import com.hsc.cat.utilities.JSONOutputModel;
import com.hsc.cat.utilities.RESTURLConstants;
import com.hsc.cat.utilities.Roles;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProfileController {

	
	private static final Logger LOGGER = (Logger) LogManager.getLogger(EmployeeSkillService.class);
	@Autowired
	private ProfileService profileService;
	/*private static final Logger LOGGER = (Logger) LogManager.getLogger(EmployeeSkillService.class);
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value=RESTURLConstants.CREATE_PROFILE,method=RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public JSONOutputModel createProfile(@RequestBody CreateProfileVO profileVO) {
		JSONOutputModel output= new JSONOutputModel();
		LOGGER.info("Request came to create profile for:"+profileVO.getUserName());
	
//		if(profileVO.getUsername()==null) {
//			output.setMessage("Username cannot be null");
//			output.setStatus(JSONOutputEnum.FAILURE.getValue());
//			return output;
//		}
		ProfileTO createProfileTO= profileService.createProfile(profileVO);
		output.setData(createProfileTO);
		if(createProfileTO!=null) {
			output.setMessage("Profile updated successfully");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		else {
			output.setMessage("Profile could not be  updated");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		
		return output;
	}
	
	
	
	@RequestMapping(value=RESTURLConstants.VIEW_PROFILE,method=RequestMethod.GET)
	@CrossOrigin
	@ResponseBody
	public JSONOutputModel viewProfile(@PathVariable("empId")String empId) {
		JSONOutputModel output= new JSONOutputModel();
		ProfileTO createProfileTO= profileService.viewProfile(empId);
		output.setData(createProfileTO);
		
		
		if(createProfileTO!=null && createProfileTO.getProjectRole()!=null && !createProfileTO.getSelectedSkills().isEmpty()) {
			output.setMessage("Profile complete..Fetched successfully");
			output.setStatus(StatusCode.PROFILE_COMPLETE);
		}
		else if(createProfileTO!=null && createProfileTO.getProjectRole()!=null || !createProfileTO.getSelectedSkills().isEmpty()) {
			output.setMessage("Profile incomplete..Fetched successfully");
			output.setStatus(StatusCode.PROFILE_INCOMPLETE);
		}
		
		else {
			output.setMessage("You are giving invalid username");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		return output;
	}*/
	
	
	
	@RequestMapping(value=RESTURLConstants.VIEW_PROFILE,method=RequestMethod.GET,produces = "application/json",consumes="application/json")
	@CrossOrigin
	@ResponseBody
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel viewProfile(@PathVariable("empId")String empId) {
		JSONOutputModel output= new JSONOutputModel();
		ViewProfileTO viewProfileTO=profileService.viewProfile2(empId);
		
		if(viewProfileTO!=null) {
			output.setData(viewProfileTO);
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Profile fetched successfully");
		}
		else {
			output.setData(viewProfileTO);
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Profile entry not there");
		}
		return output;
		
	}
	
	
	
	@RequestMapping(value="cat/getResourcesForEachProjectRole",method=RequestMethod.GET,produces = "application/json",consumes="application/json")
	@CrossOrigin
	@ResponseBody
	@ApiOperation("Populate pie chart showing the number of resources for each Project role")
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel getResourcesForEachProjectRole() {
		JSONOutputModel output= new JSONOutputModel();
		GetResourcesForEachProjRoleResponse response=profileService.getResourcesForEachProjectRole();
		output.setData(response);
		if(response!=null && response.getList()!=null && !response.getList().isEmpty()) {
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Pie chart data fetched successfully");
		}
		
		else {
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("No data found");
		}
		return output;
	}
	
	
	@RequestMapping(value="cat/profile",method=RequestMethod.PUT,produces = "application/json",consumes="application/json")
	@CrossOrigin
	@ResponseBody
	@ApiOperation("Update profile")
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel updateProfile(@RequestBody UpdateProfileVO updateProfileVO) {
		JSONOutputModel output= new JSONOutputModel();
		ViewProfileTO viewProfileTO=profileService.updateProfile(updateProfileVO);
		if(viewProfileTO!=null) {
			output.setData(viewProfileTO);
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Profile updated successfully");
		}
		else {
			output.setData(viewProfileTO);
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Profile entry not there");
		}
		return output;
	}
}
