package kr.ac.yeonam.club.domain;

// JPA import 삭제!
import java.time.LocalDate;
import java.util.List;

public class ClubApplication {

    // JPA 어노테이션 삭제!
    private Long id;

    private String applicantName;
    private String clubName;
    private LocalDate appliedDate;

    // 어노테이션 삭제, 필드만 남김
    private List<QuestionAnswer> questionAnswers;

    // Enum 어노테이션 삭제
    private ClubStatus status;

    public ClubApplication() {}

    // 기타 생성자, getter, setter...
    // (DTO에서 변환시 모든 필드 포함)

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }
    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }

    public List<QuestionAnswer> getQuestionAnswers() { return questionAnswers; }
    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) { this.questionAnswers = questionAnswers; }

    public ClubStatus getStatus() { return status; }
    public void setStatus(ClubStatus status) { this.status = status; }
}
