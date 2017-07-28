
CREATE TABLE schools(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  code varchar(15) NOT NULL ,
  sname varchar(150) NOT NULL,
  dateofestablishment date NOT NULL,
  timezone varchar(64) NOT NULL,
  dbname varchar(15) NOT NULL,
  createdOn timestamp(3) DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  statusid smallint(6),
  url varchar(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY name_UNIQUE (code,url)
--  UNIQUE KEY name_UNIQUE (url)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO schools(code, sname,dateofestablishment, timezone, dbname, statusid,url)
			VALUES("oakridge","Oakridge High School","2017-06-15","Asia/Calcutta","oakridge",1,"ischool.akrantha"),
				  ("DPS","Delhi Public High School","2017-06-15","Asia/Calcutta","dps",1,"dps.akrantha");

CREATE TABLE clients(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  schoolid bigint(8) NOT NULL,
  fname varchar(150) NOT NULL,
  lname varchar(150) NOT NULL,
  contactnumber varchar(16),
  emailid varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  FOREIGN KEY (schoolid) REFERENCES schools (id),
  UNIQUE KEY name_UNIQUE (emailid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;				  

INSERT INTO clients(schoolid,fname,lname,emailid,password)
			VALUES((select id from schools where dbname='oakridge'),'omniwyse','oakridge','oakridge@gmail.com','omniwyse'),
				  ((select id from schools where dbname='dps'),'omniwyse','dps','dps@gmail.com','omniwyse');
	

CREATE TABLE clientaddress(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  schoolid bigint(8) NOT NULL,
  doornumber varchar(50) NOT NULL,
  street varchar(150) NOT NULL,
  city varchar(150) NOT NULL,
  state varchar(150) NOT NULL,
  country varchar(150) NOT NULL,
  pin bigint(8) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  FOREIGN KEY (schoolid) REFERENCES schools (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE status(
  id bigint(8) NOT NULL,
  description varchar(150) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id),
  UNIQUE KEY name_UNIQUE (description)
)DEFAULT CHARSET=latin1;

INSERT INTO status(id, description)
			VALUES(1,'Active'),
				  (2,'InActive'),
			 	  (3,'Pending');
