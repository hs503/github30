insert all
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10023,'book1.jpg','�� ���� ������ �Բ��ϴ�','������',22000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10022,'book2.jpg','IT Ʈ���� ����� ����Ʈ 2018','�輮��,��¿�,������',14000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10019,'book3.jpg','����鼭 ���� ����Ƽ VR ���� ����','�豤��,�赵��',30000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10024,'book4.jpg','���ִ� ������ ���伥,�Ϸ���Ʈ������ CC 2017','������,��ȿ��,���̻��(����ī)',23000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10017,'book5.jpg','���� ū ����ġ�� : ������+����','������ ���콺Ű',9800,100,'etc',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10055,'book6.jpg','����Ƽ�� C(3��)','�� �ͱ׳�',25000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10013,'book7.jpg','�����Ŀ�� 3�� ������','������(�����Ŀ) ',22000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10000,'book8.jpg','���˰� �Բ��ϴ� ��Ʃ�� ũ�������� �Ǳ�','����,������,������',15000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(2865,'book9.jpg','RxJava ���α׷���','����ȯ,������',25000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(5208,'book10.jpg','����ϰ� ���఩�ϴ�','����,������',13800,100,'human',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(5144,'book11.jpg','���� ���� ����','���̸���Ʈ��',15800,100,'social',0)
select * from dual;

insert into bmsBoard(num         ,-- �۹�ȣ
                      id          ,-- id
                      writer      ,-- �ۼ���
                      pwd         ,-- ��й�ȣ
                      subject     ,-- ������
                      content     ,-- �۳���
                      readCnt     ,-- ��ȸ��
                      ref         ,-- �亯�� �׷�ȭ ���̵�
                      ref_step    ,-- �亯�� �׷� ����
                      ref_level   ,-- �亯�� �׷� ����
                      req_date)-- �ۼ���
values(board_seq.nextval,'id1','kim1','1234','������1','�۳���1',0,board_seq.currval,0,0,sysdate);