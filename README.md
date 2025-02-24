# CusCom

조립식 컴퓨터 부품 정보 제공 및 호환 여부 확인 서비스

## 개발 환경

- Springboot, Spring JPA, Spring Security
- Kotlin
- Mysql, MongoDB
- Azure Blob

## 주요 기능

### PC 견적 시뮬레이팅
|Screen #1|
|:---:|
|<img src="https://github.com/Kan9hee/CusCom/blob/main/CusCom_estimatePage.PNG"/>|
- 부품 정보 제공
    - 케이스, 쿨러, CPU, 저장장치, 그래픽카드, 메모리, 마더보드, 파워서플라이
    - 부품의 상세 정보 및 이미지 확인 가능
        - imgscalr를 통한 부품 이미지 리사이징
        - Azure Blob을 통한 이미지 데이터 저장 및 관리
- 견적 호환성 확인
    - 전력 소비, 쿨러 높이, 그래픽카드 길이, 파워서플라이 사이즈
    - 견적에 포함시킨 부품간의 적합성을 시각적으로 표시

### 견적 공유 커뮤니티
|Screen #2|Screen #3|
|:---:|:---:|
|<img src="https://github.com/Kan9hee/CusCom/blob/main/CusCom_searchPage.PNG" width="400"/>|<img src="https://github.com/Kan9hee/CusCom/blob/main/CusCom_postPage.PNG" width="400"/>|
- 사용자가 등록한 견적 게시글 CRUD
    - 견적 게시글 검색(제목, 태그, 작성자, 부품명)
    - 견적 게시글 조회수 카운트
    - 견적 게시글 댓글
        - 조회 및 삽입 기능만을 사용하므로 MongoDB를 이용
- 사용자 정보
    - 회원가입, 로그인, 로그아웃, 견적 게시 및 편집, 삭제
    - 견적 게시, 편집, 삭제

## 참고자료
https://quasarzone.com/comparison/cpu
