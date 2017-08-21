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

INSERT INTO messages values(1, 'pay fee f T to p3', '2017-08-19 08:53:58', 1, 1, 3, 'T', 0, 0, 1),
						   (2, 'your son is absent f T to p4', '2017-08-19 08:54:25', 1, 1, 4, 'T', 0, 0, 1),
						   (3, 'all have to come tomorrow f T to all p', '2017-08-19 08:56:44', 1, 1,-1, 'T',0,0, 0),
					       (4,'will pay f p3 to T','2017-08-19 08:56:44',1,3,1,'P',1,1,1),
 					       (5,'thank you f p4 to T','2017-08-19 08:56:44',1,4,1,'P',2,2,1),
 					       (6,'ok fine f T to p3','2017-08-19 08:56:44',1,1,3,'T',4,1,1),
 					       (7,'done f p3 to T','2017-08-19 08:56:44',1,3,1,'P',6,1,1),
 					       (8,'tomarrow my son will absent from p4 to T','2017-08-19 08:56:44',1,3,1,'P',0,0,1),
 					       (9,'my son is sick, so he is not coming tomorrow','2017-08-19 08:56:44',1,1,3,'P',0,0,1),
 					       (10,'ok','2017-08-19 08:56:44',1,3,1,'T',9,9,1);
 					       