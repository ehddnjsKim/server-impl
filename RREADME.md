# 게시판 프로젝트 (Spring Boot + MyBatis)

> 회원 기능과 게시판 CRUD 기능이 포함된 웹 애플리케이션입니다.  
> Spring Boot 기반 RESTful API 방식으로 개발되었으며, MyBatis를 이용해 MySQL과 연동합니다.

# 기술 스택

- **Back-end**: Spring Boot, MyBatis, Java
- **Database**: MySQL
- **Front-end**: HTML + JavaScript (폼 방식)
- **Build Tool**: Maven (또는 Gradle)
- **인증 방식**: 세션 기반 로그인 인증

# 주요 기능

- 회원가입 및 로그인
- 로그인한 사용자만 게시글 작성/수정/삭제 가능
- 게시글 목록 조회 (최신순 + 페이징)
- 게시글 상세 보기
- 게시글 수정 및 삭제

# 프로젝트 구조
board/
├── src/
│ └── main/
│ ├── java/com/example/board/
│ │ ├── controller/
│ │ ├── model/
│ │ ├── mapper/
│ │ └── service/
│ └── resources/
│ ├── application.properties (.gitignore로 제외)
│ └── mapper/*.xml
├── static/ (HTML, JS 클라이언트 파일)
├── database.sql ✅ (모든 SQL 쿼리 포함)
├── .gitignore ✅
├── README.md ✅

# 실행 방법
# MySQL 데이터베이스 설정

- MySQL에서 DB 생성 후 `database.sql` 파일의 내용을 실행하여 테이블 생성 및 샘플 데이터 입력

```sql
CREATE DATABASE board;
USE board;
-- 아래 쿼리는 database.sql 파일 참조