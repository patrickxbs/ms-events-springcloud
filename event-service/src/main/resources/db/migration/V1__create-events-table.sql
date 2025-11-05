CREATE TABLE events (
    id bigint PRIMARY KEY auto_increment,
    title varchar(255) not null,
    description TEXT,
    capacity int not null,
    remote boolean,
    date timestamp,
    city varchar(100),
    street varchar(150),
    number varchar(20)
);
