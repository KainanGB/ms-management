package ms.management.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COURSES")
public class CoursesEntity {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "instructor_id")
    private Long instructorId;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Timestamp updatedAt;
}
