-- *************** GRADES TABLES **************************************************

CREATE TABLE ischool_grades(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	gradenumber bigint(8) NOT NULL,
	gradename VARCHAR(30) NOT NULL);

INSERT INTO ischool_grades (gradenumber, gradename)
			values  (1, '1st Class'),
					(2, '2nd Class'),
					(3, '3rd Class'),
					(4, '4th Class'),
					(5, '5th Class'),
					(6, '6th Class'),
					(7, '7th Class'),
					(8, '8th Class'),
					(9, '9th Class'),
				    (10, '10th Class');

-- ******************* SUBJECTS TABLES *******************************************

CREATE TABLE ischool_subjects(
	id bigint(8) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	subjectname VARCHAR(150) NOT NULL,
	subjectid bigint(8));

INSERT INTO ischool_subjects(subjectname, subjectid) 
  values('Telugu',1),('Hindi',2),('English',3),('Physics',4),('Maths',5),('Biology',6),
		('Chemistry',7),('Natural Science',8),('Social Studies',9),('History',10),
		('Economics',11),('Geography',12),('Zoology',13),('Botany',14);

--******************** WorkSheets  TABLES *********************************************

CREATE TABLE ischool_degreeofdifficulty(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
degreeofdifficulty VARCHAR(50) NOT NULL
);

INSERT INTO ischool_degreeofdifficulty (degreeofdifficulty) values('EASY'),('INTERMEDIATE'),('ADVANCED');

CREATE TABLE ischool_worksheets(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
worksheetname VARCHAR(50) NOT NULL,
gradeid bigint NOT NULL,
subjectid bigint NOT NULL,
degreeofdifficultyid bigint NOT NULL,
tags VARCHAR(100) NOT NULL,
worksheetpath VARCHAR(100) NOT NULL,
createdby VARCHAR(50),
FOREIGN KEY(gradeid) REFERENCES ischool_grades(id),
FOREIGN KEY(subjectid) REFERENCES ischool_subjects(id),
FOREIGN KEY(degreeofdifficultyid) REFERENCES ischool_degreeofdifficulty (id)
); 
