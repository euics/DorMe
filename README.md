# :paperclip: 세종대학교 창의학기제
> 자바 ORM 표준 JPA 기반 행복기숙사 학사관 정보 취합 웹 서비스 개발 및 배포

![캡처](https://user-images.githubusercontent.com/103410386/214241170-726f8e20-4f64-49b7-a988-d88eeb5c05b9.PNG)

## 목차
- [들어가며](#들어가며)
  - [프로젝트 소개](#1-프로젝트-소개)    
  - [프로젝트 기능](#2-프로젝트-기능)    
  - [사용 기술](#3-사용-기술)   
     - [백엔드](#3-1-백엔드)
     - [프론트엔드](#3-2-프론트엔드)
  - [실행 화면](#4-실행-화면)   


- [구조 및 설계](#구조-및-설계)
  - [패키지 구조](#1-패키지-구조)
  - [DB 설계](#2-db-설계)

- [발표 영상](#발표-영상)

## 들어가며
### 1. 프로젝트 소개

지방학생들이 서울에 있는 학교에 올라올 때 주거 환경에 대한 고민이 존재한다.
<br>
DorMe 프로젝트는 학생들마다 갈 수 있는 학사관 정보 취합 웹 서비스를 만들어
<br>
행복기숙사/학사관을 선택하는데 있어 도움을 줄 수 있다.
<br><br>
참여인원: 박종훈, 변은성, 김부교, 이성빈

### 2. 프로젝트 기능

- **사용자 -** Security 회원가입 및 로그인, 회원가입시 유효성 검사 및 중복 검사
- **문의하기 -** Google 설문조사를 통한 문의하기 가능
- **리뷰게시판 -** CRUD 기능, 조회수, 페이징 및 검색 처리
- **지도 -** 카카오 API를 이용한 지도 구현, 서울 전체 기숙사 검색, 내가 갈 수 있는 기숙사 검색, 거리 구현
- **크롤링 -** 실시간 기숙사 정보
- **마이페이지 -** 나의 정보, 내가 쓴 게시물, 비밀번호 변경, 회원 탈퇴 구현

### 3. 사용 기술

#### 3-1 백엔드

##### 주요 프레임워크 / 라이브러리
- Java 11
- SpringBoot 2.7.3
- JPA(Spring Data JPA)
- Spring Security
- Spring Validation
- Jsoup
- AWS ec2

##### Build Tool
- Gradle

##### DataBase
- MySQL
- H2Database
- MariaDB

#### 3-2 프론트엔드
- Html/Css
- JavaScript
- Bootstrap
- KaKao API
- Thymeleaf


### 4. 실행 화면
  <details>
    <summary>로그인 회원가입 관련</summary>   
       
    
  **1. 회원가입 항목**   
  ![image](https://user-images.githubusercontent.com/103410386/214244218-30b47cc5-bf65-4a11-8398-3a68604ec914.png)
  <br>
  ID/PASSWORD/NickName/Local/University 항목에 대해 입력
     
  **1-1. 회원가입 Validation**   
  ![image](https://user-images.githubusercontent.com/103410386/214244503-5e5643fb-b449-4f44-81a1-06fbdfd5938f.png)
  <br>
  회원 중복 관리 및 비밀번호 조건에 대한 항목 유효성 검사
  
  **2. 로그인**   
  ![image](https://user-images.githubusercontent.com/103410386/214244897-36267985-2089-46b0-aafb-7e1951cb130b.png)
  
  **2.-1 로그인 Validation**   
  ![image](https://user-images.githubusercontent.com/103410386/214245103-c97412a3-73c8-47fb-ac60-59984ca49ef4.png)
  
     
  </details>
  <br/>   
  
 
  
   <details>
    <summary>게시판 관련</summary>   
       
  **1. 게시글 전체 목록**   
  ![image](https://user-images.githubusercontent.com/103410386/214245778-ae61d66b-ec78-4758-9988-9cc96a5ff87c.png)
  <br>
  전체 목록을 페이징 처리하여 조회
  
  **2. 게시글 등록**   
  ![image](https://user-images.githubusercontent.com/103410386/214246166-678c640a-3f01-435c-805d-2f2f49b86926.png)
  <br>
  로그인 한 사용자만 새로운 글을 작성할 수 있고, 작성 후 목록 화면으로 redirect
  
  **3. 게시글 상세보기**   
  ![image](https://user-images.githubusercontent.com/103410386/214246365-90846ef7-11ed-42d4-9a19-c0e091f67331.png)
  <br>
  본인이 작성한 글만 수정 및 삭제 가능
           
  </details>
  <br/>   
 
   <details>
    <summary>마이페이지 관련</summary>   
       
  **1. 마이페이지 목록**   
  ![image](https://user-images.githubusercontent.com/103410386/214247638-eb142b79-ab8b-4118-ac9e-0885d386f68e.png)
  <br>
  내 정보, 내가 쓴 게시물, 비밀번호 변경, 회원 탈퇴 기능
           
  </details>
  <br/>
  
  <details>
    <summary>지도 관련</summary>   
       
  **1. 전체 행복기숙사/학사관 검색**   
  ![image](https://user-images.githubusercontent.com/103410386/214248648-78c43990-c7b3-4988-88f0-9ef8becbc002.png)
  <br>
  비로그인시에도 가능한 서울시 전체 행복기숙사/학사관 위치 정보
  
  **2. 내가 갈 수 있는 행복기숙사/학사관 검색**   
  ![image](https://user-images.githubusercontent.com/103410386/214248994-727de7d9-ee7b-420b-a921-d9f3c2a6ccd1.png)
  <br>
  지도 샘플을 위해 본가가 강원도인 세종대학교 학생 기준인 지도
  
  **2. 행복기숙사/학사관 실시간 크롤링 정보**   
  ![image](https://user-images.githubusercontent.com/103410386/214249597-86d548a8-32ab-4bbd-9e66-48063dec9a5b.png)
  ![image](https://user-images.githubusercontent.com/103410386/214249691-ade9e955-73d4-4dc0-97c8-0bcc92d7cd86.png)
  ![image](https://user-images.githubusercontent.com/103410386/214249731-c62bf07f-9121-468d-937c-62fb7cd274d4.png)
  ![image](https://user-images.githubusercontent.com/103410386/214249799-ebb8c204-0cf9-4e8f-92a4-7ffc1ecf6293.png)
  ![image](https://user-images.githubusercontent.com/103410386/214249914-818f5d64-6888-4746-80d7-296398853250.png)
  <br>
  웹 서버를 실행 했을 때 기숙사 정보들을 크롤링 해온 후 DB에 저장
  <br>
  그 이후 18시 기준으로 새로 크롤링을 해온 후 DB에 정보 교체
  
  **3. 지도 거리 구현**   
  ![image](https://user-images.githubusercontent.com/103410386/214255662-b988d3cd-8a6a-43f8-a382-4cbcbbed69a7.png)
           
  </details>
  <br/>
   
## 구조 및 설계   
   
### 1. 패키지 구조
   
<details>
  
<summary>패키지 구조 보기</summary>   
 

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂sejong
 ┃ ┃ ┃ ┗ 📂dormitory
 ┃ ┃ ┃ ┃ ┗ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuditConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuditorAwareImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂constant
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Role.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dormitoryPageController
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChongNamController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GangwonDobongController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GangwonGwanakController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SejongDormitoryController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TamLaController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardCommentController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HomeController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MapController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MypageController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardCommentDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MmeberFormDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PhotoDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PhotoResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dormitoryPage
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChongNam.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GangwonDobong.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GangwonGwankak.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GyeonggiDo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SejongDormitory.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TamLa.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseEntity.abstract
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseTimeEntity.abstract
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Board.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardComment.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Photo.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dormitoryPage
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChongNamRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GangwonDobongRepoisitory.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GangwonGwankakRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GyeonggiDoRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SejongDormitoryRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TamLaRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardCommentRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PhotoRepository.interface
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┣ 📜Dormi_1.css
 ┃ ┃ ┃ ┃ ┣ 📜entireMap.css
 ┃ ┃ ┃ ┃ ┣ 📜LoginForm.css
 ┃ ┃ ┃ ┃ ┣ 📜loginMap.css
 ┃ ┃ ┃ ┃ ┣ 📜MainHome.css
 ┃ ┃ ┃ ┃ ┣ 📜map.css
 ┃ ┃ ┃ ┃ ┣ 📜memberRegister.css
 ┃ ┃ ┃ ┃ ┣ 📜Mypage.css
 ┃ ┃ ┃ ┃ ┣ 📜mavbar.css
 ┃ ┃ ┃ ┃ ┣ 📜postList.css
 ┃ ┃ ┃ ┃ ┗ 📜RegisterForm.css
 ┃ ┃ ┃ ┣ 📂img
 ┃ ┃ ┃ ┃ ┣ 📜about_1_dollar.svg
 ┃ ┃ ┃ ┃ ┣ 📜about_2_dollar.svg
 ┃ ┃ ┃ ┃ ┣ 📜about_3_dollar.svg
 ┃ ┃ ┃ ┃ ┣ 📜favicon.png
 ┃ ┃ ┃ ┃ ┣ 📜favicon_img.png
 ┃ ┃ ┃ ┃ ┣ 📜main_bg.jpg
 ┃ ┃ ┃ ┃ ┗ 📜naver.svg
 ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┣ 📜Dormi_1.js
 ┃ ┃ ┃ ┃ ┣ 📜map.js
 ┃ ┃ ┃ ┃ ┣ 📜modal.js
 ┃ ┃ ┃ ┃ ┗ 📜postList.js
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┣ 📜postCreateForm.html
 ┃ ┃ ┃ ┃ ┣ 📜postList.html
 ┃ ┃ ┃ ┃ ┗ 📜viewPost.html
 ┃ ┃ ┃ ┣ 📂dormitoryPage
 ┃ ┃ ┃ ┃ ┣ 📜chongnam.html
 ┃ ┃ ┃ ┃ ┣ 📜gangwonDobong.html
 ┃ ┃ ┃ ┃ ┣ 📜gangwonGwanak.html
 ┃ ┃ ┃ ┃ ┣ 📜gyeonggiDo.html
 ┃ ┃ ┃ ┃ ┣ 📜sejongDormitory.html
 ┃ ┃ ┃ ┃ ┗ 📜tamLa.html
 ┃ ┃ ┃ ┣ 📂map
 ┃ ┃ ┃ ┃ ┣ 📜entireMap.html
 ┃ ┃ ┃ ┃ ┗ 📜loginMap.html
 ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┣ 📜memberLoginForm.html
 ┃ ┃ ┃ ┃ ┗ 📜memberRegister.html
 ┃ ┃ ┃ ┣ 📂mypage
 ┃ ┃ ┃ ┃ ┣ 📜footer.html
 ┃ ┃ ┃ ┃ ┣ 📜MainHome.html
 ┃ ┃ ┃ ┃ ┗ 📜navbar.html
 ┃ ┃ ┣ 📜application-oauth.properties
 ┃ ┃ ┗ 📜application.properties
 ```
  
 </details>   
 <br/>    
   
     
 ### 2. DB 설계

![image](https://user-images.githubusercontent.com/103410386/214255339-939880e7-5dfe-4cb7-9a97-878fddad8dff.png)
   
<br/>

## 발표 영상
### 1. 시연
https://youtu.be/5hLvlM2dKLE
<br>
### 2. 베포
https://youtu.be/-c9Ni6BiGDQ
