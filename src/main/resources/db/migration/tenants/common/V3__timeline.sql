--******************** Timeline  TABLES ***********************************************************

CREATE TABLE lessons(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
lessonname VARCHAR(50) NOT NULL,
startdate date,
enddate date,
gradeid bigint NOT NULL,
subjectid bigint NOT NULL,
FOREIGN KEY(gradeid) REFERENCES grades(id),
FOREIGN KEY(subjectid) REFERENCES grade_subjects(id)
);

CREATE TABLE assignments(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
assignmentname VARCHAR(50) NOT NULL,
dateofassigned date NOT NULL,
gradeid bigint NOT NULL,
subjectid bigint NOT NULL,
FOREIGN KEY(gradeid) REFERENCES grades(id),
FOREIGN KEY(subjectid) REFERENCES grade_subjects(id)
);

CREATE TABLE timeline(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
lessonsid bigint NOT NULL,
assignmentsid bigint NOT NULL,
FOREIGN KEY(lessonsid) REFERENCES lessons(id),
FOREIGN KEY(assignmentsid) REFERENCES assignments(id)
);



