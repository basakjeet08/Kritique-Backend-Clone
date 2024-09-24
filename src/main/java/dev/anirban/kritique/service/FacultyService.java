package dev.anirban.kritique.service;


import dev.anirban.kritique.dto.faculty.FacultyDTO;
import dev.anirban.kritique.entity.Faculty;
import dev.anirban.kritique.exception.FacultyNotFound;
import dev.anirban.kritique.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepo;

    public FacultyDTO createFaculty(FacultyDTO faculty) {
        faculty.setAvgRating(0.0);
        faculty.setTotalRatings(0);
        return facultyRepo
                .save(faculty.toFaculty())
                .toFacultyDTO();
    }

    public List<FacultyDTO> findAllFaculty(Pageable pageable) {
        return facultyRepo
                .findAll(pageable)
                .stream()
                .map(Faculty::toFacultyDTO)
                .toList();
    }

    public FacultyDTO findFacultyById(String id) {
        return facultyRepo
                .findById(id)
                .map(Faculty::toFacultyDTO)
                .orElseThrow(() -> new FacultyNotFound(id));
    }

    public List<FacultyDTO> findFacultyByName(String name, Pageable pageable) {
        return facultyRepo
                .findByNameContaining(name, pageable)
                .stream()
                .map(Faculty::toFacultyDTO)
                .toList();
    }

    public void deleteFacultyById(String id) {
        if (!facultyRepo.existsById(id))
            throw new FacultyNotFound(id);

        facultyRepo.deleteById(id);
    }
}
