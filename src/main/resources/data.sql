INSERT INTO person(id,name,age) values
(1,'Esther',20),
(2,'Karl',30)^;

INSERT into HOUSE(address, type,zipcode,id) values
('street1 Munich','FLAT','81375',1),
('street2 Munich','ESTATE','98087',2)^;

INSERT INTO CHILD(ID, GENDER,AGE,NAME,HAIR_COLOR,BICYCLE_COLOR,PERSON) values
(1,'M',11,"Amyra",null,"white",1),
(2,'M',11,"Esra",null,"blue",2),
(3,'M',11,"Emil","green",null,1),
(4,'M',11,"Tanya","red",null,2)^;

INSERT INTO MEAL(ID, INVENTED,NAME) values
(1,'2022-01-23',"SOUP"),
(2,'1987-01-01',"PASTA"),
(3,'1999-03-12',"BREAD"),
(4,'1920-03-12',"SALAD")^;

INSERT INTO CHILD_MEAL(CHILD_ID,MEAL_ID) values
(1,2),
(1,3),
(2,3),
(2,1),
(1,4),
(3,1),
(4,2)^;




