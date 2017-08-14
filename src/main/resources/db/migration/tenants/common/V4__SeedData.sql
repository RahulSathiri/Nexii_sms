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
			   (2,2,100,'Unit-3,Unit-4'),
			   (1,3,100,'Unit-1,Unit-2');
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

 INSERT INTO worksheets (worksheetname, gradeid,subjectid, degreeofdifficultyid, tags, 
 				description, worksheetpath, createdby)
 				values ('Simple Calculations', 2, 4, 2, 'Excer-1,Excer-2', 'Calculations based on Addition and Subtraction', 'iSchool/2/4/M20','Ramesh Rangam'),
 					   ('Reading Comprehension', 2, 3, 3, 'Excer-1', 'Story of WorldWar-2 at Dunkirk', 'iSchool/2/3/E10', 'Rahul Sathiri'),
 					   ('Rhymes', 1, 1, 1, 'Exer-1', 'Reading Rhymes', 'iSchool/1/1/T1', 'Tejaswini'),
 					   ('Sounds', 1, 2, 2, 'Excer-2', 'Listen Sounds', 'iSchool/1/2/H5', 'Khan');
 					   
 --*******************************------
 
 INSERT INTO lessons (lessondescription, lessonstartdate, status, lessonname, subjectid, classroomid)
 		values ('Lesson-1', '2017-06-25', 'Completed', 'Simple Poems', 1, 1),
 			   ('Lesson-1', '2017-06-22', 'Completed', 'Verbs', 2, 2),
 			   ('Lesson-2', '2017-06-30', 'in-Progress', 'Sounds', 2, 1),
 			   ('Lesson-2', '2017-06-28', 'in-Progress', 'Sounds', 3, 2);
 				
--*******************************------
 			   
 INSERT INTO assignments (assignmentname, dateofassigned, tags, assignmentduedate, classroomid, subjectid, lessonsid)
 			values ('Assignment-1', '2017-06-26', 'Simple Calculation', '2017-06-28', 1, 1, 1),
 				   ('Assignment-2', '2017-06-28', 'History of Madivel India', '2017-06-30', 1, 2, 3),
 				   ('Assignment-1', '2017-06-26', 'Photosynthesis', '2017-06-28', 2, 1, 2),
 				   ('Assignment-2', '2017-06-28', 'Dolphin Story', '2017-06-30', 2, 2, 4);
 				   
 				   
 				
--*******************************------

 INSERT INTO classroom_worksheets (worksheetsid, dateofassigned, worksheetduedate, classroomid, subjectid, lessonsid)
 		values (3, '2017-06-26', '2017-06-28', 1, 1, 1),
 				(4,'2017-06-28', '2017-06-30', 1, 2, 3),
 				(1,'2017-06-26', '2017-06-28', 2, 1, 2),
 				(2, '2017-06-28', '2017-06-30', 2, 2, 4);
 