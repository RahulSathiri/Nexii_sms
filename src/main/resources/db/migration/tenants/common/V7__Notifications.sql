--******************** WorkSheets  TABLES ***********************************************************

CREATE TABLE  notifications(
id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
notificationname VARCHAR(100) NOT NULL,
description VARCHAR(200) NOT NULL,
status bigint,
notificationdate date NOT NULL,
publishedby VARCHAR(50) NOT NULL,
actioncode VARCHAR(30) NOT NULL,
parentactionrequired VARCHAR(30)
);

