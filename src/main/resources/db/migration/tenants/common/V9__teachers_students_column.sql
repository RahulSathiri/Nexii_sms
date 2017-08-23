
-- ***************** TEACHERS TABLES **************************************

ALTER TABLE teachers add 
(middlename VARCHAR(30),
maritalstatus VARCHAR(30)
);

UPDATE teachers SET maritalstatus = 'SINGLE' where id=1; 
UPDATE teachers SET maritalstatus = 'SINGLE' where id=2;
UPDATE teachers SET maritalstatus = 'SINGLE' where id=3;
UPDATE teachers SET maritalstatus = 'MARRIED' where id=4;
UPDATE teachers SET maritalstatus = 'MARRIED' where id=5;
UPDATE teachers SET maritalstatus = 'MARRIED' where id=6;
UPDATE teachers SET maritalstatus = 'MARRIED' where id=7;
UPDATE teachers SET maritalstatus = 'MARRIED' where id=8;
UPDATE teachers SET maritalstatus = 'MARRIED' where id=9;
UPDATE teachers SET maritalstatus = 'MARRIED' where id=10; 


-- ********** STUDENTS TABLES ******************************

ALTER TABLE students add 
(middlename VARCHAR(30),
lastname VARCHAR(30) NOT NULL,
bloodgroup VARCHAR(10) NOT NULL,
height VARCHAR(10) NOT NULL,
weight VARCHAR(10) NOT NULL
);

UPDATE students SET lastname = 'pechetti',middlename='', bloodgroup= 'O+',height='4.2',weight='22' WHERE id = 1;
UPDATE students SET lastname = 'duggineni',middlename='', bloodgroup= 'AB+',height='4.1',weight='20' WHERE id = 2;
UPDATE students SET lastname = 'suddala',middlename='', bloodgroup= 'A+',height='4.3',weight='24' WHERE id = 3;
UPDATE students SET lastname = 'katari', middlename='',bloodgroup= 'O-',height='4.0',weight='20' WHERE id = 4;
