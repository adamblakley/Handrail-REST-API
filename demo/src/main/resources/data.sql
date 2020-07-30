INSERT INTO ROLE (role_id, role) VALUES
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

INSERT INTO User (user_id,user_bio,user_created,user_dob,user_email,user_first_name,user_last_name,user_password,user_type) VALUES
(1,'bio1','2020-06-19 15:27:28.054','2020-06-19 15:27:28.054', '1@1','1','11','pass1',1),
(2,'bio2','2020-06-19 15:27:28.054','2020-06-19 15:27:28.054', '2@2','jimmy2','22','pass2',2),
(3,'testbio','2020-06-19 15:27:28.054','2020-06-19 15:27:28.054', 'test@test','test','testing','$2y$10$lYCwUUJm/iEsHAA/wlomC.Bx23dW65QJlEFMQ7Hjd7/DsWed/71vG',1),
(4,'bio4','2020-06-19 15:27:28.054','2020-06-19 15:27:28.054', '4@4','sally4','44','pass4',1),
(5,'bio5','2020-06-19 15:27:28.054','2020-06-19 15:27:28.054', '5@5','bobby5','55','pass5',1);

INSERT INTO Course (course_id, course_name, course_date, USER_ID) VALUES
(1,'My First Course', '2020-06-19 15:27:28.054',1),
(2,'My Second Course', '2020-06-19 15:27:28.054',3);

INSERT INTO Control (CONTROL_ID, CONTROL_POSITION, CONTROL_ALTITUDE, CONTROL_COMPLETED, CONTROL_LATITUDE, CONTROL_LONGITUDE, CONTROL_NAME, CONTROL_NOTE,
CONTROL_COURSE_COURSE_ID) VALUES
(1,0,50.1,FALSE,54.573496,-5.959225,'a','notea',1),
(2,1,50.2,FALSE,54.574207,-5.958369,'b','noteb',1),
(3,2,50.3,FALSE,54.574471,-5.956827,'c','notec',1),
(4,3,50.3,FALSE,54.575136,-5.955886,'c','notec',1);

INSERT INTO Control_Photograph (PHOTO_ID, PHOTO_NAME, PHOTO_PATH, Reference_ID) VALUES
(1,'myphoto1','/photos/controlphotos/myphoto1.jpg',1),
(2,'myphoto2','/photos/controlphotos/myphoto2.jpg',2),
(3,'myphoto3','/photos/controlphotos/myphoto3.jpg',3),
(4,'myphoto4','/photos/controlphotos/myphoto4.jpg',4);


INSERT INTO Event (eventid,event_created,event_date,event_name,event_note,event_status, course_id, user_id) VALUES
(1,'2020-06-19 15:27:28.054','2020-06-19 15:27:28.054','event1','eventnote1',2,1,1);

INSERT INTO Event_Photograph (PHOTO_ID, PHOTO_NAME, PHOTO_PATH, Reference_ID) VALUES
(1,'myphoto1','https://www.sciencemag.org/sites/default/files/styles/inline__450w__no_aspect/public/dogs_1280p_0.jpg?itok=4t_1_fSJ',1);

INSERT INTO Participant (PARTICIPANT_ID,  	EVENTID,  	USER_ID  ) VALUES
(1,1,2),
(2,1,4),
(3,1,5);

INSERT INTO Participant_Control_Performance (pcp_id, control_time, control_id, participant_id) VALUES
(1, '120208', 1, 1),
(2, '220500', 2, 1),
(3, '360605', 3, 1),
(4, '413400', 4, 1),
(5, '120208', 1, 2),
(6, '220500', 2, 2),
(7, '360605', 3, 2),
(8, '433400', 4, 2),
(9, '120208', 1, 3),
(10, '220500', 2, 3),
(11, '360605', 3, 3),
(12, '423400', 4, 3);

INSERT INTO Route_Point (ROUTE_POINT_ID,  	ROUTE_POINT_LATITUDE,  	ROUTE_POINT_LONGITUDE,  	ROUTE_POINT_POSITION,  	PARTICIPANT_ID) VALUES
(1,54.573485, -5.959185,0,1),
(2,54.573770, -5.958721,1,1),
(3,54.574647, -5.957871,2,1),
(4,54.575108, -5.955871,3,1),
(5,54.573485, -5.959185,0,2),
(6,54.573885, -5.958815,1,2),
(7,54.575088, -5.955804,2,2),
(8,54.575142,-5.955888,3,2),
(9,54.573485, -5.959185,0,3),
(10,54.573999, -5.958335,1,3),
(11,54.573975, -5.956375,2,3),
(12,54.575043, -5.955547,3,3);
