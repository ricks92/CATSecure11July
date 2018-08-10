package com.hsc.cat.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsc.cat.entity.EmployeeSkillEntity;
import com.hsc.cat.entity.Skill;
import com.hsc.cat.enums.CompetencyLevelsEnum;
import com.hsc.cat.repository.EmployeeSkillRepository;
import com.hsc.cat.repository.SkillRepository;


@Service
public class FetchMapService {
	

	private static final Logger LOGGER = (Logger) LogManager.getLogger(FetchMapService.class);
	
	@Autowired
	private EmployeeSkillRepository employeeSkillRepository;
	@Autowired
	private SkillRepository skillRepository;
	
	
	public FetchMapTO fetchMap(NewFetchMapVO newFetchMapVO) {
		FetchMapTO fetchMapTO = new FetchMapTO();
		/*LOGGER.debug("Incoming request to fetch map for empId:"+newFetchMapVO.getEmpId()+" and quarter:"+newFetchMapVO.getQuarter());
		String empId=newFetchMapVO.getEmpId();
		int quarter=newFetchMapVO.getQuarter();
		
		
		SkillMapResponse skillMapResponse = new SkillMapResponse();
		
		Map<String,String> mapTemporary = new HashMap<>(); 
		
		Map<String,String> map = new HashMap<>();//Key=value1 , SkillName:
		
		 int start=0;
		   int end=0;
		   
		   switch(quarter) {
		   case 1:{
			   start=1;
			   end=start+12;
			   break;
		   }
		   case 2:{
			   start=14;
			   end=start+12;
			   break;
		   }
		   case 3:{
			   start=27;
			   end=start+12;
			   break;
		   }
		   case 4:{
			   start=40;
			   end=start+12;
			   break;
		   }
		   default:System.out.println("Illegal");
		   }
		
		List<EmployeeSkillEntity> employeeSkillEntityList=employeeSkillRepository.getAllRatedSkillsCustom(empId, start, end);
	
		//System.out.println("\n\n\n\n%%%%%%%%"+employeeSkillEntityList);
		
		
		for(EmployeeSkillEntity e:employeeSkillEntityList) {
			Skill skill=skillRepository.findOne(e.getSkillId());
			//map.put("value"+countOfRatedSkills, skill.getSkillName());
			mapTemporary.put(skill.getSkillName(), "value"+skill.getSkillId());
			
		}
		
		for(Entry<String,String> entry:mapTemporary.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		
		System.out.println("\n\n\n\n\n"+map);
		
		
		skillMapResponse.setMap(map);
		
		fetchMapTO.setSkillMapResponse(skillMapResponse);
		
		
		
		List<Map<String,String>> listOfselfReviews=new ArrayList<>();
		
		
		List<Map<String,String>> listOfpeerReviews=new ArrayList<>();
		
		List<EmployeeSkillEntity> getSelfReviewRowList=employeeSkillRepository.getReviewSelf(empId, start, end);
		
		if(getSelfReviewRowList!=null && !getSelfReviewRowList.isEmpty()) {
			LOGGER.debug("Self review is present in database");
		}
		else
			LOGGER.debug("Self review is not present in database");
		
		List<Integer> weekNumbersSelf=employeeSkillRepository.getDistinctWeekMumber(empId, start, end, "Self");
		//Collections.sort(weekNumbersSelf);
		System.out.println(getSelfReviewRowList);
		//System.out.println("Distinct count:"+weekNumbersSelf);
		
		for(int i=0;i<weekNumbersSelf.size();i++) {
			Map<String,String> selfreviewmap=new LinkedHashMap<>();
			selfreviewmap.put("category", "week-"+weekNumbersSelf.get(i));
		    
			
			for(Entry<String,String> entry:map.entrySet()) {
		    	//int skillId=Integer.valueOf(entry.getKey().substring(5,5));
		    	
		    	//System.out.println("\n\n\n\n&&&&&&"+skillRepository.findSkillIdBySkillNameCustom(entry.getValue()));
		    	int skillId=skillRepository.findSkillIdBySkillNameCustom(entry.getValue());
		    	String rating=employeeSkillRepository.getSpecificRating(empId, weekNumbersSelf.get(i), skillId, "Self");
		    	if(rating!=null)
		    	selfreviewmap.put("value"+skillId, ""+CompetencyLevelsEnum.getLevelFromName(rating));
		    }
		    listOfselfReviews.add(selfreviewmap);
			//System.out.println("\n\n\nselfreviewmap:"+selfreviewmap);
		}
	
		
		fetchMapTO.setListOfselfReviews(listOfselfReviews);
		
		//Peer review
		List<EmployeeSkillEntity> getPeerReviewRowList=employeeSkillRepository.getReviewPeer(empId, start, end);
		
		if(getPeerReviewRowList!=null && !getPeerReviewRowList.isEmpty()) {
			LOGGER.debug("Peer review is present in database");
		}
		else
			LOGGER.debug("Peer review is not present in database");
		
		
		List<Integer> weekNumbersPeer=employeeSkillRepository.getDistinctWeekMumber(empId, start, end, "Peer");
		//Collections.sort(weekNumbersPeer);
		
		for(int i=0;i<weekNumbersPeer.size();i++) {
			Map<String,String> peerreviewmap=new LinkedHashMap<>();
			peerreviewmap.put("category", "week-"+weekNumbersPeer.get(i));
		    
			
			for(Entry<String,String> entry:map.entrySet()) {
		    	//int skillId=Integer.valueOf(entry.getKey().substring(5,5));
		    	
		    	//System.out.println("\n\n\n\n&&&&&&"+skillRepository.findSkillIdBySkillNameCustom(entry.getValue()));
		    	int skillId=skillRepository.findSkillIdBySkillNameCustom(entry.getValue());
		    	String rating=employeeSkillRepository.getSpecificRating(empId, weekNumbersPeer.get(i), skillId, "Peer");
		    	//System.out.println("skill "+skillId+" has rating:"+rating);
		    	if(rating!=null)
		    	peerreviewmap.put("value"+skillId, ""+CompetencyLevelsEnum.getLevelFromName(rating));
		    }
			listOfpeerReviews.add(peerreviewmap);
			//System.out.println("\n\n\npeerreviewmap:"+peerreviewmap);
		}
		
		
		fetchMapTO.setListOfpeerReviews(listOfpeerReviews);
		
		System.out.println(getPeerReviewRowList);*/
		return fetchMapTO;
	}
}
