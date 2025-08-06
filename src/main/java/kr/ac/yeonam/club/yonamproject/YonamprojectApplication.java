package kr.ac.yeonam.club.yonamproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 이 클래스는 스프링부트 프로젝트의 "시작점"
// @SpringBootApplication: 컴포넌트 스캔, 자동 설정, 빈 등록 등 핵심 설정을 담당
@SpringBootApplication(scanBasePackages = "kr.ac.yeonam.club")
public class YonamprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(YonamprojectApplication.class, args);
    }
}
