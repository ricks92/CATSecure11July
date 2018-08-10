package com.hsc.cat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.hsc.cat.TO.GetResourcesForEachProjRoleResponse;
import com.hsc.cat.TO.GetResourcesForEachProjRoleTO;
import com.hsc.cat.TO.ProfileTO;
import com.hsc.cat.TO.ViewProfileTO;
import com.hsc.cat.VO.CreateProfileVO;
import com.hsc.cat.VO.UpdateProfileVO;
import com.hsc.cat.entity.EmployeeDetails;
import com.hsc.cat.entity.ProfileEntity;
import com.hsc.cat.entity.UserDetailsEntity;
import com.hsc.cat.enums.RoleCategoryEnum;
import com.hsc.cat.enums.SkillCategoryEnum;
import com.hsc.cat.repository.EmployeeDetailRepository;
import com.hsc.cat.repository.ProfileRepository;
import com.hsc.cat.repository.SkillProfileRepository;
import com.hsc.cat.repository.UserRepository;
import com.hsc.cat.utilities.Roles;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
private SkillProfileService skillProfileService;
	
	@Autowired
	private SkillProfileRepository skillProfileRepository;
	
	@Autowired
	private EmployeeDetailRepository employeeDetailRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public ProfileTO createProfile(CreateProfileVO profileVO) {
		
		ProfileEntity exists=profileRepository.findByEmpId(profileVO.getUserName());
		
		List<String> skillsInProfile=new ArrayList<>();
		
		ProfileTO createProfileTO=new ProfileTO();
		
		if(exists==null) {
		ProfileEntity profileEntity=new ProfileEntity();
		profileEntity.setEmpId(profileVO.getUserName());
		profileEntity.setProjectRole(profileVO.getProjectRole());
		Date date=new Date();
		profileEntity.setCreationDate(date);
		profileRepository.save(profileEntity);
		
		ProfileEntity saved=profileRepository.findByEmpId(profileVO.getUserName());
		
		createProfileTO.setFirstName(profileVO.getFirstName());
		createProfileTO.setLastName(profileVO.getLastName());
		createProfileTO.setDepartment(profileVO.getDepartment());
		createProfileTO.setEmailId(profileVO.getEmailId());
		createProfileTO.setProjectRole(profileEntity.getProjectRole());
		createProfileTO.setUserName(profileVO.getUserName());
	//	createProfileTO.setSelectedSkills(profileVO.getSelectedSkills());
		createProfileTO.setSelectedDomainSkills(profileVO.getSelectedDomainSkills());
		createProfileTO.setSelectedOtherDomainSkills(profileVO.getSelectedOtherDomainSkills());
		//skillsInProfile.addAll(profileVO.getSelectedDomainSkills());
		//skillsInProfile.addAll(profileVO.getSelectedOtherDomainSkills());
		
		//createProfileTO.setSelectedSkills(skillsInProfile);
		System.out.println("\n\n\n#######################"+saved.getId());
		
		for(String skill:createProfileTO.getSelectedDomainSkills()) {
			skillProfileService.saveSkillProfile(skill, saved.getId());
		}
		
		for(String skill:createProfileTO.getSelectedOtherDomainSkills()) {
			skillProfileService.saveSkillProfile(skill, saved.getId());
		}
		
//		for(int i=0;i<createProfileTO.getSelectedSkills().size();i++) {
//			System.out.print(createProfileTO.getSelectedSkills().get(i)+" ");
//		}
		
		}
		
		
		else {
		//We need to update the profile
			
			profileRepository.updateProfile(profileVO.getUserName(), profileVO.getProjectRole());
			ProfileEntity saved=profileRepository.findByEmpId(profileVO.getUserName());
			System.out.println("\n\n\n#######################"+saved.getId());
			createProfileTO.setFirstName(profileVO.getFirstName());
			createProfileTO.setLastName(profileVO.getLastName());
			createProfileTO.setDepartment(profileVO.getDepartment());
			createProfileTO.setEmailId(profileVO.getEmailId());
			createProfileTO.setProjectRole(saved.getProjectRole());
			createProfileTO.setUserName(profileVO.getUserName());
			createProfileTO.setSelectedDomainSkills(profileVO.getSelectedDomainSkills());
			createProfileTO.setSelectedOtherDomainSkills(profileVO.getSelectedOtherDomainSkills());
			//createProfileTO.setSelectedSkills(profileVO.getSelectedSkills());
			//skillsInProfile.addAll(profileVO.getSelectedDomainSkills());
			//skillsInProfile.addAll(profileVO.getSelectedOtherDomainSkills());
			
			//createProfileTO.setSelectedSkills(skillsInProfile);
//			if(profileVO.getSelectedSkills()!=null && !profileVO.getSelectedSkills().isEmpty())
//			{
//				//delete previous skills
////				//skillProfileRepository.deleteByProfileId(saved.getId());
////				String query="Delete from SkillProfileEntity e WHERE e.profileId=:profileId";
////				Query q=entityManager.createQuery(query);
////				q.setParameter("profileId",saved.getId());
////				List<Long> list=q.getResultList();
////				System.out.println("Skills deleted from profile: "+list.size());
//				
//				skillProfileRepository.deleteByProfileId(saved.getId());
//			}
			
			
			/*for(int i=0;i<createProfileTO.getSelectedSkills().size();i++) {
				System.out.print(createProfileTO.getSelectedSkills().get(i)+" ");
			}
	*/
			
			for(String skill:createProfileTO.getSelectedDomainSkills()) {
				skillProfileService.saveSkillProfile(skill, saved.getId());
			}
			
			for(String skill:createProfileTO.getSelectedOtherDomainSkills()) {
				skillProfileService.saveSkillProfile(skill, saved.getId());
			}
			
			
			
			
			skillsInProfile.addAll(createProfileTO.getSelectedDomainSkills());
			skillsInProfile.addAll(createProfileTO.getSelectedOtherDomainSkills());
			
			Set<String> set=new HashSet<>(skillsInProfile);
			
			List<String> skillsInProfileWithoutDuplicates=new ArrayList<>(set);
			
			if(skillsInProfileWithoutDuplicates!=null && !skillsInProfileWithoutDuplicates.isEmpty())
			{
				skillProfileRepository.deleteByProfileId(saved.getId());
			}
			for(String skill:skillsInProfileWithoutDuplicates) {
				skillProfileService.saveSkillProfile(skill, saved.getId());
			}
			
		}
		return createProfileTO;
	}
	
	
	public String findCategoryFromRole(String role) {
String category=null;
		
		if(role.equalsIgnoreCase(RoleCategoryEnum.BACKEND_DEVELOPER.getName()))
			category=SkillCategoryEnum.BACKEND.getName();
		else if(role.equalsIgnoreCase(RoleCategoryEnum.FRONTEND_DEVELOPER.getName()))
			category=SkillCategoryEnum.FRONTEND.getName();
			else if(role.equalsIgnoreCase(RoleCategoryEnum.FULLSTACK_DEVELOPER.getName()))
				category=SkillCategoryEnum.FULLSTACK.getName();
			else if(role.equalsIgnoreCase(RoleCategoryEnum.MANUAL_TESTER.getName()))
				category=SkillCategoryEnum.MANUAL.getName();
			else if(role.equalsIgnoreCase(RoleCategoryEnum.AUTOMATION_TESTER.getName()))
				category=SkillCategoryEnum.AUTOMATION.getName();
			
		
		return category;
	}
	
	public ProfileTO viewProfile(String empId) {
		ProfileTO createProfileTO=new ProfileTO();
		ProfileEntity profileEntity=profileRepository.findByEmpId(empId);
		
		EmployeeDetails emp=employeeDetailRepository.findOne(empId);
		
		
		
		createProfileTO.setFirstName(emp.getFirstName());
		createProfileTO.setLastName(emp.getLastName());
		createProfileTO.setEmailId(emp.getEmail());
		createProfileTO.setProjectRole(profileEntity.getProjectRole());
		createProfileTO.setUserName(empId);
		createProfileTO.setDepartment(emp.getDepartment());
		//createProfileTO.setSelectedSkills(skillProfileService.viewSkillsInProfile(profileEntity.getId()));
		String category=findCategoryFromRole(profileEntity.getProjectRole());
		
		System.out.println("\n\n*****"+category);
		List<String> domainSkillsList=new ArrayList<>();
		List<String> otherDomainSkillsList =new ArrayList<>();
		
		if(profileEntity.getProjectRole().equalsIgnoreCase(RoleCategoryEnum.FULLSTACK_DEVELOPER.getName())) {
			domainSkillsList=skillProfileRepository.getSelectedDomainSKillsFullstack(empId);
			otherDomainSkillsList=skillProfileRepository.getSelectedOtherDomainSKillsFullstack(empId);
		}
		else {
			
			domainSkillsList=skillProfileRepository.getSelectedDomainSKills(empId, category);
			otherDomainSkillsList=skillProfileRepository.getSelectedOtherDomainSKills(empId, category);
		
		}
		
		createProfileTO.setSelectedDomainSkills(domainSkillsList);
		createProfileTO.setSelectedOtherDomainSkills(otherDomainSkillsList);
		
		List<String> selectedSkillList=new ArrayList<>();
	     selectedSkillList.addAll(domainSkillsList);
	     selectedSkillList.addAll(otherDomainSkillsList);
	     createProfileTO.setSelectedSkills(selectedSkillList);
		return createProfileTO;
	}
	
	
	
	
	
	public ViewProfileTO viewProfile2(String empid) {
		ProfileEntity profileEntity=profileRepository.findByEmpId(empid);
		
		ViewProfileTO viewProfileTO =new ViewProfileTO();
		if(profileEntity==null) return null;
		viewProfileTO.setFirstName(profileEntity.getFirstname());
		viewProfileTO.setLastName(profileEntity.getLastname());
		viewProfileTO.setEmailId(profileEntity.getEmail());
		viewProfileTO.setUserName(profileEntity.getEmpId());
		viewProfileTO.setProjectRole(RoleCategoryEnum.getProjectRoleNumberFromName(profileEntity.getProjectRole()));
		
		return viewProfileTO;
	}
	
	
	
	
	
	
	public GetResourcesForEachProjRoleResponse getResourcesForEachProjectRole() {
		GetResourcesForEachProjRoleResponse response = new GetResourcesForEachProjRoleResponse();
		List<GetResourcesForEachProjRoleTO> list=new ArrayList<>();
		for(RoleCategoryEnum rce: RoleCategoryEnum.values()) {
			String projectRole=rce.getName();
			Query q=entityManager.createQuery("select count(pf) from ProfileEntity pf where pf.projectRole=:projectRole");
			q.setParameter("projectRole", projectRole);
			long count=(long) q.getResultList().get(0);
			System.out.println("Resources in:"+projectRole+":"+count);
			GetResourcesForEachProjRoleTO object= new GetResourcesForEachProjRoleTO();
			object.setRole(projectRole);
			object.setNumberOfResources(count);
			list.add(object);
		}
		response.setList(list);
		return response;
	}
	
	
	public ViewProfileTO updateProfile(@RequestBody UpdateProfileVO updateProfileVO) {
		ViewProfileTO viewProfileTO =new ViewProfileTO();
		String projectRole=RoleCategoryEnum.getProjectRoleNameFromNumber(updateProfileVO.getProjectRole());
		
		UserDetailsEntity user=userRepository.findByUsername(updateProfileVO.getEmpId());
		 int profileId=0;
		if(user.getRole().equalsIgnoreCase(Roles.EMPLOYEE))
	   {
			profileId=profileRepository.updateProfile2(updateProfileVO.getEmpId(), projectRole, updateProfileVO.getFirstname(), updateProfileVO.getLastname());
	   }
		
		else if(user.getRole().equalsIgnoreCase(Roles.MANAGER))
				{
			profileId=profileRepository.updateProfileForManager(updateProfileVO.getEmpId(), updateProfileVO.getFirstname(), updateProfileVO.getLastname());
				}
		
		
		
		System.out.println("\n\n\n\n&&&&&&&&&&"+profileId);
	  ProfileEntity profileEntity=profileRepository.findByEmpId(updateProfileVO.getEmpId());
	  
	  List<EmployeeDetails> emp=employeeDetailRepository.findByEmpid(updateProfileVO.getEmpId());
	  employeeDetailRepository.updateName(profileEntity.getFirstname(), profileEntity.getLastname(), profileEntity.getEmpId());
	  //We need to update the first name and last name in emp details as well
	  
	  if(profileEntity==null) return null;
		viewProfileTO.setFirstName(profileEntity.getFirstname());
		viewProfileTO.setLastName(profileEntity.getLastname());
		viewProfileTO.setEmailId(profileEntity.getEmail());
		viewProfileTO.setUserName(profileEntity.getEmpId());
		viewProfileTO.setProjectRole(RoleCategoryEnum.getProjectRoleNumberFromName(profileEntity.getProjectRole()));
		
		return viewProfileTO;
	}
	
//	public List<String> populateDropdownOtherDomain(PopulateDropdownOtherDomainVO incomingVO){
//		String projectRole=RoleCategoryEnum.getProjectRoleNameFromNumber(incomingVO.getProjectRole());
//		List<String> domainSelectedSkills=incomingVO.getDomainSelectedSkills();
//		
//		
//		
//	}
}
