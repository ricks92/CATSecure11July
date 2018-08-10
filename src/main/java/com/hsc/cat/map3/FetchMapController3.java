/**
**********************************************************************************************************
--  FILENAME		: FetchMapController3.java
--  DESCRIPTION		: REST API for displaying dashboard of employees
--
--  Copyright		: Copyright (c) 2018.
--  Company			: HSC
--
--  Revision History
-- --------------------------------------------------------------------------------------------------------
-- |VERSION |      Date                              |      Author              |      Reason for Changes                                         |
-- --------------------------------------------------------------------------------------------------------
-- |  0.1   |   June 20, 2018                         |     Richa Anand      |       Initial draft                                                        |
-- --------------------------------------------------------------------------------------------------------
--
************************************************************************************************************
**/
package com.hsc.cat.map3;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.controller.SkillController;
import com.hsc.cat.utilities.JSONOutputEnum;
import com.hsc.cat.utilities.JSONOutputModel;

import io.swagger.annotations.ApiOperation;




@RestController
public class FetchMapController3 {

	
	private static final Logger LOGGER = (Logger) LogManager.getLogger(FetchMapController3.class);
	@Autowired
	private FetchMapService3 fetchMapService;
	
	
	@ApiOperation("Display assessment chart in employee dashboard")
	@ResponseBody
	@RequestMapping(value="/cat/fetchMapNew3",method=RequestMethod.POST,produces = "application/json",consumes="application/json")
	@CrossOrigin
	public JSONOutputModel  fetchMap(@RequestBody NewFetchMapVO3 newFetchMapVO) {
		
		LOGGER.debug("Inside new fetch map controller");
		JSONOutputModel output=new JSONOutputModel();
		
		
		if(newFetchMapVO.getEmpId()==null ||newFetchMapVO.getEmpId().equals("") || newFetchMapVO.getQuarter()<1 || newFetchMapVO.getQuarter()>4) {
			output.setMessage("Invalid parameters");
			LOGGER.debug("Invalid parameters");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		FetchMapTO3 fetchMapTO=fetchMapService.fetchMap(newFetchMapVO);
		
		output.setData(fetchMapTO);
		
		if(fetchMapTO!=null && fetchMapTO.getSkillMapResponse()!=null &&( !fetchMapTO.getListOfselfReviews().isEmpty() ||!fetchMapTO.getListOfpeerReviews().isEmpty())) {
			
			output.setMessage("Map fetched successfully");
			LOGGER.debug("Map fetched successfully");
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
		}
		else {
			output.setMessage("No data found");
			LOGGER.debug("No data found");
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
		}
		return output;
	}
}
