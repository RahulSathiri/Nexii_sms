

--************NEW SCHOOL*********

INSERT INTO schools(code, sname,dateofestablishment, timezone, dbname, statusid,url)
			VALUES("GRKY","Gorkey Public school","2017-08-23","Asia/Calcutta","Gorkey",1,"gorkey.omniwyse.co");
			
--**************

			
			
--************CLIENTS*********			
			
INSERT INTO clients(schoolid,fname,lname,emailid,password)
			VALUES((select id from schools where dbname='Gorkey'),'omniwyse','gorkey','gorkey@gmail.com','omniwyse');
				  
--**************
			