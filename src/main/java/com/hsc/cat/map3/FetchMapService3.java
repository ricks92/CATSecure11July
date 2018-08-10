/**
**********************************************************************************************************
--  FILENAME		: FetchMapService3.java
--  DESCRIPTION		: Service class for displaying dashboard of employees
--
--  Copyright		: Copyright (c) 2018.
--  Company			: HSC
--
--  Revision History
-- --------------------------------------------------------------------------------------------------------
-- |VERSION |      Date                              |      Author              |      Reason for Changes                                         |
-- --------------------------------------------------------------------------------------------------------
-- |  0.1   |   June 14, 2018                         |     Richa Anand      |       Initial draft                                                        |
-- --------------------------------------------------------------------------------------------------------
--
************************************************************************************************************
**/


package com.hsc.cat.map3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsc.cat.entity.Skill;
import com.hsc.cat.enums.CompetencyLevelsEnum;
import com.hsc.cat.enums.SdlcCategory;
import com.hsc.cat.repository.EmployeeSkillRepository;
import com.hsc.cat.repository.SkillRepository;


@Service
public class FetchMapService3 {
	

	private static final Logger LOGGER = (Logger) LogManager.getLogger(FetchMapService3.class);
	
	@Autowired
	private EmployeeSkillRepository employeeSkillRepository;
	@Autowired
	private SkillRepository skillRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public FetchMapTO3 fetchMap(NewFetchMapVO3 newFetchMapVO) {
		/*FetchMapTO fetchMapTO = new FetchMapTO();
		LOGGER.debug("Incoming request to fetch map for empId:"+newFetchMapVO.getEmpId()+" and quarter:"+newFetchMapVO.getQuarter());
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
		
		System.out.println(getPeerReviewRowList);
		return fetchMapTO;*/
		
		FetchMapTO3 fetchMapTO = new FetchMapTO3();
		LOGGER.debug("Incoming request to fetch map for empId:"+newFetchMapVO.getEmpId()+" and SDLC category:"+SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory())  +" and year:"+newFetchMapVO.getYear()+" and quarter:"+newFetchMapVO.getQuarter() );
		
		String empId=newFetchMapVO.getEmpId();
		int quarter=newFetchMapVO.getQuarter();
		

		
		//Computing quarters
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
		   
		   
		   
		   String queryString="select skill_id,sdlc_category,week_number,rating from cat2.employee_skills as e where e.emp_id=:empid and sdlc_category=:sdlc and week_number between :start AND :end and (select(YEAR(creation_date)))=:year";
		   Query query=entityManager.createNativeQuery(queryString);
		   query.setParameter("empid", newFetchMapVO.getEmpId());
		   query.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		   query.setParameter("start", start);
		   query.setParameter("end", end);
		   query.setParameter("year", newFetchMapVO.getYear());
		   
		   List<Object[]> fetchRatingsForSelectedYearAndQuarterAndSdlc=(List<Object[]>)query.getResultList();
		   
		   
		   SkillMapResponse3 skillMapResponse = new SkillMapResponse3();
			
			Map<String,String> mapTemporary = new HashMap<>(); 
			
			Map<String,String> map = new HashMap<>();//Key=value1 , SkillName:
			
			
			for(Object[] e:fetchRatingsForSelectedYearAndQuarterAndSdlc) {
				Skill skill=skillRepository.findOne((Integer) e[0]);
				//map.put("value"+countOfRatedSkills, skill.getSkillName());
				mapTemporary.put(skill.getSkillName(), "value"+skill.getSkillId());
				
			}
			
			for(Entry<String, String> entry:mapTemporary.entrySet()) {
				map.put(entry.getValue(), entry.getKey());
			}
			
			System.out.println("\n\n\n\n\n****************"+map);
			
			
			skillMapResponse.setMap(map);
			
			fetchMapTO.setSkillMapResponse(skillMapResponse);
			
			//Self review map
			   List<Map<String,String>> listOfselfReviews=new ArrayList<>();
			   List<Map<String,String>> listOfpeerReviews=new ArrayList<>();
			   
			   
			   
