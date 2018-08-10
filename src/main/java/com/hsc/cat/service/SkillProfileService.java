package com.hsc.cat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsc.cat.VO.DeleteSkillsFromProfileVO;
import com.hsc.cat.entity.ProfileEntity;
import com.hsc.cat.entity.Skill;
import com.hsc.cat.entity.SkillProfileEntity;
import com.hsc.cat.repository.ProfileRepository;
import com.hsc.cat.repository.SkillProfileRepository;
import com.hsc.cat.repository.SkillRepository;

@Service
@Transactional
public class SkillProfileService {

	
	private static final Logger LOGGER = (Logger) LogManager.getLogger(EmployeeSkillService.class);
	@Autowired
	private SkillProfileRepository skillProfileRepository;
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	public void saveSkillProfile(String name,int profileId) {
		Skill skill=skillRepository.findBySkillName(name);
		LOGGER.debug("Saving skill:"+name +" in profile:"+profileId);
		if(skillProfileRepository.findByProfileIdAndSkillId(profileId,skill.getSkillId())!=null) {
			
			LOGGER.debug("Record already exixts for  skill:"+name +" in profile:"+profileId);
			return;
		}
		
		
		SkillProfileEntity skillProfileEntity= new SkillProfileEntity();
		skillProfileEntity.setProfileId(profileId);
		
		skillProfileEntity.setSkillId(skill.getSkillId());
		Date d=new Date();
		skillProfileEntity.setCreationDate(d);
		//if(skillProfileRepository.findByProfileIdAndSkillId(profileId,skill.getSkillId())==null)
		skillProfileRepository.save(skillProfileEntity);
		
	}
	
	/* Method to view Skills in the Profile of a person */
	public List<String> viewSkillsInProfile(int profileId){
		List<String> profileSkills=new ArrayList<>();
		//We find all rows of skills containing the profile id
		List<SkillProfileEntity> skillProfileEntityList=skillProfileRepository.findByProfileId(profileId);
		//We traverse the rows and extract skill id
		for(SkillProfileEntity sk:skillProfileEntityList ) {
			int skillId=sk.getSkillId();
			
			//We extract skill name from skill id and add them to the profileSkills list
			profileSkills.add(skillRepository.findBySkillId(skillId).getSkillName());
		}
		
		
		return profileSkills;
	}
	
	
	/*
	 * Method to delete skills from profile
	 */
	public String deleteSkillsFromProfile(DeleteSkillsFromProfileVO incomingVO) {
//		ProfileEntity profile =profileRepository.findByEmpId(incomingVO.getEmpId());
//		int profileId=profile.getId();
		 String result="Success";
		int profileId=profileRepository.findIdByEmpId(incomingVO.getEmpId());
		LOGGER.debug("Request came to delete skills of profile"+profileId);
		for(int i=0;i<incomingVO.getSkillsToDeleteList().size();i++) {
			Skill skill=skillRepository.findBySkillName(incomingVO.getSkillsToDeleteList().get(i));
			if(skill!=null)
			{
			skillProfileRepository.deleteByProfileIdAndSkillId(profileId, skill.getSkillId());
			LOGGER.debug("Deleted skill:"+skill.getSkillName());
			}
			else {
				return "Invalid skill name:"+incomingVO.getSkillsToDeleteList().get(i);
			}
		}
		
		return result;
	}
}
