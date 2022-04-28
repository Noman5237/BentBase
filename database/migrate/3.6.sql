drop table review;

create table review
(
    id       number primary key,
    rating   number(4, 2)  not null,
    comments varchar2(1024),
    reviewer varchar2(255) not null,
    constraint fk_review_reviewer foreign key (reviewer) references "user" (email)
);

create sequence review_id_sequence;

alter table gig
    drop constraint fk_gig_seller_id;

alter table gig
    add constraint fk_gig_seller_id foreign key (seller_email) references seller (user_email);

-- Reviews against the users
create table user_review
(
    user_email varchar2(255) not null,
    review_id  number        not null,
    constraint pk_user_review primary key (user_email, review_id)
);

create table gig_review
(
    gig_id    number not null,
    review_id number not null,
    constraint pk_gig_review primary key (gig_id, review_id)
);

create table order_review
(
    order_id  number not null,
    review_id number not null,
    constraint pk_order_review primary key (order_id, review_id)
);
