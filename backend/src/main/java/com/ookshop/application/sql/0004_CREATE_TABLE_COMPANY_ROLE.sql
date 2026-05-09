create table company_role (
	id bigint auto_increment primary key,
    user_id bigint unique,
    foreign key (user_id) references user(id),
    role varchar(30)
);