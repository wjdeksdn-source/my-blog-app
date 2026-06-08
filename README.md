# 📝 My Blog - DevOps Portfolio Project

> Spring Boot + Kubernetes + GitOps 기반 개인 블로그 서비스

## 📌 프로젝트 개요

로컬 환경(VMware)에서 k3s 클러스터를 직접 구축하고,  
GitHub Actions와 ArgoCD를 활용한 GitOps CI/CD 파이프라인을 구현한 포트폴리오 프로젝트입니다.

## 🛠 기술 스택

| 분류 | 기술 |
|------|------|
| Backend | Java 17, Spring Boot 3.5, Spring Security, JPA |
| Database | PostgreSQL 14 |
| Container | Docker, DockerHub |
| Orchestration | Kubernetes (k3s) |
| Package Manager | Helm Chart |
| CI | GitHub Actions |
| CD / GitOps | ArgoCD |
| Auth | JWT |
| Infra | VMware Workstation (로컬 클러스터) |

## 🏗 아키텍처

```
개발자 (코드 작성)
       ↓ git push
GitHub (app 레포)
       ↓ 자동 실행
GitHub Actions (CI)
→ Spring Boot 빌드
→ Docker 이미지 생성
→ DockerHub push
→ infra 레포 이미지 태그 자동 업데이트
       ↓ 변경 감지
ArgoCD (CD)
→ infra 레포 기반 자동 배포
       ↓
k3s 클러스터 (VMware 로컬)
├── control-plane (마스터 + ArgoCD)
├── worker-1 (앱 파드 실행)
└── db-server (PostgreSQL)
```

## 📁 레포지토리 구조

```
my-blog-app/
├── src/
│   └── main/
│       ├── java/com/myblog/app/
│       │   ├── controller/   # API 엔드포인트
│       │   ├── entity/       # DB 테이블 매핑
│       │   ├── repository/   # DB 조회
│       │   ├── service/      # 비즈니스 로직
│       │   ├── security/     # JWT 인증
│       │   └── dto/          # 요청/응답 객체
│       └── resources/
│           └── static/       # 프론트엔드 HTML
├── Dockerfile
└── .github/workflows/ci.yml

my-blog-infra/
└── helm/my-blog/
    ├── Chart.yaml
    ├── values.yaml
    └── templates/
```

## 🚀 CI/CD 파이프라인

### CI (GitHub Actions)
1. main 브랜치 push 감지
2. Gradle로 Spring Boot 빌드
3. Docker 이미지 빌드 & DockerHub push
4. infra 레포 values.yaml 이미지 태그 자동 업데이트

### CD (ArgoCD - GitOps)
1. infra 레포 변경 자동 감지 (3분 주기)
2. Helm Chart 기반 k8s 배포
3. 롤링 업데이트로 무중단 배포

## 🗄 데이터베이스 설계

| 테이블 | 설명 |
|--------|------|
| users | 회원 정보 (이메일, 비밀번호, 닉네임) |
| posts | 게시글 (제목, 내용, 조회수) |
| comments | 댓글 |
| tags | 태그 |
| post_tags | 게시글-태그 연결 (다대다) |

## ✅ 구현 기능

- [x] JWT 기반 회원가입 / 로그인
- [x] 게시글 작성 / 조회 / 삭제
- [x] 로그인 사용자만 게시글 작성 가능
- [x] GitHub Actions 자동 빌드 & 이미지 push
- [x] ArgoCD GitOps 자동 배포
- [x] Helm Chart 기반 배포 관리
- [x] PostgreSQL 데이터 영속성

## 🖥 로컬 클러스터 구성

| 서버 | IP | 역할 |
|------|-----|------|
| control-plane | 192.168.238.10 | k3s 마스터 + ArgoCD |
| worker-1 | 192.168.238.11 | 앱 파드 실행 |
| db-server | 192.168.238.12 | PostgreSQL |
