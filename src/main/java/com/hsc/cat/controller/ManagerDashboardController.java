package com.hsc.cat.controller;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.TO.GetPieChartForCountPerLevelPerSkillPerSDLCTO;
import com.hsc.cat.TO.PieChartForCountPerLevelPerSkillComboTO;
import com.hsc.cat.VO.GetPieChartForCountPerLevelPerSkillPerSDLCVO;
import com.hsc.cat.VO.PieChartForCountPerLevelPerSkillComboVO;
import com.hsc.cat.enums.SdlcCategory;
import com.hsc.cat.service.ManagerDashboardService;
import com.hsc.cat.utilities.JSONOutputEnum;
import com.hsc.cat.utilities.JSONOutputModel;
import com.hsc.cat.utilities.Roles;

import io.swagger.annotations.ApiOperation;

@RestController
public class ManagerDashboardController {

	@Autowired
	private ManagerDashboardService managerDashboardService;
	
	
	
	private static final Logger LOGGER = (Logger) LogManager.getLogger(ManagerDashboardController.class);
	
	@ResponseBody
	@CrossOrigin
	@RequestMapping(value="cat/getPieChartForCountPerLevelPerSkillPerSDLC",method=RequestMethod.POST)
	@ApiOperation("Populate pie chart for number of resources at each level for any SDLC and skill for a given quarter of current year")
	@PreAuthorize("hasAnyRole('"+Roles.MANAGER+"')")
	public JSONOutputModel getPieChartForCountPerLevelPerSkillPerSDLC(@RequestBody GetPieChartForCountPerLevelPerSkillPerSDLCVO request) {
		JSONOutputModel output=new JSONOutputModel();
		
		LOGGER.debug("Request came to populate pie chart for number of resources at each level for any SDLC and skill for a given quarter of current year");
		LOGGER.debug("Quarter:"+request.getQuarter()+" SDLC Phase: "+SdlcCategory.getSdlcCategoryName(request.getSdlcCategory()));
		GetPieChartForCountPerLevelPerSkillPerSDLCTO response=managerDashboardService.getPieChartForCountPerLevelPerSkillPerSDLC(request);
		
		output.setData(response);
		
		if(response!=null) {
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Pie chart data fetched successfully");
			LOGGER.debug("Pie chart data fetched successfully");
		}
		else {
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("No data found");
			LOGGER.debug("No data found");
		}
		return output;
	}
	
	
	
	@ResponseBody
	@CrossOrigin
	@RequestMapping(value="cat/getPieChartForCountPerLevelPerSkillCombo",method=RequestMethod.POST)
	@ApiOperation("Populate pie chart for number of resources at each level for any skill combo for a given quarter of current year")
	@PreAuthorize("hasAnyRole('"+Roles.MANAGER+"')")
	public JSONOutputModel getPieChartForCountPerLevelPerSkillComboTO(@RequestBody PieChartForCountPerLevelPerSkillComboVO request) {
		JSONOutputModel output=new JSONOutputModel();
	
		LOGGER.debug("Request came to populate pie cahrt for number of resources at each level for any skill combo for a given quarter of current year");
		LOGGER.debug("Quarter:"+request.getQuarter()+" SDLC Phase: "+SdlcCategory.getSdlcCategoryName(request.getSdlcCategory()));
		
		if(request.getSkillIdList()==null || request.getSkillIdList().isEmpty()) {
			System.out.println("Empty skill list came for request");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Invalid parameters");
			return output;
		}
		
		
		PieChartForCountPerLevelPerSkillComboTO response=managerDashboardService.getPieChartForCountPerLevelPerSkillComboTO(request);
		
		output.setData(response);
		
		if(response!=null) {
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Pie chart for skill combo data fetched successfully");
			LOGGER.debug("Pie chart data fetched successfully");
		}
		else {
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("No data found");
			LOGGER.debug("No data found");
		}
		
		
		return output;
	}
}
