# 🏷️ board-service

## ✅ Overview
- Apache Kafka를 이용한 이벤트 발행 및 소비(컨슈머) 패턴 적용
- user-service, point-service와 Kafka 토픽을 통해 비동기 이벤트 방식으로 연동
- 서비스 간 결합도를 낮추고 확장성을 고려한 이벤트 기반 마이크로서비스 설계
- API Gateway와 통신하여 클라이언트 요청을 받아 내부 서비스로 라우팅하고, 서비스 간 통신을 효율적으로 관리

## ✅ Features
- 게시글 작성
  - 포인트 서비스의 외부 API 호출로 포인트 차감
  - 게시글 저장
  - 게시글 작성 완료 이벤트를 생성하여 Kafka 토픽으로 전송 → 사용자 서비스의 활동 점수 적립
  - 중간 실패 시 게시글 삭제 및 포인트 롤백 처리

- 게시글 단건 조회
  - 게시글과 연관된 사용자 정보 함께 노출

- 게시글 목록 조회
  - 게시글 목록과 연관된 사용자 정보 함께 노출

## ✅ Tech Stack
<div align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />&nbsp
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Spring_Cloud_Gateway-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white" />&nbsp
  <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" />&nbsp
</div>

## ✅ etc
<img width="557" height="239" alt="Image" src="https://github.com/user-attachments/assets/d9924386-8d59-4e4e-93db-5b924c0ca251" />

<img width="703" height="482" alt="Image" src="https://github.com/user-attachments/assets/ccdad428-4237-4ebb-bce4-060bc6b9e649" />



