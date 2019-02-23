DROP TABLE IF EXISTS subject;
CREATE TABLE subject (
  idSubject INT NOT NULL AUTO_INCREMENT,
  subjectName VARCHAR(60) NOT NULL,
PRIMARY KEY (idSubject));

DROP TABLE IF EXISTS student;
CREATE TABLE student (
  studentId INT NOT NULL AUTO_INCREMENT,
  studentFirstName VARCHAR(60) NOT NULL,
  studentLastName VARCHAR(60) NOT NULL,
PRIMARY KEY (studentId));

