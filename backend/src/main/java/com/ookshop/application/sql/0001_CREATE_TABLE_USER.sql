create table user (
	id bigint auto_increment primary key,
    full_name varchar(35),
    login varchar(100),
    email varchar(25),
    password varchar(100),
    creation_date date
);