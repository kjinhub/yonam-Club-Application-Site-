package kr.ac.yeonam.club.present;

import java.time.LocalDate;
import java.util.List;

// DTO 패키지에 별도로 QuestionAnswerDto가 있다고 가정
public class ClubApplicationDto {
    private Long id;
    private String applicantName;
    private String clubName;
    private LocalDate appliedDate;
    private List<QuestionAnswerDto> questionAnswers;  // 수정: DTO 타입으로 변경!
    private String status; // Enum값을 String으로 관리 (또는 ClubStatus로 변경 가능)

    public ClubApplicationDto() {}

    // 모든 필드 생성자
    public ClubApplicationDto(Long id, String applicantName, String clubName, String status, LocalDate appliedDate, 
    		List<QuestionAnswerDto> questionAnswers) {
    	
        this.id = id;
        this.applicantName = applicantName;
        this.clubName = clubName;
        this.status = status;
        this.appliedDate = appliedDate;
        this.questionAnswers = questionAnswers;
    }

    // getter, setter

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }

    public List<QuestionAnswerDto> getQuestionAnswers() { return questionAnswers; }
    public void setQuestionAnswers(List<QuestionAnswerDto> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // toString() (디버깅/로깅용)
    @Override
    public String toString() {
        return "ClubApplicationDto{" +
                "id=" + id +
                ", applicantName='" + applicantName + '\'' +
                ", clubName='" + clubName + '\'' +
                ", status='" + status + '\'' +
                ", appliedDate='" + appliedDate + '\'' +
                ", questionAnswers=" + questionAnswers +
                '}';
    }
}
