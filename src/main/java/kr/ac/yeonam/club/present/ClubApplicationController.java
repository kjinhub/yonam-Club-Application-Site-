package kr.ac.yeonam.club.present;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import kr.ac.yeonam.club.application.ClubApplicationService;
import kr.ac.yeonam.club.present.ClubApplicationDto;

@RestController
@RequestMapping("/club/applications")
public class ClubApplicationController {
    private final ClubApplicationService clubApplicationService;

    // 생성자 주입
    public ClubApplicationController(ClubApplicationService clubApplicationService) {
        this.clubApplicationService = clubApplicationService;
    }

    // 1. 동아리 신청 등록 (POST)
    @PostMapping
    public ClubApplicationDto createApplication(@RequestBody ClubApplicationDto clubApplicationDto) {
        // 서비스 계층에 등록 요청, 등록된 신청을 반환
        return clubApplicationService.apply(clubApplicationDto);
    }

    // 2. 동아리 신청 목록 조회 (GET)
    @GetMapping
    public List<ClubApplicationDto> listApplications() {
        return clubApplicationService.findAll();
    }

    // 3. 동아리 신청 상세 조회 (GET)
    @GetMapping("/{id}")
    public ClubApplicationDto findApplicationById(@PathVariable Long id) {
        return clubApplicationService.findById(id);
    }

    // 4. 동아리 신청 취소 (DELETE)
    @DeleteMapping("/{id}")
    public void cancelApplication(@PathVariable Long id) {
        clubApplicationService.cancel(id);
    }
}
