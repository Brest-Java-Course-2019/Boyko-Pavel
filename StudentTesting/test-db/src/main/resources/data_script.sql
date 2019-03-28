INSERT INTO teacher (teacher_id, name, surname, login, password) VALUES (1, 'Kolya', 'Dude', 'admin1', '1');
INSERT INTO teacher (teacher_id, name, surname, login, password) VALUES (2, 'Petr', 'Loud', 'admin2', '2');

INSERT INTO subject (subject_id, subject_name) VALUES (1, 'Biology');
INSERT INTO subject (subject_id, subject_name) VALUES (2, 'Math');
INSERT INTO subject (subject_id, subject_name) VALUES (3, 'Physics');

INSERT INTO test (test_id, name, teacher_id, subject_id, created_at) VALUES (1, 'Algebra', 1, 2, '2019-02-05');
INSERT INTO test (test_id, name, teacher_id, subject_id) VALUES (2, 'Probability theory', 1, 2);
INSERT INTO test (test_id, name, teacher_id, subject_id) VALUES (3, 'Optics', 2, 3);
INSERT INTO test (test_id, name, teacher_id, subject_id, deleted) VALUES (4, 'Optics2', 2, 3, 1);


INSERT INTO question (question_id, question, test_id) VALUES (1, 'Count 2+2=', 1);
INSERT INTO question (question_id, question, test_id, deleted) VALUES (2, 'Count 3+2=', 2, 1);
INSERT INTO question (question_id, question, test_id) VALUES (3, 'Count 3+2=', 2);
INSERT INTO question (question_id, question, test_id) VALUES (4, 'Count 3+2=', 2);
INSERT INTO question (question_id, question, test_id) VALUES (5, 'Count 3+2=', 3);


INSERT INTO question_item (question_item_id, description, question_id, answer) VALUES (1, 'ANSWER: 1', 1, 0);
INSERT INTO question_item (question_item_id, description, question_id, answer) VALUES (2, 'ANSWER: 2', 1, 0);
INSERT INTO question_item (question_item_id, description, question_id, answer, deleted) VALUES (3, 'ANSWER: 3', 1, 0, 1);
INSERT INTO question_item (question_item_id, description, question_id, answer) VALUES (8, 'ANSWER: 5', 1, 0);
INSERT INTO question_item (question_item_id, description, question_id, answer) VALUES (4, 'ANSWER: 4', 1, 1);
INSERT INTO question_item (question_item_id, description, question_id, answer) VALUES (5, 'ANSWER: 5', 2, 1);
INSERT INTO question_item (question_item_id, description, question_id, answer) VALUES (6, 'ANSWER: 6', 4, 1);
INSERT INTO question_item (question_item_id, description, question_id, answer) VALUES (7, 'ANSWER: 7', 3, 1);


INSERT INTO student (student_id, name, surname) VALUES (1, 'kolya', 'dude');
INSERT INTO student (student_id, name, surname) VALUES (2, 'petr', 'gole');
INSERT INTO student (student_id, name, surname) VALUES (3, 'ann', 'loud');

INSERT INTO student_answer (student_id, question_item_id, student_answer) VALUES (1, 1, 0);
INSERT INTO student_answer (student_id, question_item_id, student_answer) VALUES (1, 2, 0);
INSERT INTO student_answer (student_id, question_item_id, student_answer) VALUES (1, 3, 1);
INSERT INTO student_answer (student_id, question_item_id, student_answer) VALUES (1, 4, 1);
