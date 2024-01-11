package ms.management.controllers;

import ms.management.dtos.v1.request.CourseRequestDTO;
import ms.management.dtos.v1.response.CourseResponseDTO;
import ms.management.services.CoursesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("courses")
public class CoursesController {

    private final CoursesService coursesService;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> create(@RequestBody @Valid CourseRequestDTO data) {
        log.info("C=CoursesController::M=create::I={}", data);
        final var response = coursesService.create(data);
        log.info("C=CoursesController::M=create::O={}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAll() {
        log.info("C=CoursesController::M=getAll");
        final var response = coursesService.getAll();
        log.info("C=CoursesController::M=getAll::O={}", response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> getById(@PathVariable Long courseId) {
        log.info("C=CoursesController::M=getById::I={}", courseId);
        final var response = coursesService.getById(courseId);
        log.info("C=CoursesController::M=getById::O={}", response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long courseId) {
        log.info("C=CoursesController::M=delete::I={}", courseId);
        boolean deleted = coursesService.delete(courseId);
        log.info("C=CoursesController::M=delete");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleted);
    }
}
