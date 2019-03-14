

--SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
--SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
--SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
--CREATE SCHEMA IF NOT EXISTS mydb DEFAULT CHARACTER SET utf8;
--USE mydb ;

-- -----------------------------------------------------
-- Table teacher
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS teacher (
  teacher_id INT NOT NULL AUTO_INCREMENT,
  login VARCHAR(45) NOT NULL UNIQUE,
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
--  UNIQUE INDEX login_teach_UNIQUE (login ASC),
PRIMARY KEY (teacher_id)
);


-- -----------------------------------------------------
-- Table subject
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS subject (
  subject_id INT NOT NULL AUTO_INCREMENT,
  subject_name VARCHAR(60) NOT NULL,
PRIMARY KEY (subject_id)
);


-- -----------------------------------------------------
-- Table test
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS test (
  test_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  teacher_id INT NOT NULL,
  subject_id INT NOT NULL,
  deleted TINYINT(1) NOT NULL DEFAULT 0,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (test_id, teacher_id, subject_id),
INDEX fk_teacher_has_subject_subject2_idx (subject_id ASC),
INDEX fk_teacher_has_subject_teacher2_idx (teacher_id ASC),
CONSTRAINT fk_teacher_has_subject_teacher2
FOREIGN KEY (teacher_id)
REFERENCES teacher (teacher_id),
CONSTRAINT fk_teacher_has_subject_subject2
    FOREIGN KEY (subject_id)
REFERENCES subject (subject_id)
);


-- -----------------------------------------------------
-- Table question
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS question (
  question_id INT NOT NULL AUTO_INCREMENT,
  question VARCHAR(200) NOT NULL,
  test_id INT NOT NULL,
  deleted TINYINT(1) NOT NULL DEFAULT 0,
PRIMARY KEY (question_id, test_id),
INDEX fk_question_test1_idx (test_id ASC),
CONSTRAINT fk_question_test1
FOREIGN KEY (test_id)
REFERENCES test (test_id)
);


-- -----------------------------------------------------
-- Table question_item
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS question_item (
  question_item_id INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(200) NOT NULL,
  question_id INT NOT NULL,
  answer TINYINT(1) NOT NULL,
  deleted TINYINT(1) NOT NULL DEFAULT 0,
PRIMARY KEY (question_item_id, question_id),
INDEX fk_question_item_question1_idx (question_id ASC),
CONSTRAINT fk_question_item_question1
FOREIGN KEY (question_id)
REFERENCES question (question_id)
);



-- -----------------------------------------------------
-- Table student
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS student (
  student_id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
PRIMARY KEY (student_id)
);


-- -----------------------------------------------------
-- Table student_answer
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS student_answer (
  student_answer_id INT NOT NULL AUTO_INCREMENT,
  student_id INT NOT NULL,
  question_item_id INT NOT NULL,
  student_answer TINYINT(1) NOT NULL DEFAULT 0,
PRIMARY KEY (student_answer_id, student_id, question_item_id),
INDEX fk_student_has_question_item_question_item1_idx (question_item_id ASC),
INDEX fk_student_has_question_item_student1_idx (student_id ASC),
CONSTRAINT fk_student_has_question_item_student1
FOREIGN KEY (student_id)
REFERENCES student (student_id),
CONSTRAINT fk_student_has_question_item_question_item1
    FOREIGN KEY (question_item_id)
REFERENCES question_item (question_item_id)
);