package com.hsc.cat.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsc.cat.TO.MapTO;
import com.hsc.cat.TO.PeercommentPlusDateListTO;
import com.hsc.cat.TO.SelfRatedSkillsTO;
import com.hsc.cat.TO.SkillTO;
import com.hsc.cat.TO.SubjectTO;
import com.hsc.cat.TO.UpdateSkillResponse;
import com.hsc.cat.TO.UpdateSkillTO;
import com.hsc.cat.TO.ViewSkillListTO;
import com.hsc.cat.TO.ViewSkillTO;
import com.hsc.cat.VO.GetAllPeerReviewedSkillsVO;
import com.hsc.cat.VO.MapVO;
import com.hsc.cat.VO.UpdateSkillVO;
import com.hsc.cat.VO.UpdateSkillsListVO;
import com.hsc.cat.entity.EmployeeSkillEntity;
import com.hsc.cat.entity.Skill;
import com.hsc.cat.enums.CompetencyLevelsEnum;
import com.hsc.cat.enums.SdlcCategory;
import com.hsc.cat.repository.EmployeeDetailRepository;
import com.hsc.cat.repository.EmployeeSkillRepository;
import com.hsc.cat.repository.SkillRepository;



@Service
@Transactional
public class EmployeeSkillService {
	
	private static final Logger LOGGER = (Logger) LogManager.getLogger(EmployeeSkillService.class);
	

