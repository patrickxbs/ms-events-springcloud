CREATE TABLE registrations (
    id              bigint      primary key     auto_increment,
    event_id        bigint      not null,
    price           double      not null,
    quantity        integer     not null,
    total_price     double      not null,
    status          enum('PENDING_PAYMENT', 'CONFIRMED', 'CANCELLED')    not null
);
