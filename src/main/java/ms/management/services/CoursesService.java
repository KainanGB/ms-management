package ms.management.services;


import ms.management.dtos.v1.request.CourseRequestDTO;
import ms.management.dtos.v1.response.CourseResponseDTO;
import ms.management.entities.CoursesEntity;
import ms.management.repositories.CoursesRepository;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class CoursesService {

    private final CoursesRepository repository;
    private final KafkaMessager kafkaMessager;

    public CourseResponseDTO create(CourseRequestDTO data) {
        log.info("C=CoursesService::M=create::I={}", data);

        final var course = CoursesEntity.builder()
            .title(data.title())
            .category(data.category())
            .instructorId(data.instructor_id())
            .build();

        final var courseDTO =
            new CourseResponseDTO(
                course.getId(),
                course.getInstructorId(),
                course.getTitle(),
                course.getCategory(),
                course.getCreatedAt(),
                course.getUpdatedAt()
            );

//        repository.save(course);
        kafkaMessager.sendNewCourseMessage(course);

        log.info("C=CoursesService::M=create::O={}", courseDTO);

        return courseDTO;
    }

    public CourseResponseDTO getById(Long courseId) {
        var course = repository.getReferenceById(courseId);
        log.info("C=CoursesService::M=getById::I={}", courseId);

        final var courseResponseDTO =
            new CourseResponseDTO(
                course.getId(),
                course.getInstructorId(),
                course.getTitle(),
                course.getCategory(),
                course.getCreatedAt(),
                course.getUpdatedAt()
            );

        log.info("C=CoursesService::M=getById::O={}", courseResponseDTO);

        return courseResponseDTO;
    }

    public List<CourseResponseDTO> getAll() {
        var course = repository.findAll();
        log.info("C=CoursesService::M=getAll");

        if(course.isEmpty()) {
            return Collections.emptyList();
        }

        final List<CourseResponseDTO> courses = course.stream().map(x -> new CourseResponseDTO(
            x.getId(),
            x.getInstructorId(),
            x.getTitle(),
            x.getCategory(),
            x.getCreatedAt(),
            x.getUpdatedAt()
        )).toList();

        log.info("C=CoursesService::M=getAll::O={}", courses);

        return courses;
    }

    public boolean delete(Long id) {
        var course = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("course not found."));
        repository.deleteById(course.getId());
        return true;
    }

}
