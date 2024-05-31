INSERT INTO address (id, street, city, state, country, postal_code, created_at , updated_at) VALUES
(1, '101 Main St', 'Springfield', 'IL', 'USA', '62701', now(), now()),
(2, '102 Main St', 'Springfield', 'IL', 'USA', '62702', now(), now()),
(3, '103 Main St', 'Springfield', 'IL', 'USA', '62703', now(), now()),
(4, '104 Main St', 'Springfield', 'IL', 'USA', '62704', now(), now()),
(5, '105 Main St', 'Springfield', 'IL', 'USA', '62705', now(), now()),
(6, '106 Main St', 'Springfield', 'IL', 'USA', '62706', now(), now()),
(7, '107 Main St', 'Springfield', 'IL', 'USA', '62707', now(), now()),
(8, '108 Main St', 'Springfield', 'IL', 'USA', '62708', now(), now()),
(9, '109 Main St', 'Springfield', 'IL', 'USA', '62709', now(), now()),
(10, '110 Main St', 'Springfield', 'IL', 'USA', '62710', now(), now()),
(11, '111 Main St', 'Springfield', 'IL', 'USA', '62711', now(), now()),
(12, '112 Main St', 'Springfield', 'IL', 'USA', '62712', now(), now()),
(13, '113 Main St', 'Springfield', 'IL', 'USA', '62713', now(), now()),
(14, '114 Main St', 'Springfield', 'IL', 'USA', '62714', now(), now()),
(15, '115 Main St', 'Springfield', 'IL', 'USA', '62715', now(), now()),
(16, '116 Main St', 'Springfield', 'IL', 'USA', '62716', now(), now()),
(17, '117 Main St', 'Springfield', 'IL', 'USA', '62717', now(), now()),
(18, '118 Main St', 'Springfield', 'IL', 'USA', '62718', now(), now()),
(19, '119 Main St', 'Springfield', 'IL', 'USA', '62719', now(), now()),
(20, '120 Main St', 'Springfield', 'IL', 'USA', '62720', now(), now());
-- Seed the Student table (assuming each student has a unique address)
INSERT INTO students (id, username, email, firstname, lastname, address_id , created_at , updated_at) VALUES
(1, 'johndoe1', 'john.doe1@example.com', 'John', 'Doe', 1, now(), now()),
(2, 'janedoe2', 'jane.doe2@example.com', 'Jane', 'Doe', 2, now(), now()),
(3, 'samsmith3', 'sam.smith3@example.com', 'Sam', 'Smith', 3, now(), now()),
(4, 'ellagray4', 'ella.gray4@example.com', 'Ella', 'Gray', 4, now(), now()),
(5, 'natelee5', 'nate.lee5@example.com', 'Nate', 'Lee', 5, now(), now()),
(6, 'oliviamartin6', 'olivia.martin6@example.com', 'Olivia', 'Martin', 6, now(), now()),
(7, 'noahbrown7', 'noah.brown7@example.com', 'Noah', 'Brown', 7, now(), now()),
(8, 'emmawilson8', 'emma.wilson8@example.com', 'Emma', 'Wilson', 8, now(), now()),
(9, 'liamthomas9', 'liam.thomas9@example.com', 'Liam', 'Thomas', 9, now(), now()),
(10, 'sophiahall10', 'sophia.hall10@example.com', 'Sophia', 'Hall', 10, now(), now()),
(11, 'masonlee11', 'mason.lee11@example.com', 'Mason', 'Lee', 11, now(), now()),
(12, 'chloejones12', 'chloe.jones12@example.com', 'Chloe', 'Jones', 12, now(), now()),
(13, 'ethanmoore13', 'ethan.moore13@example.com', 'Ethan', 'Moore', 13, now(), now()),
(14, 'avaking14', 'ava.king14@example.com', 'Ava', 'King', 14, now(), now()),
(15, 'jameswright15', 'james.wright15@example.com', 'James', 'Wright', 15, now(), now()),
(16, 'isabellascott16', 'isabella.scott16@example.com', 'Isabella', 'Scott', 16, now(), now()),
(17, 'loganwhite17', 'logan.white17@example.com', 'Logan', 'White', 17, now(), now()),
(18, 'miaclark18', 'mia.clark18@example.com', 'Mia', 'Clark', 18, now(), now()),
(19, 'lucasgreen19', 'lucas.green19@example.com', 'Lucas', 'Green', 19, now(), now()),
(20, 'ameliadavis20', 'amelia.davis20@example.com', 'Amelia', 'Davis', 20, now(), now());

