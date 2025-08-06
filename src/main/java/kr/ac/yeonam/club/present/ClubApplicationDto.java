package kr.ac.yeonam.club.present;

public class ClubApplicationDto {
    private Long id;              // 신청 고유번호
    private String applicantName; // 신청자 이름
    private String clubName;      // 동아리 이름
    private String status;        // 신청 상태 (심사중/심사완료/합격/불합격 등)
    private String appliedDate;   // 신청 날짜 (yyyy-MM-dd 형태 등)

    // 기본 생성자
    public ClubApplicationDto() {}

    // 모든 필드 생성자 (선택)
    public ClubApplicationDto(Long id, String applicantName, String clubName, String status, String appliedDate) {
        this.id = id;
        this.applicantName = applicantName;
        this.clubName = clubName;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppliedDate() {
        return appliedDate;
    }
    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

    // toString() (디버깅/로깅용)
    @Override
    public String toString() {
        return "ClubApplicationDto{" +
                "id=" + id +
                ", applicantName='" + applicantName + '\'' +
                ", clubName='" + clubName + '\'' +
                ", status='" + status + '\'' +
                ", appliedDate='" + appliedDate + '\'' +
                '}';
    }
}
