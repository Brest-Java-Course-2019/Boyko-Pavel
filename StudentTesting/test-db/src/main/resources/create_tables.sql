
CREATE TABLE IF NOT EXISTS teacher (
  teacher_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(45) NOT NULL UNIQUE,
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
);


CREATE TABLE IF NOT EXISTS subject (
  subject_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  subject_name VARCHAR(60) NOT NULL,
);


CREATE TABLE IF NOT EXISTS test (
  test_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  teacher_id INT NOT NULL,
  subject_id INT NOT NULL,
  deleted TINYINT(1) NOT NULL DEFAULT 0,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id),
FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);


CREATE TABLE IF NOT EXISTS question (
  question_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  question VARCHAR(200) NOT NULL,
  test_id INT NOT NULL,
  deleted TINYINT(1) NOT NULL DEFAULT 0,
FOREIGN KEY (test_id) REFERENCES test (test_id)
);


CREATE TABLE IF NOT EXISTS question_item (
  question_item_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(200) NOT NULL,
  question_id INT NOT NULL,
  answer TINYINT(1) NOT NULL,
  deleted TINYINT(1) NOT NULL DEFAULT 0,
FOREIGN KEY (question_id) REFERENCES question (question_id)
);


CREATE TABLE IF NOT EXISTS student (
  student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  login VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(45) NOT NULL,
);


CREATE TABLE IF NOT EXISTS student_answer (
  student_answer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  test_id INT NOT NULL,
  student_id INT NOT NULL,
  question_item_id INT NOT NULL,
  student_answer TINYINT(1) NOT NULL DEFAULT 0,
FOREIGN KEY (student_id) REFERENCES student (student_id),
FOREIGN KEY (question_item_id) REFERENCES question_item (question_item_id)
);
