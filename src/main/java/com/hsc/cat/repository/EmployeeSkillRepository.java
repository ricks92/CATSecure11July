package com.hsc.cat.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsc.cat.entity.EmployeeSkillEntity;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkillEntity, Integer>{

	//@Query("select e from EmployeeSkillEntity e where e.empId:=empId AND e.skillId:=skillId AND e.weekNumber:=weekNumber AND e.ratingDoneBy:=ratingDoneBy")
	EmployeeSkillEntity findByEmpIdAndSkillIdAndWeekNumberAndRatingDoneByAndRatingDoneByEmpIdAndSdlcCategory(String empId,int skillId,int weekNumber,String ratingDoneBy,String ratingDoneByEmpId,String sdlcCategory);
	
	
	
	List<EmployeeSkillEntity> findByEmpId(String empId);
	
	@Query("select e from EmployeeSkillEntity e WHERE e.empId=:empId and weekNumber BETWEEN :start and :end")
	List<EmployeeSkillEntity> findByEmpIdCustom(@Param("empId")String empId, @Param("start")int start, @Param("end")int end);
	
	
//	@Query("select e from EmployeeSkillEntity e where e.empId:=empId and e.skillId:=skillId and e.ratingDoneBy:=ratingDoneBy order by e.weekNumber asc")
	List<EmployeeSkillEntity> findByEmpIdAndSkillIdAndRatingDoneByOrderByWeekNumber(@Param("empId")String empId, @Param("skillId")int skillId, @Param("ratingDoneBy")String ratingDoneBy);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE EmployeeSkillEntity e SET  e.rating=:rating,e.comment=:comment,e.creationDate=:creationDate WHERE e.skillId=:skillId AND e.weekNumber=:weekNumber AND e.ratingDoneBy=:ratingDoneBy  AND e.empId=:empId AND e.ratingDoneByEmpId=:ratingDoneByEmpId AND e.sdlcCategory=:sdlcCategory") 
	int updateEmployeeSkill(@Param("skillId") int skillId,@Param("rating")String rating,@Param("comment")String comment,@Param("weekNumber") int weekNumber,@Param("ratingDoneBy") String ratingDoneBy,@Param("creationDate") Date creationDate,@Param("empId")String empId,@Param("ratingDoneByEmpId")String ratingDoneByEmpId,@Param("sdlcCategory") String sdlcCategory);
	
	@Query("select e from EmployeeSkillEntity e WHERE e.empId=:empId")
	List<EmployeeSkillEntity> findByEmpIdSkill(@Param("empId")String empId);
	
	@Query("select e from EmployeeSkillEntity e WHERE e.rating!='Cannot Assess' and e.ratingDoneBy='Self' and e.empId=:empId ")
	List<EmployeeSkillEntity> getAllSelfRatedSkillsCustom(@Param("empId")String empId);
	
	@Query("select e from EmployeeSkillEntity e WHERE e.rating!='Cannot Assess' and e.ratingDoneBy='Peer' and e.empId=:empId ")
	List<EmployeeSkillEntity> getAllPeerRatedSkillsCustom(@Param("empId")String empId);
	
	@Query("select e.comment from EmployeeSkillEntity e where e.ratingDoneBy='Self' and e.empId=:empId  order by e.creationDate desc")
	List<String> getLatestSelfComment(@Param("empId")String empId);
	
	@Query("select e.comment from EmployeeSkillEntity e where e.ratingDoneBy='Peer' and e.empId=:empId  order by e.creationDate desc")
	List<String> getLatestPeerComment(@Param("empId")String empId);
	
	
	@Query("select e from EmployeeSkillEntity e WHERE e.rating!='Cannot Assess' and e.empId=:empId and weekNumber BETWEEN :start and :end")
	List<EmployeeSkillEntity> getAllRatedSkillsCustom(@Param("empId")String empId, @Param("start")int start, @Param("end")int end);
	
	@Query("select e from EmployeeSkillEntity e WHERE e.empId=:empId and  weekNumber BETWEEN :start and :end AND e.ratingDoneBy='Self'")
	List<EmployeeSkillEntity> getReviewSelf(@Param("empId")String empId, @Param("start")int start, @Param("end")int end);
	
	
	@Query("select e from EmployeeSkillEntity e WHERE e.empId=:empId and  weekNumber BETWEEN :start and :end AND e.ratingDoneBy='Peer'")
	List<EmployeeSkillEntity> getReviewPeer(@Param("empId")String empId, @Param("start")int start, @Param("end")int end);
	
	//@Query("select DISTINCT e.weekNumber from EmployeeSkillEntity e WHERE e.empId=:empId and  weekNumber BETWEEN :start and :end AND  e.ratingDoneBy=:ratingDoneBy")
	@Query("select DISTINCT e.weekNumber from EmployeeSkillEntity e WHERE e.empId=:empId and  weekNumber BETWEEN :start and :end AND  e.ratingDoneBy=:ratingDoneBy order by e.creationDate")
	List<Integer> getDistinctWeekMumber(@Param("empId")String empId, @Param("start")int start, @Param("end")int end,@Param("ratingDoneBy")String ratingDoneBy);
	
	@Query("select e.rating from EmployeeSkillEntity e WHERE e.empId=:empId and e.weekNumber=:weekNumber and e.skillId=:skillId and e.ratingDoneBy=:ratingDoneBy and e.sdlcCategory=:sdlcCategory")
	String getSpecificRating(@Param("empId")String empId,@Param("weekNumber")int weekNumber,@Param("skillId")int skillId,@Param("ratingDoneBy")String ratingDoneBy,@Param("sdlcCategory")String sdlcCategory);
	//TreeSet<Integer> findAllWeekNumberCustom();
	
//	@Query("select e.rating from EmployeeSkillEntity e WHERE ")
//	Integer getRatingByEmpIdSkillId(@Param("empId")String empId,@Param("skillId") int skillId);
	
//	
	@Query("select e from EmployeeSkillEntity e WHERE e.empId=:empId order by e.creationDate desc ")
	List<EmployeeSkillEntity> getViewHistory(@Param("empId")String empId);
	
	
//	@Query("select e from EmployeeSkillEntity e WHERE e.rating!='Cannot Assess' and e.empId=:empId and weekNumber BETWEEN :start and :end")
//	List<EmployeeSkillEntity> getAllRatedSkillsCustomNew(@Param("empId")String empId, @Param("start")int start, @Param("end")int end,@Param("sdlcCategory") int sdlcCategory);
	
	@Query("select e.creationDate from EmployeeSkillEntity e where e.ratingDoneBy='Self' and e.empId=:empId  order by e.creationDate desc")
	List<Date> getLatestSelfCommentDate(@Param("empId")String empId);
}
