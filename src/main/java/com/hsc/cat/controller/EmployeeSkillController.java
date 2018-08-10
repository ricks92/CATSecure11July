/**
**********************************************************************************************************
--  FILENAME		: FetchMapController3.java
--  DESCRIPTION		: REST API for self assessment, peer assessment
--
--  Copyright		: Copyright (c) 2018.
--  Company			: HSC
--
--  Revision History
-- --------------------------------------------------------------------------------------------------------
-- |VERSION |      Date                              |      Author              |      Reason for Changes                                         |
-- --------------------------------------------------------------------------------------------------------
-- |  0.1   |   April 2, 2018                         |     Richa Anand      |       Initial draft                                                        |
-- --------------------------------------------------------------------------------------------------------
--
************************************************************************************************************
**/

package com.hsc.cat.controller;

import java.util.List;

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

import com.hsc.cat.TO.MapTO;
import com.hsc.cat.TO.SelfRatedSkillsTO;
import com.hsc.cat.TO.SkillTO;
import com.hsc.cat.TO.UpdateSkillResponse;
import com.hsc.cat.TO.UpdateSkillTO;
import com.hsc.cat.TO.ViewSkillListTO;
import com.hsc.cat.VO.GetAllPeerReviewedSkillsVO;
import com.hsc.cat.VO.MapVO;
import com.hsc.cat.VO.UpdateSkillsListVO;
import com.hsc.cat.enums.SdlcCategory;
import com.hsc.cat.service.EmployeeSkillService;
import com.hsc.cat.utilities.JSONOutputEnum;
import com.hsc.cat.utilities.JSONOutputModel;
import com.hsc.cat.utilities.RESTURLConstants;
import com.hsc.cat.utilities.Roles;

import io.swagger.annotations.ApiOperation;


@RestController
public class EmployeeSkillController {

	private static final Logger LOGGER = (Logger) LogManager.getLogger(EmployeeSkillController.class);
	
	@Autowired
	private EmployeeSkillService updateSkillService;
	
	@ApiOperation(value="Add rating for self or peer")
	@ResponseBody
	@RequestMapping(value=RESTURLConstants.UPDATE_SKILL,method=RequestMethod.POST,produces = "application/json",consumes="application/json")
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel updateSkill(@RequestBody UpdateSkillsListVO updateSkillsListVO) {
		JSONOutputModel output = new JSONOutputModel();
		
		
		if(updateSkillsListVO.getListOfEmployeeSkills()==null) {
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Invalid request");
			return output;
		}
		else if(updateSkillsListVO.getListOfEmployeeSkills().isEmpty()) {
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Invalid request");
			return output;
		}
		UpdateSkillResponse response=updateSkillService.updateSkill(updateSkillsListVO);
		 List<UpdateSkillTO> updateSkillTOList=response.getUpdateSkillTOList();
		
		if(response!=null && response.getUpdateSkillTOList()!=null && !response.getUpdateSkillTOList().isEmpty() && response.getUpdateSkillTOList().size()!=0 ) {
			output.setData(response.getUpdateSkillTOList());
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Skills updated successfully");
			LOGGER.debug("Skills updated successfully");
		}
		
		else {
			
		
//				output.setData(response.getUpdateSkillTOList());
				output.setStatus(JSONOutputEnum.FAILURE.getValue());
				output.setMessage("Skills could not be updated");
			
		}
		
		
		return output;
		
	}
	
