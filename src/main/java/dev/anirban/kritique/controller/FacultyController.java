package dev.anirban.kritique.controller;

import dev.anirban.kritique.constants.NetworkStatusCodes;
import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.common.CustomResponse;
import dev.anirban.kritique.dto.common.EmptyObject;
import dev.anirban.kritique.dto.faculty.FacultyDTO;
import dev.anirban.kritique.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService service;

    @PostMapping(UrlConstants.CREATE_FACULTY)
    public CustomResponse<FacultyDTO> createFacultyHandler(@RequestBody FacultyDTO faculty) {
        return new CustomResponse<>(
                NetworkStatusCodes.CREATED,
                "Faculty Created Successfully",
                service.createFaculty(faculty)
        );
    }

    @GetMapping(UrlConstants.FIND_ALL_FACULTIES)
    public CustomResponse<List<FacultyDTO>> findAllFacultiesHandler(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "Faculty Fetched Successfully",
                service.findAllFaculty(PageRequest.of(page, limit))
        );
    }

    @GetMapping(UrlConstants.FIND_ALL_FACULTY_BY_NAME)
    public CustomResponse<List<FacultyDTO>> findAllFacultiesByNameHandler(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "Faculty Fetched Successfully",
                service.findFacultyByName(name, PageRequest.of(page, limit))
        );
    }

    @GetMapping(UrlConstants.FIND_FACULTY_BY_ID)
    public CustomResponse<FacultyDTO> findFacultyByIdHandler(@PathVariable String id) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "Faculty Details Fetched Successfully",
                service.findFacultyById(id)
        );
    }

    @DeleteMapping(UrlConstants.DELETE_FACULTY)
    public CustomResponse<EmptyObject> deleteFacultyByIdHandler(@PathVariable String id) {
        service.deleteFacultyById(id);
        return new CustomResponse<>(
                NetworkStatusCodes.DELETED,
                "Faculty Deleted Successfully",
                new EmptyObject()
        );
    }
}
