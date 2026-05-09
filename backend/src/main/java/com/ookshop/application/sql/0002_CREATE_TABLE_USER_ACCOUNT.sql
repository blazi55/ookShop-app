create table user_account (
	id bigint auto_increment primary key,
    amount_book bigint not null,
    user_id bigint unique,
    foreign key (user_id) references user(id),
    book_id bigint unique,
    foreign key (book_id) references book(id)
);