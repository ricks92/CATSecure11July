package com.hsc.cat.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.TO.SearchSkillTOList;
import com.hsc.cat.TO.SkillTO;
import com.hsc.cat.VO.AddSkillVO;
import com.hsc.cat.enums.SkillCategoryEnum;
import com.hsc.cat.enums.SkillSubCategoryEnum;
import com.hsc.cat.service.SkillService;
import com.hsc.cat.utilities.JSONOutputEnum;
import com.hsc.cat.utilities.JSONOutputModel;
import com.hsc.cat.utilities.RESTURLConstants;
import com.hsc.cat.utilities.Roles;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class SkillController {


	private static final Logger LOGGER = (Logger) LogManager.getLogger(SkillController.class);
	
	@Autowired
	private SkillService skillService;
	
	@ApiOperation(value="Add a new skill to database")
	@ResponseBody
	@RequestMapping(value=RESTURLConstants.SKILLS,method=RequestMethod.POST)
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel addSkill(@RequestBody AddSkillVO svo) {
		LOGGER.debug("Inside add skill controller method");
		JSONOutputModel output = new JSONOutputModel();
		SkillTO skillTO=skillService.addSkill(svo);
		if(skillTO!=null) {
			
			if(skillTO.getIssue()!=null) {
				output.setData(new String(skillTO.getIssue()));
				output.setStatus(JSONOutputEnum.FAILURE.getValue());
				output.setMessage(skillTO.getIssue());
				LOGGER.error(skillTO.getIssue());
			}
			else {
			output.setData(skillTO);
			output.setStatus(HttpStatus.CREATED.value());
			output.setMessage("Skills saved successfully");
			LOGGER.debug("Skills saved successfully");
			}
			
		}
		else {
			output.setData(skillTO);
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Skills could not be saved");
			LOGGER.error("Skills could not be saved");
		}
		
		return output;
	}
	
	@ApiOperation(value="Fetch all the skills from database")
	@ResponseBody
	@RequestMapping(value=RESTURLConstants.SKILLS,method=RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel fetchAllSkills() {
		LOGGER.debug("Inside fetch all skills controller method");
		JSONOutputModel output = new JSONOutputModel();
		
		List<SkillTO> skillTOList=skillService.fetchAllSkills();
		if(!skillTOList.isEmpty() && skillTOList.size()!=0) {
			output.setData(skillTOList);
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Skills fetched successfully");
			LOGGER.debug("Skills fetched successfully");
		}else {
			output.setData(skillTOList);
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("No skill found");
			LOGGER.error("No skill found");
		}
		
		return output;
	}
	
	
	@ResponseBody
	@RequestMapping(value="searchSkill/{skillName}",method=RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel searchSkill(@PathVariable("skillName") String skillName) {
		JSONOutputModel output = new JSONOutputModel();
		LOGGER.debug("Inside search skill controller method");
		SearchSkillTOList searchSkillTO=skillService.skillExists(skillName);
		
		if(searchSkillTO.isExists()) {
			output.setData(searchSkillTO.getSkills());
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Skill already exists!!!");
			LOGGER.debug("Skill already exists!!!");
		}
		else {
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Skill does not exist!!!");
			LOGGER.error("Skill does not exist!!!");
		}
		
		return output;
	}
	
	
	@ApiOperation(value="Fetch all skills in a given category")
	@ResponseBody
	@RequestMapping(value=RESTURLConstants.FETCH_SKILLS_IN_A_CATEGORY,method=RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel fetchSkillsInACategory(@PathVariable("skillCategory")int skillCategory) {
		JSONOutputModel output = new JSONOutputModel();
		List<SkillTO> skillTOList=skillService.fetchSkillsInACategory(skillCategory);
		output.setData(skillTOList);
		if(skillTOList!=null && !skillTOList.isEmpty()) {
			output.setMessage("Skills fetched in skill category:"+SkillCategoryEnum.getTechnologyNameFromNumber(skillCategory));
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		
		else {
			output.setMessage("No skill in skill category:"+SkillCategoryEnum.getTechnologyNameFromNumber(skillCategory));
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		return output;
	}
	
	@ApiOperation(value="Fetch all skills in a given category and subcategory")
	@ResponseBody
	@RequestMapping(value=RESTURLConstants.FETCH_SKILLS_IN_A_CATEGORY_SUBCATEGORY,method=RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel fetchSkillsInACategorySubcategory(@RequestParam("skillCategory")int skillCategory,@RequestParam("skillSubCategory")int skillSubCategory) {
		JSONOutputModel output = new JSONOutputModel();
		List<SkillTO> skillTOList=skillService.fetchSkillsInACategorySubcategory(skillCategory, skillSubCategory);
		output.setData(skillTOList);
		if(skillTOList!=null && !skillTOList.isEmpty()) {
			output.setMessage("Skills fetched in skill category:"+SkillCategoryEnum.getTechnologyNameFromNumber(skillCategory) +" and subCategory: "+SkillSubCategoryEnum.getTechnologyNameFromNumber(skillSubCategory));
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		
		else {
			output.setMessage("No skill found in skill category:"+SkillCategoryEnum.getTechnologyNameFromNumber(skillCategory) +" and subCategory: "+SkillSubCategoryEnum.getTechnologyNameFromNumber(skillSubCategory));
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		return output;
	}
	
	/*
	 * To populate dropdown in profile
	 * with skills of other domains
	 */
	@ResponseBody
	@RequestMapping(value="cat/fetchSkillsNotInACategory/{role}",method=RequestMethod.GET)
	@CrossOrigin
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel fetchSkillsNotInACategory(@PathVariable("role")int role) {
		JSONOutputModel output = new JSONOutputModel();
		List<String> skillList=skillService.fetchSkillsNotInACategory(role);
		output.setData(skillList);
		if(skillList!=null && !skillList.isEmpty()) {
			output.setMessage("Other domain skills fetched successfully");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		else {
			output.setMessage("No skills there in other doamins");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		
		
		return output;
	}
}
