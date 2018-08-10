package com.hsc.cat.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsc.cat.entity.Skill;

@Repository
@Transactional
public interface SkillRepository extends JpaRepository<Skill, Integer>{

	Skill findBySkillName(String skillName);
	
	@Query("select skills from Skill skills where skills.skillName LIKE :skillName%")
	List<Skill> findSkillsByName(@Param("skillName")String skillName);
	
	@Query("select e.skillId from Skill e where e.skillName=:skillName")
	Integer findSkillIdBySkillNameCustom(@Param("skillName")String skillName);
	
	//List<Skill> findBySkillNameContaining(String skillName);
	
	
	Skill findBySkillId(int skillId);
	
	
	List<Skill> findBySkillCategory(String skillCategory);
	
	@Query("select s from Skill s where s.skillCategory='Fullstack'")
	List<Skill> getFullstackCategoryskills();
	
	List<Skill> findBySkillCategoryAndSkillSubCategory(String skillCategory,String skillSubCategory);
	
	
	@Query("select s from Skill s where s.skillCategory='Fullstack' and s.skillSubCategory=:skillSubCategory")
	List<Skill> getFullstackCategorySubcategoryskills(@Param("skillSubCategory")String skillSubCategory);
	
	
	//Get skills not in a category
	@Query("select skill.skillName from Skill skill where skill.skillCategory!=:skillCategory")
	List<String> getSkillsNotInACategory(@Param("skillCategory")String skillCategory);
	
	@Query("select skill.skillName from Skill skill where skill.skillCategory not in('Backend','Frontend')")
	List<String> getSkillsNotInACategoryForFullstack();
}


