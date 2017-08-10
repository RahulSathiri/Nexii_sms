--******************** MESSAGES TABLES *************************************
CREATE TABLE messages(
 id bigint(8) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 message VARCHAR(500) NOT NULL,
 messagedate timestamp NOT NULL,
 senderid bigint(8) NOT NULL,
 recieverid bigint(8) NOT NULL,
 sentflag VARCHAR(10) NOT NULL,
 rootmessageid bigint(8)  
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


