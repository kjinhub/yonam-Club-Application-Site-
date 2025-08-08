package kr.ac.yeonam.club.application;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.ac.yeonam.club.domain.ClubApplication;
import kr.ac.yeonam.club.domain.QuestionAnswer;
import kr.ac.yeonam.club.domain.ClubStatus;
import kr.ac.yeonam.club.infrastructure.ClubApplicationRepository;
import kr.ac.yeonam.club.present.ClubApplicationDto;
import kr.ac.yeonam.club.present.QuestionAnswerDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClubApplicationService {

    private final ClubApplicationRepository clubApplicationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClubApplicationService(
            ClubApplicationRepository clubApplicationRepository,
            ModelMapper modelMapper
    ) {
        this.clubApplicationRepository = clubApplicationRepository;
        this.modelMapper = modelMapper;
    }

    // 동아리 신청 등록 (Create)
    public ClubApplicationDto apply(ClubApplicationDto clubApplicationDto) {
        // DTO → Entity 변환
        ClubApplication clubApplication = modelMapper.map(clubApplicationDto, ClubApplication.class);

        // questionAnswers 수동 매핑 (ModelMapper가 자동 매핑 못할 때)
        if (clubApplicationDto.getQuestionAnswers() != null) {
            List<QuestionAnswer> answers = clubApplicationDto.getQuestionAnswers().stream()
                .map(dto -> new QuestionAnswer(dto.getQuestion(), dto.getAnswer()))
                .collect(Collectors.toList());
            clubApplication.setQuestionAnswers(answers);
        }

        // 상태 세팅
        clubApplication.setStatus(ClubStatus.PENDING);

        // 저장
        ClubApplication saved = clubApplicationRepository.save(clubApplication);

        // Entity → DTO 변환 (questionAnswers 포함)
        ClubApplicationDto savedDto = modelMapper.map(saved, ClubApplicationDto.class);
        if (saved.getQuestionAnswers() != null) {
            List<QuestionAnswerDto> answerDtos = saved.getQuestionAnswers().stream()
                .map(q -> new QuestionAnswerDto(q.getQuestion(), q.getAnswer()))
                .collect(Collectors.toList());
            savedDto.setQuestionAnswers(answerDtos);
        }

        return savedDto;
    }

    // 신청 목록 전체 조회 (Read)
    public List<ClubApplicationDto> findAll() {
        List<ClubApplication> entities = clubApplicationRepository.findAll();
        return entities.stream()
            .map(entity -> {
                ClubApplicationDto dto = modelMapper.map(entity, ClubApplicationDto.class);
                if (entity.getQuestionAnswers() != null) {
                    List<QuestionAnswerDto> answerDtos = entity.getQuestionAnswers().stream()
                        .map(q -> new QuestionAnswerDto(q.getQuestion(), q.getAnswer()))
                        .collect(Collectors.toList());
                    dto.setQuestionAnswers(answerDtos);
                }
                return dto;
            })
            .collect(Collectors.toList());
    }

    // 신청 상세 조회 (Read)
    public ClubApplicationDto findById(Long id) {
        ClubApplication entity = clubApplicationRepository.findById(id);
        if (entity == null) return null; // or throw exception
        ClubApplicationDto dto = modelMapper.map(entity, ClubApplicationDto.class);
        if (entity.getQuestionAnswers() != null) {
            List<QuestionAnswerDto> answerDtos = entity.getQuestionAnswers().stream()
                .map(q -> new QuestionAnswerDto(q.getQuestion(), q.getAnswer()))
                .collect(Collectors.toList());
            dto.setQuestionAnswers(answerDtos);
        }
        return dto;
    }

    // 신청 취소 (Delete)
    public void cancel(Long id) {
        clubApplicationRepository.deleteById(id);
    }

    // 신청 정보 수정 (Update)
    public ClubApplicationDto update(ClubApplicationDto clubApplicationDto) {
        ClubApplication clubApplication = modelMapper.map(clubApplicationDto, ClubApplication.class);

        // questionAnswers 수동 매핑
        if (clubApplicationDto.getQuestionAnswers() != null) {
            List<QuestionAnswer> answers = clubApplicationDto.getQuestionAnswers().stream()
                .map(dto -> new QuestionAnswer(dto.getQuestion(), dto.getAnswer()))
                .collect(Collectors.toList());
            clubApplication.setQuestionAnswers(answers);
        }

        ClubApplication updated = clubApplicationRepository.update(clubApplication);

        ClubApplicationDto updatedDto = modelMapper.map(updated, ClubApplicationDto.class);
        if (updated.getQuestionAnswers() != null) {
            List<QuestionAnswerDto> answerDtos = updated.getQuestionAnswers().stream()
                .map(q -> new QuestionAnswerDto(q.getQuestion(), q.getAnswer()))
                .collect(Collectors.toList());
            updatedDto.setQuestionAnswers(answerDtos);
        }

        return updatedDto;
    }
 // ClubApplicationService.java

    public ClubApplicationDto partialUpdate(Long id, Map<String, Object> updates) {
        ClubApplication application = clubApplicationRepository.findById(id);
        if (application == null) throw new RuntimeException("Not found");

        // 상태 변경
        if (updates.containsKey("status")) {
            String status = (String) updates.get("status");
            application.setStatus(ClubStatus.valueOf(status));
        }

        // 질문-답변 변경
        if (updates.containsKey("questionAnswers")) {
            // 값 추출 & 변환
            List<Map<String, String>> qaList = (List<Map<String, String>>) updates.get("questionAnswers");
            List<QuestionAnswer> newQA = qaList.stream()
                .map(m -> new QuestionAnswer(m.get("question"), m.get("answer")))
                .collect(Collectors.toList());
            application.setQuestionAnswers(newQA);
        }

        // 저장
        clubApplicationRepository.save(application);
        // DTO로 변환하여 반환
        return modelMapper.map(application, ClubApplicationDto.class);
    }

}
