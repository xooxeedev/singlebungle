# ⭐️Portfolio - SingleBungle


<!-- contents -->
<details open="open">
  <summary>Contents</summary>
  <ol>
    <li>
      <a href="#개요">개요</a>
    </li>
    <li>
      <a href="#내용">내용</a>
    </li>
    <li><a href="#구현-기능">구현 기능</a>
      <ul>
        <li><a href="#회원가입">회원가입</a></li>
        <li><a href="#아이디/비밀번호 찾기">아이디/비밀번호 찾기</a></li>
        <li><a href="#로그인">로그인</a></li>
        <li><a href="#마이페이지 조회">마이페이지 조회</a></li>
        <li><a href="#내 정보 수정">내 정보 수정</a></li>
        <li><a href="#비밀번호 변경">비밀번호 변경</a></li>
        <li><a href="#회원탈퇴">회원탈퇴</a></li>
      </ul>
    </li>
  </ol>
</details>

------------

# 📝개요

* 프로젝트 명 : SingleBungle

* 일정 : 2021년 01월 29일 ~ 2021년 03월 11일

* 개발 목적 : 1인 가구의 정보를 공유하고 소통할 수 있는 커뮤니티 사이트 제작

* 개발 환경
  - O/S : Windows 10
  - Server : Apache-tomcat-8.5.61
  - Java EE IDE : Eclipse ( version 2020-06 (4.16.0) )
  - Database : Oracle SQL Developer ( version 20.2.0 )
  - Programming Language : JAVA, HTML, CSS, JavaScript, JSP, SQL
  - Framework/flatform : Spring, mybatis, jQuery 3.5.1, Bootstrap v4.6.x
  - API : WebSocket, Kakao map, summernote
  - Version management : Git

------------

# 📝내용

* 팀원별 역할
  - 공통 : 기획, 요구 사항 정의, 와이어 프레임, DB 설계
  - 강수정 : 만남의 광장 게시판 CRUD, 채팅
  - 강보령 : 싱글이의 영수증 게시판 CRUD, 쪽지
  - 김현혜 : 공지사항 게시판 CRUD, 고객센터 게시판 CRUD, 관리자
  - 신주희 : 로그인, 회원가입, 아이디/비밀번호 찾기, 마이페이지 기능
  - 이솔이 : 일상을 말해봐 게시판 CRUD, 먹보의 하루 게시판 CRUD
  - 이한솔 : 벙글 장터 게시판 CRUD

* 프로젝트 시 활용해본 대표 기술
  - 회원가입시 이메일 인증

* 구현 기능
  - 회원가입
  - 아이디/비밀번호 찾기
  - 로그인
  - 마이페이지 조회 
  - 내 정보 수정
  - 비밀번호 변경
  - 회원탈퇴



