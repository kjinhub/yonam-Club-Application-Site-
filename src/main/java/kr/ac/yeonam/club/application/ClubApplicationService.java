package kr.ac.yeonam.club.application;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.ac.yeonam.club.domain.ClubApplication;
import kr.ac.yeonam.club.domain.ClubStatus;
import kr.ac.yeonam.club.infrastructure.ClubApplicationRepository;
import kr.ac.yeonam.club.present.ClubApplicationDto;

import java.util.List;
import java.util.stream.Collectors;

@Service // 이 클래스가 스프링의 Service Bean으로 등록됨
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
        // 상태는 Enum 값으로 반드시 세팅
        clubApplication.setStatus(ClubStatus.PENDING);
        // 저장
        ClubApplication saved = clubApplicationRepository.save(clubApplication);
        // Entity → DTO 변환 후 반환
        return modelMapper.map(saved, ClubApplicationDto.class);
    }

    // 신청 목록 전체 조회 (Read)
    public List<ClubApplicationDto> findAll() {
        List<ClubApplication> entities = clubApplicationRepository.findAll();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, ClubApplicationDto.class))
                .collect(Collectors.toList());
    }

    // 신청 상세 조회 (Read)
    public ClubApplicationDto findById(Long id) {
        ClubApplication entity = clubApplicationRepository.findById(id);
        if (entity == null) return null; // or throw exception
        return modelMapper.map(entity, ClubApplicationDto.class);
    }

    // 신청 취소 (Delete)
    public void cancel(Long id) {
        clubApplicationRepository.deleteById(id);
    }

    // 신청 정보 수정 (Update, 선택)
    public ClubApplicationDto update(ClubApplicationDto clubApplicationDto) {
        ClubApplication clubApplication = modelMapper.map(clubApplicationDto, ClubApplication.class);
        ClubApplication updated = clubApplicationRepository.update(clubApplication);
        return modelMapper.map(updated, ClubApplicationDto.class);
    }
}
