package com.hsc.cat.map2;

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
public class FetchMapService2 {
	

	private static final Logger LOGGER = (Logger) LogManager.getLogger(FetchMapService2.class);
	
	@Autowired
	private EmployeeSkillRepository employeeSkillRepository;
	@Autowired
	private SkillRepository skillRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public FetchMapTO2 fetchMap(NewFetchMapVO2 newFetchMapVO) {
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
		
		FetchMapTO2 fetchMapTO = new FetchMapTO2();
		LOGGER.debug("Incoming request to fetch map for empId:"+newFetchMapVO.getEmpId()+" and SDLC category:"+SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory())+" and flag:"+newFetchMapVO.getFlag());
		/*
		 * 1=last one month
		 * 2=last 3 months
		 * 3=last 6 months
		 * 4=last year
		 */
		
		
	/* if(newFetchMapVO.getFlag()==1) {
			fetchMapTO=fetchMapForLastThreemonths(newFetchMapVO,-1);
		}
	 else if(newFetchMapVO.getFlag()==2) {
			fetchMapTO=fetchMapForLastThreemonths(newFetchMapVO,-3);
		}
	 else if(newFetchMapVO.getFlag()==3) {
			fetchMapTO=fetchMapForLastThreemonths(newFetchMapVO,-6);
		}
	 else if(newFetchMapVO.getFlag()==4)
		fetchMapTO=fetchMapForLastYear(newFetchMapVO);*/
		return fetchMapTO;
	}
	
	
	
	/*
	private FetchMapTO2 fetchMapForLastYear(NewFetchMapVO2 newFetchMapVO) {
		


		FetchMapTO2 fetchMapTO = new FetchMapTO2();
		String empId=newFetchMapVO.getEmpId();
		String queryString="SELECT skill_id,sdlc_category,week_number,rating FROM cat2.employee_skills WHERE emp_id=:empid and sdlc_category=:sdlc AND (select(YEAR(creation_date)))=((select year(NOW()))-1) order by week_number;";
		
		Query query=entityManager.createNativeQuery(queryString);
		query.setParameter("empid", newFetchMapVO.getEmpId());
		query.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		
		List<Object[]> fetchMapForLastThreemonthsResponse=(List<Object[]>)query.getResultList();
	
		
		 // Common code starts
		 
		
		SkillMapResponse2 skillMapResponse = new SkillMapResponse2();
		
       Map<String,String> mapTemporary = new HashMap<>(); 
		
		Map<String,String> map = new HashMap<>();//Key=value1 , SkillName:
		
		
		
		for(Object[] e:fetchMapForLastThreemonthsResponse) {
			Skill skill=skillRepository.findOne((Integer) e[0]);
			//map.put("value"+countOfRatedSkills, skill.getSkillName());
			mapTemporary.put(skill.getSkillName(), "value"+skill.getSkillId());
			
		}
		
		for(Entry<String, String> entry:mapTemporary.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		
		System.out.println("\n\n\n\n\n"+map);
		
		
		skillMapResponse.setMap(map);
		
		fetchMapTO.setSkillMapResponse(skillMapResponse);
		
		
  
		//Self review map
		   List<Map<String,String>> listOfselfReviews=new ArrayList<>();
		   List<Map<String,String>> listOfpeerReviews=new ArrayList<>();
		   
		  //Get self review
		   String selfReviewqueryString="select skill_id,sdlc_category,week_number,rating,rating_done_by from cat2.employee_skills as e WHERE e.emp_id=:empid and sdlc_category=:sdlc AND (select(YEAR(creation_date)))=((select year(NOW()))-1) AND e.rating_Done_By='Self'";
		
		   Query selfReviewquery=entityManager.createNativeQuery(selfReviewqueryString);
		   selfReviewquery.setParameter("empid", newFetchMapVO.getEmpId());
		   selfReviewquery.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		   
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
		   }
		   
		   System.out.println(listOfselfReviews);
		   
		   fetchMapTO.setListOfselfReviews(listOfselfReviews);
		   
		   
		   
		   //Get peer review
		   
		   String peerReviewqueryString="select skill_id,sdlc_category,week_number,rating,rating_done_by from cat2.employee_skills as e WHERE e.emp_id=:empid and sdlc_category=:sdlc AND (select(YEAR(creation_date)))=((select year(NOW()))-1) AND e.rating_Done_By='Peer'";
		   
		   Query peerReviewquery=entityManager.createNativeQuery(peerReviewqueryString);
		   peerReviewquery.setParameter("empid", newFetchMapVO.getEmpId());
		   peerReviewquery.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		   
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
		   
		   
		   
		   
		   for(int i=0;i<weekNumbersPeer.size();i++) {
			   Map<String,String> peerreviewmap=new LinkedHashMap<>();
			   peerreviewmap.put("category", "week-"+weekNumbersPeer.get(i));
			   
			   
			   for(Entry<String,String> entry:map.entrySet()) {
			    	//int skillId=Integer.valueOf(entry.getKey().substring(5,5));
			    	
			    	//System.out.println("\n\n\n\n&&&&&&"+skillRepository.findSkillIdBySkillNameCustom(entry.getValue()));
			    	int skillId=skillRepository.findSkillIdBySkillNameCustom(entry.getValue());
			    	String rating=employeeSkillRepository.getSpecificRating(empId, weekNumbersPeer.get(i), skillId, "Self");
			    	if(rating!=null)
			    		peerreviewmap.put("value"+skillId, ""+CompetencyLevelsEnum.getLevelFromName(rating));
			    }
			   listOfpeerReviews.add(peerreviewmap);
		   }
		   
		   System.out.println(listOfpeerReviews);
		   
		   fetchMapTO.setListOfpeerReviews(listOfpeerReviews);
		   
		   
		   
		return fetchMapTO;
	}*/




	public FetchMapTO2 fetchMapForLastThreemonths(NewFetchMapVO2 newFetchMapVO,int interval) {
		FetchMapTO2 fetchMapTO = new FetchMapTO2();
		/*String empId=newFetchMapVO.getEmpId();
		String queryString="SELECT skill_id,sdlc_category,week_number,rating FROM cat2.employee_skills WHERE emp_id=:empid and sdlc_category=:sdlc AND creation_date >= DATE_ADD(NOW(), INTERVAL :interval MONTH) order by week_number;";
		
		Query query=entityManager.createNativeQuery(queryString);
		query.setParameter("empid", newFetchMapVO.getEmpId());
		query.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		query.setParameter("interval", interval);
		
		List<Object[]> fetchMapForLastThreemonthsResponse=(List<Object[]>)query.getResultList();
	
		
		
		
		 * Common code starts
		 
		
		SkillMapResponse2 skillMapResponse = new SkillMapResponse2();
		
       Map<String,String> mapTemporary = new HashMap<>(); 
		
		Map<String,String> map = new HashMap<>();//Key=value1 , SkillName:
		
		
		
		for(Object[] e:fetchMapForLastThreemonthsResponse) {
			Skill skill=skillRepository.findOne((Integer) e[0]);
			//map.put("value"+countOfRatedSkills, skill.getSkillName());
			mapTemporary.put(skill.getSkillName(), "value"+skill.getSkillId());
			
		}
		
		for(Entry<String, String> entry:mapTemporary.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		
		System.out.println("\n\n\n\n\n"+map);
		
		
		skillMapResponse.setMap(map);
		
		fetchMapTO.setSkillMapResponse(skillMapResponse);
		
		
  
		//Self review map
		   List<Map<String,String>> listOfselfReviews=new ArrayList<>();
		   List<Map<String,String>> listOfpeerReviews=new ArrayList<>();
		   
		  //Get self review
		   String selfReviewqueryString="select skill_id,sdlc_category,week_number,rating,rating_done_by from cat2.employee_skills as e WHERE e.emp_id=:empid and sdlc_category=:sdlc AND creation_date >= DATE_ADD(NOW(), INTERVAL :interval MONTH) AND e.rating_Done_By='Self'";
		
		   Query selfReviewquery=entityManager.createNativeQuery(selfReviewqueryString);
		   selfReviewquery.setParameter("empid", newFetchMapVO.getEmpId());
		   selfReviewquery.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		   selfReviewquery.setParameter("interval", interval);
		   
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
		   }
		   
		   System.out.println(listOfselfReviews);
		   
		   fetchMapTO.setListOfselfReviews(listOfselfReviews);
		   
		   
		   
		   //Get peer review
		   
		   String peerReviewqueryString="select skill_id,sdlc_category,week_number,rating,rating_done_by from cat2.employee_skills as e WHERE e.emp_id=:empid and sdlc_category=:sdlc AND creation_date >= DATE_ADD(NOW(), INTERVAL :interval MONTH) AND e.rating_Done_By='Peer'";
		   
		   Query peerReviewquery=entityManager.createNativeQuery(peerReviewqueryString);
		   peerReviewquery.setParameter("empid", newFetchMapVO.getEmpId());
		   peerReviewquery.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		   peerReviewquery.setParameter("interval", interval);
		   
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
		   
		   
		   
		   
		   for(int i=0;i<weekNumbersPeer.size();i++) {
			   Map<String,String> peerreviewmap=new LinkedHashMap<>();
			   peerreviewmap.put("category", "week-"+weekNumbersPeer.get(i));
			   
			   
			   for(Entry<String,String> entry:map.entrySet()) {
			    	//int skillId=Integer.valueOf(entry.getKey().substring(5,5));
			    	
			    	//System.out.println("\n\n\n\n&&&&&&"+skillRepository.findSkillIdBySkillNameCustom(entry.getValue()));
			    	int skillId=skillRepository.findSkillIdBySkillNameCustom(entry.getValue());
			    	String rating=employeeSkillRepository.getSpecificRating(empId, weekNumbersPeer.get(i), skillId, "Self");
			    	if(rating!=null)
			    		peerreviewmap.put("value"+skillId, ""+CompetencyLevelsEnum.getLevelFromName(rating));
			    }
			   listOfpeerReviews.add(peerreviewmap);
		   }
		   
		   System.out.println(listOfpeerReviews);
		   
		   fetchMapTO.setListOfpeerReviews(listOfpeerReviews);
		   
		   
		   
		return fetchMapTO;
	}
	
	
	
	
	
	
	private FetchMapTO2 fetchMapForLastYear(NewFetchMapVO2 newFetchMapVO) {
		


		FetchMapTO2 fetchMapTO = new FetchMapTO2();
		String empId=newFetchMapVO.getEmpId();
		String queryString="SELECT skill_id,sdlc_category,week_number,rating FROM cat2.employee_skills WHERE emp_id=:empid and sdlc_category=:sdlc AND creation_date >= DATE_ADD(NOW(), INTERVAL -366 DAY) order by week_number;";
		
		Query query=entityManager.createNativeQuery(queryString);
		query.setParameter("empid", newFetchMapVO.getEmpId());
		query.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		
		List<Object[]> fetchMapForLastThreemonthsResponse=(List<Object[]>)query.getResultList();
	
		
		 // Common code starts
		 
		
		SkillMapResponse2 skillMapResponse = new SkillMapResponse2();
		
       Map<String,String> mapTemporary = new HashMap<>(); 
		
		Map<String,String> map = new HashMap<>();//Key=value1 , SkillName:
		
		
		
		for(Object[] e:fetchMapForLastThreemonthsResponse) {
			Skill skill=skillRepository.findOne((Integer) e[0]);
			//map.put("value"+countOfRatedSkills, skill.getSkillName());
			mapTemporary.put(skill.getSkillName(), "value"+skill.getSkillId());
			
		}
		
		for(Entry<String, String> entry:mapTemporary.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		
		System.out.println("\n\n\n\n\n"+map);
		
		
		skillMapResponse.setMap(map);
		
		fetchMapTO.setSkillMapResponse(skillMapResponse);
		
		
  
		//Self review map
		   List<Map<String,String>> listOfselfReviews=new ArrayList<>();
		   List<Map<String,String>> listOfpeerReviews=new ArrayList<>();
		   
		  //Get self review
		   String selfReviewqueryString="select skill_id,sdlc_category,week_number,rating,rating_done_by from cat2.employee_skills as e WHERE e.emp_id=:empid and sdlc_category=:sdlc AND creation_date >= DATE_ADD(NOW(), INTERVAL -366 DAY) AND e.rating_Done_By='Self'";
		
		   Query selfReviewquery=entityManager.createNativeQuery(selfReviewqueryString);
		   selfReviewquery.setParameter("empid", newFetchMapVO.getEmpId());
		   selfReviewquery.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		   
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
		   }
		   
		   System.out.println(listOfselfReviews);
		   
		   fetchMapTO.setListOfselfReviews(listOfselfReviews);
		   
		   
		   
		   //Get peer review
		   
		   String peerReviewqueryString="select skill_id,sdlc_category,week_number,rating,rating_done_by from cat2.employee_skills as e WHERE e.emp_id=:empid and sdlc_category=:sdlc AND creation_date >= DATE_ADD(NOW(), INTERVAL -366 DAY) AND e.rating_Done_By='Peer'";
		   
		   Query peerReviewquery=entityManager.createNativeQuery(peerReviewqueryString);
		   peerReviewquery.setParameter("empid", newFetchMapVO.getEmpId());
		   peerReviewquery.setParameter("sdlc", SdlcCategory.getSdlcCategoryName(newFetchMapVO.getSdlcCategory()));
		   
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
		   
		   
		   
		   
		   for(int i=0;i<weekNumbersPeer.size();i++) {
			   Map<String,String> peerreviewmap=new LinkedHashMap<>();
			   peerreviewmap.put("category", "week-"+weekNumbersPeer.get(i));
			   
			   
			   for(Entry<String,String> entry:map.entrySet()) {
			    	//int skillId=Integer.valueOf(entry.getKey().substring(5,5));
			    	
			    	//System.out.println("\n\n\n\n&&&&&&"+skillRepository.findSkillIdBySkillNameCustom(entry.getValue()));
			    	int skillId=skillRepository.findSkillIdBySkillNameCustom(entry.getValue());
			    	String rating=employeeSkillRepository.getSpecificRating(empId, weekNumbersPeer.get(i), skillId, "Self");
			    	if(rating!=null)
			    		peerreviewmap.put("value"+skillId, ""+CompetencyLevelsEnum.getLevelFromName(rating));
			    }
			   listOfpeerReviews.add(peerreviewmap);
		   }
		   
		   System.out.println(listOfpeerReviews);
		   
		   fetchMapTO.setListOfpeerReviews(listOfpeerReviews);
		   */
		   
		   
		return fetchMapTO;
	}
}
