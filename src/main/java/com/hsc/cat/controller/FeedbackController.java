package com.hsc.cat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.cat.TO.FeedbackTO;
import com.hsc.cat.VO.FeedbackVO;
import com.hsc.cat.service.FeedbackService;
import com.hsc.cat.utilities.JSONOutputEnum;
import com.hsc.cat.utilities.JSONOutputModel;
import com.hsc.cat.utilities.Roles;

@RestController
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="cat/feedback",method=RequestMethod.POST)
	@PreAuthorize("hasAnyRole('"+Roles.EMPLOYEE+"','"+Roles.MANAGER+"')")
	public JSONOutputModel reviewEmployee(@RequestBody FeedbackVO feedbackVO) {
		JSONOutputModel output= new JSONOutputModel();
		FeedbackTO feedbackTO=feedbackService.reviewEmployee(feedbackVO);
		output.setData(feedbackTO);
		if(feedbackTO!=null) {
			output.setStatus(JSONOutputEnum.SUCCESS.getValue());
			output.setMessage("Feedback saved successfully");
		}
		else {
			output.setStatus(JSONOutputEnum.FAILURE.getValue());
			output.setMessage("Feedback could not be saved");
		}
		return output;
	}
}
