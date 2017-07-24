--******************** Timeline  TABLES ***********************************************************

CREATE TABLE lessons(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
lessonname VARCHAR(50) NOT NULL,
subjectid bigint NOT NULL,
classroomid bigint NOT NULL,
FOREIGN KEY(classroomid) REFERENCES classrooms(id),
FOREIGN KEY(subjectid) REFERENCES grade_subjects(id)
);

CREATE TABLE assignments(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
assignmentname VARCHAR(50) NOT NULL,
dateofassigned date,
duedate date,
classroomid bigint NOT NULL,
subjectid bigint NOT NULL,
FOREIGN KEY(classroomid) REFERENCES classrooms(id),
FOREIGN KEY(subjectid) REFERENCES grade_subjects(id)
);

CREATE TABLE classroom_worksheets(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
worksheetsid bigint NOT NULL,
dateofassigned date,
duedate date,
classroomid bigint NOT NULL,
subjectid bigint NOT NULL,
teacherid bigint NOT NULL,
FOREIGN KEY(classroomid) REFERENCES classrooms(id),
FOREIGN KEY(worksheetsid) REFERENCES worksheets(id),
FOREIGN KEY(teacherid) REFERENCES teachers(id)
);

CREATE TABLE timeline(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
lessonsid bigint NOT NULL,
assignmentsid bigint NOT NULL,
worksheetsid bigint NOT NULL,
teacherid  bigint NOT NULL,
FOREIGN KEY(lessonsid) REFERENCES lessons(id),
FOREIGN KEY(assignmentsid) REFERENCES assignments(id),
FOREIGN KEY(worksheetsid) REFERENCES worksheets(id),
FOREIGN KEY(teacherid) REFERENCES teachers(id)
);









