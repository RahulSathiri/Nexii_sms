
DROP TABLE IF EXISTS users;
CREATE TABLE users(
  userid bigint(8) NOT NULL AUTO_INCREMENT,
  fname varchar(150) NOT NULL,
  lname varchar(150) NOT NULL,
  contactname varchar(50),
  contactnumber varchar(16),
  emailid varchar(255),
  password varchar(255),
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  statusId smallint(6),
  PRIMARY KEY (userid),
  UNIQUE KEY name_UNIQUE (emailid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS address;
CREATE TABLE address(
  addressid bigint(8) NOT NULL AUTO_INCREMENT,
  userid bigint(8) NOT NULL,
  doornumber varchar(50) NOT NULL,
  street varchar(150) NOT NULL,
  city varchar(150) NOT NULL,
  state varchar(150) NOT NULL,
  country varchar(150) NOT NULL,
  createdOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  modifiedOn timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (addressid),
  FOREIGN KEY (userid) REFERENCES users (userid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;