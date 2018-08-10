package com.hsc.cat.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsc.cat.entity.SkillProfileEntity;
@Repository
@Transactional
public interface SkillProfileRepository extends JpaRepository<SkillProfileEntity, Integer>{

	
	SkillProfileEntity findByProfileIdAndSkillId(int profileId,int skillId);
	
	    @Modifying
	    @Transactional
	    @Query("Delete from SkillProfileEntity e WHERE e.profileId=:profileId")
	    void deleteByProfileId(@Param("profileId")int profileId);
	    @Modifying
	    @Transactional
	    void deleteByProfileIdAndSkillId(int profileId,int skillId);
	    
	    
	    
	    List<SkillProfileEntity> findByProfileId(int profileId);
	    
	    @Transactional
		@Query("select a.skillName from Skill a,SkillProfileEntity b where a.skillId=b.skillId and a.skillCategory=:category and b.profileId in(select id from ProfileEntity where empId=:empId) ")
		List<String> getSelectedDomainSKills(@Param("empId")String empId,@Param("category")String category);
	    
	    @Transactional
		@Query("select a.skillName from Skill a,SkillProfileEntity b where a.skillId=b.skillId and a.skillCategory!=:category and b.profileId in(select id from ProfileEntity where empId=:empId) ")
		List<String> getSelectedOtherDomainSKills(@Param("empId")String empId,@Param("category")String category);
	    
	    
	    
	    @Transactional
		@Query("select a.skillName from Skill a,SkillProfileEntity b where a.skillId=b.skillId and a.skillCategory in('Backend','Frontend') and b.profileId in(select id from ProfileEntity where empId=:empId) ")
		List<String> getSelectedDomainSKillsFullstack(@Param("empId")String empId);
	    
	    @Transactional
		@Query("select a.skillName from Skill a,SkillProfileEntity b where a.skillId=b.skillId and  a.skillCategory not in('Backend','Frontend') and b.profileId in(select id from ProfileEntity where empId=:empId) ")
		List<String> getSelectedOtherDomainSKillsFullstack(@Param("empId")String empId);
	
}
