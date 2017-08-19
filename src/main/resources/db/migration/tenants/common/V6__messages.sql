--******************** MESSAGES TABLES *************************************
CREATE TABLE messages(
 id bigint(8) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 message VARCHAR(500) NOT NULL,
 messagedate timestamp NOT NULL,
 classroomid bigint(8) NOT NULL,
 senderid bigint(8) NOT NULL,
 recieverid bigint(8) NOT NULL,
 sentflag VARCHAR(10) NOT NULL,
 rootmessageid bigint(8),
 parentmessageid bigint(8),
 isreply VARCHAR(20) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO messages values(1, 'you have to pay the fee', '2017-08-19 08:53:58', 1, 1, 1, 'T', 0, 0, 'true'),
(2, 'your son is absent', '2017-08-19 08:54:25', 1, 1, 3, 'T', 0, 0, 'true'),
(3, 'all have to come tomorrow', '2017-08-19 08:56:44', 1, 1,-1, 'T',0,0, 'true'),
(4,'ok we will pay','2017-08-19 08:56:44',1,1,1,'P',1,1,'true'),
(5,'thank you for informing','2017-08-19 08:56:44',1,3,1,'P',1,1,'true')