	@ApiOperation(value="View ratings of a user")
	@ResponseBody
	@RequestMapping(value=RESTURLConstants.VIEW_SKILL,method=RequestMethod.GET,produces = "application/json",consumes="application/json")
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel viewSkills(@PathVariable ("empId") String empid,@PathVariable("viewHistory")boolean flag) {
		JSONOutputModel output = new JSONOutputModel();
		
		ViewSkillListTO viewSkillListTO= new ViewSkillListTO();
		
		
		if(flag)
		{
			 viewSkillListTO=updateSkillService.getViewHistory(empid);	
		}
		else {
		
		 viewSkillListTO = updateSkillService.viewSkills(empid);
		}
		
		
		System.out.println(viewSkillListTO);
		//System.out.println(viewSkillListTO.getListOfEmployeeSkills().size());
		if(viewSkillListTO!=null && viewSkillListTO.getListOfEmployeeSkills()!=null && !viewSkillListTO.getListOfEmployeeSkills().isEmpty() &&viewSkillListTO.getListOfSkillId()!=null &&  !viewSkillListTO.getListOfSkillId().isEmpty()) {
			output.setData(viewSkillListTO);
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Employee skills fetched successfully");
			LOGGER.debug("Employee skills fetched successfully");
		}
		else {
			output.setData(viewSkillListTO);
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Employee skills entry not there");
			LOGGER.debug("Employee skills entry not there");
		}
		
		
		
		return output;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/fetchMap",method=RequestMethod.POST,produces = "application/json",consumes="application/json")
	@CrossOrigin
	public JSONOutputModel fetchMapDetails(@RequestBody MapVO mapVO) {
		JSONOutputModel output = new JSONOutputModel();
		MapTO map=updateSkillService.fetchMapDetails(mapVO);
		if(map!=null && !map.getMap().isEmpty()) {
			output.setData(map);
			output.setMessage("Map details fetched successfuly");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		else {
			output.setData(map);
			output.setMessage("No data found for this quarter");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		
		
		return output;
		
	}
	
	@ApiOperation(value="Get all self assessed skills")
	@ResponseBody
	@RequestMapping(value="/cat/getAllSelfRatedSkills/{empId}/{sdlcCategoryNum}",method=RequestMethod.GET,produces = "application/json",consumes="application/json")
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel getAllSelfRatedSkills(@PathVariable("empId") String empId,@PathVariable("sdlcCategoryNum")int sdlcCategoryNum) {
		JSONOutputModel output = new JSONOutputModel();
		List<SelfRatedSkillsTO> list=updateSkillService.getAllSelfRatedSkills(empId,sdlcCategoryNum);
		
		System.out.println(list.size());
		if(list!=null && !list.isEmpty()) {
			output.setData(list);
			output.setMessage("Rated skill details fetched successfully");
			LOGGER.debug("Rated skill details fetched successfully");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		
		else {
			output.setData(list);
			output.setMessage("No rated skill details found for employee:"+empId);
			LOGGER.debug("No rated skill details found for employee:"+empId);
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		return output;
	}
	
	@ApiOperation(value="Get all skills that have not been self assessed")
	@ResponseBody
	@RequestMapping(value="/cat/getAllNotSelfRatedSkills/{empId}/{sdlcCategoryNum}",method=RequestMethod.GET,produces = "application/json",consumes="application/json")
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel getAllNotSelfRatedSkills(@PathVariable("empId") String empId,@PathVariable("sdlcCategoryNum")int sdlcCategoryNum) {
		JSONOutputModel output = new JSONOutputModel();
		List<SkillTO> list=updateSkillService.getAllNotSelfRatedSkills(empId,sdlcCategoryNum);
		
		System.out.println("Total unrated skills found:"+list.size());
		if(list!=null && !list.isEmpty()) {
			output.setData(list);
			output.setMessage("Unrated skill details fetched successfully");
			LOGGER.debug("Unrated skill details fetched successfully");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		
		else {
			output.setData(list);
			output.setMessage("No Unrated skill details found for employee:"+empId);
			LOGGER.debug("No Unrated skill details found for employee:"+empId);
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		return output;
	}
	
	@ApiOperation(value="Get all peer reviewed skills")
	@ResponseBody
	@RequestMapping(value="cat/getAllPeerReviewedSkills",method=RequestMethod.POST,produces = "application/json",consumes="application/json")
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel getAllPeerReviewedSkills(@RequestBody GetAllPeerReviewedSkillsVO getAllPeerReviewedSkillsVO){
		JSONOutputModel output = new JSONOutputModel();
		if(getAllPeerReviewedSkillsVO==null) {
			output.setMessage("Empty request body");
			LOGGER.debug("Empty request body");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			return output;
		}
	if(getAllPeerReviewedSkillsVO.getEmpId()==null ||getAllPeerReviewedSkillsVO.getEmpId().equals("")||getAllPeerReviewedSkillsVO.getPeerEmpId()==null||getAllPeerReviewedSkillsVO.getPeerEmpId().equals("")) {
			output.setMessage("Invalid parameters--empid");
			LOGGER.debug("Invalid parameters--empid");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		return output;
		}
		
	if(SdlcCategory.getSdlcCategoryName(getAllPeerReviewedSkillsVO.getSdlcCategoryNum()).equalsIgnoreCase("Invalid"))
		{
			output.setMessage("Invalid parameters for SDLC category");
			LOGGER.debug("Invalid parameters for SDLC category");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		return output;
		}
		
		
		
		List<SelfRatedSkillsTO> list=updateSkillService.getAllPeerReviewedSkills(getAllPeerReviewedSkillsVO);
		if(list!=null && !list.isEmpty()) {
			output.setData(list);
			output.setMessage("Rated skill details fetched successfully");
			LOGGER.debug("Rated skill details fetched successfully");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		
		else {
			output.setData(list);
			output.setMessage("No rated skill details found for employee:"+getAllPeerReviewedSkillsVO.getEmpId());
			LOGGER.debug("No rated skill details found for employee:"+getAllPeerReviewedSkillsVO.getEmpId());
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		return output;
	}
}
