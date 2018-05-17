INSERT INTO `user` (username, password, is_admin)
VALUES ('user', '$2a$10$MaS9yLXjjj9S/pmUEuxxV.vg/erao./nV9s4rNxWSEJ4WjI2Qo8da', false);

INSERT INTO `user` (username, password, is_admin)
VALUES ('admin', '$2a$10$Ew.IJLBzqzx2W3paIJMxK.XZb6tHQZPTMbjFyEhq.Kc.eQg5t370.', true);

INSERT INTO `user` (username, password, is_admin)
VALUES ('johndoe', '$2a$10$MaS9yLXjjj9S/pmUEuxxV.vg/erao./nV9s4rNxWSEJ4WjI2Qo8da', false);

INSERT INTO `score` (username, points) VALUES ('user', 5);
INSERT INTO `score` (username, points) VALUES ('user', 10);
INSERT INTO `score` (username, points) VALUES ('johndoe', 13);
INSERT INTO `score` (username, points) VALUES ('johndoe', 7);
