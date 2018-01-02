 create table cusInfo(				--회원정보
    id varchar2(10) primary key,	--id
    pwd varchar2(20),				--pwd
    name varchar2(10),				--이름
    gender varchar2(10),			--성별
    birth varchar2(10),				--생년월일
    email varchar2(20),				--이메일
    hp varchar2(13),				--연락처
    addr varchar2(100)              --배송지 주소
);

create table bookList( --재고 리스트
    code number(10) PRIMARY key,        --물류코드
    image varchar2(50) not null,       --책 이미지
    title varchar2(100) not null,       --책제목
    author varchar2(50) not null,      --저자
    price number(20) not null,         --판매가
    amount number(20) not null,        --판매수량
    bookChat varchar2(200) not null,   --책 종류(humen,medical,it,social,etc)
    sellCnt number(20) default 0 not null,--판매된 책 개수
    publisher TIMESTAMP DEFAULT sysdate--출간일
);

create SEQUENCE castsNum --시퀀스 생성
    start with 1    --1부터 시작
    increment by 1  --1씩 증가
    maxvalue 99999; --99999최대값
drop table buyList;

create table carts( --장바구니 리스트
    num number(30) primary key,  	   --장바구니 구분번호
	code number(10) not null,  		   --물류코드
    id VARCHAR2(100) not null,         --구분할 id
    image varchar2(50) not null,       --책 이미지
    title varchar2(100) not null,      --책제목
    author varchar2(50) not null,      --저자
    price number(20) not null,         --판매가
    amount number(20) not null,        --판매수량
    publisher TIMESTAMP DEFAULT sysdate--출간일
);

create SEQUENCE board_seq
    start with 1    --1부터 시작
    increment by 1  --1씩 증가
    maxvalue 99999; --99999최대값

CREATE TABLE bmsBoard(
    num         number(5), -- 글번호
    id          varchar2(30) not null,
    writer      varchar2(20) not null, -- 작성자
    pwd         varchar2(10) not null, -- 비밀번호
    subject     varchar2(50) not null, -- 글제목
    content     varchar2(500),         -- 글내용
    readCnt     number(5)    default 0,-- 조회수
    ref         number(5)    default 0,-- 답변글 그룹화 아이디 --답변 글번호로서 원글번호와 일치하여야 한다.
    ref_step    number(5)    default 0,-- 답변글 그룹 스텝
    ref_level   number(5)    default 0,-- 답변글 그룹 레벨
    req_date    TIMESTAMP    default sysdate, --작성일
    constraint  mvc_board_num_pk primary key(num)
);

create SEQUENCE orderNum--시퀀스 생성
    start with 1    --1부터 시작
    increment by 1  --1씩 증가
    maxvalue 99999; --99999최대값


create table orderList( --주문지 정보
    num number(30) primary key,--구분할 번호
    id varchar2(70) not null,
    name varchar2(10) not null,--주문자 이름
    hp varchar2(13) not null,--연락처
    email varchar2(20),--이메일
    addr varchar2(100) not null,--배송지 주소
    title varchar2(200) not null, --책 이름
    amount number(20) not null,--구매수량
    publisher Timestamp default sysdate, --publisher 구매일자
    price number(20) not null--결제금액
);

create table Delivery(
    num number(30) primary key,--구분할 번호
    id varchar2(70) not null,
    name varchar2(10) not null,--주문자 이름
    hp varchar2(13) not null,  --연락처
    email varchar2(20),        --이메일
    addr varchar2(200) not null,--배송지 주소
    title varchar2(200) not null, --책 이름
    amount number(20) not null,--구매수량
    publisher Timestamp default sysdate, --publisher 구매일자
    price number(20) not null,--결제금액
    refund varchar2(100)default '판매' --환불처리 여부[판매/환불]
);

create table closing( --결산
    num number(38) primary key ,
    totalSale number(38) default 0,  --총 판매금액   [누적]
    soltOutCnt number(38)default 0,  --재고품절 갯수 [갱신]
    refundCnt number(38) default 0,  --환불신청 갯수 [갱신]
    human   number(38)   default 0,  --인문책 판매갯수[누적]
    medical number(38)   default 0,  --의학책 판매갯수[누적]
    it number(38)        default 0,  --it책 판매갯수[누적]
    etc number(38)       default 0,  --기타 책 판매갯수[누적]
    social number(38)    default 0   --사회책 판매갯수[누적]
);