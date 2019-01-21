create table users(
	user_id int identity,
	email varchar(256) not null,
	password varchar(256) not null, 
	first_name varchar(256) not null,
	last_name varchar(256) not null,
	created_on timestamp not null,
	last_reset timestamp
);

create table user_cred(
	user_id int,
	user_name varchar(256),
	password varchar(256),
	last_reset timestamp
);