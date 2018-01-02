--BMS 계정생성
 CREATE USER bms IDENTIFIED BY host DEFAULT TABLESPACE USERS;
 
 --권한주기
 GRANT CONNECT, RESOURCE TO bms;
 
 --락 헤제
 ALTER USER bms ACCOUNT UNLOCK;
 
 --오라클 포트 변경
 --오라클을 설치하면 포트번호가 8080이므로 톰캣과 충돌함.
 --따라서 오라클 포트번호를 9090으로 변경설정 -- 사용하지 않는 포트번호로 설정
 --SQL DEVELOPER의 SYSTEM 계정에서 변경
 --커멘드 : CONN SYSTEM/oracle
 
 EXEC dbms_xdb.setHttpPort(9080);
 SELECT dbms_xdb.getHttpPort() FROM dual;--9090
 



