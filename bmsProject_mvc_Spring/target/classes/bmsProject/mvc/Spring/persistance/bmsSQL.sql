--BMS ��������
 CREATE USER bms IDENTIFIED BY host DEFAULT TABLESPACE USERS;
 
 --�����ֱ�
 GRANT CONNECT, RESOURCE TO bms;
 
 --�� ����
 ALTER USER bms ACCOUNT UNLOCK;
 
 --����Ŭ ��Ʈ ����
 --����Ŭ�� ��ġ�ϸ� ��Ʈ��ȣ�� 8080�̹Ƿ� ��Ĺ�� �浹��.
 --���� ����Ŭ ��Ʈ��ȣ�� 9090���� ���漳�� -- ������� �ʴ� ��Ʈ��ȣ�� ����
 --SQL DEVELOPER�� SYSTEM �������� ����
 --Ŀ��� : CONN SYSTEM/oracle
 
 EXEC dbms_xdb.setHttpPort(9080);
 SELECT dbms_xdb.getHttpPort() FROM dual;--9090
 



