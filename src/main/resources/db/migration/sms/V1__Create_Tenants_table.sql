
CREATE TABLE schools(
  id bigint(8) NOT NULL AUTO_INCREMENT,
  code varchar(15) NOT NULL ,
  name varchar(150) NOT NULL,
  Dateofestablishment timestamp(3) NOT NULL,
  timezone varchar(64) NOT NULL,
  dbname varchar(15) NOT NULL,
  location varchar(150) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  statusId smallint(6),
  PRIMARY KEY (id),
  UNIQUE KEY name_UNIQUE (code)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO schools(code, name, timezone, dbname, location, statusId)
			VALUES('oakridge','Oakridge High School','Asia/Calcutta','oakridge','HYD',1),
				  ('DPS','Delhi Public High School','Asia/Calcutta','DPS','SEC',1);

CREATE TABLE clients(
  clientid bigint(8) NOT NULL AUTO_INCREMENT,
  id bigint(8) NOT NULL,
  fname varchar(150) NOT NULL,
  lname varchar(150) NOT NULL,
  contactnumber varchar(16),
  emailid varchar(255),
  password varchar(255),
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (clientid),
  FOREIGN KEY (id) REFERENCES schools (id),
  UNIQUE KEY name_UNIQUE (emailid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;				  
				  
CREATE TABLE clientaddress(
  addressid bigint(8) NOT NULL AUTO_INCREMENT,
  id bigint(8) NOT NULL,
  doornumber varchar(50) NOT NULL,
  street varchar(150) NOT NULL,
  city varchar(150) NOT NULL,
  state varchar(150) NOT NULL,
  country varchar(150) NOT NULL,
  pin bigint(8) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (addressid),
  FOREIGN KEY (id) REFERENCES schools (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

			
CREATE TABLE status(
  statusId bigint(8) NOT NULL,
  description varchar(150) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (statusId),
  UNIQUE KEY name_UNIQUE (description)
)DEFAULT CHARSET=latin1;

INSERT INTO status(statusId, description)
			VALUES(1,'Active'),
				  (2,'InActive'),
			 	  (3,'Pending');