	@Autowired
	private EmployeeSkillRepository employeeSkillRepository;
	@Autowired
	private EmployeeDetailRepository employeeDetailRepository;
	@Autowired
	private SkillRepository skillRepository;
	@Autowired
	private SkillService skillService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public UpdateSkillResponse updateSkill(UpdateSkillsListVO updateSkillsListVO) {
		
		UpdateSkillResponse response = new UpdateSkillResponse();
		
		/*List<EmployeeSkillEntity> updateSkillEntityList= new ArrayList<>();
		List<UpdateSkillVO> updateSkillVOList=updateSkillsListVO.getListOfEmployeeSkills();
		for(UpdateSkillVO updateSkillVO:updateSkillVOList) {
		EmployeeSkillEntity updateSkillEntity= new EmployeeSkillEntity();
		
		List<EmployeeSkillEntity> records=employeeSkillRepository.findAll();
		int count=records.size();
		
		System.out.println("\n\n\n"+count);
		EmployeeSkillEntity recordExists=employeeSkillRepository.findByEmpIdAndSkillIdAndWeekNumberAndRatingDoneBy(updateSkillVO.getEmpId(), updateSkillVO.getSkillId(), updateSkillVO.getWeekNumber(), updateSkillVO.getRatingDoneBy());
		
		if(recordExists!=null && !updateSkillVO.getRatingDoneBy().equalsIgnoreCase(RatingDoneByEnums.SELF.getType())) {
			UpdateSkillTO updateSkillTO= new UpdateSkillTO();
			updateSkillTO.setProblem("Record already exists!!");
		}
		updateSkillEntity.setEmpId(updateSkillVO.getEmpId());
		updateSkillEntity.setSkillId(updateSkillVO.getSkillId());
		updateSkillEntity.setRating(LevelsEnum.getLevelNameFromLevel(updateSkillVO.getRating()));
		updateSkillEntity.setWeekNumber(updateSkillVO.getWeekNumber());
		updateSkillEntity.setComment(updateSkillVO.getComment());
		updateSkillEntity.setRatingDoneBy(updateSkillVO.getRatingDoneBy());
		updateSkillEntity.setRatingDoneByEmpId(updateSkillVO.getRatingDoneByEmpId());
 //Check if self data is correct
			if (updateSkillVO.getRatingDoneBy().equalsIgnoreCase(RatingDoneByEnums.SELF.getType())
					&& !updateSkillVO.getEmpId().equals(updateSkillVO.getRatingDoneByEmpId())) {
				// do nothing when self data incorrect
				UpdateSkillTO updateSkillTO= new UpdateSkillTO();
				updateSkillTO.setProblem("Self data incorrect");
				System.out.println("Self data incorrect");
			}
 //Check if manager exists 
  else if(updateSkillVO.getRatingDoneBy().equalsIgnoreCase(RatingDoneByEnums.MANAGER.getType())) {
	  EmployeeDetails manager=employeeDetailRepository.findOne(updateSkillVO.getRatingDoneByEmpId());
	  if(manager==null ) {
		  //do nothing when manager does not exist in the table
//		  System.out.println(manager.getManagerId()!=null);
		  UpdateSkillTO updateSkillTO= new UpdateSkillTO();
		  updateSkillTO.setProblem("manager does not exist in the table");
		  System.out.println("manager does not exist in the table");
	  }
	  //Allow update for only Approved managers
	  else if(recordExists==null && manager!=null && manager.getManagerId().length()<2 && !(manager.getApprovalStatus().equals(ApprovalStatusEnum.APPROVED.getValue()))) {
		   employeeSkillRepository.save(updateSkillEntity);
			updateSkillEntityList.add(updateSkillEntity);
	  }
  }
  
			//Check if peer data i scorrect
  else if(updateSkillVO.getRatingDoneBy().equalsIgnoreCase(RatingDoneByEnums.PEER.getType())) {
	  EmployeeDetails peer=employeeDetailRepository.findOne(updateSkillVO.getRatingDoneByEmpId());
	  if(!employeeDetailRepository.exists(peer.getEmpid())) {
		  //do nothing when peer does not exist in the table
		  UpdateSkillTO updateSkillTO= new UpdateSkillTO();
		  updateSkillTO.setProblem(" peer does not exist in the table");
		  System.out.println(" peer does not exist in the table");
	  }
	  else {
	if(recordExists==null)
	{ employeeSkillRepository.save(updateSkillEntity);
			updateSkillEntityList.add(updateSkillEntity);
	}
	///
	
	  }
  }
else
			{
				if (recordExists == null)

				{
					employeeSkillRepository.save(updateSkillEntity);
					updateSkillEntityList.add(updateSkillEntity);

				}
				else if(recordExists!=null && updateSkillVO.getRatingDoneBy().equalsIgnoreCase(RatingDoneByEnums.SELF.getType())) {
					if(!updateSkillEntity.getRating().equals(updateSkillVO.getRating()))
					{
					employeeSkillRepository.save(updateSkillEntity);
					updateSkillEntityList.add(updateSkillEntity);
					}
					else {
						UpdateSkillTO updateSkillTO= new UpdateSkillTO();
						 updateSkillTO.setProblem("Nothing to update");
						
					}
				}
				
			}
		}
		List<UpdateSkillTO> updateSkillTOList=null;
		if(!updateSkillEntityList.isEmpty() && updateSkillEntityList.size()!=0)
			{
			updateSkillTOList=modelConversion(updateSkillEntityList);
			}
		
		response.setUpdateSkillTOList(updateSkillTOList);*/
		
		List<EmployeeSkillEntity> updateSkillEntityList= new ArrayList<>();
		List<UpdateSkillVO> updateSkillVOList=updateSkillsListVO.getListOfEmployeeSkills();
		for(UpdateSkillVO updateSkillVO:updateSkillVOList) {
			
		//	if(employeeSkillRepository.exists(updateSkillVO.getSkillId())) {
				EmployeeSkillEntity recordExists=employeeSkillRepository.findByEmpIdAndSkillIdAndWeekNumberAndRatingDoneByAndRatingDoneByEmpIdAndSdlcCategory(updateSkillVO.getEmpId(), updateSkillVO.getSkillId(), updateSkillVO.getWeekNumber(), updateSkillVO.getRatingDoneBy(),updateSkillVO.getRatingDoneByEmpId(),SdlcCategory.getSdlcCategoryName(updateSkillVO.getSdlcCategory()));
				
				if(recordExists==null) {
					LOGGER.debug("Creating new row for rating and comments..Rating \"+updateSkillVO.getEmpId()+\" Feedback is being given by \"+updateSkillVO.getRatingDoneByEmpId()");
					EmployeeSkillEntity updateSkillEntity= new EmployeeSkillEntity();
					updateSkillEntity.setEmpId(updateSkillVO.getEmpId());
					updateSkillEntity.setSkillId(updateSkillVO.getSkillId());
					updateSkillEntity.setRating(CompetencyLevelsEnum.getLevelNameFromLevel(updateSkillVO.getRating()));
					updateSkillEntity.setWeekNumber(updateSkillVO.getWeekNumber());
					updateSkillEntity.setComment(updateSkillVO.getComment());
					updateSkillEntity.setRatingDoneBy(updateSkillVO.getRatingDoneBy());
					updateSkillEntity.setRatingDoneByEmpId(updateSkillVO.getRatingDoneByEmpId());
					updateSkillEntity.setCreationDate(new Date());
					updateSkillEntity.setComment(updateSkillVO.getComment());
					updateSkillEntity.setSdlcCategory(SdlcCategory.getSdlcCategoryName(updateSkillVO.getSdlcCategory()));
					System.out.println("\n\n\n\nDate is:"+new Date());
					employeeSkillRepository.save(updateSkillEntity);
					
					updateSkillEntityList.add(updateSkillEntity);
				}
				
				else if(recordExists!=null) {
					LOGGER.debug("Updating already existing rating and comments..Rating "+updateSkillVO.getEmpId()+" Feedback is being given by "+updateSkillVO.getRatingDoneByEmpId());
					
					Date date = new Date();
				employeeSkillRepository.updateEmployeeSkill(updateSkillVO.getSkillId(), CompetencyLevelsEnum.getLevelNameFromLevel(updateSkillVO.getRating()),updateSkillVO.getComment(),updateSkillVO.getWeekNumber(),updateSkillVO.getRatingDoneBy(),date,updateSkillVO.getEmpId(),updateSkillVO.getRatingDoneByEmpId(),SdlcCategory.getSdlcCategoryName(updateSkillVO.getSdlcCategory()));
				
				
				
				EmployeeSkillEntity updateSkillEntity=employeeSkillRepository.findByEmpIdAndSkillIdAndWeekNumberAndRatingDoneByAndRatingDoneByEmpIdAndSdlcCategory(updateSkillVO.getEmpId(), updateSkillVO.getSkillId(), updateSkillVO.getWeekNumber(), updateSkillVO.getRatingDoneBy(),updateSkillVO.getRatingDoneByEmpId(),SdlcCategory.getSdlcCategoryName(updateSkillVO.getSdlcCategory()));
				
				
				System.out.println("\n\n\n"+updateSkillEntity.toString());
				updateSkillEntityList.add(updateSkillEntity);
				}
		//	}//
			/*else {
			EmployeeSkillEntity updateSkillEntity= new EmployeeSkillEntity();
			updateSkillEntity.setEmpId(updateSkillVO.getEmpId());
			updateSkillEntity.setSkillId(updateSkillVO.getSkillId());
			updateSkillEntity.setRating(LevelsEnum.getLevelNameFromLevel(updateSkillVO.getRating()));
			updateSkillEntity.setWeekNumber(updateSkillVO.getWeekNumber());
			updateSkillEntity.setComment(updateSkillVO.getComment());
			updateSkillEntity.setRatingDoneBy(updateSkillVO.getRatingDoneBy());
			updateSkillEntity.setRatingDoneByEmpId(updateSkillVO.getRatingDoneByEmpId());
			updateSkillEntity.setCreationDate(new Date());
			System.out.println("\n\n\n\nDate is:"+new Date());
			employeeSkillRepository.save(updateSkillEntity);
			
			updateSkillEntityList.add(updateSkillEntity);
			}*/
		}
		
		
		List<UpdateSkillTO> updateSkillTOList=null;
		if(!updateSkillEntityList.isEmpty() && updateSkillEntityList.size()!=0)
		{
		updateSkillTOList=modelConversion(updateSkillEntityList);
		}
		response.setUpdateSkillTOList(updateSkillTOList);
		return response;
	}
	
	
	
/*	public ViewSkillListTO viewSkills(String empid) {
		ViewSkillListTO skillsList= new ViewSkillListTO();
		
		List<ViewSkillTO> viewSkillTOList=new ArrayList<>();
		List<EmployeeSkillEntity> employeeSkillEntityList=employeeSkillRepository.findAll();
		
		for(EmployeeSkillEntity employeeSkillEntity : employeeSkillEntityList) {
			if(employeeSkillEntity.getEmpId().equals(empid) && employeeSkillEntity.getRating()!=LevelsEnum.CANNOT_ASSESS.getLevelName()) {
				ViewSkillTO viewSkillTO = new ViewSkillTO();
				viewSkillTO.setEmpId(employeeSkillEntity.getEmpId());
				viewSkillTO.setSkillId(employeeSkillEntity.getSkillId());
				viewSkillTO.setRating(LevelsEnum.getLevelFromName(employeeSkillEntity.getRating()));
				viewSkillTO.setRatingDoneBy(employeeSkillEntity.getRatingDoneBy());
				viewSkillTO.setWeekNumber(employeeSkillEntity.getWeekNumber());
				Skill skill=skillRepository.findOne(employeeSkillEntity.getSkillId());
				viewSkillTO.setSkillName(skill.getSkillName());
				viewSkillTO.setRatingDoneByEmpId(employeeSkillEntity.getRatingDoneByEmpId());
				viewSkillTO.setDescription(skill.getDescription());
				viewSkillTO.setUpdationDate(skill.getUpdationDate());
				viewSkillTOList.add(viewSkillTO);
			}
			
			
			skillsList.setListOfEmployeeSkills(viewSkillTOList);
		}
		return skillsList;
	}*/
	
	
	public ViewSkillListTO viewSkills(String empid) {
		ViewSkillListTO skillsList= new ViewSkillListTO();
		List<ViewSkillTO> viewSkillTOList=new ArrayList<>();
		List<EmployeeSkillEntity> employeeSkillEntityList=new ArrayList<>();
		Set<Integer> listOfSkillId=new HashSet<Integer>();
	/*	if(flag=="true")
		{
			 employeeSkillEntityList=employeeSkillRepository.getViewHistory(empid);
			 
			 HashMap<Integer,EmployeeSkillEntity> selfReviewsMap=new HashMap<>();
			 HashMap<Integer,EmployeeSkillEntity> peerReviewsMap=new HashMap<>();
			 for(EmployeeSkillEntity employeeSkillEntity : employeeSkillEntityList) {
				 
				 if(employeeSkillEntity.getRatingDoneBy().equalsIgnoreCase("Self")) {
					 LOGGER.debug("View history self data putting in map");
					 if(selfReviewsMap.containsKey(employeeSkillEntity.getSkillId())) {
						 //do nothing
					 }
					 else
						 {
						 selfReviewsMap.put(employeeSkillEntity.getSkillId(), employeeSkillEntity);
						 ViewSkillTO viewSkillTO=modelConversion(employeeSkillEntity);
							viewSkillTOList.add(viewSkillTO);
								listOfSkillId.add(employeeSkillEntity.getSkillId());
						 }
				 }
				 else if(employeeSkillEntity.getRatingDoneBy().equalsIgnoreCase("Peer"))
				 {
					 LOGGER.debug("View history peer data putting in map");
					 if(peerReviewsMap.containsKey(employeeSkillEntity.getSkillId())) {
						 //do nothing
					 }
					 else
						 {
						 peerReviewsMap.put(employeeSkillEntity.getSkillId(), employeeSkillEntity);
						 ViewSkillTO viewSkillTO=modelConversion(employeeSkillEntity);
							viewSkillTOList.add(viewSkillTO);
								listOfSkillId.add(employeeSkillEntity.getSkillId());
						 }
				 }
				 
				 skillsList.setListOfSkillId(listOfSkillId);
					skillsList.setListOfEmployeeSkills(viewSkillTOList);
				 
				 //All latest data inserted
				 
				 
				 
				 
			 }
			 
			 
			 
		}*/
		//else {
		 employeeSkillEntityList=employeeSkillRepository.findByEmpIdSkill(empid);
		
		System.out.println("\n\n\n\n66666666666666666666"+employeeSkillEntityList+" "+" Employeeskill is not null "+employeeSkillEntityList!=null+" List is empty  "+employeeSkillEntityList.isEmpty());
		if((employeeSkillEntityList!=null && employeeSkillEntityList.isEmpty())){
			LOGGER.debug("There is no skills record in table for employee id:"+empid);
			LOGGER.info("There is no skills record in table for employee id:"+empid);
			return skillsList;
		}
		
		 listOfSkillId=new HashSet<Integer>();
		
		for(EmployeeSkillEntity employeeSkillEntity : employeeSkillEntityList) {
			
				/*ViewSkillTO viewSkillTO = new ViewSkillTO();
				viewSkillTO.setEmpId(employeeSkillEntity.getEmpId());
				viewSkillTO.setSkillId(employeeSkillEntity.getSkillId());
				viewSkillTO.setRating(LevelsEnum.getLevelFromName(employeeSkillEntity.getRating()));
				viewSkillTO.setRatingDoneBy(employeeSkillEntity.getRatingDoneBy());
				viewSkillTO.setWeekNumber("week-"+employeeSkillEntity.getWeekNumber());
				viewSkillTO.setCreationDate(employeeSkillEntity.getCreationDate());
				Skill skill=skillRepository.findOne(employeeSkillEntity.getSkillId());
				viewSkillTO.setSkillName(skill.getSkillName());
				viewSkillTO.setRatingDoneByEmpId(employeeSkillEntity.getRatingDoneByEmpId());
				viewSkillTO.setDescription(skill.getDescription());
				viewSkillTO.setCreationDateString(getDateForResponse(employeeSkillEntity.getCreationDate()));
				///viewSkillTO.setComment(employeeSkillEntity.getComment());
				 * 
*/				
			ViewSkillTO viewSkillTO=modelConversion(employeeSkillEntity);
			viewSkillTOList.add(viewSkillTO);
				listOfSkillId.add(employeeSkillEntity.getSkillId());
			
		}
			skillsList.setListOfSkillId(listOfSkillId);
			skillsList.setListOfEmployeeSkills(viewSkillTOList);
			
		
			
			String selfComment=null;
			String peerComment=null;
			Date selfCommentDate=null;
//			String selfComment=employeeSkillRepository.getLatestSelfComment(empid).get(0);
//			String peerComment=employeeSkillRepository.getLatestPeerComment(empid).get(0);
			List<String> selfCommentList=employeeSkillRepository.getLatestSelfComment(empid);
			List<Date> selfCommentDateList=employeeSkillRepository.getLatestSelfCommentDate(empid);
			if(selfCommentList!=null && !selfCommentList.isEmpty())
				{
				LOGGER.debug("Latest self comment fetched for: "+empid);
				selfComment=selfCommentList.get(0);
				}
			
			if(selfCommentDateList!=null && !selfCommentDateList.isEmpty())
			{
			
				selfCommentDate=selfCommentDateList.get(0);
			}
			
			/*List<String> peerCommentList=employeeSkillRepository.getLatestPeerComment(empid);
			if(peerCommentList!=null && !peerCommentList.isEmpty())
				{
				LOGGER.debug("Latest peer comment fetched for: "+empid);
				peerComment=peerCommentList.get(0);
				}
			*/
			
		  skillsList.setSelfComment(selfComment);
		 // skillsList.setPeerComment(peerComment);
		  
		  //To get list of peer comments :: default to latest 10
		  String queryString="select distinct comment,creationDate from EmployeeSkillEntity e where e.empId=:empId and  e.ratingDoneBy='Peer' order by e.creationDate desc";
		  Query query=entityManager.createQuery(queryString);
		  query.setParameter("empId", empid);
		  query.setMaxResults(10);
		  
		  List<Object[]> fetchedResult=query.getResultList();
		  List<PeercommentPlusDateListTO> peercommentPlusDateList=new ArrayList<>();
		  List<String> peerCommentList=new ArrayList<>();
		  List<Date> peerCommentDateList=new ArrayList<>();
		  
		  for(int i=0;i<fetchedResult.size();i++)
		  {
			 Object[] ob=fetchedResult.get(i);
			 peerCommentList.add((String) ob[0]);
			 peerCommentDateList.add( (Date) ob[1]);
			 PeercommentPlusDateListTO temp=new PeercommentPlusDateListTO();
			 temp.setComment((String) ob[0]);
			temp.setTimestamp((Date) ob[1]);
			 
			 peercommentPlusDateList.add(temp);
		  }
		  
		  skillsList.setPeercomments(peerCommentList);
		  skillsList.setPeercommentsDate(peerCommentDateList);
		  skillsList.setSelfCommentTime(selfCommentDate);
		  skillsList.setPeercommentPlusDateList(peercommentPlusDateList);
		  
		return skillsList;
	}
	
	
	