			//Get self review
String selfReviewqueryString="select skill_id,sdlc_category,week_number,rating,rating_done_by from cat2.employee_skills as e WHERE e.emp_id=:empid and sdlc_category=:sdlc AND week_number between :start AND :end and (select(YEAR(creation_date)))=:year AND e.rating_Done_By='Self'";
			
Query selfReviewquery=entityManager.createNativeQuery(selfReviewqueryString);
selfReviewquery.setParameter("empid", newFetchMapVO.getEmpId());
selfReviewquery.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
selfReviewquery.setParameter("start", start);
selfReviewquery.setParameter("end", end);
selfReviewquery.setParameter("year", newFetchMapVO.getYear());


List<Object[]> getSelfReviewRowList=selfReviewquery.getResultList();

if(getSelfReviewRowList!=null && !getSelfReviewRowList.isEmpty()) {
		LOGGER.debug("Self review is present in database");
	}
	else
		LOGGER.debug("Self review is not present in database");


TreeSet weekNumbersSelfSet=new TreeSet<>();


//Fill the distinct weekNumberSelf treeset

for(int i=0;i<getSelfReviewRowList.size();i++) {
	   Object[] ob=getSelfReviewRowList.get(i);
	   weekNumbersSelfSet.add(ob[2]);
}

List<Integer> weekNumbersSelf=new ArrayList<>(weekNumbersSelfSet);
System.out.println(weekNumbersSelf);
String sdlcCategory=SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory());
for(int i=0;i<weekNumbersSelf.size();i++) {
	   Map<String,String> selfreviewmap=new LinkedHashMap<>();
	   selfreviewmap.put("category", "week-"+weekNumbersSelf.get(i));
	   
	   
	   for(Entry<String,String> entry:map.entrySet()) {
	    	//int skillId=Integer.valueOf(entry.getKey().substring(5,5));
	    	
	    	//System.out.println("\n\n\n\n&&&&&&"+skillRepository.findSkillIdBySkillNameCustom(entry.getValue()));
	    	int skillId=skillRepository.findSkillIdBySkillNameCustom(entry.getValue());
	    	String rating=employeeSkillRepository.getSpecificRating(empId, weekNumbersSelf.get(i), skillId, "Self",sdlcCategory);
	    	if(rating!=null)
	    	selfreviewmap.put("value"+skillId, ""+CompetencyLevelsEnum.getLevelFromName(rating));
	    }
	    listOfselfReviews.add(selfreviewmap);
}

System.out.println(listOfselfReviews);

fetchMapTO.setListOfselfReviews(listOfselfReviews);




//Get peer review

String peerReviewqueryString="select skill_id,sdlc_category,week_number,rating,rating_done_by from cat2.employee_skills as e WHERE e.emp_id=:empid and sdlc_category=:sdlc AND week_number between :start AND :end and (select(YEAR(creation_date)))=:year AND e.rating_Done_By='Peer'";

Query peerReviewquery=entityManager.createNativeQuery(peerReviewqueryString);
peerReviewquery.setParameter("empid", newFetchMapVO.getEmpId());
peerReviewquery.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
peerReviewquery.setParameter("start", start);
peerReviewquery.setParameter("end", end);
peerReviewquery.setParameter("year", newFetchMapVO.getYear());

List<Object[]> getPeerReviewRowList=peerReviewquery.getResultList();

if(getPeerReviewRowList!=null && !getPeerReviewRowList.isEmpty()) {
		LOGGER.debug("Peer review is present in database");
	}
	else
		LOGGER.debug("Peer review is not present in database");


TreeSet weekNumbersPeerSet=new TreeSet<>();


//Fill the distinct weekNumberPeer treeset

for(int i=0;i<getPeerReviewRowList.size();i++) {
	   Object[] ob=getPeerReviewRowList.get(i);
	   weekNumbersPeerSet.add(ob[2]);
}

List<Integer> weekNumbersPeer=new ArrayList<>(weekNumbersPeerSet);
System.out.println(weekNumbersPeer);
LOGGER.debug(weekNumbersPeer);




for(int i=0;i<weekNumbersPeer.size();i++) {
	   Map<String,String> peerreviewmap=new LinkedHashMap<>();
	   peerreviewmap.put("category", "week-"+weekNumbersPeer.get(i));
	   
	   
	   for(Entry<String,String> entry:map.entrySet()) {
	    	//int skillId=Integer.valueOf(entry.getKey().substring(5,5));
	    	
	    	//System.out.println("\n\n\n\n&&&&&&"+skillRepository.findSkillIdBySkillNameCustom(entry.getValue()));
	    	int skillId=skillRepository.findSkillIdBySkillNameCustom(entry.getValue());
	    	String rating=employeeSkillRepository.getSpecificRating(empId, weekNumbersPeer.get(i), skillId, "Peer",sdlcCategory);
	    	if(rating!=null)
	    		peerreviewmap.put("value"+skillId, ""+CompetencyLevelsEnum.getLevelFromName(rating));
	    }
	   listOfpeerReviews.add(peerreviewmap);
}

System.out.println(listOfpeerReviews);

fetchMapTO.setListOfpeerReviews(listOfpeerReviews);

		return fetchMapTO;
	}
	
	
	



}
