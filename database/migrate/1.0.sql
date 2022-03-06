create table "user"
(
    email      varchar2(255) not null,
    first_name varchar2(255),
    last_name  varchar2(255),
    dob        date,
    constraint pk_user primary key (email)
);

create table seller
(
    user_email varchar2(255) not null,
    constraint pk_seller primary key (user_email),
    constraint fk_seller_user_email foreign key (user_email) references "user" (email)
);

create table admin
(
    user_email varchar2(255) not null,
    constraint pk_admin primary key (user_email),
    constraint fk_admin_user_email foreign key (user_email) references "user" (email)
);

create table buyer
(
    user_email varchar2(255) not null,
    constraint pk_buyer primary key (user_email),
    constraint fk_buyer_userEmail foreign key (user_email) references "user" (email)
);
