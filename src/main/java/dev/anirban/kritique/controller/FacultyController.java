package dev.anirban.kritique.controller;

import dev.anirban.kritique.dto.CustomResponse;
import dev.anirban.kritique.entity.Faculty;
import dev.anirban.kritique.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService service;

    @PostMapping("/faculties")
    public CustomResponse<Faculty> createFacultyHandler(@RequestBody Faculty faculty) {
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Faculty Created Successfully",
                service.createFaculty(faculty)
        );
    }

    @GetMapping("/faculties")
    public CustomResponse<List<Faculty>> findAllFacultiesHandler() {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Faculties fetched successfully",
                service.findAllFaculty()
        );
    }

    @GetMapping("/faculties/{id}")
    public CustomResponse<Faculty> findFacultyByIdHandler(@PathVariable Integer id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Faculty Details Fetched Successfully",
                service.findFacultyById(id)
        );
    }

    @DeleteMapping("/faculties/{id}")
    public CustomResponse<Void> deleteFacultyByIdHandler(@PathVariable Integer id) {
        service.deleteFacultyById(id);
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Faculty Deleted Successfully",
                null
        );
    }
}
