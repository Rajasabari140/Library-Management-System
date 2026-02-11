SQL> DESC AUTHOR_TBL;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 AUTHOR_CODE                                        NUMBER(5)
 AUTHOR_NAME                                        VARCHAR2(20)
 CONTACT_NO                                         NUMBER(15)

SQL> DESC book_tbl;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ISBN                                      NOT NULL VARCHAR2(10)
 BOOK_TITLE                                NOT NULL VARCHAR2(20)
 BOOK_TYPE                                          CHAR(1)
 AUTHOR_CODE                               NOT NULL NUMBER(5)
 BOOK_COST                                 NOT NULL NUMBER(8,2)


 MENU PAGE : <img width="817" height="293" alt="Screenshot 2026-02-09 115253" src="https://github.com/user-attachments/assets/98661cdd-b748-430f-9632-4b452af719b8" />
 ADD BOOK PAGE:<img width="1027" height="415" alt="Screenshot 2026-02-09 115430" src="https://github.com/user-attachments/assets/234bc984-a8c7-4122-a030-36649e952f21" />
 VIEW PAGE :<img width="1105" height="401" alt="image" src="https://github.com/user-attachments/assets/8c04c917-f8ae-43fe-95a6-142aaa1a81bd" />



