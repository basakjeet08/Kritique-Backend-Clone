package dev.anirban.kritique.controller;

import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.CustomResponse;
import dev.anirban.kritique.dto.faculty.FacultyDTO;
import dev.anirban.kritique.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService service;

    @PostMapping(UrlConstants.CREATE_FACULTY)
    public CustomResponse<FacultyDTO> createFacultyHandler(@RequestBody FacultyDTO faculty) {
        return new CustomResponse<>(
                HttpStatus.CREATED.value(),
                "Faculty Created Successfully",
                service.createFaculty(faculty)
        );
    }

    @GetMapping(UrlConstants.FIND_AND_FILTER_ALL_FACULTY)
    public CustomResponse<List<FacultyDTO>> findAllFacultiesHandler(
            @RequestParam(value = "name", required = false) String name
    ) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Faculty Fetched Successfully",
                (name != null && !name.isEmpty()) ? service.findFacultyByName(name) : service.findAllFaculty()
        );
    }

    @GetMapping(UrlConstants.FIND_FACULTY_BY_ID)
    public CustomResponse<FacultyDTO> findFacultyByIdHandler(@PathVariable String id) {
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Faculty Details Fetched Successfully",
                service.findFacultyById(id)
        );
    }

    @DeleteMapping(UrlConstants.DELETE_FACULTY)
    public CustomResponse<Void> deleteFacultyByIdHandler(@PathVariable String id) {
        service.deleteFacultyById(id);
        return new CustomResponse<>(
                HttpStatus.OK.value(),
                "Faculty Deleted Successfully",
                null
        );
    }
}
