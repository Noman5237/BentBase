create table "user"
(
    "email"     varchar2(255) not null,
    "firstName" varchar2(255),
    "lastName"  varchar2(255),
    "dob"       date,

    constraint pk_user primary key ("email")
);

create table "seller"
(
    "userEmail" varchar2(255) not null,

    constraint pk_seller primary key ("userEmail"),
    constraint fk_seller_userEmail foreign key ("userEmail") references "user" ("email")
);

create table "buyer"
(
    "userEmail" varchar2(255) not null,

    constraint pk_buyer primary key ("userEmail"),
    constraint fk_buyer_userEmail foreign key ("userEmail") references "user" ("email")
);

create table "admin"
(
    "userEmail" varchar2(255) not null,

    constraint pk_admin primary key ("userEmail"),
    constraint fk_admin_userEmail foreign key ("userEmail") references "user" ("email")
);
