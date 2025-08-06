package kr.ac.yeonam.club.domain;

// 신청 상태를 Enum으로 정의: 안전하고 가독성 좋은 코드가 됨
public enum ClubStatus {
    PENDING,    // 심사중
    REVIEWED,   // 심사완료
    ACCEPTED,   // 합격
    REJECTED    // 불합격
}
