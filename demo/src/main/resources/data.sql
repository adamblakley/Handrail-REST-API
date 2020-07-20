INSERT INTO User (user_id,user_bio,user_created,user_dob,user_email,user_first_name,user_last_name,user_password,user_type) VALUES
(1,'bio1','2020-06-19 15:27:28.054','2020-06-19 15:27:28.054', '1@1','1','11','pass1',1),
(2,'bio2','2020-06-19 15:27:28.054','2020-06-19 15:27:28.054', '2@2','2','22','pass2',2),
(3,'testbio','2020-06-19 15:27:28.054','2020-06-19 15:27:28.054', 'test@test','test','testing','testpass',1);

INSERT INTO Course (course_id, course_name, course_date, USER_ID) VALUES
(1,'My First Course', '2020-06-19 15:27:28.054',1),
(2,'My Second Course', '2020-06-19 15:27:28.054',3);

INSERT INTO Control (CONTROL_ID, CONTROL_POSITION, CONTROL_ALTITUDE, CONTROL_COMPLETED, CONTROL_LATITUDE, CONTROL_LONGITUDE, CONTROL_NAME, CONTROL_NOTE,
CONTROL_COURSE_COURSE_ID) VALUES
(1,0,50.1,FALSE,54.573496,-5.959225,'a','notea',1),
(2,1,50.2,FALSE,54.574207,-5.958369,'b','noteb',1),
(3,2,50.3,FALSE,54.574471,-5.956827,'c','notec',1),
(4,3,50.3,FALSE,54.575136,-5.955886,'c','notec',1);

INSERT INTO Control_Photograph (CONTROL_PHOTO_ID, CONTROL_PHOTO_NAME, CONTROL_PHOTO_PATH, CONTROL_CONTROL_ID) VALUES
(1,'myphoto1','/photos/controlphotos/myphoto1.jpg',1),
(2,'myphoto2','/photos/controlphotos/myphoto2.jpg',2),
(3,'myphoto3','/photos/controlphotos/myphoto3.jpg',3),
(4,'myphoto4','/photos/controlphotos/myphoto4.jpg',4);

INSERT INTO Event (eventid,event_created,event_date,event_name,event_note,event_status, course_id, user_id) VALUES
(1,'2020-06-19 15:27:28.054','2020-06-19 15:27:28.054','event1','eventnote1',1,1,1);

INSERT INTO Participant (PARTICIPANT_ID,  	EVENTID,  	USER_ID  ) VALUES
(1,1,2);

INSERT INTO Participant_Control_Performance (pcp_id, control_time, control_id, participant_id) VALUES
(1, '120208', 1, 1),
(2, '220500', 2, 1),
(3, '160605', 3, 1),
(4, '213400', 4, 1);

INSERT INTO Route_Point (ROUTE_POINT_ID,  	ROUTE_POINT_LATITUDE,  	ROUTE_POINT_LONGITUDE,  	ROUTE_POINT_POSITION,  	PARTICIPANT_ID) VALUES
(1,54.573496,-5.959225,0,1),
(2,54.574207,-5.958369,1,1),
(3,54.574471,-5.956827,2,1),
(4,54.575136,-5.955886,3,1);
