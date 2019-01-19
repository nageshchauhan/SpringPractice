insert into users(user_id, email, password, first_name, last_name, created_on) values(1, 'nagesh@example.com', 'nagesh1', 'Nagesh', 'Chauhan', '2013-10-04 20:30:00');
insert into users(user_id, email, password, first_name, last_name, created_on) values(2, 'admin@example.com', 'admin1', 'Admin', 'Admin', '2012-10-04 20:30:00');
insert into users(user_id, email, password, first_name, last_name, created_on) values(3, 'user@example.com', 'user1', 'User', 'User', '2014-10-04 20:30:00');


insert into user_cred(user_id, user_name, password, last_reset) values(1,'nagesh','nagesh',null);
insert into user_cred(user_id, user_name, password, last_reset) values(2,'admin','admin',null);
insert into user_cred(user_id, user_name, password, last_reset) values(3,'user','user',null);