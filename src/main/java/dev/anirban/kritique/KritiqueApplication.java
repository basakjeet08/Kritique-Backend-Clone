package dev.anirban.kritique;

import dev.anirban.kritique.dto.faculty.FacultyDTO;
import dev.anirban.kritique.service.FacultyService;
import dev.anirban.kritique.service.RandomNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class KritiqueApplication {

    private final FacultyService service;
    private final RandomNameGenerator randomNameGenerator;

    public static void main(String[] args) {
        SpringApplication.run(KritiqueApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return _ -> {
            for (int i = 0; i < 50; i++) {
                FacultyDTO facultyDTO = FacultyDTO
                        .builder()
                        .name(randomNameGenerator.generateRandomName())
                        .experience(0.0)
                        .photoUrl("")
                        .avgRating(0.0)
                        .totalRatings(0)
                        .build();

                service.createFaculty(facultyDTO);
            }
        };
    }
}