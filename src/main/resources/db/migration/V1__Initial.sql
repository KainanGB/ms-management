CREATE TABLE COURSES(
	course_id INT NOT NULL AUTO_INCREMENT,
    instructor_id VARCHAR(100) NOT NULL,
	title VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(course_id, instructor_id)
);

CREATE TABLE COURSE_VIDEOS (
    course_id INT NOT NULL,
    video_id INT NOT NULL,
    PRIMARY KEY (video_id),
    FOREIGN KEY(course_id) REFERENCES COURSES(course_id)
);

CREATE TABLE FORMATIONS(
	formation_id INT NOT NULL AUTO_INCREMENT,
    course_id INT NOT NULL,
	title VARCHAR(100) NOT NULL,
    instructor_id VARCHAR(100) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(formation_id, course_id),
    FOREIGN KEY(course_id) REFERENCES COURSES(course_id)
);

CREATE TABLE FORMATION_VIDEOS (
    formation_id INT,
    course_id INT,
    PRIMARY KEY (formation_id, course_id),
    FOREIGN KEY(formation_id) REFERENCES FORMATIONS(formation_id)
);

CREATE TABLE COURSES_TRACK(
	course_id INT,
	user_id INT,
    progress FLOAT,
    status ENUM('ENROLLED', 'WATCHING', 'DONE'),
    PRIMARY KEY (user_id, course_id),
    FOREIGN KEY (course_id) REFERENCES COURSES(course_id)
);