* DB 설계<br>
![singlebungle](https://user-images.githubusercontent.com/72387870/111110444-e7c9fa00-859f-11eb-926b-ac04e8e5d0ab.png)

------------

# 📝구현 기능

## 로그인

  * 화면 설명 : 로그인 페이지입니다.
   ![로그인](https://user-images.githubusercontent.com/73690758/111954922-2b86ab80-8b2c-11eb-91f4-adedcf5a9e1a.JPG)

   
  * 구현 기능 설명
	-	네비게이션 우측 상단에 로그인 글씨를 클릭하면 로그인 페이지로 이동합니다.
	- 인풋창에 아이디와 비밀번호를 입력하고 로그인 버튼을 누르면 로그인이 됩니다.
	- 아이디 저장 체크박스를 누르면, 다음 로그인시 아이디가 저장됩니다.
	- 로그인 버튼 밑에 아이디찾기/비밀번호찾기/회원가입 버튼을 넣었습니다.

------------

## 회원가입

  * 화면 설명 : 회원가입 페이지입니다.
   
  * 구현 기능 설명
     - 카테고리, 지역, 모임 장소, 날짜, 시간, 인원, 성별을 지정할 수 있습니다.
     - 지난 날짜를 선택했을 경우에는 알림 창이 뜨도록 구현하였습니다.
 
     ![1_게시글 작성_모임날짜](https://user-images.githubusercontent.com/72387870/111111157-2e6c2400-85a1-11eb-85d5-aaa8036980b6.png)
     - 내용은 summernote를 이용하여 작성할 수 있도록 구현했습니다.<br> 이미지 첨부 시 ajax를 통해 이미지가 저장되고 화면에 출력 됩니다.
     - 모든 요소 작성 후 등록 버튼 클릭 시 글이 작성되고 상세조회 페이지로 이동합니다.<br>
     ![1_게시글 작성 완료_조회](https://user-images.githubusercontent.com/72387870/111111250-5c516880-85a1-11eb-8050-a65b39eb1cb9.png)
     - 상세조회 페이지에는 제목에 카테고리와 모집 성별을 표시하였습니다.
     - 작성자 닉네임, 작성 시간, 조회 수, 모임 장소, 날짜, 시간, 참여인원을 확인할 수 있습니다.
     - 채팅하기 버튼을 통해 참여 신청한 회원들과 채팅이 가능합니다.

------------

## 만남의 광장 게시글 수정

  * 화면 설명 : 작성한 글에 수정사항이 있을 경우 게시글을 수정하는 페이지입니다.<br>
   ![1_게시글 수정](https://user-images.githubusercontent.com/72387870/111112047-d504f480-85a2-11eb-950a-3a48671ad3a2.png)

  * 구현 기능 설명
    - 제목, 카테고리, 지역, 모임 장소, 모임 날짜, 모임 시간, 모집인원을 수정할 수 있습니다.
    - 글 작성 시 지정해놓은 성별로 참여 신청을 받기 때문에 성별은 수정이 불가능하도록 구현했습니다.<br>
    ![녹화_2021_03_15_15_29_52_832](https://user-images.githubusercontent.com/72387870/111112341-58264a80-85a3-11eb-9402-4aaa60dd9a0b.gif)
    - 수정 버튼 클릭 시 수정한 게시글 상세조회로 이동하게 됩니다.<br>
    ![녹화_2021_03_15_15_57_38_189](https://user-images.githubusercontent.com/72387870/111114869-38912100-85a7-11eb-999c-65142551fa28.gif)

------------

## 만남의 광장 게시글 삭제

  * 화면 설명 : 작성한 글을 삭제하고 싶은 경우 게시글을 삭제하는 페이지입니다.
   ![녹화_2021_03_15_15_58_55_134](https://user-images.githubusercontent.com/72387870/111115076-768e4500-85a7-11eb-827e-c80666f97b7d.gif)

  * 구현 기능 설명
    - 삭제 버튼 클릭 시 alert 창을 출력하여 한 번 더 확인할 수 있도록 했습니다.
    - alert 창에서 확인 클릭 시 글의 상태가 삭제로 바뀌며 게시글 목록으로 이동됩니다.

------------

## 만남의 광장 게시글 신고

  * 화면 설명 : 부적절한 게시글을 신고하는 화면입니다.
   ![image](https://user-images.githubusercontent.com/72387870/111344583-d6780f00-86bf-11eb-8797-23498e8e3f98.png)

  * 구현 기능 설명
    - 타 회원의 부적절한 게시글을 신고 버튼을 클릭하여 신고할 수 있습니다.
    - 신고 제목, 신고 사유, 신고 내용을 입력하여 신고 버튼을 클릭하면 신고가 접수됩니다.

------------

<h3 id="search">검색(카테고리)</h3>

  * 화면 설명 : 카테고리 별 제목, 닉네임, 제목+내용으로 검색할 수 있는 화면입니다.
  ![녹화_2021_03_15_18_42_46_38](https://user-images.githubusercontent.com/72387870/111133908-66ce2b00-85be-11eb-9bc8-4f90633af751.gif)   

  * 구현 기능 설명
    - 카테고리 값을 hidden type의 input 태그로 전달하여 검색 시 카테고리별 검색이 가능하도록 구현했습니다.

------------

<h3 id="reply">댓글/답글 작성, 수정, 삭제, 신고</h3>

  * 화면 설명 : 게시글에 댓글을 작성하는 화면입니다.
  ![댓글작성_수정](https://user-images.githubusercontent.com/72387870/111137572-4d2ee280-85c2-11eb-94ad-79956315899f.gif)
     
  * 구현 기능 설명
    - 댓글 작성 시 ajax를 통해 비동기적으로 등록한 댓글이 추가됩니다.
    - 댓글 수정 시 댓글 수정 내용을 입력할 수 있도록 기존 내용 영역이 textarea로 변경됩니다.
    - 수정 버튼 클릭 시 작성 시와 마찬가지로 ajax를 통해 댓글이 수정됩니다.
    - 답글 버튼 클릭 시 해당 댓글 영역 밑에 답글을 입력할 수 있는 textarea가 추가됩니다.
    - 답글 수정 버튼 클릭 시 댓글 수정과 마찬가지로 기존 내용 영역이 textarea로 변경됩니다.
    - 삭제 버튼 클릭 시 alert 창을 출력하여 한 번 더 확인할 수 있도록 했습니다.
    - alert 창에서 확인 클릭 시 답글 상태가 삭제로 바뀌며 ajax를 통해 댓글이 삭제됩니다.<br>
    ![답글작성_수정_삭제](https://user-images.githubusercontent.com/72387870/111137630-5e77ef00-85c2-11eb-8715-b946602df5b8.gif)
    - 타 회원의 부적절한 댓글은 신고를 클릭하여 신고할 수 있습니다.
    - 신고 제목, 신고 사유, 신고 내용을 입력하여 신고 버튼을 클릭하면 신고가 접수됩니다.
    ![6_댓글_신고](https://user-images.githubusercontent.com/72387870/111343823-260a0b00-86bf-11eb-957d-e77ea975b108.png)
  
------------  
  
## 참여신청

  * 화면 설명 : 원하는 친구 찾기 글에 참가신청 버튼을 클릭하여 참가할 수 있습니다.
  ![채팅하기_참여신청_채팅하기](https://user-images.githubusercontent.com/72387870/111138932-deeb1f80-85c3-11eb-9051-5520291b0855.gif)

  * 구현 기능 설명
    - 참가신청하지 않고 채팅하기 버튼을 클릭할 경우 알림 창을 출력할 수 있도록 구현했습니다.
    - 글 작성 시 지정한 모집인원수가 다 찬 경우에는 모집 신청 버튼이 모집 마감으로 변경되며<br> 
      더 이상 클릭할 수 없도록 구현했습니다.
    - 글 작성 시 지정한 모집 성별과 참여 신청하는 회원의 성별이 다를 경우에는 참여 신청할 수 없도록 구현했습니다.<br>
    ![참여신청_성별이 다를 경우](https://user-images.githubusercontent.com/72387870/111139196-2c678c80-85c4-11eb-98ec-04feea62c842.gif)
  
------------
  
## 채팅

  * 화면 설명 : 참여 신청한 회원들 간 실시간 채팅을 할 수 있는 화면입니다.<br>
  ![녹화_2021_03_15_21_48_54_714](https://user-images.githubusercontent.com/72387870/111155831-5165fa80-85d8-11eb-955f-d52d25326dad.gif)

  * 구현 기능 설명
    - WebSocket을 이용하여 실시간으로 채팅을 할 수 있습니다.
    - 자신이 보낸 채팅은 오른쪽에, 자신이 아닌 회원이 보낸 채팅은 왼쪽에 출력됩니다.
    - 채팅 내용은 저장이 되므로 이전의 채팅 내역을 볼 수 있습니다.<br>
    ![녹화_2021_03_15_22_05_51_72](https://user-images.githubusercontent.com/72387870/111157806-a86ccf00-85da-11eb-81d4-e536cffea636.gif)
    
------------
    
<p align="center">
감사합니다😄
</p>
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTkyNjMyODUzLC04OTY4NDU3NDIsMTk0ND
E0OTM0NywtMTEzMzQ3MzIsLTE0NzgxMTYzMTksMTY1ODc2OTkz
OCw0MDU0MzY2NDgsMTUxMTE3MzYwOSw2NDkyNzQ2NjgsMTQ5ND
M2MTY4Ml19
-->