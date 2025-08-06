package kr.ac.yeonam.club.domain;

// 동아리 신청 내역(엔티티)을 표현하는 클래스
public class ClubApplication {
    private Long id;                // 신청 고유번호
    private String applicantName;   // 신청자 이름
    private String clubName;        // 지원한 동아리명
    private ClubStatus status;      // 신청 상태(심사중, 합격, 불합격 등)
    private String appliedDate;     // 신청일자 (String 또는 LocalDate)

    public ClubApplication() {}

    public ClubApplication(Long id, String applicantName, String clubName, ClubStatus status, String appliedDate) {
        this.id = id;
        this.applicantName = applicantName;
        this.clubName = clubName;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    // Getter/Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public ClubStatus getStatus() { return status; }
    public void setStatus(ClubStatus status) { this.status = status; }

    public String getAppliedDate() { return appliedDate; }
    public void setAppliedDate(String appliedDate) { this.appliedDate = appliedDate; }
}
