package kr.ac.yeonam.club.infrastructure;

import java.util.*;
import org.springframework.stereotype.Repository;
import kr.ac.yeonam.club.domain.ClubApplication;
import kr.ac.yeonam.club.domain.ClubStatus;

// Repository 계층: 동아리 신청 데이터를 실제로 저장, 읽기, 삭제
@Repository
public class ClubApplicationRepository {
    // 간단히 메모리 리스트로 구현(실제로는 DB 사용)
    private final Map<Long, ClubApplication> storage = new HashMap<>();
    private Long idSequence = 1L; // ID 자동 증가용

    // 저장 (신청 등록)
    public ClubApplication save(ClubApplication application) {
        if (application.getId() == null) {
            application.setId(idSequence++);
        }
        // 신규 신청 상태는 기본적으로 PENDING
        if (application.getStatus() == null) {
            application.setStatus(ClubStatus.PENDING);
        }
        storage.put(application.getId(), application);
        return application;
    }

    // 전체 목록 조회
    public List<ClubApplication> findAll() {
        return new ArrayList<>(storage.values());
    }

    // 단건 조회
    public ClubApplication findById(Long id) {
        return storage.get(id);
    }

    // 삭제(취소)
    public void deleteById(Long id) {
        storage.remove(id);
    }

    // (선택) 업데이트 예시
    public ClubApplication update(ClubApplication application) {
        storage.put(application.getId(), application);
        return application;
    }
}
