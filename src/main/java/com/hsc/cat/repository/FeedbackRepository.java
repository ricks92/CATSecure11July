package com.hsc.cat.repository;

import org.springframework.data.repository.query.Param;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hsc.cat.entity.FeedbackEntity;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer>{

	FeedbackEntity findByEmpIdAndRatingDoneByEmpIdAndWeekNumber(String empId,String ratingDoneByEmpId,int weekNumber);
	
	@Modifying(clearAutomatically = true)
	@Query("update FeedbackEntity e SET e.comment=:comment,e.creationDate=:creationDate WHERE e.empId=:empId and e.ratingDoneByEmpId=:ratingDoneByEmpId and e.weekNumber=:weekNumber")
	int updateFeedback(@Param("empId")String empId,@Param("ratingDoneByEmpId")String ratingDoneByEmpId,@Param("weekNumber")int weekNumber, @Param("comment") String comment,@Param("creationDate") Date creationDate);
}
