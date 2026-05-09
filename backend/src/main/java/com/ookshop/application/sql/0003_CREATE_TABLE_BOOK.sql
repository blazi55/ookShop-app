create table book (
	id bigint auto_increment primary key,
    name varchar(20),
    price double not null,
    user_account_id bigint,
    foreign key (user_account_id) references user(id)
);