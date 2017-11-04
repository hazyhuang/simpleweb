-----------1.Creat SQL
drop table HAZY_USER;
drop table HAZY_COMMENTS;
CREATE TABLE "AGILE"."HAZY_USER" 
   (	"ID" NUMBER, 
	"USERNAME" VARCHAR2(100 CHAR), 
	"PASSWORD" VARCHAR2(100 CHAR)
   );
   
CREATE TABLE "AGILE"."HAZY_COMMENTS" 
   (	"ID" NUMBER, 
	"USERID" NUMBER, 
	"COMMENT2" VARCHAR2(4000 CHAR)
   );

CREATE SEQUENCE  "AGILE"."HAZY_SEQ_USER" 
MINVALUE 1 MAXVALUE 9999999999999999999999999999 
INCREMENT BY 1 START WITH 4987 NOCACHE  NOORDER  NOCYCLE ;

CREATE SEQUENCE  "AGILE"."HAZY_SEQ_COMMENTS" 
MINVALUE 1 MAXVALUE 9999999999999999999999999999 
INCREMENT BY 1 START WITH 4987 NOCACHE  NOORDER  NOCYCLE ;

----------2.  Test
select * from HAZY_USER
select * from HAZY_COMMENTS
select t.* from hazy_comments t where t.userid ='12'  order by id asc
	select hazy_seq_comments.Nextval from dual
insert into hazy_comments(ID,COMMENT2,userid) values(hazy_seq_comments.Nextval,'xxxxx',12);
delete hazy_comments
	