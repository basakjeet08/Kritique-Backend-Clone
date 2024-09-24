package dev.anirban.kritique.service;


import dev.anirban.kritique.entity.Faculty;
import dev.anirban.kritique.exception.FacultyNotFound;
import dev.anirban.kritique.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepo;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setAvgRating(0.0);
        faculty.setTotalRating(0);
        return facultyRepo.save(faculty);
    }

    public List<Faculty> findAllFaculty() {
        return facultyRepo.findAll();
    }

    public Faculty findFacultyById(String id) {
        return facultyRepo
                .findById(id)
                .orElseThrow(() -> new FacultyNotFound(id));
    }

    public List<Faculty> findFacultyByName(String name) {
        return facultyRepo.findByNameContaining(name);
    }

    public void deleteFacultyById(String id) {
        if (!facultyRepo.existsById(id))
            throw new FacultyNotFound(id);

        facultyRepo.deleteById(id);
    }
}
