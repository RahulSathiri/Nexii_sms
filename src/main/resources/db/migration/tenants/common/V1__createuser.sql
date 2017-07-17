
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
	contactnumber VARCHAR(12) NOT NULL,
	emailid VARCHAR(30) NOT NULL,
	address VARCHAR(100) NOT NULL,
	qualification VARCHAR(30) NOT NULL,
	about LONGTEXT NOT NULL,
	subjects VARCHAR(30) NOT NULL,
	dateofbirth VARCHAR(12) NOT NULL,
	dateofjoining VARCHAR(12) NOT NULL,
	gender VARCHAR(10) NOT NULL,
	noofperiods bigint(8) NOT NULL,
	UNIQUE (emailid),
	UNIQUE (teachername)
);

	insert into teachers(teachername,lname,contactnumber,emailid,address,qualification,about,subjects,dateofbirth,dateofjoining,gender,noofperiods)
	values('Ramesh',' Rangam','9555544449','ramesh@gmail.com','plot no:14-15-456,Madhapur,hyd-500074','B.Ed',
			'He have 10+ years of experience in chemistry and physics','chemistry,physics','01-01-1985','01-06-2006','male',4),
		('Tejaswi','Chava','9666655559','tejaswi.chava@gmail.com','plot no:12-13-420,Gachibowli,Hyd-500073','B.Ed',
			'She have 9+ years of experience in social ','social','01-02-1988','05-06-2008','female',5),
		('Rahul','Sathiri','9640242289','rahul@gmail.com','plot no:18-14-97/328,LB nagar,Hyd-500097',
			'B.Ed','He have 7+ years of experience in Mathematics','Mathematics','01-07-1990','10-06-2010','male',4),
		('Mahendar','Vengala','9000011119','mahendar@gmail.com','plot no:1-2-143/341,Madhapur,Hyd-500074',
			'B.Ed','He have 5+ years of experience in English','English','01-01-1987','15-06-2012','male',5),
		('Venkateshwar Reddy','Varkala','9111155559','venkateshwarreddy@gmail.com','flat no:201,srinivasa aprtments,dilsukh nagar,Hyderabad-500090',
			'B.Ed','He have 15+ years of experience in mathematics','Mathematics','02-02-1975','10-07-2002','male',4),
		('Matheen ali','Mohammed','8977690789','matheenali@gmail.com',
			'flat no:101,wasim apartments,atthapur,Mehdipatnam,hyd-500085','B.Ed','He have 12+ years of experience in Hindi','Hindi','11-04-1978','14-06-2005','Male',5),
		('Vijaya','kandimalla','8889991110','vijaya@gmail.com','plot no:1-2-302/95,road no:8A,meerpet,Hyd-500074',
			'B.Ed','She have 18+ years of experience in social','social','05-05-1970','09-06-1999','Female',5),
		('Anitha','korrapti','9999911110','anitha@gmail.com','flat no:401,Anjanadri apartments,saroor nagar,Hyd-500096',
			'B.Ed','She have 11+ years of experince in biology','Biology','06-06-1980','20-07-2006','Female',5),
		('Harbhajan','Singh','8887776665','harbhajan@gmail.com','plot no:1-4-341/1,punjagutta,Hyd-500084',
			'B.Ed','He have 7+ years of experience in English','English','01-12-1984','25-07-2009','Male',4),
		('Prasad','Gattu','7778889990','prasad@gmail.com','plot no:3-21-321/100,Hanuman nagar,Ameerpet,Hyd-500099',
			'B.Ed','He have 10+ years of experience in physics and chemistry','physics and chemistry','06-06-1980','01-07-2006','Male',5);

			
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

CREATE TABLE labs(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	description VARCHAR(100) NOT NULL
);

CREATE TABLE labsmaintain(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	roomid bigint(8) NOT  NULL,
	labid bigint(8) NOT  NULL,
	teacherid bigint(8) NOT NULL,
	FOREIGN KEY(labid) references labs(id),
	FOREIGN KEY(teacherid) references teachers(id)
);

-- ********** STUDENTS TABLES ******************************

CREATE TABLE students(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gradeid bigint(8) NOT null,
	name VARCHAR(30) NOT NULL,
	fathername VARCHAR(60) NOT NULL,
	mothername VARCHAR(60) NOT NULL,
	contactnumber VARCHAR(12) NOT NULL,
	address VARCHAR(100) NOT NULL,
	dateofbirth VARCHAR(12) NOT NULL,
	dateofjoining VARCHAR(12) NOT NULL,
	gender VARCHAR(10) NOT NULL,
	emailid VARCHAR(30) NOT NULL,
	admissionnumber VARCHAR(20) NOT NULL,
	UNIQUE(admissionnumber),
	FOREIGN KEY(gradeid) references grades(id)
);

insert into students(gradeid,name,fathername,mothername,contactnumber,address,dateofbirth,dateofjoining,gender,emailid,admissionnumber)
	values (1,'vikram','srinivas','sunitha','8977646503','hno:5-2-203, Janagaon,Telangana,508203','27-08-2005','10-06-2010','male','vikram@gmail.com','7011'),
	(2,'kalpana','koteshwar rao','padma','9977765623','plot no:12-14-97,patancheruvu,Hyd -500045','13-07-2003','15-06-2008','female','kalpana@gmail.com','90111'),
	(2,'srinadh','suresh','devi','8977690734','hno:5-203,kr nagar, mancheryal,Telangana-504203','21-08-2002','01-06-2006','male','srinadh@gmail.com','10111'),
	(1,'yamini','kamal','vimala','9955544499','plot no:12-5-50,anakapally,vizag-500085','01-01-2007','16-06-2012','female','yamini@gmail.com','50111');

CREATE TABLE class_students(
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
	istestable VARCHAR(30) NOT NULL
);

insert into subjects(subjectname,istestable) 
values('Telugu','true'),('Hindi','true'),('English','true'),('Maths','true'),('Biology','true');

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
fromdate VARCHAR(30) NOT NULL,
todate VARCHAR(30) NOT NULL
);

--******************** EVENTS TABLES ************************************************************

CREATE TABLE events(
id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
eventdate VARCHAR(30) NOT NULL,
eventname VARCHAR(50) NOT NULL,
chiefguest VARCHAR(50) NOT NULL,
description VARCHAR(100) NOT NULL
);

--******************** NEWS TABLES ************************************************************

CREATE TABLE newsfeed(
id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
headline VARCHAR(50) NOT NULL,
releasedate VARCHAR(30) NOT NULL,
description VARCHAR(150) NOT NULL
);

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
academicyear bigint(8) not null,
yearfromto VARCHAR(20) not null,
active INT
);