INSERT INTO subjects (id, name, description, created_at , updated_at ,capacity) VALUES
(1, 'Mathematics', 'Study of numbers shapes and patterns.', now(), now(),5),
(2, 'History', 'Study of past events.', now(), now() ,5),
(3, 'Science', 'Study of the natural world.', now(), now(),5),
(4, 'English', 'Study of English language and literature.', now(), now() ,5),
(5, 'Art', 'Study of visual and performing arts.', now(), now(),5),
(6, 'Music', 'Study of music theory and practice.', now(), now(),5),
(7, 'Geography', 'Study of Earth landscapes and environments.', now(), now(),5),
(8, 'Physical Education', 'Study of physical fitness and sports.', now(), now(),5),
(9, 'Biology', 'Study of living organisms.', now(), now() ,5),
(10, 'Chemistry', 'Study of substances and their properties.', now(), now(),5),
(11, 'Physics', 'Study of matter, motion, and energy.', now(), now(),5),
(12, 'Economics', 'Study of production, distribution, and consumption of goods and services.', now(), now(),5),
(13, 'Philosophy', 'Study of fundamental nature of knowledge, reality, and existence.', now(), now(),5),
(14, 'Political Science', 'Study of governments, public policies, and political behavior.', now(), now(),5),
(15, 'Psychology', 'Study of mind and behavior.', now(), now(),5),
(16, 'Sociology', 'Study of development, structure, and functioning of human society.', now(), now(),5),
(17, 'Engineering', 'Application of scientific and mathematical principles to practical ends.', now(), now(),5),
(18, 'Computer Science', 'Study of computers and computational systems.', now(), now(),5),
(19, 'Business', 'Study of business management and operations.', now(), now(),5),
(20, 'Law', 'Study of legal systems and laws.', now(), now(),5);

-- Setup the many-to-many relationship between Students and Subjects
-- Each student is enrolled in 5 subjects for simplicity
-- Each student is enrolled in 5 subjects for simplicity
INSERT INTO subscription (subject_id, student_id) VALUES
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1),
(1, 2), (2, 2), (3, 2), (4, 2), (5, 2),
(1, 3), (2, 3), (3, 3), (4, 3), (5, 3),
(1, 4), (2, 4), (3, 4), (4, 4), (5, 4),
(1, 5), (2, 5), (3, 5), (4, 5), (5, 5),
(6, 6), (7, 6), (8, 6), (9, 6), (10, 6),
(6, 7), (7, 7), (8, 7), (9, 7), (10, 7),
(6, 8), (7, 8), (8, 8), (9, 8), (10, 8),
(6, 9), (7, 9), (8, 9), (9, 9), (10, 9),
(6, 10), (7, 10), (8, 10), (9, 10), (10, 10),
(11, 11), (12, 11), (13, 11), (14, 11), (15, 11),
(11, 12), (12, 12), (13, 12), (14, 12), (15, 12),
(11, 13), (12, 13), (13, 13), (14, 13), (15, 13),
(11, 14), (12, 14), (13, 14), (14, 14), (15, 14),
(11, 15), (12, 15), (13, 15), (14, 15), (15, 15),
(16, 16), (17, 16), (18, 16), (19, 16), (20, 16),
(16, 17), (17, 17), (18, 17), (19, 17), (20, 17),
(16, 18), (17, 18), (18, 18), (19, 18), (20, 18),
(16, 19), (17, 19), (18, 19), (19, 19), (20, 19),
(16, 20), (17, 20), (18, 20), (19, 20), (20, 20);



INSERT INTO teachers (id, lastname,firstname ,subject_id, created_at , updated_at) VALUES
(1, 'Mr. Smith', 'Mr. Smith',1, now(), now()),
(2, 'Mrs. Johnson','Mrs. Johnson',2, now(), now()),
(3, 'Mr. Williams', 'Mr. Williams',3, now(), now()),
(4, 'Mrs. Brown', 'Mrs. Brown',4, now(), now()),
(5, 'Mr. Jones', 'Mr. Jones',5, now(), now()),
(6, 'Mrs. Miller', 'Mrs. Miller',6, now(), now()),
(7, 'Mr. Davis', 'Mr. Davis',7, now(), now()),
(8, 'Mrs. Garcia', 'Mrs. Garcia',8, now(), now()),
(9, 'Mr. Wilson', 'Mr. Wilson',9, now(), now()),
(10, 'Mrs. Martinez' , 'Mrs. Martinez',10, now(), now()),
(11, 'Mr. Anderson', 'Mr. Anderson',11, now(), now()),
(12, 'Mrs. Taylor', 'Mrs. Taylor',12, now(), now()),
(13, 'Mr. Thomas', 'Mr. Thomas',13, now(), now()),
(14, 'Mrs. Hernandez', 'Mrs. Hernandez',14, now(), now()),
(15, 'Mr. Moore', 'Mr. Moore',15, now(), now()),
(16, 'Mrs. Martin', 'Mrs. Martin',16, now(), now()),
(17, 'Mr. Jackson', 'Mr. Jackson',17, now(), now()),
(18, 'Mrs. Thompson', 'Mrs. Thompson',18, now(), now()),
(19, 'Mr. White', 'Mr. White',19, now(), now()),
(20, 'Mrs. Lopez', 'Mrs. Lopez',20, now(), now());




-- Seed the Chapter table with multiple entries for each subject
-- Assuming 3 chapters per subject
INSERT INTO chapters (id, title, subject_id, created_at , updated_at) VALUES
(1, 'Introduction to Algebra', 1, now(), now()),
(2, 'Intermediate Algebra', 1, now(), now()),
(3, 'Advanced Algebra', 1, now(), now())
-- continue this pattern, adjusting IDs and subject_ids appropriately