	public String getDateForResponse(Date d) {
		if(d==null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString =sdf.format(d);
		return dateString;
	}
	
   public MapTO fetchMapDetails(MapVO mapVO) {
	   String empId=mapVO.getEmpId();
	   
	   int quarter=mapVO.getQuarterNumber();
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
		List<EmployeeSkillEntity> employeeSkillEntityList=employeeSkillRepository.findByEmpIdCustom(empId,start,end);
		
		String ratingDoneBy=mapVO.getRatingDoneBy();
		List<String> skillNameList=new ArrayList<>();
		MapTO mapTO=new MapTO();
		HashMap<String, List<SubjectTO>> map=new HashMap<>();
		for(EmployeeSkillEntity e:employeeSkillEntityList) {
			if(e.getRatingDoneBy().equalsIgnoreCase(ratingDoneBy)) {
				int skillId=e.getSkillId();
				Skill skill =skillRepository.findOne(skillId);
				String skillName=skill.getSkillName();
               skillNameList.add(skill.getSkillName());
			}
		}
		
		for(int i=0;i<skillNameList.size();i++) {
			System.out.print(skillNameList.get(i)+" ");
		}
		
		//Fetched all unique skills
		int totalSkills=skillNameList.size();
		int c=0;
		
		while(c<totalSkills) {
		Skill skill=skillRepository.findBySkillName(skillNameList.get(c));
		int skillId=skill.getSkillId();
	List<EmployeeSkillEntity> employeeSkillsList= employeeSkillRepository.findByEmpIdAndSkillIdAndRatingDoneByOrderByWeekNumber(empId, skillId, ratingDoneBy);
	List<SubjectTO> subjectsList= new ArrayList<>();
	for(EmployeeSkillEntity e: employeeSkillsList) {
		SubjectTO subjectTO=new SubjectTO();
		subjectTO.setRating(CompetencyLevelsEnum.getLevelFromName(e.getRating()));
		subjectTO.setWeekNumber(e.getWeekNumber());
		subjectsList.add(subjectTO);
		}
	map.put(skillNameList.get(c), subjectsList);
	++c;
	
		
	
		}//while
		mapTO.setMap(map);
		  return mapTO;
		
	}
   
   
   
   
  /* public List<SkillTO> getAllSelfRatedSkills(String empId){
	 //  List<String> list=new ArrayList<>();
	   List<SkillTO> skillTOList=new ArrayList<>();
	   List<EmployeeSkillEntity> employeeSkillEntityList=employeeSkillRepository.getAllSelfRatedSkillsCustom(empId);
	   
	   HashMap<Skill,Object> map=new HashMap<>();
	   for(EmployeeSkillEntity e:employeeSkillEntityList ) {
		   int skillId=e.getSkillId();
		   Skill skill=skillRepository.findOne(skillId);
		  // String skillName=skill.getSkillName();
		   map.put(skill, null);
	   }
	   
	   System.out.println("MAP:"+map);
	   
	  for(Entry<Skill, Object> entry:map.entrySet()) {
		 SkillTO skillTO=skillService.modelConversion(entry.getKey());
		 skillTOList.add(skillTO);
	  }
	   return skillTOList;
   }*/
   
   
   
   public List<SelfRatedSkillsTO> getAllSelfRatedSkills(String empId,int sdlcCategoryNum){
		 LOGGER.debug("Request came to view all self rated skills of employee:"+empId+ " for SDLC phase:"+SdlcCategory.getSdlcCategoryName(sdlcCategoryNum));
		   List<SelfRatedSkillsTO> skillTOList=new ArrayList<>();
		   List<EmployeeSkillEntity> employeeSkillEntityList=employeeSkillRepository.getAllSelfRatedSkillsCustom(empId);
		   
		   String sdlcCategory=SdlcCategory.getSdlcCategoryName(sdlcCategoryNum);
		   
		  /* HashMap<Skill,Object> map=new HashMap<>();
		   for(EmployeeSkillEntity e:employeeSkillEntityList ) {
			   int skillId=e.getSkillId();
			   Skill skill=skillRepository.findOne(skillId);
			  // String skillName=skill.getSkillName();
			   map.put(skill, null);
		   }
		   
		   System.out.println("MAP:"+map);
		   
		  for(Entry<Skill, Object> entry:map.entrySet()) {
			 SkillTO skillTO=skillService.modelConversion(entry.getKey());
			 skillTOList.add(skillTO);
		  }*/
		   
//		   String weekQueryString="select weekNumber from EmployeeSkillEntity where sdlcCategory=:sdlcCategory order by weekNumber desc";
//		   Query queryWeek=entityManager.createQuery(weekQueryString);
//		   queryWeek.setParameter("sdlcCategory", sdlcCategory);
//		   queryWeek.setMaxResults(1);
//		   
//		   int weekNumber=(int) queryWeek.getResultList().get(0);
		   
		   
		 //  System.out.println("\n\n\n***"+weekNumber);
		   String queryString="select a.skillId,a.rating from EmployeeSkillEntity a where a.ratingDoneBy='Self' and a.empId=:empId and a.sdlcCategory=:sdlcCategory order by a.weekNumber desc";
		   Query query=entityManager.createQuery(queryString);
		   query.setParameter("empId", empId);
		   query.setParameter("sdlcCategory", sdlcCategory);
		   List<Object[]> objectsList=query.getResultList();
		   HashMap<Integer,Integer> map=new HashMap<>(); //Key=skillId and value=rating
		   
		   /*
		    * If rating already exists don't overwrite.
		    * Get latest ratings per skill
		    */
		   for(int i=0;i<objectsList.size();i++)
		   {
			   Object[] ob=objectsList.get(i);
			   int skillId=(int) ob[0];
			   int rating=CompetencyLevelsEnum.getLevelFromName((String) ob[1]);
			   if(map.containsKey(skillId)) {
				   continue;
			   }
			   else {
				   map.put(skillId, rating);
			   }
			   Skill skill=skillRepository.findBySkillId(skillId);
			   SelfRatedSkillsTO selfRatedSkillsTO= new SelfRatedSkillsTO();
			   selfRatedSkillsTO.setEmpid(empId);
			   selfRatedSkillsTO.setSkillId(skillId);
			   selfRatedSkillsTO.setSkillName(skill.getSkillName());
			   selfRatedSkillsTO.setDescription(skill.getDescription());
			   selfRatedSkillsTO.setRating(rating);
			   selfRatedSkillsTO.setSdlcCategory(sdlcCategoryNum);
			   skillTOList.add(selfRatedSkillsTO);
		   }
		   return skillTOList;
	   }
   
   
   
   public List<SkillTO> getAllNotSelfRatedSkills(String empId,int sdlcCategoryNum){
		 LOGGER.debug("Request came to view all not self rated skills of employee:"+empId+ " for SDLC phase:"+SdlcCategory.getSdlcCategoryName(sdlcCategoryNum));
		   List<SkillTO> skillTOList=new ArrayList<>();
		   
		   List<Skill> allSkills=skillRepository.findAll();
		   
		   String queryString="select a.skillId from EmployeeSkillEntity a WHERE a.empId=:empId and a.sdlcCategory=:sdlcCategory and a.ratingDoneBy='Self'";
		   Query query=entityManager.createQuery(queryString);
		   query.setParameter("empId", empId);
		   query.setParameter("sdlcCategory", SdlcCategory.getSdlcCategoryName(sdlcCategoryNum));
		   List<Integer> skillIdList=query.getResultList();
		   
		   
		   HashMap<Integer,Object> map=new HashMap<>(); //key=skillid value=dummy object
		   for(int i=0;i<skillIdList.size();i++) {
			   map.put(skillIdList.get(i), null);
		   }
		   
		   System.out.println("\n\n***"+map);
		   
		   for(int i=0;i<allSkills.size();i++) {
			   Skill skill=allSkills.get(i);
			   if(!map.containsKey(skill.getSkillId())) {
				   SkillTO skillTO=skillService.modelConversion(skill);
				   skillTOList.add(skillTO);
			   }
		   }
		   return skillTOList;
	   }
   
   
   
   
   
   

	public List<SelfRatedSkillsTO> getAllPeerReviewedSkills(GetAllPeerReviewedSkillsVO getAllPeerReviewedSkillsVO){
		List<SelfRatedSkillsTO> skillTOList=new ArrayList<>();
		
		/*String queryString="select a.skillId,a.rating from EmployeeSkillEntity a where a.ratingDoneBy='Peer' and a.empId=:empId and a.ratingDoneByEmpId=:peerId and a.sdlcCategory=:sdlcCategory order by a.weekNumber desc";
		
		Query query=entityManager.createQuery(queryString);
		   query.setParameter("empId", getAllPeerReviewedSkillsVO.getEmpId());
		   query.setParameter("sdlcCategory", SdlcCategory.getSdlcCategoryName(getAllPeerReviewedSkillsVO.getSdlcCategoryNum()));
		   query.setParameter("peerId", getAllPeerReviewedSkillsVO.getPeerEmpId());
		   List<Object[]> objectsList=query.getResultList();
		   HashMap<Integer,Integer> map=new HashMap<>(); //Key=skillId and value=rating
		   
		   
		    * If rating already exists don't overwrite.
		    * Get latest ratings per skill
		    
		   for(int i=0;i<objectsList.size();i++)
		   {
			   Object[] ob=objectsList.get(i);
			   int skillId=(int) ob[0];
			   int rating=CompetencyLevelsEnum.getLevelFromName((String) ob[1]);
			   if(map.containsKey(skillId)) {
				   continue;
			   }
			   else {
				   map.put(skillId, rating);
			   }
			   Skill skill=skillRepository.findBySkillId(skillId);
			   SelfRatedSkillsTO selfRatedSkillsTO= new SelfRatedSkillsTO();
			   selfRatedSkillsTO.setEmpid(getAllPeerReviewedSkillsVO.getEmpId());
			   selfRatedSkillsTO.setSkillId(skillId);
			   selfRatedSkillsTO.setSkillName(skill.getSkillName());
			   selfRatedSkillsTO.setDescription(skill.getDescription());
			   selfRatedSkillsTO.setRating(rating);
			   selfRatedSkillsTO.setSdlcCategory(getAllPeerReviewedSkillsVO.getSdlcCategoryNum());
			   skillTOList.add(selfRatedSkillsTO);
		   }*/
		
		
		String queryString2="select a.skillId from EmployeeSkillEntity a where a.ratingDoneBy='Self' and a.empId=:empId and a.sdlcCategory=:sdlcCategory order by a.weekNumber desc";
		Query query2=entityManager.createQuery(queryString2);
		   query2.setParameter("empId",  getAllPeerReviewedSkillsVO.getEmpId());
		   query2.setParameter("sdlcCategory", SdlcCategory.getSdlcCategoryName(getAllPeerReviewedSkillsVO.getSdlcCategoryNum()));
		   
		   
		   List<Integer> objectsList2=(List<Integer>)query2.getResultList();
		   
		//We will store all the self rated skills without their rating
		   HashMap<Integer,Integer> map=new HashMap<>(); //Key=skillId and value=rating
		   List<Integer> list=new ArrayList<>();
		   for(int i=0;i<objectsList2.size();i++) {
			   int skillId=objectsList2.get(i);
			   if(map.containsKey(skillId))
				   continue;
			   else
				   {
				   map.put(skillId, 0);
				   list.add(skillId);
				   }
		   }
		   
		   System.out.println("\n^^^^"+map);
		
		   String queryString="select a.skillId,a.rating from EmployeeSkillEntity a where a.ratingDoneBy='Peer' and a.empId=:empId and a.ratingDoneByEmpId=:peerId and a.sdlcCategory=:sdlcCategory order by a.weekNumber desc";
			
			Query query=entityManager.createQuery(queryString);
			   query.setParameter("empId", getAllPeerReviewedSkillsVO.getEmpId());
			   query.setParameter("sdlcCategory", SdlcCategory.getSdlcCategoryName(getAllPeerReviewedSkillsVO.getSdlcCategoryNum()));
			   query.setParameter("peerId", getAllPeerReviewedSkillsVO.getPeerEmpId());
			   List<Object[]> objectsList=query.getResultList();
			   
			   for(int i=0;i<objectsList.size();i++)
			   {
				   Object[] ob=objectsList.get(i);
				   int skillId=(int) ob[0];
				   int rating=CompetencyLevelsEnum.getLevelFromName((String) ob[1]);
				   if(!map.containsKey(skillId)) {
					   continue;
				   }
				   else if(map.get(skillId)==0) {
					   map.put(skillId, rating);
					   list.remove(new Integer(skillId));
				   }
				   
				   
				   
				   
				   
				   Skill skill=skillRepository.findBySkillId(skillId);
				   SelfRatedSkillsTO selfRatedSkillsTO= new SelfRatedSkillsTO();
				   selfRatedSkillsTO.setEmpid(getAllPeerReviewedSkillsVO.getEmpId());
				   selfRatedSkillsTO.setSkillId(skillId);
				   selfRatedSkillsTO.setSkillName(skill.getSkillName());
				   selfRatedSkillsTO.setDescription(skill.getDescription());
				   selfRatedSkillsTO.setRating(rating);
				   selfRatedSkillsTO.setSdlcCategory(getAllPeerReviewedSkillsVO.getSdlcCategoryNum());
				   skillTOList.add(selfRatedSkillsTO);
			   }
			   
			   
			   
			   //Do same for remaining list items to populate self rated skills
			   for(int i=0;i<list.size();i++) {
				   int skillId=list.get(i);
				   Skill skill=skillRepository.findBySkillId(skillId);
				   SelfRatedSkillsTO selfRatedSkillsTO= new SelfRatedSkillsTO();
				   selfRatedSkillsTO.setEmpid(getAllPeerReviewedSkillsVO.getEmpId());
				   selfRatedSkillsTO.setSkillId(skillId);
				   selfRatedSkillsTO.setSkillName(skill.getSkillName());
				   selfRatedSkillsTO.setDescription(skill.getDescription());
				   selfRatedSkillsTO.setRating(1000);
				   selfRatedSkillsTO.setNotPeerRated(true);
				   selfRatedSkillsTO.setSdlcCategory(getAllPeerReviewedSkillsVO.getSdlcCategoryNum());
				   skillTOList.add(selfRatedSkillsTO); 
			   }
		return skillTOList;
	}
   
   
  public ViewSkillListTO getViewHistory(String empid) {
	  ViewSkillListTO skillsList= new ViewSkillListTO();
		List<ViewSkillTO> viewSkillTOList=new ArrayList<>();
		List<EmployeeSkillEntity> employeeSkillEntityList=new ArrayList<>();
		Set<Integer> listOfSkillId=new HashSet<Integer>();
		
			 employeeSkillEntityList=employeeSkillRepository.getViewHistory(empid);
			 
			 HashMap<Integer,EmployeeSkillEntity> selfReviewsMap=new HashMap<>();
			 HashMap<Integer,EmployeeSkillEntity> peerReviewsMap=new HashMap<>();
			 for(EmployeeSkillEntity employeeSkillEntity : employeeSkillEntityList) {
				 
				 if(employeeSkillEntity.getRatingDoneBy().equalsIgnoreCase("Self")) {
					 LOGGER.debug("View history self data putting in map");
					 if(selfReviewsMap.containsKey(employeeSkillEntity.getSkillId())) {
						 //do nothing
					 }
					 else
						 {
						 selfReviewsMap.put(employeeSkillEntity.getSkillId(), employeeSkillEntity);
						 ViewSkillTO viewSkillTO=modelConversion(employeeSkillEntity);
							viewSkillTOList.add(viewSkillTO);
								listOfSkillId.add(employeeSkillEntity.getSkillId());
						 }
				 }
				 else if(employeeSkillEntity.getRatingDoneBy().equalsIgnoreCase("Peer"))
				 {
					 LOGGER.debug("View history peer data putting in map");
					 if(peerReviewsMap.containsKey(employeeSkillEntity.getSkillId())) {
						 //do nothing
					 }
					 else
						 {
						 peerReviewsMap.put(employeeSkillEntity.getSkillId(), employeeSkillEntity);
						 ViewSkillTO viewSkillTO=modelConversion(employeeSkillEntity);
							viewSkillTOList.add(viewSkillTO);
								listOfSkillId.add(employeeSkillEntity.getSkillId());
						 }
				 }
				 
				 skillsList.setListOfSkillId(listOfSkillId);
					skillsList.setListOfEmployeeSkills(viewSkillTOList);
					
			 }
			 
			 String selfComment=null;
				String peerComment=null;
				Date selfCommentDate=null;
//				String selfComment=employeeSkillRepository.getLatestSelfComment(empid).get(0);
//				String peerComment=employeeSkillRepository.getLatestPeerComment(empid).get(0);
				List<String> selfCommentList=employeeSkillRepository.getLatestSelfComment(empid);
				List<Date> selfCommentDateList=employeeSkillRepository.getLatestSelfCommentDate(empid);
				if(selfCommentList!=null && !selfCommentList.isEmpty())
					{
					LOGGER.debug("Latest self comment fetched for: "+empid);
					selfComment=selfCommentList.get(0);
					}
				
				if(selfCommentDateList!=null && !selfCommentDateList.isEmpty())
				{
				
					selfCommentDate=selfCommentDateList.get(0);
				}
				
			/*	List<String> peerCommentList=employeeSkillRepository.getLatestPeerComment(empid);
				if(peerCommentList!=null && !peerCommentList.isEmpty())
					{
					LOGGER.debug("Latest peer comment fetched for: "+empid);
					peerComment=peerCommentList.get(0);
					}*/
				
				
			  skillsList.setSelfComment(selfComment);
			 // skillsList.setPeerComment(peerComment);
			  
			  //To get list of peer comments :: default to latest 10
			  String queryString="select distinct comment,creationDate from EmployeeSkillEntity e where e.empId=:empId and  e.ratingDoneBy='Peer' order by e.creationDate desc";
			  Query query=entityManager.createQuery(queryString);
			  query.setParameter("empId", empid);
			  query.setMaxResults(10);
			  
			  List<Object[]> fetchedResult=query.getResultList();
			  List<PeercommentPlusDateListTO> peercommentPlusDateList=new ArrayList<>();
			  List<String> peerCommentList=new ArrayList<>();
			  List<Date> peerCommentDateList=new ArrayList<>();
			  
			  for(int i=0;i<fetchedResult.size();i++)
			  {
				 Object[] ob=fetchedResult.get(i);
				 peerCommentList.add((String) ob[0]);
				 peerCommentDateList.add( (Date) ob[1]);
				 PeercommentPlusDateListTO temp=new PeercommentPlusDateListTO();
				 temp.setComment((String) ob[0]);
				temp.setTimestamp((Date) ob[1]);
				 
				 peercommentPlusDateList.add(temp);
			  }
			  
			  skillsList.setPeercomments(peerCommentList);
			  skillsList.setPeercommentsDate(peerCommentDateList);
			  skillsList.setSelfCommentTime(selfCommentDate);
			  skillsList.setPeercommentPlusDateList(peercommentPlusDateList);
			return skillsList;
		
  }

	public List<UpdateSkillTO> modelConversion(List<EmployeeSkillEntity> updateSkillEntityList) {
		List<UpdateSkillTO> updateSkillTOList= new ArrayList<>();
		for(EmployeeSkillEntity saved: updateSkillEntityList) {
		UpdateSkillTO updateSkillTO = new UpdateSkillTO();
		updateSkillTO.setId(saved.getId());
		updateSkillTO.setEmpId(saved.getEmpId());
		updateSkillTO.setSkillId(saved.getSkillId());
		updateSkillTO.setComment(saved.getComment());
		updateSkillTO.setSdlcCategory(SdlcCategory.getCategoryFromName(saved.getSdlcCategory()));
		updateSkillTO.setRating(CompetencyLevelsEnum.getLevelFromName(saved.getRating()));
		updateSkillTO.setRatingDoneBy(saved.getRatingDoneBy());
		updateSkillTO.setRatingDoneByEmpId(saved.getRatingDoneByEmpId());
		updateSkillTO.setWeekNumber(saved.getWeekNumber());
		updateSkillTO.setCreationDate(saved.getCreationDate());
		updateSkillTO.setCreationDateString(getDateForResponse(updateSkillTO.getCreationDate()));
		updateSkillTOList.add(updateSkillTO);
		
		}
		
		return updateSkillTOList;
	}
	
	
	public ViewSkillTO modelConversion(EmployeeSkillEntity employeeSkillEntity) {
		ViewSkillTO viewSkillTO = new ViewSkillTO();
		viewSkillTO.setEmpId(employeeSkillEntity.getEmpId());
		viewSkillTO.setSkillId(employeeSkillEntity.getSkillId());
		viewSkillTO.setRating(CompetencyLevelsEnum.getLevelFromName(employeeSkillEntity.getRating()));
		viewSkillTO.setRatingDoneBy(employeeSkillEntity.getRatingDoneBy());
		viewSkillTO.setWeekNumber("week-"+employeeSkillEntity.getWeekNumber());
		viewSkillTO.setCreationDate(employeeSkillEntity.getCreationDate());
		Skill skill=skillRepository.findOne(employeeSkillEntity.getSkillId());
		viewSkillTO.setSkillName(skill.getSkillName());
		viewSkillTO.setRatingDoneByEmpId(employeeSkillEntity.getRatingDoneByEmpId());
		viewSkillTO.setDescription(skill.getDescription());
		viewSkillTO.setCreationDateString(getDateForResponse(employeeSkillEntity.getCreationDate()));
		viewSkillTO.setSdlcCategory(SdlcCategory.getCategoryFromName(employeeSkillEntity.getSdlcCategory()));
		///viewSkillTO.setComment(employeeSkillEntity.getComment());
		
		return viewSkillTO;
	}
}
