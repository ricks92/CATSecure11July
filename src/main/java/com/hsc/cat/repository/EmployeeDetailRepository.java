package com.hsc.cat.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsc.cat.entity.EmployeeDetails;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetails, String>{
	
	@Query("SELECT e FROM EmployeeDetails e WHERE approvalStatus='APPROVED'") 
    List<EmployeeDetails> findAllManagers();
	
	
	@Modifying
	@Transactional
	@Query("UPDATE EmployeeDetails e SET  e.approvalStatus='APPROVED' WHERE e.empid=:empid") 
    int updateManagersApprovalStatus(@Param("empid") String empid);
	
	@Transactional
	@Query("select e from EmployeeDetails e where  e.managerId=:managerId order by firstName asc")
	 List<EmployeeDetails> findEmployeeUnderManager(@Param("managerId") String managerId);
	
	@Transactional
	@Query("SELECT e FROM EmployeeDetails e WHERE approvalStatus='NA'") 
    List<EmployeeDetails> findAllEmployees();
	
	@Transactional
	@Query("SELECT e FROM EmployeeDetails e WHERE approvalStatus='REJECTED'") 
    List<EmployeeDetails> findAllManagersToDelete();
	@Query("select e.approvalStatus from EmployeeDetails e WHERE e.empid=:empid")
	String getApprovalStatus(@Param("empid")String empid);
	
	@Transactional
	@Query("select e from EmployeeDetails e WHERE e.email=:email")
	List<EmployeeDetails> getEmail(@Param("email")String email);
	
	@Transactional
	@Query("select e from EmployeeDetails e WHERE e.empid<>:empid")
	List<EmployeeDetails> getAllPeers(@Param("empid")String empid);
	
	
	List<EmployeeDetails> findByEmpid(String empid);
	
	@Modifying(clearAutomatically=true)
	@Transactional
	@Query("UPDATE EmployeeDetails e SET  e.firstName=:firstName,e.lastName=:lastName WHERE e.empid=:empid") 
	int updateName(@Param("firstName")String firstName,@Param("lastName") String lastName,@Param("empid")String empid);
}
