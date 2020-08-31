# SistAirlines-Project
[쌍용강북교육센터] 2차 프로젝트 : Sist Airlines (프로젝트 기간 : 2020.01 - 2020.03)
  
JSP를 활용한 항공권 예매 시스템


# Sist Airlines

### 0. 목차
+ 프로젝트명 / 팀명
+ 팀원 / 팀장
+ 담당업무
+ 개발환경
+ 개발아키텍쳐 : MVC
+ 프로젝트 Work Flow
+ 프로젝트 화면 구성
+ 요구사항정의서
+ 기본설계서
+ 시연 동영상
  
### 1. 프로젝트명 / 팀명
    - Sist Airlines / Sist Airlines
   
    
### 2. 팀원 / 팀장
    - 팀장 : 정진우
    - 팀원 : 김민지(본인), 김민지, 홍승민, 박성연   
    
### 2.1. 담당업무
    - 1) 메인 페이지 / 항공권 스케줄 조회 : 김민지(본인)
        - 1.1) 왕복 / 편도 : 선택에 따라 화면 구성이 바뀐다.
        - 1.2) 출발지 / 도착지 : 선택한 도시에 인천이 포함되어 있지 않을 경우, 경유하는 스케쥴을 출력한다.
        - 1.3) 출발 날짜 / 도착 날짜 : 
                  - 출발 날짜 선택 시, 도착 날짜는 출발 날짜 이전의 날짜를 선택하지 못한다.
                  - 실제 시각(현재 시각) 이전의 날짜는 선택하지 못한다.
        - 1.4) 성인 / 소아 / 유아 : 탑승할 승객 수 선택
                  - 0보다 작은 수로 선택하지 못한다.
        - 1.5) 항공권 스케줄 조회 : 메인페이지에서 선택한 조건들에 부합하는 항공권 스케줄을 출력. (편도 선택시 출발 스케줄만 출력.)
                  - 인천을 포함하지 않는 왕복일 경우, 인천을 경유하는 스케줄을 조회한다.
                  - 인천을 경유할 경우, A 도시 출발 - 인천 도착 - 인천 출발 - B도시 도착의 스케줄을 출력한다.
                  - 이 경우, 인천 출발 시각이 인천 도착 시각보다 이전의 스케줄은 제외, 도착 시각과 비교하여 가장 빨리 출발하는 시각을 출력한다.
    
    - 2) 좌석 선택 / 탑승객 정보 입력 : 정진우
    - 3) 탑승객 정보 입력 / 결제 : 홍승민
    - 4) 로그인 / 예약 조회 : 김민지
    - 5) 회원가입 / 예약 조회 : 박성연
  
### 3. 개발환경
    - OS            :   Windows 10 pro
    - JDK           :   Java SE 8 (Oracle JDK 1.8)
    - CM            :   Github
    - Tools         :   Eclipse /SQLDeveloper
    - Server        :   Apache Tomcat 8.5
    - 라이브러리    :   java-json.jar / log4j-1.2.17.jar / ojdbc6.jar
    - Front-end     :   JavaScript / Jquery / JSP / Html5 / Bootstrap / CSS
    - Back-end      :   Java / Servlets / Oracle / JSP

### 4. 개발아키텍쳐 : MVC
![SistAirlines_MVC](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_MVC.png "SistAirlines_MVC.png")  

### 5. 프로젝트 Work Flow
![SistAirlines_WorkFlow](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_WorkFlow.PNG "SistAirlines_WorkFlow") 

### 6. 프로젝트 화면 구성
1. 메인 페이지 / 항공권 스케줄 조회 : 김민지(본인)
    + Main Page  
    
    ![SistAirlines_MainPage.png](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_MainPage.png "SistAirlines_MainPage.png")
    
    + Main Page 기능 설명
    
    ![SistAirlines_MainPage 기능설명.PNG](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_MainPage%20%EA%B8%B0%EB%8A%A5%EC%84%A4%EB%AA%85.PNG "SistAirlines_MainPage 기능설명.PNG")
    
    + 항공권 스케줄 조회 (왕복) 
    
    ![SistAirlines_Schedule 왕복.png](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_Schedule%20%EC%99%95%EB%B3%B5.png "SistAirlines_Schedule 왕복.png")
    
    + 항공권 스케줄 조회 (편도) 
    
    ![SistAirlines_Schedule 편도.png](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_Schedule%20%ED%8E%B8%EB%8F%84.png "SistAirlines_Schedule 편도.png")
    
    + 항공권 스케줄 조회 기능 설명 : 인천을 경유할 경우 인천에서 출발하는 시각이 인천에 도착하는 시각보다 이전일 수 없는 기능
    
    ![SistAirlines_Schedule 기능설명.PNG](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_Schedule%20%EA%B8%B0%EB%8A%A5%EC%84%A4%EB%AA%85.PNG "SistAirlines_Schedule 기능설명.PNG")
    
    
2. 좌석 선택 / 탑승객 정보 입력 / 결제 : 정진우, 홍승민
    + 좌석 선택 / 탑승객 정보 입력  
    
    ![SistAirlines_Information.png](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_Information.png "SistAirlines_Information.png")
    
    + 좌석 선택 / 탑승객 정보 입력 기능 설명
    
    ![SistAirlines_Information 기능설명.PNG](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_Information%20%EA%B8%B0%EB%8A%A5%EC%84%A4%EB%AA%85.PNG "SistAirlines_Information 기능설명.PNG")
    
3. 회원가입 / 로그인 / 예약 조회 : 박성연 / 김민지
    + 예약 조회
    
    ![SistAirlines_AdminPage 기능설명.PNG](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_AdminPage%20%EA%B8%B0%EB%8A%A5%EC%84%A4%EB%AA%85.PNG "SistAirlines_AdminPage 기능설명.PNG")

### 6. 요구사항정의서
![SistAirlines_요구사항정의서.png](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%EC%A0%95%EC%9D%98%EC%84%9C.png "SistAirlines_요구사항정의서.png")  

### 7. 기본설계서
![SistAirlines_기본설계서.png](https://github.com/MIN-04/SistAirlines-Project/blob/master/SistAirLine/doc/SistAirlines_%EA%B8%B0%EB%B3%B8%EC%84%A4%EA%B3%84%EC%84%9C.png "SistAirlines_기본설계서.png")  

### 8. 시연 동영상
[![Sist Airlines](https://i9.ytimg.com/vi/A1TNlvXQtGA/mq3.jpg?sqp=COTAs_oF&rs=AOn4CLDs9tFCOlxQ7OJsd-zAP5XMcRcw6Q)](https://youtu.be/A1TNlvXQtGA)

