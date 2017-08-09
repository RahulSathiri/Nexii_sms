--******************* OPTIONS *****************************
CREATE TABLE options(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
optionvalue VARCHAR(100) NOT NULL,
UNIQUE(name)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;