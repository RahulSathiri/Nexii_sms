INSERT INTO academicyears(passingyear,academicyearstarting,academicyearending,active) 
values(2019,'2018-06-12','2018-03-20',0);

--*******************************------

INSERT INTO classroom_students (classid, studentid)
	values(1,1),(2,2),(1,3),(2,4);

--*******************************------
	
INSERT INTO test_type (testtype) 
	values('UNIT TEST-1'),('UNIT TEST-2'),('TALENT TEST'),('UNIT TEST-3'),('QUARTERLY'),
		  ('HALF-YEARLY'),('ANNUALLY');
		  
--*******************************------
		 
INSERT INTO test_create (gradeid, academicyear, testtypeid, startdate,
				enddate, modeid, maxmarks)
				VALUES  (1, 2017, 1, '2017-08-20', '2017-08-22', 2, 150),
					    (1, 2017, 2, '2017-10-13', '2017-10-15', 2, 150),
						(2, 2017, 1, '2017-08-20', '2017-08-22', 2, 150),
						(2, 2017, 2, '2017-10-13', '2017-10-15', 2, 150),
						(1, 2017, 3, '2017-11-05', '2017-11-05', 1, 100),
						(2, 2017, 3, '2017-11-06', '2017-11-06', 1, 100),
						(1, 2017, 5, '2017-12-03', '2017-12-13', 2, 600),
						(2, 2017, 5, '2017-12-03', '2017-12-13', 2, 600);

--*******************************------
		 
INSERT INTO test_syllabus (testid, subjectid, maxmarks, syllabus)
		values (1,1,100,'Unit-1,Unit-2'),
			   (1,2,100,'Unit-1,Unit-2'),
			   (2,1,100,'Unit-3,Unit-4'),
			   (2,2,100,'Unit-3,Unit-4');
			   
--*******************************------

INSERT INTO classroom_testresult (classid, studentid, testid, resultorgrade)
	values (1,1,1,'A'),
		   (2,2,3,'C'),
		   (2,4,3,'B'),
		   (1,3,1,'B');
		   
--*******************************------

INSERT INTO student_testresult	(classid, testid, studentid, subjectid, marks)   
	values(1, 1, 1, 1, 85),
		  (2, 3, 2, 2, 65),
		  (2, 3, 4, 1, 72),
		  (1, 1, 3, 3, 78);

--*******************************------
	
INSERT INTO classroom_periods (periodfrom,periodto,subjectid,classroomid,classroomweekdayid,dateofassigning)
  values('08:30', '09:30', 1, 1, 2, '2017-08-01'),
  		('10:30', '11:30', 2, 2, 2, '2017-08-01'),
  		('11:30', '12:30', 1, 1, 3, '2017-08-02'),
  		('14:00', '15:00', 2, 2, 3, '2017-08-02');
	
--*******************************------

INSERT INTO classroom_attendance (classroomid,studentid, dateofattendance, attendancestatus)
  values(1, 1, '2017-08-01', 1),
  		(2, 2, '2017-08-01', 0),
  		(1, 3, '2017-08-01', 0),
  		(2, 4, '2017-08-01', 1);
 --*******************************------

  		
