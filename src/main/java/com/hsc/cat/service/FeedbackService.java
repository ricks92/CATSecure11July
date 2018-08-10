package com.hsc.cat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsc.cat.TO.FeedbackTO;
import com.hsc.cat.VO.FeedbackVO;
import com.hsc.cat.entity.EmployeeDetails;
import com.hsc.cat.entity.FeedbackEntity;
import com.hsc.cat.repository.EmployeeDetailRepository;
import com.hsc.cat.repository.FeedbackRepository;
import com.hsc.cat.utilities.DateFormatterUtil;

@Service
@Transactional
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private EmployeeDetailRepository employeeDetailRepository;
	public FeedbackTO reviewEmployee(FeedbackVO feedbackVO) {
		List<EmployeeDetails> emp1=employeeDetailRepository.findByEmpid(feedbackVO.getEmpId());
		List<EmployeeDetails> emp2=employeeDetailRepository.findByEmpid(feedbackVO.getRatingDoneByEmpId());
		if(emp1==null ||emp1.isEmpty() || emp2==null || emp2.isEmpty())
		{
			return null;
		}
		
		FeedbackEntity recordExists=feedbackRepository.findByEmpIdAndRatingDoneByEmpIdAndWeekNumber(feedbackVO.getEmpId(),feedbackVO.getRatingDoneByEmpId(),feedbackVO.getWeekNumber());
		if(recordExists!=null) {
			//Record exists for current week
			//Hence,we need to update
		int savedId=feedbackRepository.updateFeedback(feedbackVO.getEmpId(), feedbackVO.getRatingDoneByEmpId(), feedbackVO.getWeekNumber(), feedbackVO.getComment(),new Date());
		FeedbackEntity saved=feedbackRepository.findOne(savedId);
        FeedbackTO feedbackTO =modelConversion(saved);
		
		return feedbackTO;
		}
		
		FeedbackEntity feedbackEntity=new FeedbackEntity();
		
		Date date= new Date();
		feedbackEntity.setComment(feedbackVO.getComment());
		feedbackEntity.setEmpId(feedbackVO.getEmpId());
		feedbackEntity.setRatingDoneBy(feedbackVO.getRatingDoneBy());
		feedbackEntity.setRatingDoneByEmpId(feedbackVO.getRatingDoneByEmpId());
		feedbackEntity.setWeekNumber(feedbackVO.getWeekNumber());
		feedbackEntity.setCreationDate(date);
		FeedbackEntity saved=feedbackRepository.save(feedbackEntity);
		
		FeedbackTO feedbackTO =modelConversion(saved);
		
		return feedbackTO;
	}
	
	
	public FeedbackTO modelConversion(FeedbackEntity entity) {
		FeedbackTO feedbackTO =new FeedbackTO();
		feedbackTO.setId(entity.getId());
		feedbackTO.setEmpId(entity.getEmpId());
		feedbackTO.setRatingDoneBy(entity.getRatingDoneBy());
		feedbackTO.setRatingDoneByEmpId(entity.getRatingDoneByEmpId());
		feedbackTO.setWeekNumber(entity.getWeekNumber());
		feedbackTO.setCreationDate(entity.getCreationDate());
		feedbackTO.setComment(entity.getComment());
		feedbackTO.setCreationDateString(DateFormatterUtil.getStringFromDateTime(entity.getCreationDate()));
		
		return feedbackTO;
	}
}
