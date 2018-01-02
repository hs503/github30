 create table cusInfo(				--ȸ������
    id varchar2(10) primary key,	--id
    pwd varchar2(20),				--pwd
    name varchar2(10),				--�̸�
    gender varchar2(10),			--����
    birth varchar2(10),				--�������
    email varchar2(20),				--�̸���
    hp varchar2(13),				--����ó
    addr varchar2(100)              --����� �ּ�
);

create table bookList( --��� ����Ʈ
    code number(10) PRIMARY key,        --�����ڵ�
    image varchar2(50) not null,       --å �̹���
    title varchar2(100) not null,       --å����
    author varchar2(50) not null,      --����
    price number(20) not null,         --�ǸŰ�
    amount number(20) not null,        --�Ǹż���
    bookChat varchar2(200) not null,   --å ����(humen,medical,it,social,etc)
    sellCnt number(20) default 0 not null,--�Ǹŵ� å ����
    publisher TIMESTAMP DEFAULT sysdate--�Ⱓ��
);

create SEQUENCE castsNum --������ ����
    start with 1    --1���� ����
    increment by 1  --1�� ����
    maxvalue 99999; --99999�ִ밪
drop table buyList;

create table carts( --��ٱ��� ����Ʈ
    num number(30) primary key,  	   --��ٱ��� ���й�ȣ
	code number(10) not null,  		   --�����ڵ�
    id VARCHAR2(100) not null,         --������ id
    image varchar2(50) not null,       --å �̹���
    title varchar2(100) not null,      --å����
    author varchar2(50) not null,      --����
    price number(20) not null,         --�ǸŰ�
    amount number(20) not null,        --�Ǹż���
    publisher TIMESTAMP DEFAULT sysdate--�Ⱓ��
);

create SEQUENCE board_seq
    start with 1    --1���� ����
    increment by 1  --1�� ����
    maxvalue 99999; --99999�ִ밪

CREATE TABLE bmsBoard(
    num         number(5), -- �۹�ȣ
    id          varchar2(30) not null,
    writer      varchar2(20) not null, -- �ۼ���
    pwd         varchar2(10) not null, -- ��й�ȣ
    subject     varchar2(50) not null, -- ������
    content     varchar2(500),         -- �۳���
    readCnt     number(5)    default 0,-- ��ȸ��
    ref         number(5)    default 0,-- �亯�� �׷�ȭ ���̵� --�亯 �۹�ȣ�μ� ���۹�ȣ�� ��ġ�Ͽ��� �Ѵ�.
    ref_step    number(5)    default 0,-- �亯�� �׷� ����
    ref_level   number(5)    default 0,-- �亯�� �׷� ����
    req_date    TIMESTAMP    default sysdate, --�ۼ���
    constraint  mvc_board_num_pk primary key(num)
);

create SEQUENCE orderNum--������ ����
    start with 1    --1���� ����
    increment by 1  --1�� ����
    maxvalue 99999; --99999�ִ밪


create table orderList( --�ֹ��� ����
    num number(30) primary key,--������ ��ȣ
    id varchar2(70) not null,
    name varchar2(10) not null,--�ֹ��� �̸�
    hp varchar2(13) not null,--����ó
    email varchar2(20),--�̸���
    addr varchar2(100) not null,--����� �ּ�
    title varchar2(200) not null, --å �̸�
    amount number(20) not null,--���ż���
    publisher Timestamp default sysdate, --publisher ��������
    price number(20) not null--�����ݾ�
);

create table Delivery(
    num number(30) primary key,--������ ��ȣ
    id varchar2(70) not null,
    name varchar2(10) not null,--�ֹ��� �̸�
    hp varchar2(13) not null,  --����ó
    email varchar2(20),        --�̸���
    addr varchar2(200) not null,--����� �ּ�
    title varchar2(200) not null, --å �̸�
    amount number(20) not null,--���ż���
    publisher Timestamp default sysdate, --publisher ��������
    price number(20) not null,--�����ݾ�
    refund varchar2(100)default '�Ǹ�' --ȯ��ó�� ����[�Ǹ�/ȯ��]
);

create table closing( --���
    num number(38) primary key ,
    totalSale number(38) default 0,  --�� �Ǹűݾ�   [����]
    soltOutCnt number(38)default 0,  --���ǰ�� ���� [����]
    refundCnt number(38) default 0,  --ȯ�ҽ�û ���� [����]
    human   number(38)   default 0,  --�ι�å �ǸŰ���[����]
    medical number(38)   default 0,  --����å �ǸŰ���[����]
    it number(38)        default 0,  --itå �ǸŰ���[����]
    etc number(38)       default 0,  --��Ÿ å �ǸŰ���[����]
    social number(38)    default 0   --��ȸå �ǸŰ���[����]
);