insert all
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10023,'book1.jpg','맥 쓰는 사람들과 함께하는','고래돌이',22000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10022,'book2.jpg','IT 트렌드 스페셜 리포트 2018','김석기,김승열,정도희',14000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10019,'book3.jpg','만들면서 배우는 유니티 VR 게임 개발','김광일,김도윤',30000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10024,'book4.jpg','맛있는 디자인 포토샵,일러스트레이터 CC 2017','박정아,박효근,윤이사라(포완카)',23000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10017,'book5.jpg','아주 큰 스케치북 : 오리기+찢기','마유미 예우스키',9800,100,'etc',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10055,'book6.jpg','이펙티브 C(3판)','빌 와그너',25000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10013,'book7.jpg','골빈해커의 3분 딥러닝','김진중(골빈해커) ',22000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(10000,'book8.jpg','허팝과 함께하는 유튜브 크리에이터 되기','허팝,강전희,안정기',15000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(2865,'book9.jpg','RxJava 프로그래밍','유동환,박정준',25000,100,'it',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(5208,'book10.jpg','퇴사하고 여행갑니다','김대근,김태현',13800,100,'human',0)
into bookList(code,image,title,author,price,amount,bookChat,sellCnt) 
values(5144,'book11.jpg','마이 리얼 유럽','마이리얼트립',15800,100,'social',0)
select * from dual;

insert into bmsBoard(num         ,-- 글번호
                      id          ,-- id
                      writer      ,-- 작성자
                      pwd         ,-- 비밀번호
                      subject     ,-- 글제목
                      content     ,-- 글내용
                      readCnt     ,-- 조회수
                      ref         ,-- 답변글 그룹화 아이디
                      ref_step    ,-- 답변글 그룹 스텝
                      ref_level   ,-- 답변글 그룹 레벨
                      req_date)-- 작성일
values(board_seq.nextval,'id1','kim1','1234','글제목1','글내용1',0,board_seq.currval,0,0,sysdate);