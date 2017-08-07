

--******************** MULTI-LEVEL ATTENDANCE  TABLE ************************************************************

CREATE TABLE school_attendance(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
attendance_type VARCHAR(20) NOT NULL,
status INT);

INSERT INTO school_attendance (attendance_type) values ('SUBJECTWISE'),('ONETIME');


--********************ATTENDANCE SUBJECTWISE  TABLE ************************************************************

CREATE TABLE attendance_subjectwise(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
studentid bigint NOT NULL,
classroomid bigint NOT NULL,
dateofattendance DATE NOT NULL,
attendancestatus INT NOT NULL,
subjectid bigint NOT NULL,
FOREIGN KEY(studentid) REFERENCES students(id),
FOREIGN KEY(classroomid) REFERENCES classrooms(id),
FOREIGN KEY(subjectid) REFERENCES subjects(id)); 



--******************** SCHOOL-TIMINGS  TABLE ************************************************************

CREATE TABLE school_timings(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
from_time TIME NOT NULL,
to_time TIME NOT NULL);