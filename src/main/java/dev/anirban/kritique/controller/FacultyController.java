package dev.anirban.kritique.controller;

import dev.anirban.kritique.constants.NetworkStatusCodes;
import dev.anirban.kritique.constants.UrlConstants;
import dev.anirban.kritique.dto.common.CustomResponse;
import dev.anirban.kritique.dto.common.EmptyObject;
import dev.anirban.kritique.dto.faculty.FacultyDTO;
import dev.anirban.kritique.service.FacultyService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(UrlConstants.FIND_AND_FILTER_ALL_FACULTY)
    public CustomResponse<List<FacultyDTO>> findAllFacultiesHandler(
            @RequestParam(value = "name", required = false) String name
    ) {
        return new CustomResponse<>(
                NetworkStatusCodes.SUCCESSFUL,
                "Faculty Fetched Successfully",
                (name != null && !name.isEmpty()) ? service.findFacultyByName(name) : service.findAllFaculty()
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
