package com.hsc.cat.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsc.cat.TO.GetPieChartForCountPerLevelPerSkillPerSDLCTO;
import com.hsc.cat.TO.PieChartForCountPerLevelPerSkillComboTO;
import com.hsc.cat.TO.SelfDataPieChartTO;
import com.hsc.cat.TO.SkillComboDataTO;
import com.hsc.cat.VO.GetPieChartForCountPerLevelPerSkillPerSDLCVO;
import com.hsc.cat.VO.PieChartForCountPerLevelPerSkillComboVO;
import com.hsc.cat.entity.Skill;
import com.hsc.cat.enums.SdlcCategory;
import com.hsc.cat.repository.SkillRepository;

@Service
@Transactional(readOnly = true)
public class ManagerDashboardService {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private SkillRepository skillRepository;
	
	public GetPieChartForCountPerLevelPerSkillPerSDLCTO getPieChartForCountPerLevelPerSkillPerSDLC(GetPieChartForCountPerLevelPerSkillPerSDLCVO request) {
		GetPieChartForCountPerLevelPerSkillPerSDLCTO response=new GetPieChartForCountPerLevelPerSkillPerSDLCTO();
		
		int quarter=request.getQuarter();
		int year = Year.now().getValue();
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
		   
		   Skill skill=skillRepository.findOne(request.getSkillId());
		   String skillName=skill.getSkillName();
		   int skillId=request.getSkillId();
		   String sdlc=SdlcCategory.getSdlcCategoryName(request.getSdlcCategory());
		   
		   //For self data
		   
		   String queryStringSelf="select count(e),rating from EmployeeSkillEntity e where e.weekNumber between :start and :end and e.sdlcCategory=:sdlc and e.skillId=:skillId and e.ratingDoneBy='Self' and year(creationDate)=:year group by rating";
		   
		   Query querySelf=entityManager.createQuery(queryStringSelf);
		   querySelf.setParameter("start",start);
		   querySelf.setParameter("end",end);
		   querySelf.setParameter("sdlc",sdlc);
		   querySelf.setParameter("skillId",skillId);
		   querySelf.setParameter("year",year);
		   
		   List<Object[]> objects=querySelf.getResultList();
		   
		   List<SelfDataPieChartTO> listOfSelfDataPieChartTOs=new ArrayList<>();
		   for(Object[] ob:objects) {
			   SelfDataPieChartTO selfDataPieChartTO=new SelfDataPieChartTO();
			   selfDataPieChartTO.setNoOfResources((long) ob[0]);
			  // int level=CompetencyLevelsEnum.getLevelFromName((String) ob[1]);
			   selfDataPieChartTO.setLevel((String) ob[1]);
			   listOfSelfDataPieChartTOs.add(selfDataPieChartTO);
		   }
		   
 //For peer data
		   
		   String queryStringPeer="select count(e),rating from EmployeeSkillEntity e where e.weekNumber between :start and :end and e.sdlcCategory=:sdlc and e.skillId=:skillId and e.ratingDoneBy='Peer' and year(creationDate)=:year  group by rating";
		   
		   Query queryPeer=entityManager.createQuery(queryStringPeer);
		   queryPeer.setParameter("start",start);
		   queryPeer.setParameter("end",end);
		   queryPeer.setParameter("sdlc",sdlc);
		   queryPeer.setParameter("skillId",skillId);
		   queryPeer.setParameter("year",year);
		   
		   List<Object[]> objects2=queryPeer.getResultList();
		   
		   List<SelfDataPieChartTO> listOfSelfDataPieChartTOsPeer=new ArrayList<>();
		   for(Object[] ob:objects2) {
			   SelfDataPieChartTO selfDataPieChartTO=new SelfDataPieChartTO();
			   selfDataPieChartTO.setNoOfResources((long) ob[0]);
			   //int level=CompetencyLevelsEnum.getLevelFromName((String) ob[1]);
			   selfDataPieChartTO.setLevel((String) ob[1]);
			   listOfSelfDataPieChartTOsPeer.add(selfDataPieChartTO);
		   }
		   response.setQuarter(quarter);
		   response.setSkillName(skillName);
		   response.setSkillId(skillId);
		   response.setSdlcCategory(request.getSdlcCategory());
		   response.setSelfData(listOfSelfDataPieChartTOs);
		   response.setPeerData(listOfSelfDataPieChartTOsPeer);
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public PieChartForCountPerLevelPerSkillComboTO getPieChartForCountPerLevelPerSkillComboTO(PieChartForCountPerLevelPerSkillComboVO request) {
		PieChartForCountPerLevelPerSkillComboTO response= new PieChartForCountPerLevelPerSkillComboTO();
		
		int quarter=request.getQuarter();
		int year = Year.now().getValue();
		String sdlc=SdlcCategory.getSdlcCategoryName(request.getSdlcCategory());
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
		   
		   
		   List<Integer> skillIdList=request.getSkillIdList();
		   List<SkillComboDataTO> skillList=new ArrayList<>();
		   
		  
		 
		for(int i=0;i<skillIdList.size();i++) {
			int skillId=skillIdList.get(i);
			Skill skill=skillRepository.findBySkillId(skillId);
			String skillName=skill.getSkillName();
			SkillComboDataTO skillComboDataTO= new SkillComboDataTO();
			skillComboDataTO.setSkillId(skillId);
			skillComboDataTO.setSkillName(skillName);
			
			//For self data of a specific skill
			 String queryStringSelf="select count(e),rating from EmployeeSkillEntity e where e.weekNumber between :start and :end and e.sdlcCategory=:sdlc and e.skillId=:skillId and e.ratingDoneBy='Self' and year(creationDate)=:year group by rating";
			   
			   Query querySelf=entityManager.createQuery(queryStringSelf);
			   querySelf.setParameter("start",start);
			   querySelf.setParameter("end",end);
			   querySelf.setParameter("sdlc",sdlc);
			   querySelf.setParameter("skillId",skillId);
			   querySelf.setParameter("year",year);
			   
			   List<Object[]> objects=querySelf.getResultList();
			   
			   List<SelfDataPieChartTO> listOfSelfDataPieChartTOs=new ArrayList<>();
			   for(Object[] ob:objects) {
				   SelfDataPieChartTO selfDataPieChartTO=new SelfDataPieChartTO();
				   selfDataPieChartTO.setNoOfResources((long) ob[0]);
				  // int level=CompetencyLevelsEnum.getLevelFromName((String) ob[1]);
				   selfDataPieChartTO.setLevel((String) ob[1]);
				   listOfSelfDataPieChartTOs.add(selfDataPieChartTO);
			   }
			   
			   
			   //For peer data
			   
			   String queryStringPeer="select count(e),rating from EmployeeSkillEntity e where e.weekNumber between :start and :end and e.sdlcCategory=:sdlc and e.skillId=:skillId and e.ratingDoneBy='Peer' and year(creationDate)=:year  group by rating";
			   
			   Query queryPeer=entityManager.createQuery(queryStringPeer);
			   queryPeer.setParameter("start",start);
			   queryPeer.setParameter("end",end);
			   queryPeer.setParameter("sdlc",sdlc);
			   queryPeer.setParameter("skillId",skillId);
			   queryPeer.setParameter("year",year);
			   
			   List<Object[]> objects2=queryPeer.getResultList();
			   
			   List<SelfDataPieChartTO> listOfSelfDataPieChartTOsPeer=new ArrayList<>();
			   for(Object[] ob:objects2) {
				   SelfDataPieChartTO selfDataPieChartTO=new SelfDataPieChartTO();
				   selfDataPieChartTO.setNoOfResources((long) ob[0]);
				   //int level=CompetencyLevelsEnum.getLevelFromName((String) ob[1]);
				   selfDataPieChartTO.setLevel((String) ob[1]);
				   listOfSelfDataPieChartTOsPeer.add(selfDataPieChartTO);
			   }
			   
			   skillComboDataTO.setSelfData(listOfSelfDataPieChartTOs);
			   skillComboDataTO.setPeerData(listOfSelfDataPieChartTOsPeer);
			   skillList.add(skillComboDataTO);
			
			
		}
		response.setQuarter(quarter);
		response.setSdlcCategory(request.getSdlcCategory());
		response.setSkillList(skillList);
		return response;
	}
}
