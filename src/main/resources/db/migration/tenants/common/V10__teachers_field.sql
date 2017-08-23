-- ***************** TEACHERS TABLES **************************************

ALTER TABLE teachers add (bloodgroup VARCHAR(30) NOT NULL);

UPDATE teachers SET bloodgroup = 'O+' where id=1; 
UPDATE teachers SET bloodgroup = 'A+' where id=2;
UPDATE teachers SET bloodgroup = 'B+' where id=3;
UPDATE teachers SET bloodgroup = 'AB+' where id=4;
UPDATE teachers SET bloodgroup = 'O-' where id=5;
UPDATE teachers SET bloodgroup = 'A-' where id=6;
UPDATE teachers SET bloodgroup = 'B-' where id=7;
UPDATE teachers SET bloodgroup = 'AB-' where id=8;
UPDATE teachers SET bloodgroup = 'O+' where id=9;
UPDATE teachers SET bloodgroup = 'O-' where id=10; 
