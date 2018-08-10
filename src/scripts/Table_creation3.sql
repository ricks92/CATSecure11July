create database cat2;

use cat2;


 CREATE TABLE `cat2`.`login_details` (
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`username`)
   
  );
  
CREATE TABLE `cat2`.`skill_details` (
  `skill_id` INT  NOT NULL AUTO_INCREMENT,
  `skill_name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `creation_date` DATE NOT NULL,
  `updation_date` DATE NOT NULL,
`skill_category` VARCHAR(255) NULL,
`skill_sub_category` VARCHAR(255) NULL,
  PRIMARY KEY (`skill_id`),
  UNIQUE INDEX `skill_name_UNIQUE` (`skill_name` ASC));


CREATE TABLE `cat2`.`employee_details` (
  `emp_id` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `department` VARCHAR(255) NULL,
  `manager_id` VARCHAR(255) ,
  `email_id` VARCHAR(255) NOT NULL ,
  `creation_date` DATE NOT NULL,
  `updation_date` DATE NOT NULL,
  `SECURITY_QUESTION_1` VARCHAR(255) NOT NULL,
  `SECURITY_ANSWER_1` VARCHAR(255) NOT NULL,
  `SECURITY_QUESTION_2` VARCHAR(255) NOT NULL,
  `SECURITY_ANSWER_2` VARCHAR(255) NOT NULL,
  `APPROVAL_STATUS` VARCHAR(255),
  PRIMARY KEY (`emp_id`),
  constraint fk1
  foreign key(`emp_id`) references `login_details`(`username`)
  );




 
  
CREATE  TABLE `cat2`.`employee_skills` ( 
    `id` INT  NOT NULL AUTO_INCREMENT, 
   `emp_id` VARCHAR(255) NOT NULL , 
   `skill_id` INT NOT NULL , 
   `sdlc_category` INT NOT NULL ,
   `week_number` INT NOT NULL ,
   `rating` VARCHAR(45) NOT NULL ,
   `rating_done_by` VARCHAR(45) NOT NULL ,
   `comment` VARCHAR(255),
   `RATING_DONE_BY_EMP_ID` VARCHAR(45) NOT NULL ,
   `creation_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`), 
  constraint fk1_skills
  foreign key(`emp_id`) 
  references `employee_details`(`emp_id`),
  constraint fk2_skills
  foreign key(`skill_id`) 
  references `skill_details`(`skill_id`)
  
  );
  
  ALTER TABLE `cat2`.`employee_skills` CHANGE COLUMN `sdlc_category` `sdlc_category` VARCHAR(255) NOT NULL  ;


  
  CREATE  TABLE `cat2`.`profile_details` ( 
    `id` INT  NOT NULL AUTO_INCREMENT, 
   `emp_id` VARCHAR(255) NOT NULL , 
   `project_role` VARCHAR(255),
   `description` VARCHAR(255),
   `creation_date` DATETIME,
  PRIMARY KEY (`id`), 
  constraint emp_profile
  foreign key(`emp_id`) 
  references `employee_details`(`emp_id`)
  );
  
  ALTER TABLE `cat2`.`profile_details` ADD COLUMN `firstname` VARCHAR(255) NULL  AFTER `emp_id` , ADD COLUMN `lastname` VARCHAR(255) NULL  AFTER `firstname` , ADD COLUMN `email` VARCHAR(255) NULL  AFTER `lastname` ;
  
  ALTER TABLE `cat2`.`profile_details` DROP FOREIGN KEY `emp_profile` ;

ALTER TABLE `cat2`.`profile_details` CHANGE COLUMN `emp_id` `emp_id_profile` VARCHAR(255) NOT NULL  , 

  ADD CONSTRAINT `emp_profile`

  FOREIGN KEY (`emp_id_profile` )

  REFERENCES `cat2`.`employee_details` (`emp_id` );


  
CREATE  TABLE `cat2`.`skill_profile` ( 
    `id` INT  NOT NULL AUTO_INCREMENT, 
   `profile_id` INT NOT NULL , 
   `skill_id` INT NOT NULL,
   `creation_date` DATETIME,
  PRIMARY KEY (`id`), 
  constraint skills_profile
  foreign key(`profile_id`) 
  references `profile_details`(`id`)
  );
  


ALTER TABLE cat2.skill_profile
ADD CONSTRAINT skills_profile_sk
FOREIGN KEY (skill_id) REFERENCES skill_details(skill_id);



CREATE  TABLE `cat2`.`security_questions` (

  `id` INT NOT NULL ,

  `question` VARCHAR(255) NOT NULL ,

  PRIMARY KEY (`id`) );


CREATE  TABLE `cat2`.`feedback_details` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `emp_id` VARCHAR(145) NOT NULL ,

  `rating_done_by` VARCHAR(145) NOT NULL ,

  `rating_done_by_empid` VARCHAR(145) NOT NULL ,

  `comment` VARCHAR(356) NULL ,

  `creation_date` DATETIME NOT NULL ,

  PRIMARY KEY (`id`) );

ALTER TABLE `cat2`.`feedback_details` ADD COLUMN `week_number` INT NULL  AFTER `creation_date` ;
ALTER TABLE `cat2`.`feedback_details` CHANGE COLUMN `rating_done_by_empid` `rating_done_by_emp_id` VARCHAR(145) NOT NULL  ;

