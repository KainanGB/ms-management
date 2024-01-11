package ms.management.repositories;


import ms.management.entities.CoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<CoursesEntity, Long> {
}
