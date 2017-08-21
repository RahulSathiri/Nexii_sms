-- **************** USER ROLES TABLES ************************************

CREATE TABLE roles(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  role varchar(255) NOT NULL, 
  description varchar(255) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  UNIQUE KEY name_UNIQUE (role)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

 INSERT INTO roles(role,description)
 				 VALUES("SUPERADMIN","School owner"),
 				 	   ("ADMIN","School admin"),
 				 	   ("TEACHER","School teacher"),
 				 	   ("PARENT","Father or gardian of student"),
 				 	   ("STUDENT","student");
 				 	   
 CREATE TABLE userstatus(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  status varchar(150) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  UNIQUE KEY name_UNIQUE (status)
 )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO userstatus(status)
			VALUES("Active"),
				  ("InActive"),
			 	  ("Pending");

CREATE TABLE usercredentials(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  mail varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  statusid bigint(8)NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  FOREIGN KEY (statusid) REFERENCES userstatus (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO usercredentials(mail,password,statusid) 
					 VALUES("bhaskar@omniwyse.com","bhaskar",1);

 CREATE TABLE UserRoleMaintain(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  userid bigint(8) NOT NULL,
  roleid bigint(8) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  FOREIGN KEY (userid) REFERENCES usercredentials (id),
  FOREIGN KEY (roleid) REFERENCES roles (id)
 )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
 
 INSERT INTO UserRoleMaintain(userid,roleid)
 					   VALUES((SELECT ID FROM usercredentials WHERE mail = "bhaskar@omniwyse.com"),(SELECT ID FROM roles WHERE role = "ADMIN"));

-- ********** GRADES TABLES ***************************

CREATE TABLE grades(
	id bigint(8) NOT NULL AUTO_INCREMENT,
	gradenumber bigint(8) NOT NULL,
	gradename VARCHAR(30) NOT NULL,
	subjects VARCHAR(100) NOT NULL,
	syllabustype VARCHAR(30) NOT NULL,
	PRIMARY KEY (id,syllabustype)
);

insert into grades(gradenumber, gradename, subjects, syllabustype)
	values(1, "1st class","['Telugu','Hindi','English']","state"),
	(2, "2nd class","['Telugu','Hindi','English','Maths']","cbse");

CREATE TABLE  grade_subjects(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gradeid bigint(8) NOT NULL,
	subjectid bigint(8) NOT NULL,
	FOREIGN KEY(gradeid) references grades(id)
);

insert into grade_subjects(gradeid,subjectid)
	values(1,1),(1,2),(1,3),(2,1),(2,2),(2,3),(2,4);

-- ***************** TEACHERS TABLES **************************************

CREATE TABLE teachers(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	teachername VARCHAR(30) NOT NULL,
	lname VARCHAR(30),
	bloodgroup VARCHAR(10) NOT NULL,
	contactnumber VARCHAR(12) NOT NULL,
	emailid VARCHAR(30) NOT NULL,
	address VARCHAR(100) NOT NULL,
	qualification VARCHAR(30) NOT NULL,
	about LONGTEXT NOT NULL,
	subjects VARCHAR(30) NOT NULL,
	dateofbirth date NOT NULL,
	dateofjoining date NOT NULL,
	gender VARCHAR(10) NOT NULL,
	noofperiods bigint(8) NOT NULL,
	UNIQUE (emailid),
	UNIQUE (teachername)
);

	insert into teachers(teachername,lname,bloodgroup,contactnumber,emailid,address,qualification,about,subjects,dateofbirth,dateofjoining,gender,noofperiods)
	values('Ramesh',' Rangam','O+','9555544449','ramesh@gmail.com','plot no:14-15-456,Madhapur,hyd-500074','B.Ed',
			'He have 10+ years of experience in chemistry and physics','chemistry,physics','1985-01-01','2006-06-01','male',4),
		('Tejaswi','Chava','AB-','9666655559','tejaswi.chava@gmail.com','plot no:12-13-420,Gachibowli,Hyd-500073','B.Ed',
			'She have 9+ years of experience in social ','social','1988-02-01','2008-06-05','female',5),
		('Rahul','Sathiri','AB+','9640242289','rahul@gmail.com','plot no:18-14-97/328,LB nagar,Hyd-500097',
			'B.Ed','He have 7+ years of experience in Mathematics','Mathematics','1990-07-01','2010-06-10','male',4),
		('Mahendar','Vengala','O-','9000011119','mahendar@gmail.com','plot no:1-2-143/341,Madhapur,Hyd-500074',
			'B.Ed','He have 5+ years of experience in English','English','1987-01-01','2012-06-15','male',5),
		('Venkateshwar Reddy','Varkala','A+','9111155559','venkateshwarreddy@gmail.com','flat no:201,srinivasa aprtments,dilsukh nagar,Hyderabad-500090',
			'B.Ed','He have 15+ years of experience in mathematics','Mathematics','1975-02-02','2002-07-10','male',4),
		('Matheen ali','Mohammed','B+','8977690789','matheenali@gmail.com',
			'flat no:101,wasim apartments,atthapur,Mehdipatnam,hyd-500085','B.Ed','He have 12+ years of experience in Hindi','Hindi','1978-04-11','2005-06-14','Male',5),
		('Vijaya','kandimalla','B-','8889991110','vijaya@gmail.com','plot no:1-2-302/95,road no:8A,meerpet,Hyd-500074',
			'B.Ed','She have 18+ years of experience in social','social','1970-05-05','1999-06-09','Female',5),
		('Anitha','korrapti','A-','9999911110','anitha@gmail.com','flat no:401,Anjanadri apartments,saroor nagar,Hyd-500096',
			'B.Ed','She have 11+ years of experince in biology','Biology','1980-06-06','2006-07-20','Female',5),
		('Harbhajan','Singh','B+','8887776665','harbhajan@gmail.com','plot no:1-4-341/1,punjagutta,Hyd-500084',
			'B.Ed','He have 7+ years of experience in English','English','1984-12-01','2009-07-25','Male',4),
		('Prasad','Gattu','O-','7778889990','prasad@gmail.com','plot no:3-21-321/100,Hanuman nagar,Ameerpet,Hyd-500099',
			'B.Ed','He have 10+ years of experience in physics and chemistry','physics and chemistry','1980-06-06','2006-07-01','Male',5);

			
-- ***************************************** CLASSROOM TABLES *******************************************************************************************
			
CREATE TABLE classrooms(
	id bigint(8) NOT NULL AUTO_INCREMENT,
	gradeid bigint(8) NOT NULL,
	academicyear bigint(8) NOT NULL,
	sectionname VARCHAR(10) NOT NULL,
	classteacherid bigint(8),
	PRIMARY KEY(id,gradeid,sectionname,academicyear),
	FOREIGN KEY(classteacherid) references teachers (id),
	FOREIGN KEY(gradeid) references grades(id)
);

insert into classrooms(gradeid,academicyear,sectionname,classteacherid)
	values(1,2017,'A',1),(2,2017,'A',2),(2,2017,'B',3);
	
	
--************ HOUSES TABLES *****************************

CREATE TABLE houses(
id bigint(8) NOT NULL PRIMARY KEY AUTO_INCREMENT,
housename VARCHAR(50) NOT NULL,
UNIQUE (housename),
description VARCHAR(400) NOT NULL
);


INSERT INTO houses(housename,description) VALUES
		('DARIYA','passion towards learning'),
		('C.V.Raman','C.V.Raman was a nobel prize winner for his work on Molecular Diffraction of Light popularly known as Raman Effect . His achievements signify a scientific and rational outlook of the school system.'),
		('Lal Bhahdur Shastri','Lal Bhahdur Shastri house recalls the memory of Great Indian Politician and Second Prime Minister of India'),
		('Chhatrapati Shivaji','Shivaji is a house of the hero of Maharashtra known as Chhatrapati Shivaji . An icon in Freedom Fight  teaches us how to be an able administrator.'),
		('Subhash Chandra Bose','Subhash house is a house of Subhash Chandra Bose, popularly known as Netaji  a freedom fighter.It teaches us never to give up in life.');

--************************* PARENTS TABLE ************************

CREATE TABLE parents(
id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
mothername VARCHAR(50) NOT NULL,
fathername VARCHAR(50) NOT NULL,
emailid VARCHAR(50) NOT NULL,
contactnumber bigint NOT NULL,
address VARCHAR(200) NOT NULL,
UNIQUE(emailid)
);


INSERT INTO parents(mothername,fathername,emailid,contactnumber,address) VALUES('srinivas','sunitha','srinivas@gmail.com',9345666666,'hno:5-2-203, Janagaon,Telangana,508203'),
('koteshwar rao','padma','koteswarrao@gmail.com',9721666666,'plot no:12-14-97,patancheruvu,Hyd -500045'),
('suresh','devi','suresh@gmail.com',9277327323,'hno:5-203,kr nagar, mancheryal,Telangana-504203'),
('kamal','vimala','kamal@gmail.com',7330123423,'plot no:12-5-50,anakapally,vizag-500085');
		
		
		
		
-- ********** STUDENTS TABLES ******************************

CREATE TABLE students(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gradeid bigint(8) NOT null,
	name VARCHAR(30) NOT NULL,
	parentid bigint NOT NULL,
	address VARCHAR(100) NOT NULL,
	dateofbirth date NOT NULL,
	dateofjoining date NOT NULL,
	gender VARCHAR(10) NOT NULL,
	emailid VARCHAR(30),
	admissionnumber VARCHAR(20) NOT NULL,
	houseid bigint(8),
	UNIQUE(admissionnumber),
	FOREIGN KEY(gradeid) references grades(id),
	FOREIGN KEY(parentid) references parents(id)
);

insert into students(parentid,houseid,gradeid,name,address,dateofbirth,dateofjoining,gender,emailid,admissionnumber)
	values (1,3,1,'vikram','hno:5-2-203, Janagaon,Telangana,508203','2005-07-26','2010-06-10','male','vikram@gmail.com','7011'),
	(2,2,2,'kalpana','plot no:12-14-97,patancheruvu,Hyd -500045','2003-07-25','2008-01-01','female','kalpana@gmail.com','90111'),
	(3,2,2,'srinadh','hno:5-203,kr nagar, mancheryal,Telangana-504203','2002-07-25','2006-06-06','male','srinadh@gmail.com','10111'),
	(4,1,1,'yamini','plot no:12-5-50,anakapally,vizag-500085','2007-01-01','2012-06-19','female','yamini@gmail.com','50111');

CREATE TABLE classroom_students(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	classid bigint(8) NOT NULL,
	studentid bigint(8) NOT NULL,
	FOREIGN KEY(classid) references classrooms(id),
	FOREIGN KEY(studentid) references students(id)
);

--insert into students(classid,name,classname,fathername,mothername,contactnumber,address,dateofbirth,
--dateofjoining,gender,emailid,admissionnumber) values
--(7,'vikram','7','srinivas','sunitha','8977646503','hno:5-2-203, Janagaon,Telangana,508203','27-08-2005','10-06-2010','male',
--'vikram@gmail.com','70111'),(3,'kalpana','9','koteshwar rao','padma','9977765623','plot no:12-14-97,patancheruvu,Hyd -500045',
--'13-07-2003','15-06-2008','female','kalpana@gmail.com','90111'),(1,'srinadh','10','suresh','devi','8977690734','hno:5-203,kr nagar, mancheryal,Telangana-504203',
--'21-08-2002','01-06-2006','male','srinadh@gmail.com','100111'),(5,'yamini','8','kamal','vimala','9955544499','plot no:12-5-50,anakapally,vizag-500085',
--'01-01-2007','16-06-2012','female','yamini@gmail.com','50111');

-- *********** SUBJECTS TABLES **********************************

CREATE TABLE subjects(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	subjectname VARCHAR(150) NOT NULL,
	subjectid bigint(8),
	istestable VARCHAR(30) NOT NULL
);

insert into subjects(subjectname,subjectid,istestable) 
values('Telugu',1,'true'),('Hindi',2,'true'),('English',3,'true'),('Physics',4,'true'),('Maths',5,'true'),('Biology',6,'true');
	


CREATE TABLE class_subjects(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	classid bigint(8) NOT NULL,
	subjectid bigint(8) NOT NULL,
	FOREIGN KEY(classid) references classrooms(id),
	FOREIGN KEY(subjectid) references subjects(id)
);

CREATE TABLE class_subject_teacher(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	subjectid bigint(8) NOT NULL,
	teacherid bigint(8),
	classid bigint(8) NOT NULL,
	FOREIGN KEY(subjectid) references subjects(id),
	FOREIGN KEY(classid) references classrooms(id)
	);

	insert into class_subject_teacher (subjectid, teacherid, classid)
		values(1,2,1),(2,4,1),(3,1,1),(2,2,2),(2,4,2);
	
-- ******************** SYLLABUS TABLES ******************************************

	CREATE TABLE syllabus(
	id bigint(8) NOT NULL PRIMARY KEY,
	syllabustype  VARCHAR(40) NOT NULL
);
insert into syllabus values(1,'cbse'),(2,'icse'),(3,'state');




-- ************* TESTS TABLES ************************************************************************

CREATE TABLE test_type(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	testtype VARCHAR(100) NOT NULL);

CREATE TABLE test_mode(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	testmode VARCHAR(30) NOT NULL);

insert into test_mode(testmode) values('online'),
									  ('offline');

CREATE TABLE test_create(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gradeid bigint(8) NOT NULL,
	academicyear bigint(8) NOT NULL, 
	testtypeid bigint(8) NOT NULL,
	startdate DATE NOT NULL,
	enddate DATE NOT NULL,
	modeid bigint(8) NOT NULL,
	maxmarks bigint(8) NOT NULL,
	FOREIGN KEY(gradeid) REFERENCES grades(id),
	FOREIGN KEY(testtypeid) REFERENCES test_type(id),
	FOREIGN KEY(modeid) REFERENCES test_mode(id)
);

CREATE TABLE test_syllabus(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	testid bigint(8) NOT NULL,
	subjectid bigint(8) NOT NULL, 
	maxmarks bigint(8) NOT NULL,
	syllabus VARCHAR(250),
	FOREIGN KEY(testid) REFERENCES test_create(id)
);

-- ************************* RESULT TABLE ********************************************************

CREATE TABLE classroom_testresult(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  classid bigint(8) NOT NULL,
  studentid bigint(8) NOT NULL,
  testid bigint(8) NOT NULL,
  resultorgrade varchar(15),
  PRIMARY KEY (id),
  FOREIGN KEY (classid) REFERENCES classrooms (id),
  FOREIGN KEY (studentid) REFERENCES students (id),
  FOREIGN KEY (testid) REFERENCES test_create (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE student_testresult(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  classid bigint(8) NOT NULL, 
  testid bigint(8) NOT NULL,
  studentid bigint(8) NOT NULL,
  subjectid bigint(8) NOT NULL,
  marks bigint(8),
  PRIMARY KEY (id),
  FOREIGN KEY (testid) REFERENCES test_create (id),
  FOREIGN KEY (subjectid) REFERENCES grade_subjects (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- ************** NOTICEBOARD ********************************************************************

CREATE TABLE noticeboard(
id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
noticeid bigint(8) NOT NULL,
noticeddate DATE NOT NULL,
description VARCHAR(30) NOT NULL,
FOREIGN KEY(noticeid) references grades(id)
);

--******************** HOLIDAYS TABLES ************************************************************

CREATE TABLE holidays(
id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
occassion VARCHAR(50) NOT NULL,
fromdate date NOT NULL,
todate date NOT NULL
);


insert into holidays (occassion,fromdate,todate) values('Ramzan','2017-06-26','2017-06-27'),('Dussera','2017-09-04','2017-09-12'),('Diwali','2017-10-10','2017-10-11'), 
                                                        ('Christmas','2017-12-25','2017-12-26'),('Pongal','2018-01-09','2018-01-16'); 
                                                         

--******************** EVENTS TABLES ************************************************************

CREATE TABLE events(
id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
eventdate date NOT NULL,
eventname VARCHAR(50) NOT NULL,
chiefguest VARCHAR(50) NOT NULL,
description VARCHAR(100) NOT NULL
);

insert into events(eventdate,eventname,chiefguest,description) values('2017-08-15','Independence Day','Former tamilnadu governor K.Rosaiah','flag hosting Ceremony at 8:30 A.M by chief guest and Prize distribution'), 
                                                                    ('2017-11-14','Childrens Day','Sri Prashanthi, IAS Joint Collector,Hyderabad','Cycles distribution'), 
                                                                    ('2018-01-26','Republic Day','Indian Navy Admiral Sunil Lanba','flag hosting Ceremony at 8:30 A.M and speech by chief admiral at 9:00 A.M'), 
                                                                    ('2018-02-28','Science Day','ISRO Chief A.S Kiran Kumar','Prize distribution by chief guest at 10:00 A.M'), 
                                                                    ('2018-03-03','School Anniversary','Super Star Mahesh babu','Speech by guest at 5:30 P.M and then culturel activities'); 
 

--******************** NEWS TABLES ************************************************************

CREATE TABLE newsfeed(
id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
headline VARCHAR(50) NOT NULL,
releasedate date NOT NULL,
description VARCHAR(150) NOT NULL
);


insert into newsfeed (headline,releasedate,description) values('Dinesh and Raju selected for Distric Kabaddi team','2017-06-20','From the Kabaddi team sent to School Zonals these two are selected for Distric team'), 
                                                             ('Sheela got 2nd Prize in Science exhibition','2017-06-03','Sheela who is studying 8th class got 2nd prize in district level Science exhibition held in Bhashyam Schools'); 

--******************** TIMETABLE TABLES ************************************************************

CREATE TABLE weekdays(
	id bigint(8) NOT NULL AUTO_INCREMENT,
	day varchar(15) NOT NULL,
	PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

	INSERT INTO weekdays(day) VALUES ('MONDAY'),
									 ('TUESEDAY'),
									 ('WEDNESDAY'),
									 ('THURSEDAY'),
									 ('FRIDAY'),
									 ('SATERDAY'),
									 ('SUNDAY');

CREATE TABLE classroom_weekdays(
	id bigint(8) NOT NULL AUTO_INCREMENT,
	classroomid bigint(8) NOT NULL,
	weekdayid bigint(8) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (weekdayid) REFERENCES weekdays(id),
	FOREIGN KEY(classroomid) references classrooms(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE classroom_periods( 
	id bigint(8) NOT NULL AUTO_INCREMENT,
	periodfrom time NOT NULL,
	periodto time NOT NULL,
	subjectid INT NOT NULL,
	classroomid bigint(8) NOT NULL,
	classroomweekdayid bigint(8) NOT NULL,
	dateofassigning date NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(classroomweekdayid) references weekdays(id),
	FOREIGN KEY(classroomid) references classrooms(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 

--******************** ATTENDANCE  TABLES ************************************************************

CREATE TABLE classroom_attendance(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
studentid bigint NOT NULL,
classroomid bigint NOT NULL,
dateofattendance DATE NOT NULL,
attendancestatus INT NOT NULL,
FOREIGN KEY(studentid) REFERENCES students(id),
FOREIGN KEY(classroomid) REFERENCES classrooms(id)); 


--**************************ACADEMIC YEARS TABLES*******************************************************

CREATE TABLE academicyears(
id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
passingyear bigint(8) NOT NULL,
academicyearstarting date NOT NULL,
academicyearending date NOT NULL,
active INT NOT NULL
);

INSERT INTO academicyears(passingyear,academicyearstarting,academicyearending,active) values(2018,'2017-03-04','2018-03-04',1);

